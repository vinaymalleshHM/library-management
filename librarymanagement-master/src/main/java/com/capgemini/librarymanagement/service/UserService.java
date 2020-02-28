package com.capgemini.librarymanagement.service;

import com.capgemini.librarymanagement.dto.BookInfo;

public interface UserService {
	public BookInfo searchBook(int bookId);
}
