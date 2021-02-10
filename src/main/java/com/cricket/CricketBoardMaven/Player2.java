package com.cricket.CricketBoardMaven;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author swapnilu
 *
 */
@Entity
@Table (name = "player")
public class Player2 implements Comparable<Player2> {
	private int id;
	private String name;
	private int run;
	private Team2 playsFor;
	@Transient
	private BattingStatus bat;
	@Transient
	private static int playing = 1;

	
	public Player2() {
	}

	public static void setPlaying(int playing) {
		Player2.playing = playing;
	}

	public Player2(String name) {
		this.name = name;
		this.randomInit();
	}
	public Player2(String playerName, String teamName) {
		this.name = playerName;
		this.playsFor = CricketBoard2.searchTeam(teamName);
		this.randomInit();
	}

	public Player2(String name, int run,String teamName) {
		this.name = name;
		this.run = run;
		this.playsFor=CricketBoard2.searchTeam(teamName);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the run
	 */
	public int getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(int run) {
		this.run = run;
	}

	/**
	 * @return the bat
	 */
	public BattingStatus getBat() {
		return bat;
	}

	/**
	 * @param bat the bat to set
	 */
	public void setBat(BattingStatus bat) {
		this.bat = bat;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null && !(obj instanceof Player2))
			return false;
		return this.name.equals(((Player2) obj).name);
	}

	@Override
	public int compareTo(Player2 o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return name+" Runs: "+this.run;
	}
	public void randomInit() {
		int key = (int) (Math.random() * 3);
		if (key == 0) {
			this.bat = BattingStatus.PLAYED;
			this.run = (int) (Math.random() * 100);
		} else if (key == 1)
			this.bat = BattingStatus.NYP;
		else if (playing <= 2) {
			this.bat = BattingStatus.PLAYING;
			this.run = (int) (Math.random() * 100);
			playing++;
		} else {
			this.bat = BattingStatus.NYP;
		}
	}

	/**
	 * @return the playsFor
	 */
	@OneToOne (targetEntity = Team2.class, cascade = CascadeType.PERSIST)
	public Team2 getPlaysFor() {
		return playsFor;
	}

	/**
	 * @param playsFor the playsFor to set
	 */
	public void setPlaysFor(String playsFor) {
		this.playsFor = CricketBoard2.searchTeam(playsFor);
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
