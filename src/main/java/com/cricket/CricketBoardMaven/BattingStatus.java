package com.cricket.CricketBoardMaven;

public enum BattingStatus {
	PLAYED("Played and Out"),
	PLAYING("Currently playing"),
	NYP("Not Yet Played");

	private String description;

	BattingStatus(String string) {
		this.description = string;
	}

	/**
	 * @return the value
	 */
	public String getDescription() {
		return description;
	}
}
