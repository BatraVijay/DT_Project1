app.controller("friendCtrl",function($scope,$http,$location,$rootScope){
	$scope.friend={"id":"","fromId":"","toId":"","status":""}
	
	
	$scope.suggestedusers=function(){
		console.log("Fetching all suggested Users ");
		console.log($scope.friend);
		
		$http.get("http://localhost:9390/ChatKing_Middleware/suggestedusers",$scope.job)
		.then(
		function(response){
			console.log(response.data);
			$scope.suggestedUsers=response.data;
			
			
			
		},function(response){
			alert('Error in fetching');
			$scope.error=response.data;
			console.log($scope.error);
		});
	};
	
	$scope.addFriend=function(user){
		console.log(user.email);
		$http.post("http://localhost:9390/ChatKing_Middleware/addfriend",user)
		.then(
		function(response){
		alert("Friend Request Sent")
			
		},function(response){
			alert('Error in sending Friend Request. Try again');
			
		});
	};
	$scope.pendingrequest=function(){
		console.log("Viewing all pending Request ");
		console.log($scope.friend);
		
		$http.get("http://localhost:9390/ChatKing_Middleware/pendingrequests")
		.then(
		function(response){
			console.log(response.data);
			$scope.pendingrequest=response.data;
			
			
			
		},function(response){
			alert('Error in fetching');
			$scope.error=response.data;
			console.log($scope.error);
		});
	};

	$scope.AcceptRequest=function(request){
		
		$http.put("http://localhost:9390/ChatKing_Middleware/acceptrequest",request)
		.then(
		function(response){
		alert("Friend Request accpeted")
			
		},function(response){
			alert('Error in accpeting Friend Request. Try again');
			
		});
	};

	$scope.RejectRequest=function(request){
		
		$http.put("http://localhost:9390/ChatKing_Middleware/deleterequest",request)
		.then(
		function(response){
		alert("Friend Request rejected")
			
		},function(response){
			alert('Error in rejecting Friend Request. something went wrong please try again later');
			
		});
	};
	$scope.fetchfriends=function(){
		console.log("fetching all friends ");
		console.log($scope.friend);
		
		$http.get("http://localhost:9390/ChatKing_Middleware/friend",$scope.friend)
		.then(
		function(response){
			console.log(response.data);
			$scope.friends=response.data;
			
			
			
		},function(response){
			alert('Error in fetching');
			$scope.error=response.data;
			console.log($scope.error);
		});
	};
	
});












