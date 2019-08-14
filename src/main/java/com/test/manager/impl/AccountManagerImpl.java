package com.test.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.Account;
import com.test.manager.AccountManager;
import com.test.repository.AccountRepository;
import com.test.util.StaticVariable;
import com.test.viewmodel.InfoViewModel;

@Service
public class AccountManagerImpl extends ManagerImpl implements AccountManager {

	@Autowired
	AccountRepository bookRepository;

	@Override
	public Long saveBook(Account book) {
		try {
			Account result = bookRepository.save(book);

			InfoViewModel info = new InfoViewModel();
			info.setMessage(HttpStatus.CREATED.name());
			info.setStatus(HttpStatus.CREATED.value());
			info.setDetailInfo(HttpStatus.CREATED);
			info.setDetailmessage(result.getId());
			this.setInfo(info);
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}
		return null;
	}

	@Override
	public boolean deleteBookById(Long id) {
		try {
			bookRepository.delete(id);
			InfoViewModel info = new InfoViewModel();
			info.setMessage(HttpStatus.CREATED.name());
			info.setStatus(HttpStatus.CREATED.value());
			info.setDetailInfo(HttpStatus.CREATED);
			info.setDetailmessage(id);
			this.setInfo(info);
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}
		return false;
	}

	@Override
	public Long updateBook(Account book) {
		try {
			Account result = bookRepository.save(book);
			InfoViewModel info = new InfoViewModel();
			info.setMessage(HttpStatus.OK.name());
			info.setStatus(HttpStatus.OK.value());
			info.setDetailInfo(HttpStatus.OK);
			info.setDetailmessage(result.getId());
			this.setInfo(info);
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}
		return null;
	}

	@Override
	public Account getBookById(Long id) {
		try {
			Account result = bookRepository.findOne(id);
			if (null != result && result.getId() > 0) {
				InfoViewModel info = new InfoViewModel();
				info.setMessage(HttpStatus.CREATED.name());
				info.setStatus(HttpStatus.CREATED.value());
				info.setDetailInfo(HttpStatus.CREATED);
				info.setDetailmessage(result.getId());
				this.setInfo(info);
				return result;
			}
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}
		return null;
	}

	@Override
	public List<Account> findAllBooks() {
		List<Account> result = new ArrayList<Account>();
		try {
			bookRepository.findAll().forEach(book -> result.add(book));
			if (result.size() > 0) {
				InfoViewModel info = new InfoViewModel();
				info.setMessage(HttpStatus.CREATED.name());
				info.setStatus(HttpStatus.CREATED.value());
				info.setDetailInfo(HttpStatus.CREATED);
				info.setDetailmessage("SUCESS GET BOOKS");
				this.setTotalRow(result.size());
				this.setInfo(info);
				return result;
			}
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			setInfoConflict(errMsg);
		}
		return null;
	}

	public void setInfoConflict(String errMsg) {
		InfoViewModel info = new InfoViewModel();

		info.setMessage(HttpStatus.CONFLICT.name());
		info.setStatus(HttpStatus.CONFLICT.value());
		info.setDetailInfo(HttpStatus.CONFLICT);
		info.setDetailmessage(errMsg);

		setInfo(info);
	}
}
