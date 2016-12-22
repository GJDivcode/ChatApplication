package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;
import com.niit.collboration.dao.FriendDAO;
import com.niit.collboration.dao.UserDAO;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	User user;

	@Autowired
	FriendDAO friendDAO;

	@Autowired
	Friend friend;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/makeAdmin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("userid") String empID) {

		log.debug("calling method makeAdmin");
		user = userDAO.get(empID);
		if (user == null) {
			log.debug("employee does not exists with the id:" + empID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exists");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		String role = "Role_Admin";
		user.setRole(role);
		userDAO.update(user);
		log.debug("employee role updated as admin successfully:" + empID);
		return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getallusers() {
		log.debug(".......calling the method getallusers");
		List<User> userList = userDAO.getallusers();
		if (userList.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("Users Are Not Available");
			userList.add(user);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(value = "userid") String userid) {

		log.debug("..>....>.calling  method getusers");
		log.debug("-->-->id" + userid);
		user = userDAO.get(userid);
		if (user == null) {
			log.debug("..>....>.users does not exists with id:" + userid);
			user = new User();// to avoid Null pointer Exception
			user.setErrorCode("404");
			user.setErrorMessage("user does not exists with id:" + userid);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);

		}
		log.debug("..>....>.users  exists with id:" + userid);
		log.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user, HttpSession httpsession) {
		log.debug("...>...>Calling the method Authenticate or validate");

		user = userDAO.validate(user.getUserid(), user.getPassword());
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials please Try Again.....!");

		} else {
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully loggedIn!");
			user.setIsOnline('Y');
			log.debug("...>....>valid Credentials");
			httpsession.setAttribute("loggedInUser", user);
			httpsession.setAttribute("loggedInUser", user.getUserid());
			user.setIsOnline('Y');
			/*
			 * friendDAO.setOnline(user.getUserid()); open when table friend is
			 * created
			 */
			userDAO.update(user);

		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/accept/{userid}", method = RequestMethod.GET)
	public ResponseEntity<User> acceptRegistration(@PathVariable("userid") String userid) {
		log.debug("Starting of the method Accept");
		user = updateStatus(userid, 'A', "");
		log.debug("Ending of the method Accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	private User updateStatus(String userid, char status, String reason) {
		log.debug("Starting of the method updatestatus");
		log.debug("status:" + status);

		user = userDAO.get(userid);
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status");
		} else {
			user.setStatus(status);
			user.setReason(reason);

			userDAO.update(user);
			user.setErrorCode("200");
			user.setErrorMessage("updated the status successfully");

		}
		log.debug("ending of the method updatestatus");
		return user;
	}

	@RequestMapping(value = "/reject/{userid}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> rejectRegistration(@PathVariable("userid") String userid,
			@PathVariable("reason") String reason) {

		user = userDAO.get(userid);
		user = updateStatus(userid, 'R', reason);
		user.setStatus('R');
		user.setReason(reason);

		if (userDAO.update(user) == true) {
			// user=new User();
			user.setErrorCode("200");
			user.setErrorMessage("Successfully Updated");

		} else {
			user.setErrorCode("404");
			user.setErrorMessage("Not able to Update the Status");
			// user.setReason("Your Registration is not Accepted");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession httpsession) {
		log.debug("...>>....>>calling the method");
		String loggedInUserID = (String) httpsession.getAttribute("loggedInUserID");
		/*
		 * friendDAO.setOffLine(loggedInUserID); according to the usercontroller
		 * methodology
		 */
		user = userDAO.get(loggedInUserID);
		user.setIsOnline('N');

		if (userDAO.update(user)) {
			user.setErrorCode("200");
			user.setErrorMessage("You Successfully Logged Out");

		} else {
			user.setErrorCode("404");
			user.setErrorMessage("Some Proble occured During Logged Out");

		}	
		httpsession.invalidate();

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user) {
		log.debug("........calling the method Create User");
		if (userDAO.get(user.getUserid()) == null) {
			log.debug("........>user is going  to create  with id:" + user.getUserid());
			log.debug("...>....>.....>user is going to create with  password:" + user.getPassword());
			user.setIsOnline('N');
			user.setStatus('N');

			if (userDAO.save(user) == true) {
				user.setErrorCode("200");
				user.setErrorMessage("Thanks for the Registeration");

			} else {
				user.setErrorCode("404");
				user.setErrorMessage("Could not complete the peration contact Admin");
			}

			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		log.debug("..>...>User alreadyexists with the id:" + user.getUserid());
		user.setErrorCode("404");
		user.setErrorMessage("User alreadyexists with the id:" + user.getUserid());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateuser(@RequestBody User user) {

		log.debug("....>...>calling the method updateuser");
		if (userDAO.get(user.getUserid()) == null) {
			log.debug("..>..>...>user does not exists with id:" + user.getUserid());
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("user does not exists with id:" + user.getUserid());
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		userDAO.update(user);
		log.debug("..>...>user Updated Successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/user/{userid}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteuser(@PathVariable("userid") String userid) {
		log.debug("........calling the method deleteuser");
		User user = userDAO.get(userid);
		if (user == null) {
			log.debug("........user does not exists with id:" + userid);
			user = new User();
			user.setErrorMessage("user does not exists with id:" + userid);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);

		}
		userDAO.deleteuserid(userid);
		log.debug("........user does not exists with id:" + userid);
		user.setErrorMessage("users delete successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session) {
		log.debug("--->...>calling of the method myprofile");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		User user = userDAO.get(loggedInUserID);
		if (user == null) {
			log.debug("--->user does not exists with id:" + loggedInUserID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("user does not exists");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("...>.....>user exists with id:" + loggedInUserID);
		log.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

}
