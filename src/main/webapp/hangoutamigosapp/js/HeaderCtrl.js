hangoutamigosapp.controller('HeaderCtrl',function($scope,$window,$rootScope) {
	
	
	var hc = this;
	
	hc.initHeader = function(){
		if($window.sessionStorage.userId){
			$rootScope.userId = $window.sessionStorage.userId;
			$rootScope.userEmail = $window.sessionStorage.userEmail;
		}
		else{
			$rootScope.userId = null;
			$rootScope.userEmail = null;
		}
			
	};
	
	hc.logout=function(){
		$rootScope.userId=null;
		$rootScope.userEmail=null;
		delete $window.sessionStorage.userId;
		delete $window.sessionStorage.userEmail;
	};
	
	
	
});