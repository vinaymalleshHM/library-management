package com.capgemini.librarymanagement.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagement.LibraryMainPage;
import com.capgemini.librarymanagement.dto.BookInfo;
import com.capgemini.librarymanagement.dto.UserInfoBean;
import com.capgemini.librarymanagement.exception.BookGenericException;
import com.capgemini.librarymanagement.exception.UserGenericException;
import com.capgemini.librarymanagement.service.AdminBookService;
import com.capgemini.librarymanagement.service.AdminBookServiceImpl;
import com.capgemini.librarymanagement.service.UserService;
import com.capgemini.librarymanagement.service.UserServiceImpl;
import com.capgemini.librarymanagement.validation.LibraryManageValidation;

public class LibraryManagementController {

	Scanner scanner = new Scanner(System.in);
	AdminBookService adminBookService = new AdminBookServiceImpl();
	UserService userService = new UserServiceImpl();

	final Pattern validEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

	final Pattern validName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

	final Pattern validPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

	public void admin() {

		System.out.println(" Admin Successfull login");
		while (true) {
			System.out.println("Enter your choice");
			System.out.println("1.Add Book");
			System.out.println("2.Add User");
			System.out.println("3.Delete Book");
			System.out.println("4.Delete User");
			System.out.println("5.Show All Users");
			System.out.println("6.Show All Books");
			System.out.println("7.Update User Details");
			System.out.println("8.Logout");
			System.out.println("-------------------------");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter book id: ");
				int bookId = scanner.nextInt();

				System.out.println("Enter name of book");
				String bookName = scanner.next().trim();

				System.out.println("Enter author name");
				String bookAuth = scanner.next().trim();

				System.out.println("Enter no of books");
				int bookNum = scanner.nextInt();

				System.out.println("Enter publisher name");
				String pubName = scanner.next().trim();

				LibraryManageValidation validation = new LibraryManageValidation();
				if (validation.bookValidation(bookId, bookName, bookAuth, bookNum, pubName)) {
					BookInfo bookInfo = new BookInfo();
					bookInfo.setBookId(bookId);
					bookInfo.setBookName(bookName);
					bookInfo.setBookAuthor(bookAuth);

					bookInfo.setNoOfBooks(bookNum);
					bookInfo.setPublisher(pubName);

					if (adminBookService.addBook(bookInfo)) {
						System.out.println("Books added successfully with " + bookNum + "copy");
					}
				} else {
					throw new BookGenericException("Book Deatails Invalid");
				}

				break;
			case 2:
				System.out.println("Enter UserId: ");
				int usrId = scanner.nextInt();

				System.out.println("Enter UserName:");
				String usrName = scanner.next().trim();

				System.out.println("Enter Email: ");
				String usrEmail = scanner.next().trim();

				System.out.println("Enter Password:");
				String usrPassword = scanner.next().trim();

				LibraryManageValidation userValidation = new LibraryManageValidation();

				if (userValidation.userValidation(usrId, usrName, usrEmail, usrPassword)) {
					UserInfoBean userInfoBean = new UserInfoBean();
					userInfoBean.setUsrId(usrId);
					userInfoBean.setUsrName(usrName);
					userInfoBean.setUsrEmail(usrEmail);
					userInfoBean.setUsrPassword(usrPassword);
					if (adminBookService.addUser(userInfoBean)) {
						System.out.println("Successfully Added the User");
					}
				} else {
					throw new UserGenericException("Users Details is invalid");

				}

				break;
			case 3:

				System.out.println("enter the Book id for delete");
				int bookId1 = scanner.nextInt();

				LibraryManageValidation bookIdValidation = new LibraryManageValidation();

				if (bookIdValidation.bookValidation(bookId1)) {
					if (adminBookService.deleteBook(bookId1)) {
						System.out.println("Deleted Successfully");
					} else {
						System.out.println("Book Id not found");
					}

				} else {
					throw new BookGenericException("Invalid Book Id");

				}

				break;
			case 4:
				System.out.println("enter the user id for delete");
				int userId = scanner.nextInt();
				LibraryManageValidation userIdValidation = new LibraryManageValidation();

				if (userIdValidation.userValidation(userId)) {
					if (adminBookService.deleteUser(userId)) {
						System.out.println("Deleted Successfully");
					} else {
						System.out.println("User Id not found");
					}
				} else {
					throw new UserGenericException("User Id is Invalid");

				}
				break;
			case 5:
				System.out.println("---User Details---");
				List<UserInfoBean> list1 = adminBookService.showAllUser();
				if (!list1.isEmpty()) {
					for (UserInfoBean users : list1) {
						System.out.println(
								"User Id=" + users.getUsrId() + "\t User Name=" + users.getUsrName() + "\t User Email="
										+ users.getUsrEmail() + "\t User Password=" + users.getUsrPassword());
					}
				} else {
					System.out.println("No Users to show");
				}
				break;
			case 6:
				System.out.println("---Book Details---");
				List<BookInfo> list = adminBookService.showAllBooks();
				if (!list.isEmpty()) {
					for (BookInfo books : list) {
						System.out.println("Book Id=" + books.getBookId() + "\t Book Name = " + books.getBookName()
								+ " \t Book Author = " + books.getBookAuthor() + "\t Number of book copies"
								+ books.getNoOfBooks() + "\t Publisher Name=" + books.getPublisher());
					}
				} else {
					System.out.println("No books to show");
				}
				break;
			case 7:
				UserInfoBean bean = new UserInfoBean();
				System.out.println("Enter User Id to Update");
				bean.setUsrId(scanner.nextInt());

				System.out.println("Enter User Name to Update");
				bean.setUsrName(scanner.next().trim());

				System.out.println("Enter User Email to Update");
				bean.setUsrEmail(scanner.next().trim());

				System.out.println("Enter User Passsword to Upadte");
				bean.setUsrPassword(scanner.next().trim());

				LibraryManageValidation userValidatin = new LibraryManageValidation();
				if (userValidatin.userValidation(bean.getUsrId(), bean.getUsrName(), bean.getUsrEmail(),
						bean.getUsrPassword())) {
					if (adminBookService.updateUser(bean) != null) {
						System.out.println("Updated Successfully!!!");

					} else {
						System.out.println("Failed to Update");
					}

				} else {
					throw new UserGenericException("Invalid User Details");

				}

				break;

			case 8:
				System.out.println("Logout Successfull");
				LibraryMainPage.main(null);
				System.exit(0);

			default:
				System.out.println("Invalid choice");
				break;
			}

		}
	}

	
}
