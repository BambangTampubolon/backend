package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Account;
import com.test.manager.AccountManager;
import com.test.viewmodel.AccountListViewModel;
import com.test.viewmodel.InfoViewModel;
import com.test.viewmodel.MasterViewModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Account")
public class AccountController {

	@Autowired
	AccountManager bookManager;

	@PostMapping("/save")
	public ResponseEntity<MasterViewModel> saveBook(@RequestBody Account view, BindingResult binding) {
		MasterViewModel result = new MasterViewModel();
		bookManager.saveBook(view);
		InfoViewModel info = bookManager.getInfo();
		result.setContent(info);
		return new ResponseEntity<MasterViewModel>(result, (HttpStatus) info.getDetailInfo());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<MasterViewModel> updateBook(@RequestBody Account view, @PathVariable("id") Long bookId,
			BindingResult binding) {
		MasterViewModel result = new MasterViewModel();
		view.setId(bookId);
		bookManager.updateBook(view);
		InfoViewModel info = bookManager.getInfo();
		result.setContent(info);
		return new ResponseEntity<MasterViewModel>(result, (HttpStatus) info.getDetailInfo());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MasterViewModel> deleteBook(@PathVariable("id") Long bookId) {
		MasterViewModel result = new MasterViewModel();
		bookManager.deleteBookById(bookId);
		InfoViewModel info = bookManager.getInfo();
		result.setContent(info);
		return new ResponseEntity<MasterViewModel>(result, (HttpStatus) info.getDetailInfo());
	}

	@GetMapping("/getAll")
	public ResponseEntity<AccountListViewModel> getAllBook() {
		List<Account> books = bookManager.findAllBooks();
		AccountListViewModel booksVM = new AccountListViewModel(books);
		InfoViewModel info = bookManager.getInfo();
		booksVM.setInfo(info);
		return new ResponseEntity<AccountListViewModel>(booksVM, (HttpStatus) info.getDetailInfo());
	}

	@GetMapping("get/{id}")
	public ResponseEntity<AccountListViewModel> getBookById(@PathVariable("id") Long bookId) {
		Account book = bookManager.getBookById(bookId);
		AccountListViewModel booksVM = new AccountListViewModel(book);
		InfoViewModel info = bookManager.getInfo();
		booksVM.setInfo(info);
		booksVM.setTotalrows(1);
		return new ResponseEntity<AccountListViewModel>(booksVM, (HttpStatus) info.getDetailInfo());
	}
}
