package com.test.manager;

import java.util.List;

import com.test.Account;

public interface AccountManager extends ManagerBase{
	public Long saveBook(Account book);
	public boolean deleteBookById(Long id);
	public Long updateBook(Account book);
	public Account getBookById(Long id);
	public List<Account> findAllBooks();
}
