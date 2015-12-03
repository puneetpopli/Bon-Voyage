var hangoutamigosapp = angular.module('hangoutamigosapp', [ 'ngRoute', 'ngResource']);




//directive

hangoutamigosapp.directive('hcPie', function () {
	return {
		restrict: 'C',
		replace: true,
		scope: {
			items: '='
		},
		controller: function ($scope, $element, $attrs) {


		},
		template: '<div id="container" style="height: 400px; width: 450px;"></div>',
		link: function (scope, element, attrs) {

			var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'container',
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false
				},
				title: {
					text: 'Restaurant Ratings'
				},
				/*tooltip: {
		          pointFormat: '{series.name}: <b>{point.percentage}%</b>',
		          percentageDecimals: 1
		        },*/
				plotOptions: {
					column: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
							enabled: true,
							color: '#000000',
							connectorColor: '#000000',
							/* formatter: function () {
		                return '<b>' + this.point.name + '</b>: ' + this.percentage + ' %';
		              }*/
						}
					}
				},
				series: [{
					type: 'column',
					name: 'Rating',
					data: scope.items
				}]
			});
			scope.$watch("items", function (newValue) {
				chart.series[0].setData(newValue, true);
			}, true);

		}
	}
});



//directive




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

});


hangoutamigosapp.config(function($routeProvider) {


	$routeProvider

	.when('/', {
		templateUrl : 'home.html',
		controller : 'homeController'
	})

	.when('/search', {
		templateUrl : 'search.html',
		controller : 'restaurantController'
	})

	.when('/itinerary', {
		templateUrl : 'itinerary.html',
		controller : 'itineraryController'
	});

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
			$scope.success = "Thanks for signing up Amigo!"
				$location.url('/');

		});
		response.error(function(data, status, headers, config) {
			console.log(data.errorMessage);
			console.log(status);
			if(status === 400 || status === 500){
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
			$scope.success = "Login Successful!"

		});
		response.error(function(data, status, headers, config) {
			console.log(data.errorMessage);
			console.log(status);
			if(status === 400 || status === 500){
				$scope.error = data.errorMessage;
			}
			return $q.reject(response);
		});
	};



	console.log('loginController end');
});


//Search Controller
hangoutamigosapp.controller('searchController', function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {

	console.log('searchController start');

	var error = 'Please enter a valid place to Hangout!'

		$scope.searchform_search = function() {

		if(!$scope.search) {
			//alert('Please enter the search query');
			$scope.errormsg = error;
		}
		else {
			$rootScope.search = $scope.search;
			$location.path('/search');
		}
	};

	console.log('searchController end');

});


//Proceed to Itinerary page Controller
hangoutamigosapp.controller('proceedController', function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {

	console.log('proceedController start');

	$scope.proceed = function() {

		//$rootScope.search = $scope.search;
		$location.path('/itinerary');

	};

	console.log('proceedController end');

});


//Itinerary page Controller
hangoutamigosapp.controller('itineraryController', function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {

	console.log('itineraryController start');


	$scope.go = function(from, to) {

		$rootScope.from = from;
		$rootScope.to = to;
		$scope.showItineraryDiv = true;
		console.log('from ' + $rootScope.from);

	}

	/*
	 * displaying restaurant thumbnail
	 */
	var len = $rootScope.resultName.length;


	if(len == 1) {
		$scope.showThumbanil0 = true;
		$scope.td1 = true;
	}
	else if(len == 2) {
		$scope.showThumbanil0 = true;
		$scope.showThumbanil1 = true;
		
		$scope.td1 = true;
		$scope.td2 = true;
	}
	else if(len == 3) {
		$scope.showThumbanil0 = true;
		$scope.showThumbanil1 = true;
		$scope.showThumbanil2 = true;
		
		$scope.td1 = true;
		$scope.td2 = true;
		$scope.td3 = true;
	}
	else if(len == 4) {
		$scope.showThumbanil0 = true;
		$scope.showThumbanil1 = true;
		$scope.showThumbanil2 = true;
		$scope.showThumbanil3 = true;
		
		$scope.td1 = true;
		$scope.td2 = true;
		$scope.td3 = true;
		$scope.td4 = true;
	}
	else if(len == 5) {
		$scope.showThumbanil0 = true;
		$scope.showThumbanil1 = true;
		$scope.showThumbanil2 = true;
		$scope.showThumbanil3 = true;
		$scope.showThumbanil4 = true;
		
		$scope.td1 = true;
		$scope.td2 = true;
		$scope.td3 = true;
		$scope.td4 = true;
		$scope.td5 = true;
	}
	//len==6
	else if(len==6){
		$scope.showThumbanil0 = true;
		$scope.showThumbanil1 = true;
		$scope.showThumbanil2 = true;
		$scope.showThumbanil3 = true;
		$scope.showThumbanil4 = true;
		$scope.showThumbanil5 = true;
		
		$scope.td1 = true;
		$scope.td2 = true;
		$scope.td3 = true;
		$scope.td4 = true;
		$scope.td5 = true;
		$scope.td6 = true;
	}

	/*
	 * displaying places thumbnail
	 */
	var len1 = $rootScope.resultPlaceName.length;

	if(len1 == 1) {
		$scope.showThumbanil7 = true;
		
		$scope.td7 = true;
	}
	else if(len1 == 2) {
		$scope.showThumbanil7 = true;
		$scope.showThumbanil8 = true;
		
		$scope.td7 = true;
		$scope.td8 = true;
	
	}
	else if(len1 == 3) {
		$scope.showThumbanil7 = true;
		$scope.showThumbanil8 = true;
		$scope.showThumbanil9 = true;
		
		$scope.td7 = true;
		$scope.td8 = true;
		$scope.td9 = true;
	
	}
	else if(len1 == 4) {
		$scope.showThumbanil7 = true;
		$scope.showThumbanil8 = true;
		$scope.showThumbanil9 = true;
		$scope.showThumbanil10 = true;
		
		$scope.td7 = true;
		$scope.td8 = true;
		$scope.td9 = true;
		$scope.td10 = true;
	}
	else if(len1 == 5) {
		$scope.showThumbanil7 = true;
		$scope.showThumbanil8 = true;
		$scope.showThumbanil9 = true;
		$scope.showThumbanil10 = true;
		$scope.showThumbanil11 = true;
		
		$scope.td7 = true;
		$scope.td8 = true;
		$scope.td9 = true;
		$scope.td10 = true;
		$scope.td11 = true;
	
	}

	else if(len1 == 6){
		$scope.showThumbanil7 = true;
		$scope.showThumbanil8 = true;
		$scope.showThumbanil9 = true;
		$scope.showThumbanil10 = true;
		$scope.showThumbanil11 = true;
		$scope.showThumbanil12 = true;
		
		$scope.td7 = true;
		$scope.td8 = true;
		$scope.td9 = true;
		$scope.td10 = true;
		$scope.td11 = true;
		$scope.td12 = true;
	}

	console.log('itineraryController end');

});




//Click on image controller
hangoutamigosapp.controller('imageController', function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope) {

	console.log('imageController start');

	$scope.imageClick = function(search) {
		$scope.search = search;
		$rootScope.search = $scope.search;
		console.log($rootScope.search);
		$location.path('/search');
	};

	console.log('imageController end');

});



//View Restaurant
hangoutamigosapp.controller('restaurantController',
		function($scope, $http, $location, $q, dataSharing, $timeout, $rootScope, $resource, limitToFilter) {

	console.log('restaurantController start');






	//for restaurant
	//storing the entire result of the restaurant search
	$rootScope.rating1 = [];
	$rootScope.restaurantName1 = [];
	$rootScope.vicinity1 = [];
	$rootScope.placeId1 = [];
	$rootScope.restaurantLat1 = [];
	$rootScope.restaurantLng1 = [];
	$rootScope.icon1 = [];

	//storing only 5 values and values which are not undefined
	$rootScope.rating = [];
	$rootScope.restaurantName = [];
	$rootScope.vicinity = [];
	$rootScope.placeId = [];
	$rootScope.restaurantLat = [];
	$rootScope.restaurantLng = [];
	$rootScope.icon = [];


	//for near by places
	//storing the entire result of the nearby place search
	$rootScope.placeName1 = [];
	$rootScope.placeVicinity1 = [];
	$rootScope.nearByPlaceId1 = [];
	$rootScope.placeLat1 = [];
	$rootScope.placeLng1 = [];
	$rootScope.placeIcon1 = [];

	$rootScope.placeName2 = [];
	$rootScope.placeVicinity2 = [];
	$rootScope.nearByPlaceId2 = [];
	$rootScope.placeLat2 = [];
	$rootScope.placeLng2 = [];
	$rootScope.placeIcon2 = [];

	//Storing only 5 values and values which are not undefined
	$rootScope.placeName = [];
	$rootScope.placeVicinity = [];
	$rootScope.nearByPlaceId = [];
	$rootScope.placeLat = [];
	$rootScope.placeLng = [];
	$rootScope.placeIcon = [];





	$scope.initRest = function(){

		console.log("inside initRest");
		var options = {
				method: 'GET',
				url: '../../hangoutamigos/getplace/textsearch/'+$rootScope.search
		};

		$http(options).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);
			var latitude;
			var longitude;
			for(var i=0; i<length; i++) {

				console.log("inside for");
				dataSharing.setLng(response.data[i].geometry.location.lng);

				dataSharing.setLat(response.data[i].geometry.location.lat);

				$scope.latLong = [];
				$scope.latLong[0] = dataSharing.getLng()[0];
				$scope.latLong[1] = dataSharing.getLat()[0];
				console.log($scope.latLong);
				google.maps.event.addDomListener(window, 'load', initialize());

			}

		}, function errorCallback(response) {

		});

	};

	var marker;
	function initialize(){
		var mapCanvas = document.getElementById('map');

		console.log("in rest ctrl");
		console.log($scope.latLong);
		var mapOptions = {
				center: new google.maps.LatLng($scope.latLong[1], $scope.latLong[0]),
				zoom: 8,
				mapTypeId: google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(mapCanvas, mapOptions);

		marker = new google.maps.Marker({
			position: new google.maps.LatLng($scope.latLong[1], $scope.latLong[0]),
			map: map
		});
	};


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

			//Storing all the values in 1 array
			for(var l=0; l<response.data.length; l++) {
				if(typeof response.data[l].rating != undefined ||typeof response.data[l].icon != undefined || typeof response.data[l].place_id != undefined 
						|| typeof response.data[l].geometry.location.lat != undefined || typeof response.data[l].geometry.location.lng != undefined
						|| typeof response.data[l].name != undefined || typeof response.data[l].vicinity != undefined) {
					$rootScope.rating1[l] = response.data[l].rating;
					$rootScope.restaurantLat1[l] = response.data[l].geometry.location.lat;
					$rootScope.restaurantLng1[l] = response.data[l].geometry.location.lng;
					$rootScope.restaurantName1[l] = response.data[l].name;
					$rootScope.vicinity1[l] = response.data[l].vicinity;
					$rootScope.placeId1[l] = response.data[l].place_id;
					$rootScope.icon1[l] = response.data[l].icon;
				}
			}


			//high charts
			$scope.ideas = [
			                [$rootScope.restaurantName1[0], $rootScope.rating1[0]],
			                [$rootScope.restaurantName1[1], $rootScope.rating1[1]],
			                [$rootScope.restaurantName1[2], $rootScope.rating1[2]],
			                [$rootScope.restaurantName1[3], $rootScope.rating1[3]],
			                [$rootScope.restaurantName1[4], $rootScope.rating1[4]],
			                [$rootScope.restaurantName1[5], $rootScope.rating1[5]]
			                ];

			$scope.popularity = limitToFilter($scope.ideas, 6);


			for(var i=0; i<=5; i++) {

				if($rootScope.rating1[i]!==null) {

					$rootScope.restaurantLat[i] = $rootScope.restaurantLat1[i];
					$rootScope.restaurantLng[i] = $rootScope.restaurantLng1[i];
					$rootScope.restaurantName[i] = $rootScope.restaurantName1[i];
					$rootScope.vicinity[i] = $rootScope.vicinity1[i];
					$rootScope.placeId[i] = $rootScope.placeId1[i];
					$rootScope.icon[i] = $rootScope.icon1[i];
					//console.log('Rating ' + response.data[i].rating);
				}
				else {
					$rootScope.rating[i] = $rootScope.rating1[i];
					$rootScope.restaurantLat[i] = $rootScope.restaurantLat1[i];
					$rootScope.restaurantLng[i] = $rootScope.restaurantLng1[i];
					$rootScope.restaurantName[i] = $rootScope.restaurantName1[i];
					$rootScope.vicinity[i] = $rootScope.vicinity1[i];
					$rootScope.placeId[i] = $rootScope.placeId1[i];
					$rootScope.icon[i] = $rootScope.icon1[i];
					//console.log('Rating ' + response.data[i].rating);
				}

			}
			//edited here
			google.maps.event.addDomListener(window, 'load', initializeRestaurantMap());

		}, function errorCallback(response) {

		});


		//show div
		$scope.showDiv1 = true;



	};


	//This will display the map with all the restaurants on the map
	var marker1;
	function initializeRestaurantMap(){
		var mapCanvas = document.getElementById('map');
		console.log('in initializeRestaurantMap');
		var mapOptions = {
				center: new google.maps.LatLng($scope.latLong[1], $scope.latLong[0]),
				zoom: 15,
				mapTypeId: google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(mapCanvas, mapOptions);
		var infowindow = new google.maps.InfoWindow();
		for(var k=0; k<=5; k++) {
			marker1 = new google.maps.Marker({
				position: new google.maps.LatLng($rootScope.restaurantLat[k], $rootScope.restaurantLng[k]),
				map: map,
				animation: google.maps.Animation.DROP
			});

			google.maps.event.addListener(marker1, 'click', (function(marker1, k) {
				return function() {
					infowindow.setContent($rootScope.restaurantName1[k]);
					infowindow.open(map, marker1);
				}
			})(marker1, k));

		}


	};



	/*************************************************************/

	//Store the restaurants selected by user
	$rootScope.resultName = [];
	$rootScope.resultVicnity = [];

	console.log('Inside save function');

	$scope.save = function(name, vicinity) {

		$rootScope.resultName.push(name);
		$rootScope.resultVicnity.push(vicinity);	
		console.log('Name ' + $rootScope.resultName);
		console.log('Vicinity ' + $rootScope.resultVicnity);
	}


	//Store the near by places selected by user
	$rootScope.resultPlaceName = [];
	$rootScope.resultPlaceVicnity = [];

	console.log('Inside save function');

	$scope.savePlace = function(name, vicinity) {

		$rootScope.resultPlaceName.push(name);
		$rootScope.resultPlaceVicnity.push(vicinity);
		console.log('Name ' + $rootScope.resultPlaceName);
		console.log('Vicinity ' + $rootScope.resultPlaceVicnity);

	}


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
			url: '../../hangoutamigos/getplaces/'+placeLatitude+'/'+placeLongitude+'/'+3000+'/type/'+museum,
		}

		$http(options2).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);

			console.log(response.data);


			//Storing all the values in 1 array
			for(var l=0; l<response.data.length; l++) {
				if(typeof response.data[l].icon != undefined || typeof response.data[l].place_id != undefined 
						|| typeof response.data[l].geometry.location.lat != undefined || typeof response.data[l].geometry.location.lng != undefined
						|| typeof response.data[l].name != undefined || typeof response.data[l].vicinity != undefined) {

					$rootScope.placeLat1[l] = response.data[l].geometry.location.lat;
					$rootScope.placeLng1[l] = response.data[l].geometry.location.lng;
					$rootScope.placeName1[l] = response.data[l].name;
					$rootScope.placeVicinity1[l] = response.data[l].vicinity;
					$rootScope.nearByPlaceId1[l] = response.data[l].place_id;
					$rootScope.placeIcon1[l] = response.data[l].icon;
				}
			}



			for(var i=0; i<=5; i++) {
				$rootScope.nearByPlaceId[i] = $rootScope.nearByPlaceId1[i];
				$rootScope.placeName[i] = $rootScope.placeName1[i];
				$rootScope.placeIcon[i] = $rootScope.placeIcon1[i];
				$rootScope.placeVicinity[i] = $rootScope.placeVicinity1[i];
				$rootScope.placeLat[i] = $rootScope.placeLat1[i];
				$rootScope.placeLng[i] = $rootScope.placeLng1[i];

			}


		}, function errorCallback(response) {

		});

		/******** To get all the near by parks *************/

		var options3 = {
				method: 'GET',
				url: '../../hangoutamigos/getplaces/'+placeLatitude+'/'+placeLongitude+'/'+3000+'/type/'+park,
		}

		$http(options3).then(function successCallback(response) {
			var length = JSON.stringify(response.data.length);

			console.log(response.data);



			//Storing all the values in 1 array
			for(var l=0; l<response.data.length; l++) {
				if(typeof response.data[l].icon != undefined || typeof response.data[l].place_id != undefined 
						|| typeof response.data[l].geometry.location.lat != undefined || typeof response.data[l].geometry.location.lng != undefined
						|| typeof response.data[l].name != undefined || typeof response.data[l].vicinity != undefined) {

					$rootScope.placeLat2[l] = response.data[l].geometry.location.lat;
					$rootScope.placeLng2[l] = response.data[l].geometry.location.lng;
					$rootScope.placeName2[l] = response.data[l].name;
					$rootScope.placeVicinity2[l] = response.data[l].vicinity;
					$rootScope.nearByPlaceId2[l] = response.data[l].place_id;
					$rootScope.placeIcon2[l] = response.data[l].icon;
				}
			}

			for(var i=5; i<=9; i++) {
				$rootScope.nearByPlaceId[i] = $rootScope.nearByPlaceId2[i];
				$rootScope.placeName[i] = $rootScope.placeName2[i];
				$rootScope.placeIcon[i] = $rootScope.placeIcon2[i];
				$rootScope.placeVicinity[i] = $rootScope.placeVicinity2[i];
				$rootScope.placeLat[i] = $rootScope.placeLat2[i];
				$rootScope.placeLng[i] = $rootScope.placeLng2[i];
			}

			//edited here
			google.maps.event.addDomListener(window, 'load', initializePlaceMap());


		}, function errorCallback(response) {

		});

		//show div
		$scope.showDiv2 = true;

		//This will display the map with all the restaurants on the map
		var marker2;
		function initializePlaceMap(){
			var mapCanvas = document.getElementById('map');
			console.log('in initializePlaceMap');
			var mapOptions = {
					center: new google.maps.LatLng($scope.latLong[1], $scope.latLong[0]),
					zoom: 12,
					mapTypeId: google.maps.MapTypeId.ROADMAP
			}
			var map = new google.maps.Map(mapCanvas, mapOptions);

			for(var m=0; m<=2; m++) {
				//console.log($rootScope.placeName[m]);
				marker1 = new google.maps.Marker({
					position: new google.maps.LatLng($rootScope.placeLat1[m], $rootScope.placeLng1[m]),
					map: map,
					animation: google.maps.Animation.DROP
				});

			}
			for(var p=0; p<=2; p++) {
				//console.log($rootScope.placeName[m]);
				marker1 = new google.maps.Marker({
					position: new google.maps.LatLng($rootScope.placeLat2[p], $rootScope.placeLng2[p]),
					map: map,
					animation: google.maps.Animation.DROP
				});

			}



		}; //map ends here



		/***************************/


		/*************************/


	};

	console.log('restaurantController end');

});

//Navbar controller
hangoutamigosapp.controller('TestCtrl', function($scope, $location, $anchorScroll) {
	$scope.scrollTo = function(id) {
		    var old = $location.hash();
		    $location.hash(id);
		    $anchorScroll();
		    //reset to old to keep any additional routing logic from kicking in
		    $location.hash(old);
	};

});
