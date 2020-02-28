package com.capgemini.librarymanagement.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagement.LibraryMainPage;
import com.capgemini.librarymanagement.dto.BookInfo;
import com.capgemini.librarymanagement.dto.UserInfoBean;
import com.capgemini.librarymanagement.exception.BookGenericException;
import com.capgemini.librarymanagement.service.AdminBookService;
import com.capgemini.librarymanagement.service.AdminBookServiceImpl;
import com.capgemini.librarymanagement.service.UserService;
import com.capgemini.librarymanagement.service.UserServiceImpl;

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
				Matcher  bookNameMatcher = validName.matcher(bookName);

				System.out.println("Enter author name");
				String bookAuth = scanner.next().trim();
				Matcher  authorNameMatcher = validName.matcher(bookAuth);

				System.out.println("Enter no of books");
				int bookNum = scanner.nextInt();

				System.out.println("Enter publisher name");
				String pubName = scanner.next().trim();
				Matcher  publisherNameMatcher = validName.matcher(pubName);
			
				if (bookId>0) {
					if (bookNameMatcher.find() && bookName.length()>2 && bookName.length()<30  ) {
						if (authorNameMatcher.find() && bookAuth.length()>2 && bookAuth.length()<50) {
							if (bookNum>0 && String.valueOf(bookNum).trim().length()>0) {
								if (publisherNameMatcher.find() && pubName.length()>2 && pubName.length()<30 ) {
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
									throw new BookGenericException("publisher Name is Invalid");
								}

							} else {
								throw new BookGenericException("please enter no of book properly");
							}

						} else {
							throw new BookGenericException("Book Author Name is Invalid");
						}

					} else {
						throw new BookGenericException("Book Name is Invalid");
					}
				}else {
					throw new BookGenericException("Book Id should be greter than 0");
				}

				break;
			case 2:
				System.out.println("Enter UserId: ");
				int usrId = scanner.nextInt();
				
				System.out.println("Enter UserName:");
				String usrName = scanner.next().trim();
				Matcher userNameMatcher = validName.matcher(usrName);
				
				System.out.println("Enter Email: ");
				String usrEmail = scanner.next().trim();
				Matcher emailMatcher = validEmail.matcher(usrEmail);
				
				System.out.println("Enter Password:");
				String usrPassword = scanner.next().trim();
				Matcher passwordMatcher = validPassword.matcher(usrEmail);
				
				if (usrId>0) {
					if (userNameMatcher.find() && usrName.length()>2 && usrName.length()<30) {
						if (emailMatcher.find() && usrEmail.length()>5 && usrEmail.length()<50) {
							if (passwordMatcher.find() && usrPassword.length()>8 && usrPassword.length()<12) {
								UserInfoBean userInfoBean = new UserInfoBean();
								userInfoBean.setUsrId(usrId);
								userInfoBean.setUsrName(usrName);
								userInfoBean.setUsrEmail(usrEmail);
								userInfoBean.setUsrPassword(usrPassword);
								if (adminBookService.addUser(userInfoBean)) {
									System.out.println("Successfully Added the User");
								}
							}else {
								throw new BookGenericException("password must contain 8 characters");
							}
							
						} else {
							throw new BookGenericException("please check entered email");
						}
						
					} else {
						throw new BookGenericException("user name Should caontain only Alphabets");
					}
					
				} else {
					throw new BookGenericException("the id should be greater than 0");
				}
				
				
				break;
			case 3:
				System.out.println("enter the Book id for delete");
				int bookId1 = scanner.nextInt();
				if (bookId1>0) {
					if (adminBookService.deleteBook(bookId1)) {
						System.out.println("Deleted Successfully");
					} else {
						System.out.println("Book Id not found");
					}
					
				} else {
					throw new BookGenericException("the id should be greater than 0");
				}
				break;
			case 4:
				System.out.println("enter the user id for delete");
				int userId = scanner.nextInt();
				if (userId>0) {
					if (adminBookService.deleteUser(userId)) {
						System.out.println("Deleted Successfully");
					} else {
						System.out.println("User Id not found");
					}
					
				} else {
					throw new BookGenericException("the id should be greater than 0");
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
				Matcher usrNameUpdateMatcher = validName.matcher(bean.getUsrName());
				
				System.out.println("Enter User Email to Update");
				bean.setUsrEmail(scanner.next().trim());
				Matcher emailUpdateMatcher = validName.matcher(bean.getUsrEmail());
				
				System.out.println("Enter User Passsword to Upadte");
				bean.setUsrPassword(scanner.next().trim());
				Matcher passswordUpdateMatcher = validName.matcher(bean.getUsrPassword());
				
				if (bean.getUsrId()>0) {
					if (usrNameUpdateMatcher.find() && bean.getUsrName().length()>2 && bean.getUsrName().length()<30) {
						if (emailUpdateMatcher.find() && bean.getUsrEmail().length()>5 && bean.getUsrEmail().length()<50) {
							if (passswordUpdateMatcher.find() && bean.getUsrPassword().length()>8 && bean.getUsrPassword().length()<12) {
								if (adminBookService.updateUser(bean) != null) {
									System.out.println("Updated Successfully!!!");

								} else {
									System.out.println("Failed to Update");
								}
							}else {
								throw new BookGenericException("password must contain 8 characters");
							}
							
						} else {
							throw new BookGenericException("please check entered email");
						}
						
					} else {
						throw new BookGenericException("the field should contain Alphabet");
					}
					
				} else {
					throw new BookGenericException("the id should be greater than 0");
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

	public void user() {
		System.out.println("User login successfully");
		while (true) {
			System.out.println("Enter your choice");
			System.out.println("1.Boorow Book\n2.Search Book");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				break;
			case 2:
				displaySearchBook();
				break;

			}
		}

	}
	private void displaySearchBook() {
		System.out.println("Search Based on Book Id");
		int bookId = scanner.nextInt();
		BookInfo book = userService.searchBook(bookId);
		if(book!=null) {
			System.out.println(book.getBookName());
		}

	}



}
