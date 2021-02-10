package com.cricket.CricketBoardMaven;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name = "Team")
public class Team2 implements Comparable<Team2> {
	@JoinColumn(name = "name")
	private String name;
	private Date date;

	public Team2() {}
	public Team2(String teamName) {
		this.name = teamName;
		this.date = new Date(System.currentTimeMillis());
	}

	public Team2(String teamName, Date date) {
		this.name = teamName;
		this.date = date;
	}

	/**
	 * @return the date
	 */

	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
		  public void setDate(Date date) { this.date = date; }
		 

	/**
	 * @return the teamName
	 */
	@Id
	public String getName() {
		return name;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setName(String teamName) {
		this.name = teamName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null && !(obj instanceof Team2))
			return false;
		return this.name.equals(((Team2) obj).name);
	}

	@Override
	public String toString() {
		String str = "Team : " + this.name + " Date : " + "\n";
		return str;
	}

	@Override
	public int compareTo(Team2 o) {

		return this.name.toString().compareTo(o.name.toString());
	}

	

}
