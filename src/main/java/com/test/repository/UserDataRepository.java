package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	@Query("SELECT u FROM UserData u where u.username = :username")
	UserData findByUsername(@Param("username") String username);
}
