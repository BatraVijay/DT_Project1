app.controller("jobCtrl",function($scope,$http,$location,$rootScope,$cookieStore,$window,$routeParams){
	
	$scope.job={"id":"","jobTitle":"","jobDescription":"","skillsRequired":"","location":"","yrsofExp":"","companyName":"","salary":"","postedOn":""};
	var id=$routeParams.id;
	
	$scope.addJob=function(){
		console.log("add Job ");
		console.log($scope.job);
		
		$http.post("http://localhost:9390/ChatKing_Middleware/addjob",$scope.job)
		.then(
		function(response){
			alert('Job Added Successfully..');
			$location.path("userHome");
			
		},function(response){
			alert('Error in Adding Job');
			$scope.error=response.data;
			console.log($scope.error);
		});
	};
	$scope.fetchAllJobs=function(){
		console.log('fetching all the jobs');
		$http.get("http://localhost:9390/ChatKing_Middleware/alljobs")
		.then(
		function(response){
			console.log(response.data);
			$scope.jobs=response.data;
			
			
		},function(response){
			alert('No Job found');
			
		});
	};
	
	$scope.getJobById=function(){
		console.log('getting job  by ID '+id);
		$http.get("http://localhost:9390/ChatKing_Middleware/getjob/"+id)
		.then(
		function(response){
			console.log(response.data);
			$scope.job=response.data;
			$http.get("http://localhost:9390/ChatKing_Middleware/checkAppliedJobs/"+id)
			.then(
			function(response){
				console.log('Hello1');
				console.log(response.data);
				$scope.status=response.data;
				
			},function(response){
				console.log('Hello2');
				console.log(response.data);
				$scope.status=false;
			});
		},function(response){
			console.log('Hello3');
			$scope.status=response.data;
			$scope.status=false;
		
		},function(response){
			console.log('Hello4');	
		});
	};

	

	
	$scope.applyJob=function(jId){
		console.log('Applying Job'+jId);
		$http.post("http://localhost:9390/ChatKing_Middleware/applyjob",jId)
		.then(
		function(response){
			alert('Job Applied Successfully..');
			
			
		},function(response){
			alert("You need to login first for applying the Job");
			$location.path("login");
		});
	};
	
	$scope.fetchAllAppliedJobs=function(){
		console.log('fetching all the jobs');
		$http.get("http://localhost:9390/ChatKing_Middleware/allAppliedJobs")
		.then(
		function(response){
			console.log(response.data);
			$scope.appliedjobs=response.data;
			
			
		},function(response){
			
			
		});
	};
	
});