'use strict'

app.factory('FriendService',['$http','$q','$rootScope',function($http,$q,$rootScope){
	
	console.log("FrienService...")
	var BASE_URL='http://localhost:8081/CollaborationBackend'
		return{
		
		getMyFriends:function(){
			return $http.get(BASE_URL+'/myFriends')
			.then(
			        function(response){
			        	return response.data;
			        	
			        },
			        null
			);
			
		},
		
		sendFriendRequest:function(friendID){
			return $http.get(BASE_URL+'/addFriend/'+friendID)
			.then(
			      function(response){
			    	/*  fi(response.data.errorCode==404){
			    		  alert(response.data.errorMessage)
			    	  }
			    	*/  return response.data;
			    	  
			      },
			      function(errResponse){
			    	  console.error('error while Creating the Friend');
			    	  return $q.reject(errResponse);
			      }
			      
			);
			
		},
		
		getMyFriendRequests:function(){
			return $http.get(BASE_URL+'/getMyFriendRequests/')
			.then(
			      function(response){
			    	  return response.data;
			    	  
			      },
			      function(errResponse){
			    	  console.error('error while Creating the Friend');
			    	  return $q.reject(errResponse);
			      }
			      
			);
			
		},
		
		unFriend:function(){
			return $http.get(BASE_URL+'/getMyFriendRequests/')
			.then(
			      function(response){
			    	  return response.data;
			      },
			      function(errResponse){
			    	  console.error('error while creating the friend');
			    	  return $q.reject(errResponse);
			    	  
			      }
			);
			
		},
		
		
		deleteFriend:function(id){
			return $http.delete(BASE_URL+'/friend/'+id)
			.then(
			       function(response){
			    	   return response.data;
			       },
			       function(errResponse){
			    	   console.error('Error while deleting Friend');
			    	   return $q.reject(errResponse);
			       }
					
			);
			
		},
		
		acceptFriendRequest:function(){
			return $http.get(BASE_URL+'/acceptFriend/')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating the friend');
						return $q.reject(errResponse);
					}
					 
			);
			
		},
		
		rejectFriendRequest:function(friendID){
			return $http.get(BASE_URL+'/getMyFriendRequests/'+friendID)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('error while creating the friend');
						return $q.reject(errResponse);
					}
					 
			);
			
		},
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
                             
                             
]);