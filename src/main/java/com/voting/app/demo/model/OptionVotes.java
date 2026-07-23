package com.voting.app.demo.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Embeddable
public class OptionVotes {
    public String voteOption;
    private long voteCount = 0L;
	public String getVoteOption() {
		return voteOption;
	}
	public void setVoteOption(String voteOption) {
		this.voteOption = voteOption;
	}
	public long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}
}
