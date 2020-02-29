package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.db.DbStore1;
import com.capgemini.librarymanagement.dto.BookInfo;

public class UserDAOImpl implements UserDAO {

	public BookInfo searchBook(int id) {
		for (BookInfo book : DbStore1.bookInfo) {
			if (book.getBookId() == id) {
				return book;
			}
		}
		return null;
	}

	public BookInfo searchBookWithName(String name) {
		for (BookInfo book : DbStore1.bookInfo) {
			if (book.getBookName().startsWith(name)  || book.getBookAuthor().startsWith(name)) {
				System.out.println(book);
				return book;
			}
			}
		return null;
	}

	

}
