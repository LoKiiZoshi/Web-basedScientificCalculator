package com.Sudurpaschimcopycompany.Springproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sudurpaschimcopycompany.Springproject.model.User;
import com.Sudurpaschimcopycompany.Springproject.repository.UserRepository;
import com.Sudurpaschimcopycompany.Springproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository ur;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		ur.save(user);
	}

	@Override
	public User loginUser(String un, String pw) {
		// TODO Auto-generated method stub
		return ur.findByUsernameAndPassword(un, pw);
	}

	@Override
	public User doesUserExist(String un) {
		// TODO Auto-generated method stub
		return ur.findByUsername(un);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return ur.findById(id);
	}

	@Override
	public boolean checkUserExists(String username) {
		// TODO Auto-generated method stub
		return ur.existsByUsername(username);
	}

	@Override
	public void signup(User user) {
		// TODO Auto-generated method stub
		ur.save(user);
	}

	@Override
	public User authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return ur.findByUsernameAndPassword(username, password);
	}

}
