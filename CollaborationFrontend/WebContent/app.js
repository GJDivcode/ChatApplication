
/*(function () {
 'use strict';

 angular
 .module('myApp', ['ngRoute', 'ngCookies'])
 .config(config)
 .run(run);

 config.$inject = ['$routeProvider', '$locationProvider'];
 function config($routeProvider, $locationProvider) {
 $routeProvider
 .when('/', {

 templateUrl:'Home/home.html'

 })


 .when('/login', {
 controller: 'UserController',
 templateUrl: 'User/login.html'

 })

 .when('/register', {
 controller: 'UserController',
 templateUrl: 'User/register.html'

 })

 .otherwise({ redirectTo: '/login' });
 }

 run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
 function run($rootScope, $location, $cookies, $http) {
 // keep user logged in after page refresh
 $rootScope.globals = $cookies.getObject('globals') || {};
 if ($rootScope.globals.currentUser) {
 $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
 }

 $rootScope.$on('$locationChangeStart', function (event, next, current) {
 // redirect to login page if not logged in and trying to access a restricted page
 var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
 var loggedIn = $rootScope.globals.currentUser;
 if (restrictedPage && !loggedIn) {
 $location.path('/login');
 }
 });
 }

 })();

 */

var app = angular.module('myApp', [ 'ngRoute'])

app.config(function($routeProvider) {
	console.log('entering configuration')
	$routeProvider

	.when('/', {
		templateUrl : 'Home/home.html'
		

	})

	.when('/login', {
		controller : 'UserController',
		templateUrl : 'User/login.html'

	})

	.when('/register', {
		templateUrl : 'User/register.html',
		controller : 'UserController'
		

	})

	.when('/manageUser', {
		templateUrl : 'Admin/manage_users.html',
		controller : 'AdminController'

	})

	.when('/about', {
		templateUrl : 'About/about.html',
		controller : 'AboutController'

	})

	.when('/myProfile', {
		templateUrl : 'User/my_profile.html',
		controller : 'UserController'

	})

	/*
	 * / * ............Blog Mapping............
	 */

	.when('/create_blog', {
		templateUrl : 'Blog/create_blog.html',
		controller : 'BlogController'

	})

	.when('/list_blog', {
		templateUrl : 'Blog/list_blog.html',
		controller : 'BlogController'

	})

	.when('/view_blog', {
		templateUrl : 'Blog/view_blog.html',
		controller : 'BlogController'

	})

	.when('/add_friend', {
		templateUrl : 'Friend/add_friend.html',
		controller : 'FriendController'

	})

	.when('/search_friend', {
		templateUrl : 'Friend/search_friend.html',
		controller : 'FriendController'

	})

	.when('/view_friend', {
		templateUrl : 'Friend/view_friend.html',
		controller : 'FriendController'

	})

	.otherwise({
		redirectTo : '/'

	});

})

/*app.run(function($rootScope, $location, $cookieStore, $http) {
	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		console.log("$locationChangeStart")
		var restrictedpage = $.inArray($location.path(), [ '/', '/search_job',
				'/view_blog', '/login' ])==-1;

		console.log("restrictedpage:" + restrictedpage)
		console.log("loggedIn:" + loggedIn)

		var loggedIn = $rootScope.currentUser.userid;
		if (!loggedIn) {
			if (restrictedpage) {
				console.log("Navigating to login Page:")
				$location.path('/login');
			}
		} else {
			var role = $rootScope.currentUser.role;
			var userRestricedPage = $
					.inArray($location.path(), [ "/post_job" ]) == 0;

			if (userRestricedPage && role != 'admin') {
				alert("you can not do this operation as you are logged as:"
						+ role)
				$location.path('/login');
			}
		}
	});

	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic'
				+ $rootScope.currrentUser;
	}

});*/



/*app.run( function($rootScope,$location,$cookieStore,$http){
    
    $rootScope.$on('$locationChangeStart',function(event,next,current){
        console.log("$locationChangeStart")
        var restrictedpage = $.inArray($location.path(), [ '/', '/search_job',
				'/view_blog', '/login', '/register' ])==-1;

        var restrictedPage=$.inArray($location.path(),['/login', '/userrole', '/userprofile', '/addjob','/addblog'])== -1;
        console.log("restrictedpage ;"+restrictedPage)
        var loggedIn=$rootScope.currentUser;
        console.log("loggedin:"+loggedIn)
        if(restrictedPage & !loggedIn){
            console.log("navigation to login page")
            $location.path('/home');
        }
        
    });
    
    $rootScope.currentUser=$cookieStore.get('currentUser')||{};
    if($rootScope.currentUser){
        $http.defaults.headers.common['Authorization']= 'Basic' + $rootScope.currentUser;
    }
    
});*/
