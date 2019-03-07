app.controller("notificationCtrl",function($scope,$http,$location,$rootScope){
	
	console.log('I m my notification ');
	function getNotificationsNotViewed(){
		$http.get("http://localhost:9390/ChatKing_Middleware/getAllNotifications")
		.then(
		function(response){
			console.log(response.data);
			
			$rootScope.notifications=response.data;
			$rootScope.notificationCount=$rootScope.notifications.length;
			
			
		},function(response){
			alert('Error in fetching');
			console.log($scope.error);
		});
	}
});












