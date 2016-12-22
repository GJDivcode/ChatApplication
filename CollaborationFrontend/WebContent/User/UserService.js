'use strict'

app.factory('UserService', [ '$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {

			console.log("UserService....")
			var BASE_URL = "http://localhost:8083/CollaborationBackend"
				
				return{
				fetchAllUsers:function(){
					console.log("calling FetchAllusers")
					return $http.get(BASE_URL+'/users')
					.then(
					    function(response){
					    	return response.data;

					    },
					    function(errResponse){
					    	console.error('error while fetching the userDetails');
					    	return $q.reject(errResponse);
					    }
					);
					
				},
				
				myProfile:function(){
					console.log("Calling the myprofile")
					return $http.get(BASE_URL+'/myProfile')
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error  while fetching users');
								return $q.reject(errResponse);
							}
							
					);
					
				},
				
				accept:function(userid){
					console.log("calling for the Approvel");
					return $http.get(BASE_URL+'/accept/'+userid)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error  while accept Registration');
								return $q.reject(errResponse);
							}
							
					);
					
				},
				
				reject:function(userid,reason){
					console.log("calling for the reject");
					return $http.get(BASE_URL+'/reject/'+userid+'/'+reason)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error  while reject Registration');
								return $q.reject(errResponse);
							}
							
					);
					
				},
				
				createUser:function(user){
					console.log("calling the users");
					return $http.post(BASE_URL+'/user/',user)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while creating the user');
								return $q.reject(errResponse);
							}
							
							);
					
				},

				updateUser:function(user,userid){
					console.log("calling the fetchallusers");
					return $http.put(BASE_URL+'/user/',user)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while updating the user');
								return $q.reject(errResponse);
							}
							
							);
					
				},
				
				logout:function(){
					console.log('logout...')
					return $http.get(BASE_URL+'/user/logout')
					.then(
					    function(response){
					    	return response.data;
					    },
					    null
					
					);
				},
				authenticate:function(user){
					console.log("calling the method authenticate with the user:"+user)
					return $http.post(BASE_URL+'/user/authenticate/',user)
					.then(
							function(response){
								return response.data;
							},
							null
							
					);
					
				},
					
			}

		}

]);