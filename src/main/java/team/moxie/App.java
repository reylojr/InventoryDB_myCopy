package team.moxie;

import java.sql.*;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		try {
			DbDriver driver = new DbDriver("192.168.1.10", "3306", "inv", "dustin", "Hellgirlfan97@");
			System.out.println("Connection successful.");

			System.out.println(driver.searchById("2FT57YS7CM97"));

			dbEntry[] array = driver.searchBySupplier("HVFCRLHL");
			for (dbEntry entry : array) {
				System.out.println(entry);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
