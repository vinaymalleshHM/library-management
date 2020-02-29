package com.capgemini.librarymanagement.controller;

import java.util.Date;
import java.util.Scanner;

import com.capgemini.librarymanagement.LibraryMainPage;
import com.capgemini.librarymanagement.dto.BookInfo;
import com.capgemini.librarymanagement.dto.UserBookDetail;
import com.capgemini.librarymanagement.dto.UserInfoBean;
import com.capgemini.librarymanagement.exception.BookGenericException;
import com.capgemini.librarymanagement.service.UserService;
import com.capgemini.librarymanagement.service.UserServiceImpl;
import com.capgemini.librarymanagement.validation.LibraryManageValidation;

public class UserController {

	UserService userService = new UserServiceImpl();
	Scanner scanner = new Scanner(System.in);

	public void user() {
		System.out.println("User login successfully");
		while (true) {
			System.out.println("Enter your choice");
			System.out.println("1.Search Book\n2.Borrow Book\n3.Logout");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				displaySearchBookWithName();
				break;
			case 2:
				
				break;
			}
		}
	}

	private void displaySearchBook() {
		System.out.println("Search Based on Book Id");
		int bookId = scanner.nextInt();
		LibraryManageValidation searchIdValidation = new LibraryManageValidation();
		if (searchIdValidation.bookValidation(bookId)) {
			BookInfo book = userService.searchBook(bookId);
			if (book != null) {
				System.out.println(book.getBookName());
			}
		} else {
			throw new BookGenericException("Invalid book id");

		}
		

	}
	

	private void displaySearchBookWithName() {
		System.out.println("Search Based on Book Name");
		String  name = scanner.next();
		LibraryManageValidation searchValidation = new LibraryManageValidation();
		
		if (searchValidation.bookValidation(name)) {
			BookInfo book = userService.searchBookWithName(name);
			if (book != null) {
				System.out.println(book.getBookName());
				UserInfoBean bean = new UserInfoBean();
				System.out.println("1.Borrow\n2.Logout\n");
				int choice = scanner.nextInt();
				switch (choice) {
				
				case 1:
					barrow(book,bean);

					break;
				case 2:
					System.out.println("Logout Successfull");
					LibraryMainPage.main(null);
				

				default:
					break;
				}
			}
		} else {
			throw new BookGenericException("Invalid Name");

		}
		
	}


	public void barrow(BookInfo book, UserInfoBean bean) {
		UserBookDetail bookDetail = new UserBookDetail();
		bookDetail.setIssueDate(new Date());
	}

}
