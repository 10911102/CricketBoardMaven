package com.cricket.CricketBoardMaven.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cricket.CricketBoardMaven.Team2;
/**
 * Database related operations like create table, insert into table
 * 
 * @author swapnilu
 *
 */
public class TeamDataOperations {
	private static Connection con;
	private static Statement stmt;
	private static String sql;

	
	public static void insert(Team2 team) {
		try {
			SessionFactory sf=new Configuration().configure().buildSessionFactory();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
					session.save(team);
					tx.commit();
					session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static List<Team2> getAll() {
		List<Team2> teams = new ArrayList<Team2>();
		try {
			con = MysqlCon.getConnection();
			stmt = con.createStatement();
			sql = "select * from Team";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Team2 team = new Team2(rs.getString(1), rs.getDate(2));
				teams.add(team);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return teams;

	}

	

	

}
