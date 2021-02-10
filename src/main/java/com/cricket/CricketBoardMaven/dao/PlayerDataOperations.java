package com.cricket.CricketBoardMaven.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cricket.CricketBoardMaven.Player2;

public class PlayerDataOperations {
	/**
	 * Insert player data into match_history table
	 * 
	 * @param player details of player
	 * 
	 */
	
	public static void insert(Player2 player) {
		try {
			SessionFactory sf=new Configuration().configure().buildSessionFactory();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
					session.save(player);
					tx.commit();
					session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static List<Player2> getAll() {
		List<Player2> players = new ArrayList<Player2>();
		try {
			SessionFactory sf=new Configuration().configure().buildSessionFactory();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
					players=session.createQuery("from Player").list();
					tx.commit();
					session.close();
					/*
					 * con = MysqlCon.getConnection(); stmt = con.createStatement(); sql =
					 * "select * from player"; ResultSet rs = stmt.executeQuery(sql);
					 * 
					 * while (rs.next()) { Player2 player = new Player2(rs.getString(2),
					 * rs.getInt(3),rs.getString(4)); players.add(player); } con.close();
					 */
		} catch (Exception e) {
			System.out.println(e);
		}
		return players;

	}


}
