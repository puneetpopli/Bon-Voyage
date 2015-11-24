var hangoutamigosapp = angular.module('hangoutamigosapp', [ 'ngRoute', 'ngResource']);

hangoutamigosapp.run(function($rootScope) {
	$rootScope.hideUserNavTabs = true;
	$rootScope.hideStaticTabs = false;
});

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
	
});


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
					$scope.success = "User Added Successfully";
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

