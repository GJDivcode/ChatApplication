package com.niit.collboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public void delete(String userid,String friendID); 
	
	public void deleteuserid(String userid);
	
	public User get(String userid);
	
	public User validate(String userid,String password);
	
	public List<User> getallusers();

}
