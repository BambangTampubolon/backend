package com.test.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.Account;

public class AccountListViewModel extends ListViewModel implements IListViewModel {
	@JsonProperty("content")
	private List<Account> listViewModel;

	@JsonProperty("content")
	public List<Account> getListViewModel() {
		return listViewModel;
	}

	@JsonProperty("content")
	public void setListViewModel(List<Account> listViewModel) {
		this.listViewModel = listViewModel;
	}

	public AccountListViewModel() {
		super();
	}

	public AccountListViewModel(List<Account> listViewModel) {
		this.listViewModel = listViewModel;
	}

	public AccountListViewModel(Account viewModel) {
		List<Account> listViewModel = new ArrayList<Account>();
		listViewModel.add(viewModel);
		this.listViewModel = listViewModel;
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return listViewModel;
	}
}
