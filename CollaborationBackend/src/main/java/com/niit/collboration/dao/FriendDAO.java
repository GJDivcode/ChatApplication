package com.niit.collboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO {
	
	 public int getMaxid();
	
     public boolean save(Friend friend);
     
     public boolean update(Friend friend);
     
     public void delete(String userID,String friendID);
     
     public List<Friend> getMyFriends(String userID);
               
     public List<Friend> getNewFriendRequest(String userID);
     
     public Friend get(String userID,String friendID);
     
     public void setOnline(String userID);
     
     public void setOffLine(String userID);
     
     
     
     
     
	

}
