package com.eduardo.connectmysql;

/*
 *  连接mysql5.6时候，需要相关的包，需要将jdbc包引入进来。
 *  java 获取Mysql的连接有四种方式，传统的连接方式
 *  读取配置文件的方式
 *  DBCP方式
 *  C3P0方式
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
			// 创建statement类对象，用来执行SQL语句
			Statement statement = con.createStatement();
			String sql="select migration_log.id, left(migration_log.`sql`,10) as daima, migration_log.success, migration_log.timestamp from migration_log";
			
			ResultSet rs=statement.executeQuery(sql);
			System.out.println("结果如下");
			
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
			System.out.println("数据库驱动找不到");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

