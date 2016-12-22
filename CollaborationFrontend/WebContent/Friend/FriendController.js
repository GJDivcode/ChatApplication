'use strict'

app.controller('FriendController', [ 'UserService', '$scope', 'FriendService',
		'$location','$rootScope',function(UserService,$scope,FriendService,$location,$routeParams,$rootScope){
	     console.log("FriendController...")
	     var self=this;
	     self.friend={id:'',userID:'',friendID:'',status:''};
	     self.friends=[];
	     
	     
	     self.user={
	    		 userid:'',
	    		 name:'',
	    		 password:'',
	    		 mobile:'',
	    		 address:'',
	    		 email:'',
	    		 isOnline:'',
	    		 role:'',
	    		 errorMessage:''
	    		 
	     };
	     self.users=[];
	     
	     self.getMyFriendRequests=function(){
	    	 FriendService.getMyFriendRequests()
	    	 .then(
	    	     function(d)
	    	     {
	    	    	 self.friends=d;
		    	     $location.path="/viewFriendRequest";	 
	    	     },
	    	     function(errResponse){
	    	    	 console.error('Error while updating the Friend');
	    	     }
	    	     
	    	 );
	    	 
	     };
	     
	     self.sendFriendRequest=sendFriendRequest;
	     
	     function sendFriendRequest(friendID){
	    	 console.log("calling friendRequest:"+friendID)
	    	 FriendService.sendFriendRequest(friendID)
	    	 .then(
	    	         function(d){
	    	        	 self.friend=d;
	    	        	 alert("Friend Request sent" + self.friend.errorMessage)
	    	         },
	    	         function(errResponse){
	    	        	 console.error('error while sending the friend Request');
	    	         }
	    	         
	    	 );
	    	 
	     };

          self.getMyFriends=function(){
        	  console.log("Getting the my Friends")
        	  FriendService.getMyFriends()
        	  .then(
        			  function(d){
        				  self.friends=d;
        				  console.log("Got the Friend list"+self.friends)
        				  
        			  },
        			  function(errResponse){
        				  console.error('error while fetching the Friends');
        			  }
        			  
        	  );
        	  
          };
          
          self.updateFriendRequest=function(friend,id){
        	  FriendService.updateFriendRequest(friend,id)
        	  .then(
        			  self.fetchAllFriends,
        			  function(errResponse){
        				  consile.error('error while updating the Friend');
        			  }
        			  );
        	  
          };
          
          self.acceptFriendRequest=function(id){
        	  FriendService.acceptFriendRequest(id)
        	  .then(
        		self.fetchAllFriends,
        		function(errResponse){
        			console.error('Error while acceptFriendRequest');
        		}
        	  );
        	  
          };
          
          self.rejectFriendRequest=function(id){
        	  FriendService.acceptFriendRequest(id)
        	  .then(
        		self.fetchAllFriends,
        		function(errResponse){
        			console.error('Error while rejectFriendRequest');
        		}
        	  );
        	  
          };
          
          
          self.unFriend=function(id){
        	  FriendService.unFriend(id)
        	  .then(
        			  self.fetchAllFriends,
        			  function(errResponse){
        				  console.error('Error while UnFriend');
        			  }
        	  )
        	  
          };
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	
}

])