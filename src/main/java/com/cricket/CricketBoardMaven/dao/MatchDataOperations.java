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

import com.cricket.CricketBoardMaven.Match;
/**
 * Database related operations like create table, insert into table
 * 
 * @author swapnilu
 *
 */
public class MatchDataOperations {
	private static Connection con;
	private static Statement stmt;
	private static String sql;
		

	/**
	 * Insert record(match) into match_history table
	 * 
	 * @param match details of match
	 */
	
	public static void insert(Match match) {

		try {
			SessionFactory sf=new Configuration().configure().buildSessionFactory();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
					session.save(match);
					tx.commit();
					session.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static List<Match> getAll() {
		List<Match> matches = new ArrayList<Match>();
		try {
			con = MysqlCon.getConnection();
			stmt = con.createStatement();
			sql = "select * from match_history";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Match match = new Match(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7));
				matches.add(match);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return matches;
	}

	

}
