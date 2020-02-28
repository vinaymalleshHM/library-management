package com.capgemini.librarymanagement.dao;

import com.capgemini.librarymanagement.dto.BookInfo;

public interface UserDAO {
	public BookInfo searchBook(int  bookId);
}
