'use strict';

app
		.controller(
				'UserController',
				[
						'$scope',
						'UserService',
						'$location',
						'$rootscope',
						'$cookiesStore',
						'$http',

						function($scope, UserService, $location, $rootscope,
								$cookiesStore, $http) {
							console.log("UserController....")
							var self = this;

							self.user = {
								userid : '',
								name : '',
								password : '',
								mobile : '',
								email : '',
								address : '',
								isOnline : '',
								role : '',
								errorCode : '',
								errorMessage : ''
							};
							self.users = [];

							/*$scope.orderbyMe = function(x) {
								$scope.myOrderBy = x;
							}*/

							self.fetchAllUsers = function() {
								console.log("fetchAllUsers.....")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};

							/*
							 * now for create users
							 */
							self.createUser = function(user) {
								console.log("createUser...")
								UserService
										.createUser(user)
										.then(
												self.reset,
												function(errResponse) {
													console
															.error('error while creating users');
												});
							};
							/*
							 * select profile
							 */

							self.myProfile = function() {
								console.log("my Profile>>....")
								UserService.myProfile().then(function(d) {
									self.user = d;
									$location.path("/myProfile")
								}, function(errResponse) {
									console.error('Error while fetch Profile');

								});
							}

								/*
								 * Accept the users
								 */
								self.accept = function(userid) {
									console.log("Accept....")
									UserService
											.accept(userid)
											.then(
													function(d) {
														self.user = d;
														self.fetchAllUsers
														$location
																.path("/manage_users")
														alert(self.user.errorMessage)
													},
													function(errResponse) {
														console
																.error('error while updating users');
													});

								};

								/*
								 * Reject the users...
								 */
								/*self.reject = function(userid) {
									console.log("reject....")
									var reason = prompt("Please enter the  Reason");
									UserService
											.reject(userid, reason)
											.then(
													function(d) {
														self.user = d;
														self.fetchAllUsers
														$location
																.path("/manage_users")
														alert(self.user.errorMessage)

													},
													function(errResponse) {
														console
																.error('Error while updating users...')
													});
								};
*/
								/*
								 * .........Update Users.......
								 */
								/*self.updateUser = function(user, userid) {
									console.log("UpdateUser.....")
									UserService
											.updateUser(user, userid)
											.then(
													self.fetchAllusers,
													function(errResponse) {
														console
																.error('error while updating the users');
													});

								};*/

								/*
								 * authenticate the users....
								 */
								/*self.authenticate = function(user) {
									console.log("authenticate")
									UserService
											.authenticate(user)
											.then(
													function(d) {
														self.user = d;
														console
																.log("user.errorCode:"
																		+ self.user.errorCode)
														if (self.user.errorCode == "404") {
															alert(self.user.errorMessage)
															self.user.userid = "";
															self.user.password = "";
														} else {
															console
																	.log("Valid Credentials");
															$rootScope.currentUser = self.user
															$http.defaults.headers.common['Authorization'] = 'Basic'
																	+ $rootScope.currentUser;
															$cookieStore
																	.put(
																			'currentUser',
																			$rootScope.currentUser);
															$location.path('/');

														}

													});

								};*/
								
								self.loginclick = function() {
									self.authenticate(self.user);
								};
								
								
								self.submit=function(){
									console.log('saving New User',self.user);
									self.createUser(self.user);
								};
								
								self.reset = function() {
									self.user = {
											userid : '',
											name : '',
											password : '',
											mobile : '',
											email : '',
											address : '',
											isOnline : '',
											role : '',
											errorCode : '',
											errorMessage : ''
									};
									$scope.myForm.$setPristine(); // reset Form
								}
								
								
								/*
								 * logout users.....
								 */

								self.logout = function() {
									console.log("logout")
									$rootScope.currentUser = {};
									$cookieStore.remove('currentUser');
									UserService.logout()
									$location.path('/')
								}

							

						}

				]);