package com.cricket.CricketBoardMaven;

import java.util.Scanner;

public class Menu {
	public static void showMenu() {
		int key = 0;
		int switchKey;
		String str = "";
		try (Scanner sc = new Scanner(System.in)) {
			do {
				printMenu();
				switchKey = sc.nextInt();
				sc.nextLine();
				Team2 team;
				switch (switchKey) {
				case 1:
					System.out.println("Enter player name");
					String playerName = sc.nextLine();
					if (CricketBoard2.searchPlayer(str) == null)
						CricketBoard2.addPlayer(playerName);
					else
						System.out.println("Already exist");
					break;
				case 2:
					System.out.println("Enter Team Name");
					str = sc.nextLine();
					if (CricketBoard2.searchTeam(str) == null)
						CricketBoard2.addTeam(str);
					else
						System.out.println("Already exist");
					break;
				case 3:
					System.out.println("Name of Teams Sorted By Name");
					System.out.println("SrNo.      Team Name        Date");
					CricketBoard2.showTeams(CricketBoard2.getTeams());
					break;
				case 4:
					CricketBoard2.showTeamsSortedByCreationDate();
					break;
				case 5:
					team = CricketBoard2.selectTeam();
					for (Player2 player : CricketBoard2.showTeam(team.getName())) {
						System.out.println(player);
					}
					break;
				case 6:
					CricketBoard2.showTeamsWithCount();
					break;

				case 7:
					CricketBoard2.showTotalTeams();
					break;
				case 8:
					System.out.println("Enter any name to search");
					str = sc.nextLine();
					CricketBoard2.search(str);
					break;
				case 9:
					System.out.println("Enter Team Name to search");
					str = sc.nextLine();
					team = CricketBoard2.searchTeam(str);
					if (team != null)
						System.out.println(team);
					else
						System.out.println("Try another name!!");
					break;
				case 10:
					System.out.println("Enter player name to search");
					str = sc.nextLine();
					CricketBoard2.searchPlayer(str);
					break;
				case 11:
					System.out.println("India wickets" + CricketBoard2.getWickets(Country.INDIA.toString()));
					System.out.println("Australia wickets" + CricketBoard2.getWickets(Country.AUSTRALIA.toString()));
					break;
				case 12:
					System.out
							.println(CricketBoard2.matchResult(Country.INDIA.toString(), Country.AUSTRALIA.toString()));
					break;
				case 13:
					CricketBoard2.showHistory();
					break;
				default:
					System.out.println("Plase select correct option");
				}
				System.out.println("Press 0 to exit");
				key = sc.nextInt();
				sc.nextLine();
			} while (key != 0);
		}

	}

	private static void printMenu() {
		System.out.println("Welcome select option");
		System.out.println("1.Add Player");
		System.out.println("2.Add Team");
		System.out.println("3.show all teams alphabetically ");
		System.out.println("4.show all teams sorted by date");
		System.out.println("5.show all players in team");
		System.out.println("6.show all teams with number of player");
		System.out.println("7.show total number of teams");
		System.out.println("8.search the given string is Team or Player");
		System.out.println("9.search the object of team by given string");
		System.out.println("10.search the object of Player by given string");
		System.out.println("11.show wickets of the team");
		System.out.println("12. Ooohhhooo!! Play the Game");
		System.out.println("13. show old Matches");
	}
}
