package com.eduardo.connectmysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/*
 *  连接mysql5.6时候，需要相关的包，需要将jdbc包引入进来。
 *  java 获取Mysql的连接有四种方式，传统的连接方式
 *  读取配置文件的方式
 *  DBCP方式
 *  C3P0方式
 *  这里是第二种方式。 读取配置文件的方式，这种方式需要使用InputStream 和Properites.
 *  
 *  这个类是要将 config.properties中的值加密的。这里我们以 encryptedConfig.properites中的数据为例
 *  该配置文件中配置的数据为密文。java获取到了数据以后，需要先调用 解密的类对它进行解密。然后再被引用。
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EncryptMysqlConnectionString {

	public static void main(String args[]) throws ClassNotFoundException {
		
	//	String a= ConnectMysqlMethodTwo.class.getResource("./").getPath();
	//	String b= ConnectMysqlMethodTwo.class.getResource("/").getPath();
		String c= ConnectMysqlMethodTwo.class.getClassLoader().getResource("./com/eduardo/connectmysql/config.properties").getPath();
		Properties pro = new Properties();
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

		// 解析配置文件中的内容
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");

		try {
			// 获取连接
			//Class.forName(driver);
			Connection con = (Connection) DriverManager.getConnection(url, username, password);

			if (!con.isClosed()) {
				System.out.println("Succeed connecting to the DB");
			}
			// 创建statement类对象，用来执行SQL语句
			Statement statement = con.createStatement();
			String sql = "select migration_log.id, left(migration_log.`sql`,10) as daima, migration_log.success, migration_log.timestamp from migration_log";

			ResultSet rs = statement.executeQuery(sql);
			System.out.println("结果如下");

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
			System.out.println("错误");
			e.printStackTrace();
		}
	}

}
