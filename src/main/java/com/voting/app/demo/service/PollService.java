package com.voting.app.demo.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.voting.app.demo.model.OptionVotes;
import com.voting.app.demo.model.Poll;
import com.voting.app.demo.repository.PollRepository;

@Service
public class PollService {
    
    @Autowired
    private PollRepository pollRepository;

    // 1. Inject the WebSocket messaging template
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Poll createPoll(Poll poll) {
        Poll savedPoll = pollRepository.save(poll);
        
        // 2. Broadcast the newly created poll to all connected clients
        messagingTemplate.convertAndSend("/topic/polls", savedPoll);
        
        return savedPoll;
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    public void vote(Long pollId, int optionIndex, String voterId) { 
        Poll poll = getPollById(pollId);
        if(poll == null){
            throw new IllegalArgumentException("Poll not found");
        }

        // NEW: Check if the poll is manually turned off
        if (!poll.isActive()) {
            throw new IllegalStateException("Voting is closed for this poll.");
        }

        // UPDATE: Check if the poll's timer has expired using Instant.now()
        if (poll.getExpiresAt() != null && Instant.now().isAfter(poll.getExpiresAt())) {
            throw new IllegalStateException("This poll has expired.");
        }

        if (poll.getVotedUserIds().contains(voterId)) {
            throw new IllegalStateException("This user has already voted on this poll.");
        }

        List<OptionVotes> options = poll.getOptions();
        if(optionIndex < 0 || optionIndex >= options.size()){
            throw new IllegalArgumentException("Invalid option index");
        }
        
        OptionVotes selectedOption = options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        poll.getVotedUserIds().add(voterId);

        Poll updatedPoll = pollRepository.save(poll);
        messagingTemplate.convertAndSend("/topic/polls", updatedPoll);
    }
}