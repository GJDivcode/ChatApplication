package com.niit.collaboration.dao.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.collaboration.model.Friend;
import com.niit.collboration.dao.FriendDAO;

@Transactional
@Repository(value="friendDAO")
public class FriendDaoImpl implements FriendDAO {
	
	private static final Logger log=LoggerFactory.getLogger(FriendDaoImpl.class);
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	public FriendDaoImpl(){
		
	}
	
	public FriendDaoImpl(SessionFactory sessionFactory){
		try{
		    this.sessionFactory = sessionFactory;
		    log.debug("Connecting to db");
		}
		catch(Exception e)
		{
			log.error("unable to connect the DB");
			e.printStackTrace();
			
		}
	}
	
	@Override
	@Transactional
	public boolean save(Friend friend) {
		try{
			log.debug("......previous id:"+getMaxid());
			friend.setId(getMaxid()+1);
			log.debug("......generated id:"+getMaxid());
			sessionFactory.getCurrentSession().save(friend);
			return true;
			
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Friend friend) {
	
		return false;
	}

	@Override
	@Transactional
	public void delete(String userID, String friendID) {
	     Friend friend=new Friend();
	     friend.setFriendID(friendID);
	     friend.setUserID(userID);
	     sessionFactory.openSession().delete(friend);
		
	}

	@Override
	@Transactional
	public List<Friend> getNewFriendRequest(String userID) {
		 String hql="from Friend where userID= " + "'" + userID + "' and status='"+"N'";
	     Query query=sessionFactory.openSession().createQuery(hql);
	     List<Friend> list=(List<Friend>)query.list();
		 return list;
	}

	@Override
	@Transactional
	public void setOnline(String userID) {
	  log.debug("starting of the method setonline");
	  String hql="UPDATE FRIEND SET isOnline='Y' where frienID='"+ userID + "'";
	  log.debug("hql:"+hql);
	  Query query=sessionFactory.openSession().createQuery(hql);
	  query.executeUpdate();
	  log.debug("Ending of the method setonline");
		
	}

	@Override
	@Transactional
	public void setOffLine(String userID) {
		log.debug("starting of the method setoffline");
		String hql="UPDATE FRIEND SET isOnline='N' where frienID='"+ userID + "'";
		log.debug("hql:"+hql);
		Query query=sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the method setoffline");
		
	}

	@Override
	@Transactional
	public List<Friend> getMyFriends(String userID) {
	     String hql="from Friend where userID= " + "'" + userID +"or friendID=" + userID + "' and status='" + "A'";
	     Query query=sessionFactory.openSession().createQuery(hql);
	     List<Friend> list=(List<Friend>)query.list();
		 return list;
	}

	@Override
	@Transactional
	public Friend get(String userID, String friendID) {
		 String hql="from Friend where userID= " + "'" + userID + "' and friendID ='" + friendID + "'";
		 log.debug("hql:"+hql);
		 Query query=sessionFactory.openSession().createQuery(hql);
		 return(Friend)query.uniqueResult();
	}

	@Override
	@Transactional
	public int getMaxid() {
		log.debug("starting of the method maxid");
		String hql="select max(id) from Friend";
		Query query=sessionFactory.openSession().createQuery(hql);
		Integer maxid;
		try{
			
			maxid=(Integer)query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
			return 100;
			
		}
		log.debug("max id:"+maxid);
		return maxid;
		
	}
	

}
