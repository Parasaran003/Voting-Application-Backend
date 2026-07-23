package com.voting.app.demo.model; // (Adjust to your exact package)

import java.time.Instant; // 1. Use Instant instead of LocalDateTime
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty; // 2. Import this annotation

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @ElementCollection
    private List<OptionVotes> options;

    @ElementCollection
    private Set<String> votedUserIds = new HashSet<>();

    // FIX 1: Force Spring Boot to send this as "isActive" in the JSON
    @JsonProperty("isActive")
    private boolean isActive = true;

    // FIX 2: Use Instant (Absolute UTC time) to eliminate timezone shifts
    private Instant expiresAt;

    @JsonProperty("isBlind")
    private boolean isBlind = false; // Defaults to normal voting

    public boolean isBlind() {
		return isBlind;
	}

	public void setBlind(boolean isBlind) {
		this.isBlind = isBlind;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<OptionVotes> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVotes> options) {
		this.options = options;
	}

	public Set<String> getVotedUserIds() {
		return votedUserIds;
	}

	public void setVotedUserIds(Set<String> votedUserIds) {
		this.votedUserIds = votedUserIds;
	}

	public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}