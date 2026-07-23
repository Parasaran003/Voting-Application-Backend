package com.voting.app.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Vote {
    private  Long pollId;
    private int optionIndex;
    private String voterId;
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public Long getPollId() {
		return pollId;
	}
	public void setPollId(Long pollId) {
		this.pollId = pollId;
	}
	public int getOptionIndex() {
		return optionIndex;
	}
	public void setOptionIndex(int optionIndex) {
		this.optionIndex = optionIndex;
	}

}
