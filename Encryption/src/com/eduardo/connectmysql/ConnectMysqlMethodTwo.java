package com.eduardo.connectmysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/*
 *  ����mysql5.6ʱ����Ҫ��صİ�����Ҫ��jdbc�����������
 *  java ��ȡMysql�����������ַ�ʽ����ͳ�����ӷ�ʽ
 *  ��ȡ�����ļ��ķ�ʽ
 *  DBCP��ʽ
 *  C3P0��ʽ
 *  �����ǵڶ��ַ�ʽ�� ��ȡ�����ļ��ķ�ʽ�����ַ�ʽ��Ҫʹ��InputStream ��Properites.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectMysqlMethodTwo {

	public static void main(String args[]) throws ClassNotFoundException {
		
		String a= ConnectMysqlMethodTwo.class.getResource("./").getPath();
		String b= ConnectMysqlMethodTwo.class.getResource("/").getPath();
		String c= ConnectMysqlMethodTwo.class.getClassLoader().getResource("./com/eduardo/connectmysql/config.properties").getPath();
		System.out.printf(a+"\n");
		System.out.printf(b+"\n");
		System.out.printf(c+"\n");
		
		Properties pro = new Properties();

		//InputStream is = ConnectMysqlMethodTwo.class.getClassLoader().getResourceAsStream("config.properites");
		InputStream is = null;
		try {
			is = new FileInputStream(c);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			pro.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ���������ļ��е�����
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");

		try {
			// ��ȡ����
			//Class.forName(driver);
			Connection con = (Connection) DriverManager.getConnection(url, username, password);

			if (!con.isClosed()) {
				System.out.println("Succeed connecting to the DB");
			}
			// ����statement���������ִ��SQL���
			Statement statement = con.createStatement();
			String sql = "select migration_log.id, left(migration_log.`sql`,10) as daima, migration_log.success, migration_log.timestamp from migration_log";

			ResultSet rs = statement.executeQuery(sql);
			System.out.println("�������");

			String daima = null;
			String shijian = null;

			while (rs.next()) {
				daima = rs.getString("daima");
				shijian = rs.getString("timestamp");
				System.out.println(daima + "\t" + shijian);
			}
			rs.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("����");
			e.printStackTrace();
		}
	}

}
