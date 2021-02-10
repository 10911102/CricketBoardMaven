package com.cricket.CricketBoardMaven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.cricket.CricketBoardMaven.dao.*;

/**
 * Utility class for Cricket Team System to add or show or search teams and
 * players in system
 * 
 * @author swapnilu
 *
 */
public class CricketBoard2 {
	private static List<Team2> teams = new ArrayList<Team2>();
	private static List<Match> matches = new ArrayList<Match>();
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Add new player in existing team
	 * 
	 * @param player player object to add in player set in team
	 * @param team   name of the team
	 * @return true if new player added successfully
	 */
	public static boolean addPlayer(String playerName) {
		boolean flag = false;

		Team2 team = selectTeam();
		Player2 player = new Player2(playerName, team.getName());
		PlayerDataOperations.insert(player);
		return flag;
	}

	public static Team2 selectTeam() {
		System.out.println("Enter player's Team Name");
		int count = 1;
		List<Team2> teams = getTeams();
		for (Team2 team : teams) {

			System.out.println(count++ + "  " + team.getName());
		}
		int choice = sc.nextInt();
		return teams.get(choice - 1);
	}

	/**
	 * Appends the new team object to teams list with players
	 * 
	 * @param teamName Country name
	 * @param players  collection of players belongs to Country
	 * 
	 */
	public static boolean addTeam(String teamName) {
		Team2 team = new Team2(teamName);
		teams.add(team);
		TeamDataOperations.insert(team);
		Player2.setPlaying(1);
		return true;
	}

	/**
	 * Add player in existing set of players
	 * 
	 * @param players existing Set of players
	 * @param player  new object to add in set
	 * @return true if new player added successfully
	 *//*
		 * public static boolean addPlayer(TreeSet<Player2> players, Player2 player) {
		 * players.add(player);
		 * 
		 * return true; }
		 */

	/**
	 * shows the list of team and players(sorted) in team.
	 * 
	 * @return
	 */
	public static List<Team2> getTeams() {
		List<Team2> t1 = TeamDataOperations.getAll();
		Collections.sort(t1);
		return t1;
	}

	/**
	 * shows the list of team sorted by time of team created.
	 * 
	 */
	public static void showTeamsSortedByCreationDate() {
		Comparator<Team2> com = new Comparator<Team2>() {

			@Override
			public int compare(Team2 o1, Team2 o2) {
				if (o1 == null || o2 == null) {
					return -1;
				}
				if (o1.getDate().before(o2.getDate())) {
					return -1;
				} else if (o1.getDate().after(o2.getDate())) {
					return 1;
				}
				return 0;
			}

		};
		List<Team2> t1 = TeamDataOperations.getAll();

		Collections.sort(t1, com);
		System.out.println("Name of Teams Sorted By Date");
		showTeams(t1);

	}

	/**
	 * print given list
	 * 
	 * @param list collection of Team class
	 */
	public static void showTeams(List<Team2> list) {
		int count = 0;
		for (Team2 team : list) {
			System.out.println(++count + ".     " + team.getName() + "          " + team.getDate());
		}
	}

	/**
	 * Displays the list of players in given team
	 * 
	 * @param string name of the team
	 * @return
	 */
	public static List<Player2> showTeam(String teamName) {
		List<Player2> players = new ArrayList<Player2>();
		for (Player2 player : PlayerDataOperations.getAll()) {
			if (player.getPlaysFor().equals(teamName)) {
				players.add(player);
			}
		}
		return players;
	}

	/**
	 * Displays the list of Team and number of players in team
	 * 
	 */
	public static void showTeamsWithCount() {
		int i = 0;
		for (Team2 team : TeamDataOperations.getAll()) {
			i = (showTeam(team.getName())).size();
			System.out.println(team.getName() + "  " + i);
		}
	}

	/**
	 * Displays the total number of teams
	 * 
	 */
	public static void showTotalTeams() {
		System.out.println("Number of teams : " + TeamDataOperations.getAll().size());

	}

	/**
	 * Returns the object Team if team is present else it will return null
	 * 
	 * @param string String to search team name
	 * @return Object of Team or null
	 */
	public static Team2 searchTeam(String teamName) {
		for (Team2 team : TeamDataOperations.getAll()) {
			if (team.getName().toString().equalsIgnoreCase(teamName)) {
				return team;
			}
		}
		return null;
	}

	/**
	 * Returns the object Player if player is present or it will return null
	 * 
	 * @param playerName String to search player name
	 * @return Object of Player or null
	 */

	public static Player2 searchPlayer(String playerName) {

		for (Player2 player : PlayerDataOperations.getAll()) {
			if (player.getName().equalsIgnoreCase(playerName)) {
				System.out.println("Player Found");
				return player;
			}
		}

		System.out.println("Try another name!!");
		return null;
	}

	/**
	 * shows the given string is Team or Player
	 * 
	 * @param string name of the team or player
	 */
	public static void search(String string) {
		for (Team2 team : TeamDataOperations.getAll()) {
			if (team.getName().equalsIgnoreCase(string)) {
				System.out.println("Team " + string + " found.");
				return;
			}
		}
		for (Player2 player : PlayerDataOperations.getAll()) {
			if (player.getName().equalsIgnoreCase(string)) {
				System.out.println("Player " + string + " Found in team " + player.getPlaysFor());
				return;
			}
		}
		System.out.println("Try another name!!");
	}

	/**
	 * Total runs scored by team
	 * 
	 * @param team name of the team
	 * @return total runs
	 */
	public static int getRuns(String team) {
		int totalRuns = 0;
		for (Player2 player : showTeam(team)) {
			totalRuns += player.getRun();
		}
		return totalRuns;
	}

	/**
	 * Total Wickets gone of team
	 * 
	 * @param team name of the team
	 * @return total wickets
	 */
	public static int getWickets(String team) {
		int totalWicks = 0;
		for (Player2 player : showTeam(team)) {
			if (player.getBat().equals(BattingStatus.PLAYED)) {
				totalWicks++;
			}
		}
		return totalWicks;
	}

	/**
	 * Shows the match result
	 * 
	 * @param team1 first team name
	 * @param team2 second team name
	 */
	public static String matchResult(String team1, String team2) {
		Match match = new Match(team1.toString(), team2.toString());

		for (Player2 player : showTeam(team1))
			player.randomInit();
		for (Player2 player : showTeam(team2))
			player.randomInit();
		matches.add(match);
		MatchDataOperations.insert(match);
		return Match.result(match);
	}

	public static void showHistory() {
		System.out.println("  Match between             Runs by   Runs by           Wining");
		System.out.println("Team1        Team2          Team1     Team2              Team");
		for (Match match : MatchDataOperations.getAll())
			System.out.println(match + Match.result(match));
	}

}
