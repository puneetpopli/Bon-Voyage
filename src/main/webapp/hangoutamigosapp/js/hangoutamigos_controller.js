var hangoutamigosapp = angular.module('hangoutamigosapp', [ 'ngRoute', 'ngResource']);



hangoutamigosapp.factory('dataSharing', function() {

	var textResult = [];
	var lat = [];
	var lng = [];


	return {
		setLat: function (latitude) {
			lat.push(latitude);
		},

		setLng: function (longitude) {
			lng.push(longitude);
		}, 

		setTextResult: function (result) {
			textResult.push(result);
		},

		getLat: function () {
			return lat;
		},

		getLng: function () {
			return lng;
		},

		getTextResult: function () {
			return textResult;
		}	
	};

//	return {
//	set : set,
//	get : get
//	}

});


hangoutamigosapp.config(function($routeProvider) {

	$routeProvider

	.when('/', {
		templateUrl : 'home.html',
		controller : 'homeController'
	})

	.when('/', {
		templateUrl : 'home.html',
		controller : 'loginController'
	})

	.when('/', {
		templateUrl : 'home.html',
		controller : 'registerController'
	})


	.when('/', {
		templateUrl : 'home.html',
		controller : 'searchController'
	})


//	.when('/', {
//	templateUrl : 'index.html',
//	controller : 'searchController'
//	})


	.when('/search', {
		templateUrl : 'search.html',
		controller : 'restaurantController'
	})

});


hangoutamigosapp.controller('homeController',function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {


	console.log('homeController start');

	console.log('homeController end');
});



//Sign up
hangoutamigosapp.controller('registerController',function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {


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
			//$location.url('/index.html');
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
hangoutamigosapp.controller('searchController', function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {

//	$rootScope.textResult = [];
//	$rootScope.lat = [];
//	$rootScope.lng = [];


	console.log('searchController start');

	$scope.searchform_search = function() {

		if(!$scope.search) {
			alert('Please enter the search query');
		}
		else {

			var options = {
					method: 'GET',
					url: '../../hangoutamigos/getplace/textsearch/'+$scope.search,

			}

			$http(options).then(function successCallback(response) {
				var length = JSON.stringify(response.data.length);
				var latitude;
				var longitude;
				for(var i=0; i<length; i++) {

					console.log("inside for");
					dataSharing.setLng(response.data[i].geometry.location.lng);

					dataSharing.setLat(response.data[i].geometry.location.lat);


					longitude = dataSharing.getLng();
					latitude = dataSharing.getLat();
					console.log(longitude);
					console.log(latitude);
				}

				console.log(length);

			}, function errorCallback(response) {

			});
			$location.path('/search');
			//window.location.href = "/hangoutamigosapp/search.html";

		} //else ends
	};

	console.log('searchController end');

});


//View Restaurant
hangoutamigosapp.controller('restaurantController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope, $resource) {

	console.log('restaurantController start');

	//for restaurant
	$rootScope.rating = [];
	$rootScope.restaurantName = [];
	$rootScope.vicinity = [];
	$rootScope.placeId = [];
	$rootScope.restaurantLat = [];
	$rootScope.restaurantLng = [];
	$rootScope.icon = [];


	//for near by places
	$rootScope.placeName = [];
	$rootScope.placeVicinity = [];
	$rootScope.nearByPlaceId = [];
	$rootScope.placeLat = [];
	$rootScope.placeLng = [];
	$rootScope.placeIcon = [];

	//function to get the restaurant
	$scope.restaurant_result = function() {

		var temparr = [];
		temparr=dataSharing.getLng();		

		var temparr1 = [];
		temparr1 = dataSharing.getLat();

		var restaurant = 'restaurant';
		var latitude;
		var longitude;


		longitude = temparr[0];
		console.log('longitude ' + longitude);

		latitude = temparr1[0];
		console.log('Latitude '+latitude)

		//var requestURL = '../../hangoutamigos/getplaces/'+latitude+'/'+longitude+'/'+500+'/type/'+restaurant;
		//console.log(requestURL);


		var options1 = {
			method: 'GET',
			url: '../../hangoutamigos/getplaces/'+latitude+'/'+longitude+'/'+500+'/type/'+restaurant,
		}



		$http(options1).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);

			console.log(response.data);

			for(var i=0; i<4; i++) {

				if($rootScope.rating[i]!==null) {
					$rootScope.rating[i] = 3.4;
					$rootScope.restaurantLat[i] = response.data[i].geometry.location.lat;
					$rootScope.restaurantLng[i] = response.data[i].geometry.location.lng;
					$rootScope.restaurantName[i] = response.data[i].name;
					$rootScope.vicinity[i] = response.data[i].vicinity;
					$rootScope.placeId[i] = response.data[i].place_id;
					$rootScope.icon[i] = response.data[i].icon;
					console.log('Rating ' + response.data[i].rating);
				}
				else {
					$rootScope.rating[i] = response.data[i].rating;
					$rootScope.restaurantName[i] = response.data[i].name;
					$rootScope.vicinity[i] = response.data[i].vicinity;
					$rootScope.placeId[i] = response.data[i].place_id;
					$rootScope.icon[i] = response.data[i].icon;
					console.log('Rating ' + response.data[i].rating);
				}
			}

			//To check the values on console
			/*
			for(var j=0; j<$rootScope.rating.length; j++) {
				console.log($rootScope.restaurantLng[j]);
				console.log($rootScope.restaurantLat[j]);
				console.log($rootScope.rating[j]);
				console.log($rootScope.restaurantName[j]);
				console.log($rootScope.vicinity[j]);
				console.log($rootScope.placeId[j]);
			}
			 */

		}, function errorCallback(response) {

		});

	};

	/************************************************************/

	//function to get the near by place
	$scope.neayByPlace_result = function() {

		var placeLngArr = [];
		placeLngArr = dataSharing.getLng();		

		var placeLatArr = [];
		placeLatArr = dataSharing.getLat();

		var museum = 'museum';
		var park = 'park';
		var placeLatitude;
		var placeLongitude;


		placeLongitude = placeLngArr[0];
		console.log('Place longitude ' + placeLongitude);

		placeLatitude = placeLatArr[0];
		console.log('Place Latitude '+placeLatitude)

		var options2 = {
				method: 'GET',
				url: '../../hangoutamigos/getplaces/'+placeLatitude+'/'+placeLongitude+'/'+5000+'/type/'+museum,
		}

		$http(options2).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);

			console.log(response.data);

			for(var i=0; i<4; i++) {
				
				$rootScope.nearByPlaceId[i] = response.data[i].place_id;
				$rootScope.placeName[i] = response.data[i].name;
				$rootScope.placeIcon[i] = response.data[i].icon;
				$rootScope.placeVicinity[i] = response.data[i].vicinity;
				$rootScope.placeLat[i] = response.data[i].geometry.location.lat;
				$rootScope.placeLng[i] = response.data[i].geometry.location.lng;


			}
			
			
			//To check the values on console
			
			/*for(var j=0; j<9; j++) {
				console.log($rootScope.nearByPlaceId[j]);
				console.log($rootScope.placeName[j]);
				console.log($rootScope.placeIcon[j]);
				console.log($rootScope.placeVicinity[j]);
				console.log($rootScope.placeLat[j]);
				console.log($rootScope.placeLng[j]);
				
			}*/
			 

		}, function errorCallback(response) {

		});
		
		/******** To get all the near by parks *************/
		
		var options3 = {
				method: 'GET',
				url: '../../hangoutamigos/getplaces/'+placeLatitude+'/'+placeLongitude+'/'+5000+'/type/'+park,
		}

		$http(options3).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);

			console.log(response.data);

			for(var i=5; i<9; i++) {
				
				$rootScope.nearByPlaceId[i] = response.data[i].place_id;
				$rootScope.placeName[i] = response.data[i].name;
				$rootScope.placeIcon[i] = response.data[i].icon;
				$rootScope.placeVicinity[i] = response.data[i].vicinity;
				$rootScope.placeLat[i] = response.data[i].geometry.location.lat;
				$rootScope.placeLng[i] = response.data[i].geometry.location.lng;


			}
			 

		}, function errorCallback(response) {

		});
		
		
		/*************************/
		

	};
	console.log('restaurantController end');

});
