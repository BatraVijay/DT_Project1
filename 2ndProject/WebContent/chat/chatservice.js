app.filter('reverse', function() {
      return function(items) {
    	  console.log(items);
        return items.slice().reverse();
      };
    });

   app.directive('ngFocus', function() {
      return function(scope, element, attrs) {
    	  console.log(scope,element,attrs);
        element.bind('click', function() {
          $('.' + attrs.ngFocus)[0].focus();
        });
      };
    });
   
app.factory('ChatService',function($rootScope){
	
	var socket=new SockJS("/ChatKing_Middleware/chatmodule")
	var stompClient=Stomp.over(socket);
	stompClient.connect('','',function(frame){
		alert('in connect with service')
		$rootScope.$broadcast('sockConnected',frame)
	})
	return{
		stompClient:stompClient
	}
})