package com.capgemini.librarymanagement.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagement.exception.BookGenericException;
import com.capgemini.librarymanagement.exception.UserGenericException;

public class LibraryManageValidation {

	final Pattern validEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

	final Pattern validName = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

	final Pattern validPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
	
	public boolean bookValidation(int bookId) {
		if (bookId > 0) {
		return true;
		} else {
			throw new BookGenericException("the id should be greater than 0");
		}
		
	}
	public boolean bookValidation(String name) {
		Matcher bookNameMatcher = validName.matcher(name);
		if (bookNameMatcher.find() && name.length() > 2 && name.length() < 30) {
			return true;
		}else {
			throw new BookGenericException("Name is Invalid");
		}
	}

	public boolean bookValidation(int bookId, String bookName, String bookAuth, int bookNum, String pubName) {
		Matcher bookNameMatcher = validName.matcher(bookName);
		Matcher authorNameMatcher = validName.matcher(bookAuth);
		Matcher publisherNameMatcher = validName.matcher(pubName);
		if (bookId > 0) {
			if (bookNameMatcher.find() && bookName.length() > 2 && bookName.length() < 30) {
				if (authorNameMatcher.find() && bookAuth.length() > 2 && bookAuth.length() < 50) {
					if (bookNum > 0 && String.valueOf(bookNum).trim().length() > 0) {
						if (publisherNameMatcher.find() && pubName.length() > 2 && pubName.length() < 30) {
							return true;
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
		} else {
			throw new BookGenericException("Book Id should be greter than 0");
		}
	}
	public boolean userValidation(int usrId) {
		if (usrId > 0) {
		} else {
			throw new BookGenericException("the id should be greater than 0");
		
		}
		return false;
		
	}

	public boolean userValidation(int usrId, String usrName, String usrEmail, String usrPassword) {
		Matcher userNameMatcher = validName.matcher(usrName);
		Matcher emailMatcher = validEmail.matcher(usrEmail);
		Matcher passwordMatcher = validPassword.matcher(usrEmail);
		if (usrId > 0) {
			if (userNameMatcher.find() && usrName.length() > 2 && usrName.length() < 30) {
				if (emailMatcher.find() && usrEmail.length() > 5 && usrEmail.length() < 50) {
					if (passwordMatcher.find() && usrPassword.length() > 8 && usrPassword.length() < 12) {
						return true;
					} else {
						throw new UserGenericException("password must contain 8 characters");
					}
				} else {
					throw new UserGenericException("please check entered email");
				}

			} else {
				throw new UserGenericException("user name Should caontain only Alphabets");
			}

		} else {
			throw new UserGenericException("the id should be greater than 0");
		}
	}
}
