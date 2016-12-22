package com.niit.collaboration.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;
import com.niit.collboration.dao.UserDAO;


@Repository(value="userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {

	private static final Logger log=LoggerFactory.getLogger(UserDaoImpl.class);
	
   @Autowired
   SessionFactory sessionFactory;
   
   @Autowired
   User user;
   
   public UserDaoImpl(){
	   
   }
    
    public UserDaoImpl(SessionFactory sessionFactory){
    	try{
    		this.sessionFactory=sessionFactory;	
    	}
    	catch(Exception e){
    	  log.error("unable to connect to db");	
    	 e.printStackTrace();
    	}
    	
    }
	
   
	@Override
	@Transactional
	public boolean save(User user) {
		log.debug("Calling of the save method");
		try{
			log.debug("opening the new session");
			/*sessionFactory.getCurrentSession().save(user);*/
			Session session=sessionFactory.openSession();
			session.save(user);
			session.flush();
			log.debug("Record Saved Successfully");
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean update(User user) {
		log.debug("Calling of the save method");
		try{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		
	
	}

	
	 @Override
	 @Transactional
	public User get(String userid) {
		 log.debug("calling of the method save");
		 log.debug("userid is:"+userid);
		 return (User)sessionFactory.getCurrentSession().get(User.class,userid);
		 
		 
		/*log.debug("starting of the method get  with the id:"+userid);
		String hql="from User where userid='"+ userid + "'";
		log.debug("HQL:"+hql);
		Query query=sessionFactory.openSession().createQuery(hql);
		User user = (User)query.uniqueResult();
		log.debug("userName:"+user.getName());*/
		/*return user;*/
	}

	 @Override
	 @Transactional
	public User validate(String userid, String password) {
		log.debug("Calling of the validate method");
		log.debug("user ud:" + userid + "password is:" + password);
		String hql="from User where userid='"+ userid +"' and password='"+ password +"'";
		
		log.debug("The Query is:"+hql);
		Query query=sessionFactory.openSession().createQuery(hql);
		return (User)query.uniqueResult();
	}

	 @Override
	 @Transactional
	public List<User> getallusers() {
	 log.debug("Calling of the getallusers method");
      String hql="from User";
      log.debug("hql:"+hql);
      Query query=sessionFactory.openSession().createQuery(hql);
      log.debug("The Query is:"+query.getQueryString());
	  return query.list();
	}


	@Override
	@Transactional
	public void delete(String userid, String friendID) {
        Friend friend=new Friend();
        friend.setFriendID(friendID);
        friend.setUserID(userid);
        sessionFactory.openSession().delete(friend);
        
		
	}
	
	@Override
	@Transactional
	public void deleteuserid(String userid) {
		try{
			sessionFactory.openSession().delete(userid);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
