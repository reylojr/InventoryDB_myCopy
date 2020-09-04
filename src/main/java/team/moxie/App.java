package team.moxie;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		try {
			DbDriver driver = new DbDriver("50.116.26.153", "3306", "inv", "team", "GJ&8YahAh%kS#i");
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
			boolean r = driver.updateEntry("2FT57YS7CM97", 111, 111.0, 111.0, "YTCMSBPA");
			if (r) {
				System.out.println("Successful\n");
			} else System.out.println("Failed\n");

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Deleting the ID: 2FT57YS7CM97");
			r = driver.deleteEntry("2FT57YS7CM97");
			if (r) {
				System.out.println("Successful\n");
			} else System.out.println("Failed\n");

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Creating an entry for the ID: 2FT57YS7CM97");
			r = driver.createEntry("2FT57YS7CM97", 112, 112.0, 112.0, "YTCMSBPA");
			if (r) {
				System.out.println("Successful\n");
			} else System.out.println("Failed\n");

			System.out.println("Searching for the ID: 2FT57YS7CM97");
			System.out.println(driver.searchById("2FT57YS7CM97"));
			System.out.println();

			System.out.println("Tests have completed.");

			//Added by Shawn, Cody - option 1
			Scanner sysIn = new Scanner(System.in);
			System.out.println("Database Manager.\nPress 1 for operations management.\nPress 2 for customer service");
			int test = sysIn.nextInt();
			switch (test) {
				case 1:
					//operations();
				case 2:
					//customer();
					Scanner cusIn = new Scanner(System.in);
					int cusSel = sysIn.nextInt();
					System.out.println("Welcome to database customer service. Please make selection:\n");
					System.out.println("1: Search inventory\n2: Make purchase");
					switch (cusSel) {
						case (1):
							System.out.println("Enter product ID");
							String ID = sysIn.nextLine();
							System.out.println(driver.searchById(ID));
					}
			}


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
