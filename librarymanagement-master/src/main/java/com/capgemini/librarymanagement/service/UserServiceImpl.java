package com.capgemini.librarymanagement.service;

import com.capgemini.librarymanagement.dao.UserDAO;
import com.capgemini.librarymanagement.dao.UserDAOImpl;
import com.capgemini.librarymanagement.dto.BookInfo;

public class UserServiceImpl implements UserService{
	
	private UserDAO dao=new UserDAOImpl();

	public BookInfo searchBook(int bookId) {
		return dao.searchBook(bookId);
		
		
	}

	
}
