var hangoutamigosapp = angular.module('hangoutamigosapp', [ 'ngRoute', 'ngResource']);



hangoutamigosapp.factory('dataSharing', function() {
	var sharedData = {}
	function set(data) {
		sharedData = data;
	}
	function get() {
		return sharedData;
	}

	return {
		set : set,
		get : get
	}

});


hangoutamigosapp.config(function($routeProvider) {
	
	$routeProvider
	
	.when('/', {
		templateUrl : 'index.html',
		controller : 'registerController'
	})
	
	.when('/', {
		templateUrl : 'index.html',
		controller : 'loginController'
	})
	
	.when('/', {
		templateUrl : 'index.html',
		controller : 'searchController'
	})
	
});

//Sign up
hangoutamigosapp.controller('registerController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	

	console.log('registerController start');

	$scope.signupform_signup = function(fn, ln, em, pass) {
				
		var data = {
				firstName : fn,
				lastName : ln,
				email : em,
				password : pass,
			};
		
		var reqObj = JSON.stringify(data);
		console.log(reqObj);
		
		var response = $http.post("../../hangoutamigos/createuser", reqObj,{});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					//$scope.success = "User Added Successfully";
				});
		response.error(function(data, status, headers, config) {
			console.log(data.errorMessage);
			console.log(status);
			if(status === 400){
				$scope.error = data.errorMessage;
			}
			return $q.reject(response);
		});	

	};
	console.log('registerController end');
});


//Login
hangoutamigosapp.controller('loginController', function($scope, $http, $location, $q,
		dataSharing, $timeout, $rootScope) {
	console.log('loginController start');

	
	
	$scope.loginform_login = function(em1, pass1) {
		console.log("--> Submitting form "
				+ $scope.loginform_email + " "
				+ $scope.loginform_password);
		console.log("--> Submitting form ");
		
		
		var data = {
			email : em1,
			password : pass1
		};
		
		var requestObj = JSON.stringify(data);
		console.log(requestObj);

		var response = $http.post("../../hangoutamigos/login", requestObj, {});
		response
				.success(function(dataFromServer, status,
						headers, config) {
					$location.url('/home');
				});
		response.error(function(data, status, headers, config) {
			console.log(data.errorMessage);
			console.log(status);
			if(status === 400){
				$scope.error = data.errorMessage;
			}
			return $q.reject(response);
		});
	};

	$scope.clickRegister = function() {
		$location.url('/register');
	}

	console.log('loginController end');
});


//Search Controller
hangoutamigosapp.controller('searchController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {
	
	$rootScope.textResult = [];
	$rootScope.lat = [];
	$rootScope.lng = [];
	

	console.log('searchController start');

	$scope.searchform_search = function(searchQuery) {
		
		
		var options = {
				 method: 'GET',
				 url: '../../hangoutamigos/getplace/textsearch/'+searchQuery,
				 
		}
		
		$http(options).then(function successCallback(response) {
		    
			
			var length = JSON.stringify(response.data.length);
			
			for(var i=0; i<length; i++) {
				
				//Storing latitude in an array
				$rootScope.lat[i] = response.data[i].geometry.location.lat;
				console.log($rootScope.lat[i]);
				
				//storing longitude in an arrya
				$rootScope.lng[i] = response.data[i].geometry.location.lng;
				console.log($rootScope.lng[i]);
				
				//storing json response in an array
				$rootScope.textResult[i] = response.data[i];
				console.log($rootScope.textResult[i]);
			}
			
			console.log(length);
			
		  }, function errorCallback(response) {
		    
		  });

	};
	console.log('registerController end');
});