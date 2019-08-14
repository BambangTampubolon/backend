package com.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	
}
