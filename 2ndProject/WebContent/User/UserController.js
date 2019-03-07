app.controller("userCtrl",function($scope,$http,$location,$rootScope,$cookieStore,$window){

$scope.user={"email":"","password":"","firstName":"","lastName":"","mobileNumber":"","role":"","online":""};


	$scope.registerUser=function(){
		console.log("I m Registering User");
		console.log($scope.user);
		
		$http.post("http://localhost:9390/ChatKing_Middleware/registerUser",$scope.user)
		.then(
		function(response){
			alert('Registered Successfully..')
			$location.path("login");

			
		},function(response){
			$scope.error=response.data;
			alert("Problem in Registering Try Again");
			
		});
	};

	$scope.loginUser=function(){
		console.log("I m login User function"+$scope.user.email+" "+$scope.user.password);
		$http.post("http://localhost:9390/ChatKing_Middleware/validateUser",$scope.user)
		.then(
		function(response){
			
			$scope.user=response.data;
			$rootScope.currentUser=response.data;
			$cookieStore.put('userDetails',response.data);
			console.log($scope.user);
			console.log($scope.user.role);
			
			$location.path("userHome");
			
		},function(response){
			alert("User is invalid");
			
		});
};


$scope.fetchUser=function(){
	console.log('I m fetching User');
	$scope.user=$rootScope.currentUser;
	console.log($scope.user);
};


$scope.updateUser=function(){
	console.log("I m Updating User");
	console.log($scope.user);
	
	$http.post("http://localhost:9390/ChatKing_Middleware/updateUser",$scope.user)
	.then(
	function(response){
		alert('Updated  Successfully..')
		$location.path("viewProfile");

		
	},function(response){
		$scope.error=response.data;
		alert("Problem in Updating Try Again");
		
	});
};


$rootScope.logout=function(){
	console.log('LogOut function');
	delete $rootScope.currentUser;
	$cookieStore.remove('userDetails');
	$location.path("logout");
}
});