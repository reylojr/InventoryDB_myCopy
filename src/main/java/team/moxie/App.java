package team.moxie;

import java.sql.*;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		try {
			DbDriver driver = new DbDriver("192.168.1.10", "3306", "inv", "dustin", "Hellgirlfan97@");
			System.out.println("Connection successful.");
			System.out.println("Running tests!");

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			//			dbEntry[] array = driver.searchBySupplier("HVFCRLHL");
			//			for (dbEntry entry : array) {
			//				System.out.println(entry);
			//			}
			//			System.out.println();

			System.out.println("Updating entry with ID: 2FT57YS7CM97");
			driver.updateEntry("2FT57YS7CM97", 111, 111.0, 111.0, "YTCMSBPA");
			System.out.println();

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Deleting the ID: 2FT57YS7CM97");
			driver.deleteEntry("2FT57YS7CM97");
			System.out.println();

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Creating an entry for the ID: 2FT57YS7CM97");
			driver.createEntry("2FT57YS7CM97", 112, 112.0, 112.0, "YTCMSBPA");
			System.out.println();

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Tests have completed.");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
