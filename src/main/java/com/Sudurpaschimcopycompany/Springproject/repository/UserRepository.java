package com.Sudurpaschimcopycompany.Springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sudurpaschimcopycompany.Springproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	User findByUsernameAndPassword(String un,String pw);
	User findByUsername(String un);
	User findById(int id);

	boolean existsByUsername(String username);
	 
	

}
