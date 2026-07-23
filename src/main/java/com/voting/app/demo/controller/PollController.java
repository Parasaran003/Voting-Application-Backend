package com.voting.app.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.app.demo.model.Poll;
import com.voting.app.demo.request.Vote;
import com.voting.app.demo.service.PollService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "https://your-frontend-name.vercel.app")
public class PollController {

    @Autowired
    private PollService pollService;
    
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }
    
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id) {
        return pollService.getPollById(id);
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
    	pollService.vote(vote.getPollId(), vote.getOptionIndex(), vote.getVoterId());    
    }
}