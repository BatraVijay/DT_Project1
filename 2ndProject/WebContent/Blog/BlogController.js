app.controller("BlogController",function($scope,$http,$rootScope,$location,$window,$routeParams)
		{
	
	var id=$routeParams.id;
$scope.blog={"id":"","blogTitle":"","blogDescription":""};


$scope.addBlog=function(){
		console.log("add Blog ");
		console.log($scope.blog);
		
		$http.post("http://localhost:9390/ChatKing_Middleware/addblog",$scope.blog)
		.then(
		function(response){
			alert('Blog Added Successfully..');
			
			
		},function(response){
			alert('Error in Adding Blog');
			$scope.error=response.data;
			console.log($scope.error);
		});
};
$scope.fetchAllBlogs=function(){
	console.log('fetching all the blogs');
	$http.get("http://localhost:9390/ChatKing_Middleware/listAllApprovedBlog")
	.then(
	function(response){
		console.log(response.data);
		$scope.blogs=response.data;
		
		
	},function(response){
		alert('No Blogs found');
		
	});
}

$scope.fetchAllPendingBlogs=function(){
	console.log('fetching all the pending blogs');
	$http.get("http://localhost:9390/ChatKing_Middleware/listPendingBlogs")
	.then(
	function(response){
		console.log(response.data);
		$scope.blogs=response.data;
		
		
	},function(response){
		alert('No Blogs found');
		
	});
}

$scope.fetchMyBlogs=function(){
	console.log('getting all the blogs');
	console.log("getting all the blogs"+$scope.currentUser.email);
	
	$http.get("http://localhost:9390/ChatKing_Middleware/listBlog/"+$scope.currentUser.email)
	.then(
	function(response){	
		console.log(response.data);
		$scope.blogs=response.data;
		console.log($scope.blogs);
		
	},function(response){
		console.log(response.data);
		alert('No Blogs found');
		
	});
};

$scope.approveBlog=function(blog){
	console.log('Approving Blog');
	$http.post("http://localhost:9390/ChatKing_Middleware/approveBlog",blog)
	.then(
	function(response){	
		console.log(response.data);
		alert("Blog Approved Succesfully");
		$location.path("getAllBlog");
		
	},function(response){
		console.log(response.data);
		alert("Problem in Approving Blog. Try again");
		
	});
	
};

$scope.rejectBlog=function(blog){
	console.log('Rejecting Blog');
	$http.post("http://localhost:9390/ChatKing_Middleware/rejectBlog",blog)
	.then(
	function(response){	
		console.log(response.data);
		alert("Blog Rejected Succesfully");
		$location.path("getAllBlog");
		
	},function(response){
		console.log(response.data);
		alert("Problem in Rejecting Blog. Try again");
		
	});
};

$scope.incrementLikes=function(blog){
	console.log('Incrementing Blog');
	$http.post("http://localhost:9390/ChatKing_Middleware/incrementLikes",blog)
	.then(
	function(response){	
		console.log(response.data);
		$window.location.reload();
		
	},function(response){
		console.log(response.data);
		
		
	});
};



$scope.addComment=function(blogId,commentText){
	console.log('Adding Comment');

	$scope.blogComment={"commentId":"","commentText":commentText,"commentedOn":"","commentedBy":"","blogId":blogId};
	
	
	
	$http.post("http://localhost:9390/ChatKing_Middleware/addComment",$scope.blogComment)
	.then(
	function(response){	
		console.log(response.data);
		alert("Comment Added Succesfully");
		
	},function(response){
		console.log(response.data);
		alert("U need to login , for making any comment");
		$location.path("login");
		
	});
};

$scope.onShowComments=function(blogId){
	console.log('show comments for blog : '+blogId);
	$http.get("http://localhost:9390/ChatKing_Middleware/getBlogComments/"+blogId)
	.then(
	function(response){	
		console.log(response.data);
		$scope.comments=response.data;
		$scope.showComments=true;
		
	},function(response){
		console.log(response.data);
		alert("No comments available")
		
	});
	
};

$scope.getBlogById=function(){
	console.log('getting Blog  by ID '+id);
	$http.get("http://localhost:9390/ChatKing_Middleware/getBlog/"+id)
	.then(
	function(response){
		console.log(response.data);
		$scope.bg=response.data;
		
		
	},function(response){
		console.log(response.data);
		alert("Some error occured in fetching blog Details");
	});
};

});










