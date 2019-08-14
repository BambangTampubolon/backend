package com.test.manager.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.UserData;
import com.test.repository.UserDataRepository;

@Service
public class UserDetailsManagerImpl implements UserDetailsService {

	private UserDataRepository userDataRepository;

	public UserDetailsManagerImpl(UserDataRepository userDataRepository) {
		this.userDataRepository = userDataRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserData userData = userDataRepository.findByUsername(username);
		if (null == userData) {
			throw new UsernameNotFoundException(username);
		}

		return new User(userData.getUsername(), userData.getPassword(), emptyList());
	}

}
