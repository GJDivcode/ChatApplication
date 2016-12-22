package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaboration.model.Friend;
import com.niit.collboration.dao.FriendDAO;

public class FriendController {
	
	private static final Logger log=LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/myFriends",method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session){
		log.debug("Calling of the method getMyFriends");
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		
		log.debug("Getting friends of:"+loggedInUserID);
		List<Friend>myFriends=friendDAO.getMyFriends(loggedInUserID);
		
		if(myFriends.isEmpty()){
			log.debug("friends does not exists for the user:"+loggedInUserID);
			friend.setErrorCode("404");
			friend.setErrorMessage("you does not have any friends");
			myFriends.add(friend);
		}
		log.debug("Send the Friend List");
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID")String friendID,HttpSession session)
	{
		log.debug("calling of the FriendRequest");
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus("N");//new-n,r-reject,a-accept
		friend.setIsOnline('N');
		
		if(friendDAO.get(loggedInUserID, friendID)!=null){
			friend.setErrorCode("404");
			friend.setErrorMessage("You have already sent the Friend Request");
		}
		else
		{
			friendDAO.save(friend);
			friend.setErrorCode("200");
			friend.setErrorMessage("Friend request successful:"+friendID);
		}
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/unFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> unFriendRequest(@PathVariable("friendID")String friendID)
	{
		log.debug("calling of the method rejectFriendRequest");
		updateRequest(friendID,"U");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/rejectFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendID")String friendID)
	{
		log.debug("calling of the method rejectFriendRequest");
		updateRequest(friendID,"R");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/acceptFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendID")String friendID)
	{
		log.debug("calling of the method rejectFriendRequest");
		updateRequest(friendID,"A");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	
	//just fort testing scenario
	@RequestMapping(value="/getMyfriendRequests/",method = RequestMethod.GET)
	public ResponseEntity<List<Friend>>getMyFriendsRequests()
	{
		log.debug("calling of the getMyFriendsrequests");
		String LoggedInUserID=(String)session.getAttribute("LoggedInUserID");
		List<Friend> myFriendsRequests=friendDAO.getNewFriendRequest(LoggedInUserID);
	    return new ResponseEntity<List<Friend>>(myFriendsRequests,HttpStatus.OK);
		
	}
	
	
	private void updateRequest(String friendID,String status){
		
		String LoggedInUserID=(String)session.getAttribute("LoggedInUserID");
		friend.setUserID(LoggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus(status);
		friendDAO.update(friend);
			
	}
	
	/*//just for the testing purpose from restclient
*/	
	@RequestMapping(value="/myFriends/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Friend>>getFriendsTemp(@PathVariable("id")String id){
		log.debug("calling of the getMyFriendsTemp");
		List<Friend>myFriends=friendDAO.getMyFriends(id);
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
