package com.eduardo.connectmysql;

/*
 *  ����mysql5.6ʱ����Ҫ��صİ�����Ҫ��jdbc�����������
 *  java ��ȡMysql�����������ַ�ʽ����ͳ�����ӷ�ʽ
 *  ��ȡ�����ļ��ķ�ʽ
 *  DBCP��ʽ
 *  C3P0��ʽ
 */

import java.sql.Connection;
import java.sql.*;

public class ConnectMysql {
	
	public static void main(String args[]) {
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://xxxxxx:3306/grafana";
		String username="xxxxxx";
		String password="xxxxxx";
		Connection con=null;
		try {
			Class.forName(driver);
			con=(Connection)DriverManager.getConnection(url,username,password);
			
			if(!con.isClosed()) {
				System.out.println("Succeed connecting to the DB");
			}
			// ����statement���������ִ��SQL���
			Statement statement = con.createStatement();
			String sql="select migration_log.id, left(migration_log.`sql`,10) as daima, migration_log.success, migration_log.timestamp from migration_log";
			
			ResultSet rs=statement.executeQuery(sql);
			System.out.println("�������");
			
			String daima=null;
			String shijian=null;
			
			while(rs.next()) {
				daima=rs.getString("daima");
				shijian=rs.getString("timestamp");
				System.out.println(daima + "\t" + shijian);
			}
			rs.close();
			con.close();			
		}catch(ClassNotFoundException e) {
			System.out.println("���ݿ������Ҳ���");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

