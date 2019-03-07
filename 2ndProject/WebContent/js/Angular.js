var app=angular.module('app',['ngRoute',,'ngCookies']);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "MainContent.html"
    })
    .when("/contactUs", {
        templateUrl : "contactus.html"
    })
    .when("/aboutUs", {
        templateUrl : "AboutUs.html"
    })
    .when("/login", {
        templateUrl : "User\\login.html",
        controller:"userCtrl"
        	
    })
    .when("/chat", {
        templateUrl : "chat\\chat.html",
        controller:"ChatCtrl"
        
    })


    
    .when("/signUp", {
    	templateUrl : "User\\signup.html",
    		controller:"userCtrl"
    })
    .when('/addjob',{
		templateUrl:"Job\\JobForm.html",
		controller:"jobCtrl"
	})
    .when("/userHome", {
        templateUrl : "User\\userHome.html"
    })
    .when("/logout", {
        templateUrl : "User\\logout.html"
        
    })
    .when("/addJob", {
        templateUrl : "job\\JobForm.html",
        controller:"jobCtrl"
        
    })
    .when("/getAllJobs", {
        templateUrl : "Job\\AllJobs.html",
        controller:"jobCtrl"
        
    })
    .when('/getJob/:id',{
		templateUrl:"Job\\JobDetail.html",
		controller:"jobCtrl"
	})
	.when("/viewAllAppliedJobs", {
        templateUrl : "job\\AllAppliedJobs.html",
        controller:"jobCtrl"
        
    })
    
    .when('/uploadprofilepic',{
		templateUrl:'User/uploadprofilepicture.html',
		
	})
    .when('/viewProfile',{
		templateUrl:'User/ViewProfile.html',
		
	})
	.when('/editProfile',{
		templateUrl:'User/EditProfile.html',
		controller:"userCtrl"
	})
	
    .when('/CreateBlog',{
		templateUrl:'Blog/BlogForm.html',
		controller:"BlogController"
	})
	
    .when('/getAllBlog',{
		templateUrl:'Blog/AllBlogs.html',
		controller:"BlogController"
	})
	
    .when('/ViewMyBlog',{
		templateUrl:'Blog/viewMyBlog.html',
		controller:"BlogController"
	})
	.when('/viewAllPendingBlogs',{
		templateUrl:'Blog/ViewAllPendingBlogs.html',
		controller:"BlogController"
	})
	 .when('/getBlog/:id',{
		templateUrl:"Blog\\BlogDetail.html",
		controller:"BlogController"
	})
	.when('/suggestedusers',{
		templateUrl:"Friends/suggestedusers.html",
		controller:"friendCtrl"
	})
	.when('/pendingrequests',{
		templateUrl:"Friends/pendingrequests.html",
		controller:"friendCtrl"
	})
	.when('/friend',{
		templateUrl:"Friends/friend.html",
		controller:"friendCtrl"
	})
    
});
app.run(function($rootScope,$cookieStore)
		{
			console.log('I am in run function');
			console.log($rootScope.currentUser);
			
				if($rootScope.currentUser==undefined)
				{
				$rootScope.currentUser=$cookieStore.get('userDetails');
				}
				else
				{
				console.log($rootScope.currentUser.email);
				console.log($rootScope.currentUser.role);
				}
		});
