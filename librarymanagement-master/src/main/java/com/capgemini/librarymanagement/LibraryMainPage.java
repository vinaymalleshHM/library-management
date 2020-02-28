package com.capgemini.librarymanagement;

import java.util.Scanner;

import com.capgemini.librarymanagement.controller.LibraryManagementController;

public class LibraryMainPage {

	public static void main(String[] args) {

		LibraryManagementController libraryManagementController = new LibraryManagementController();
		Scanner scanner = new Scanner(System.in);
		System.out.println("---*****LIBRARY MANAGEMENT SYSTEM*****---");
		System.out.println("---------------------------");
		boolean flag = true;
		while (flag) {
			System.out.println("Available Choices");
			System.out.println("1.Admin\n2.User\n3.Exit");
			System.out.println("---------------------------");
			System.out.println("Enter Your Choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("----Login----");
				System.out.println("Enter UserName \n");
				String adminName = scanner.next();
				System.out.println("Enter Password \n");
				String adminPassword = scanner.next();
				if (adminName.equals("Admin") && adminPassword.equals("admin@123")) {
					libraryManagementController.admin();
				} else {
					System.out.println("Please enter correct username and password?");
				}
				break;
			case 2:
				System.out.println("----Login---->");
				System.out.println("Enter UserName \n");
				String userName = scanner.next();
				System.out.println("Enter Password \n");
				String userPassword = scanner.next();

				if (userName.equals("User") && userPassword.equals("user@123")) {
					libraryManagementController.user();
				} else {
					System.out.println("Please enter correct username and password?");
				}
				break;
			case 3:System.exit(0);
			}
		}
		scanner.close();
	}
}
