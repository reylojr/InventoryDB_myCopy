package team.moxie;

import java.sql.*;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		try {
			DbDriver driver = new DbDriver("192.168.1.10", "3306", "inv", "dustin", "Hellgirlfan97@");
			System.out.println("Connection successful.");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
