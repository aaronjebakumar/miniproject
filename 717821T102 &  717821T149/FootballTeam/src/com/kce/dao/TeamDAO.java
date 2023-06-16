package com.kce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kce.bean.Team;

public class TeamDAO {
	private Connection connection;

	public TeamDAO(Connection connection) {
		this.connection = connection;
	}

	public void addPlayer(Team team) throws SQLException {
		String query = "INSERT INTO team (name, number, age, goals, club) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, team.getPlayerName());
			statement.setInt(2, team.getPlayerNumber());
			statement.setInt(3, team.getPlayerAge());
			statement.setInt(4, team.getPlayerGoals());
			statement.setString(5, team.getClubName());

			statement.executeUpdate();
		}
	}

	public void updatePlayer(Team team) throws SQLException {
		String query = "UPDATE team SET name = ?, number = ?, age = ?, goals = ?, club = ? WHERE number = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, team.getPlayerName());
			statement.setInt(2, team.getPlayerNumber());
			statement.setInt(3, team.getPlayerAge());
			statement.setInt(4, team.getPlayerGoals());
			statement.setString(5, team.getClubName());

			statement.executeUpdate();
		}
	}

	public void deletePlayer(int teamNumber) throws SQLException {
		String query = "DELETE FROM team WHERE number = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, teamNumber);
			statement.executeUpdate();
		}
	}

	public Team getTeamByNumber(int teamNumber) throws SQLException {
		String query = "SELECT * FROM team WHERE number= ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, teamNumber);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Team team = new Team();
					team.setPlayerName(resultSet.getString("name"));
					team.setPlayerNumber(resultSet.getInt("number"));
					team.setPlayerGoals(resultSet.getInt("goals"));
					team.setPlayerAge(resultSet.getInt("age"));
					team.setClubName(resultSet.getString("club"));
					return team;
				}
			}
		}

		return null;
	}

	public List<Team> getAllPlayers() throws SQLException {
		List<Team> teams = new ArrayList<>();
		String query = "SELECT * FROM team";

		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Team team = new Team();
				team.setPlayerName(resultSet.getString("name"));
				team.setPlayerNumber(resultSet.getInt("number"));
				team.setPlayerGoals(resultSet.getInt("goals"));
				team.setPlayerAge(resultSet.getInt("age"));
				team.setClubName(resultSet.getString("club"));

				teams.add(team);
			}
		}

		return teams;
	}

}
