package com.test.manager.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.UserData;
import com.test.manager.UserDataManager;
import com.test.repository.UserDataRepository;
import com.test.util.StaticVariable;
import com.test.viewmodel.InfoViewModel;

@Service
public class UserDataManagerImpl extends ManagerImpl implements UserDataManager {

	@Autowired
	UserDataRepository userDataRepository;

	@Override
	public Long saveNewUser(UserData userData) {
		try {
			UserData result = userDataRepository.save(userData);

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

}
