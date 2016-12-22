
var app = angular.module('webapp', []);

app.controller('BlogListController', ['$http', '$scope', function($http, $scope) {
	var self = this;
	self.blogs = {};
	self.error = '';
	
	
	$scope.refreshBlogList = function() { 		
		$http.get('/collaboration/blogs/' + userId)
		.then(function mySuccess(response){
			self.blogs = response.data;
		}, function myError(response) {
			self.error = response.statusText;
		}
		)
	}			
		
	$scope.refreshBlogList();	
	
	
	$scope.removeBlog = function(id) {
		if(confirm('Are you sure you want to remove this blog?')) {
				$http["delete"]('/collaboration/user/remove/blog/'+id)
				.then(function mySuccess(response){
					alert('Blog is deleted!');
					$scope.refreshBlogList();
				});
		}
	}
		
	
}]);


app.controller('PublicBlogListController', ['$http', function($http) {
	var self = this;
	self.blogs = {};
	self.error = '';
	
	$http.get('/collaboration/blogs/all')
		.then(function mySuccess(response){
			self.blogs = response.data;
		}, function myError(response) {
			self.error = response.statusText;
		}
	)
	
}]);
 


app.controller('BlogController', ['$http', function($http) {
	var self = this;
	self.blog = {};
	self.error = '';
	
	$http.get('/collaboration/blog/' + blogId)
		.then(function mySuccess(response){
			self.blog = response.data;
		}, function myError(response) {
			self.error = response.statusText;
		}
	)	
}]);
