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
			System.out.println("Database Manager.\nPress 1 for Supplier.\nPress 2 for Buyer");
			int test = sysIn.nextInt();
			switch (test) {
				case 1:
					//Supplier();
				case 2:
					//Buyer();
					Scanner prodIn = new Scanner(System.in);
					System.out.println("Welcome to database Buyer Event.\n");
					System.out.println("Enter Product ID");
					String ID = prodIn.nextLine();
					dbEntry product = driver.searchById(ID);
					System.out.println("You've chosen: " + product);
					boolean a;
					do {
						a = true;
						System.out.println("Enter quantity to buy:");
						int quantity = prodIn.nextInt();
						if (quantity > product.getQuantity() || quantity < 0) {
							a = false;
							System.out.println("Reenter quantity!!!!");
						}
						else{
							product.setQuantity(product.getQuantity() - quantity);
						}
					} while ( !a );
					System.out.println("Product update: " + product);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
