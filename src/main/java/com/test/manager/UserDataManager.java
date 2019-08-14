package com.test.manager;
import com.test.UserData;

public interface UserDataManager extends ManagerBase {
	public Long saveNewUser(UserData userData);
}
