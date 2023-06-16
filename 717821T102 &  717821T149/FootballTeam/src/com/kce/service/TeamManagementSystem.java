package com.kce.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.kce.bean.*;
import com.kce.bean.Team;
import com.kce.dao.TeamDAO;
import com.kce.util.DBUtil;

public class TeamManagementSystem {
	private TeamDAO teamDAO;

	public TeamManagementSystem() {
		Connection connection = DBUtil.getConnection();
		teamDAO = new TeamDAO(connection);
	}

	public void displayMenu() {
		System.out.println("*** Team Management System ***");
		System.out.println("1. Add a new player");
		System.out.println("2. Update an existing player");
		System.out.println("3. Delete a player");
		System.out.println("4. View all players");
		System.out.println("5. Exit");
		System.out.println("**********************************");
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			displayMenu();

			try {
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					addPlayer();
					break;
				case 2:
					updatePlayer();
					break;
				case 3:
					deletePlayer();
					break;
				case 4:
					viewAllPlayers();
					break;
				case 5:
					exit = true;
					System.out.println("Exiting.....");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
				scanner.nextLine();
			} catch (SQLException e) {
				System.out.println("An error occurred while executing the operation. Please try again.");
			}
		}

		scanner.close();
		DBUtil.closeConnection();
	}

	private void addPlayer() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the details of the new player:");
		System.out.print("Name: ");
		String name = scanner.nextLine();
		System.out.print("Number: ");
		int number = scanner.nextInt();
		scanner.nextLine(); 
		System.out.print("Goals: ");
		int goals = scanner.nextInt();
		scanner.nextLine(); 
		System.out.print("Age: ");
		int age = scanner.nextInt();
		scanner.nextLine(); 
		System.out.print("Club: ");
		String clubname = scanner.nextLine();

		Team team = new Team();
		team.setPlayerName(name);
		team.setPlayerNumber(number);
		team.setPlayerGoals(goals);
		team.setPlayerAge(age);
		team.setClubName(clubname);
		
		teamDAO.addPlayer(team);
		System.out.println("Player added successfully!");
	}

	private void updatePlayer() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Player Number to update: ");
		int playerNumber = scanner.nextInt();
		scanner.nextLine();

		Team team = teamDAO.getTeamByNumber(playerNumber);

		if (team == null) {
			System.out.println("Player not found with Number: " + playerNumber);
			return;
		}

		System.out.println("Team Details:");
		System.out.println("Name: " + team.getPlayerName());
		System.out.println("Number: " + team.getPlayerNumber());
		System.out.println("Age: " + team.getPlayerAge());
		System.out.println("Goals: " + team.getPlayerGoals());
		System.out.println("Club: " + team.getClubName());

		System.out.println("Enter the new details (leave blank to keep current value):");

		System.out.print("New name: ");
		String name = scanner.nextLine();
		if (!name.isEmpty()) {
			team.setPlayerName(name);
		}
		System.out.print("New number: ");
		String numberInput = scanner.nextLine();
		if (!numberInput.isEmpty()) {
			int number = Integer.parseInt(numberInput);
			team.setPlayerNumber(number);
		}
		System.out.print("New age: ");
		String ageInput = scanner.nextLine();
		if (!ageInput.isEmpty()) {
			int age = Integer.parseInt(ageInput);
			team.setPlayerAge(age);
		}
		System.out.print("New Goals: ");
		String goalinput = scanner.nextLine();
		if (!goalinput.isEmpty()) {
			int goals = Integer.parseInt(goalinput);
			team.setPlayerGoals(goals);
		System.out.print("New Club name: ");
		String email = scanner.nextLine();
		if (!email.isEmpty()) {
			team.setClubName(email);
		}
		

		teamDAO.updatePlayer(team);
		System.out.println("Team updated successfully!");
	}}

	private void deletePlayer() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Player Number to delete: ");
		int playerNumber = scanner.nextInt();
		scanner.nextLine();

		Team team = teamDAO.getTeamByNumber(playerNumber);

		if (team == null) {
			System.out.println("Player not found with Number: " + playerNumber);
			return;
		}

		System.out.println("Team Details:");
		System.out.println("Name: " + team.getPlayerName());
		System.out.println("Number: " + team.getPlayerNumber());
		System.out.println("Age: " + team.getPlayerAge());
		System.out.println("Goals: " + team.getPlayerGoals());
		System.out.println("Club: " + team.getClubName());
		
		System.out.print("Are you sure you want to delete this player? (Y/N): ");
		String confirmation = scanner.nextLine();

		if (confirmation.equalsIgnoreCase("Y")) {
			teamDAO.deletePlayer(playerNumber);
			System.out.println("player deleted successfully!");
		} else {
			System.out.println("Deletion cancelled.");
		}
	}

	private void viewAllPlayers() throws SQLException {
		List<Team> teams = teamDAO.getAllPlayers();

		if (teams.isEmpty()) {
			System.out.println("No teams found.");
			return;
		}

		System.out.println("Team List:");
		for (Team team : teams) {
			System.out.println("Name: " + team.getPlayerName());
			System.out.println("Number: " + team.getPlayerNumber());
			System.out.println("Age: " + team.getPlayerAge());
			System.out.println("Goals: " + team.getPlayerGoals());
			System.out.println("Club Name: " + team.getClubName());
			System.out.println("-*-*-*-*-*-*-*-*-*-");
		}
	}

	public static void main(String[] args) {
		TeamManagementSystem teamManagementSystem = new TeamManagementSystem();
		teamManagementSystem.run();
	}

}
