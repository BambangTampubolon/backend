package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.UserData;
import com.test.manager.UserDataManager;
import com.test.viewmodel.InfoViewModel;
import com.test.viewmodel.MasterViewModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/User")
public class UserDataController {

	@Autowired
	UserDataManager userDataManager;

	@PostMapping("/save")
	public ResponseEntity<MasterViewModel> saveNewUserData(@RequestBody UserData userData,
			BindingResult bindingResult) {
		MasterViewModel result = new MasterViewModel();
		userDataManager.saveNewUser(userData);
		InfoViewModel info = userDataManager.getInfo();
		result.setContent(info);
		return new ResponseEntity<MasterViewModel>(result, (HttpStatus) info.getDetailInfo());
	}
}
