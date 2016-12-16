var todo=angular.module('todo',['ngRoute']);
todo.config(function($routeProvider){
	$routeProvider
	.when('/register',{
		templateUrl:'partials/register.html',
		controller:'registerController'
	}).when('/task',{
		templateUrl:'partials/allTasks.html',
		controller:'taskController'
	}).when('/login',{
		templateUrl:'partials/login.html',
		controller:'loginController'
	}).when('/userHome',{
		templateUrl:'partials/userHome.html',
		controller:'userHomeController'
	}).when('/logout',{
		templateUrl:'partials/logout.html',
		controller:'logoutController'
	})
});
todo.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

todo.controller('registerController',function($scope,$http,$rootScope){
	console.log("in reg controller");
	$scope.register=function()
	{ var user={
			name:$scope.name,
			password:$scope.password,
			emailid:$scope.emailid
	};
	var res=$http.post("http://localhost:8080/todo/addUser",user);
	res.success(function(data, status, headers, config) {
			console.log("status:"+status);
			});
	}
});

todo.controller("taskController",function($scope,$http,$rootScope)	
		{	
	$rootScope.login=false;
	$rootScope.register=false;
	$rootScope.task=true;

	
	console.log(" in view task controller");
	$http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
			    .then(function (response) {
			    	$scope.tasks = response.data;
           console.log("data:"+response.data);
			    });
	$scope.newTask={};
	console.log("In Controller");
	
	$scope.addTask=function(newTask)
	{
		var dataObj = {
				taskName:$scope.taskName,
    			description:$scope.description,
    			lastDate:$scope.lastDate,
    			tasksOf:$rootScope.uname
 		};
		console.log("title:"+dataObj);
		 var res = $http.post('http://localhost:8080/todo/addTasks',dataObj);
		 $http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
	 	    .then(function (response) {$scope.tasks = response.data;});
	 		res.success(function(data, status, headers, config) {
	 			$scope.message = data;
	 			console.log("status:"+status);
	 		});
	 		 
	};
	
	$scope.editTask=function(task)
	{
		console.log("inside edittask");
		console.log("task is:"+task);
		$scope.taskToEdit=task;
	}
	$scope.saveEdit=function()
	{
		var dataObj = {
    			taskName:$scope.taskToEdit.taskName,
    			description:$scope.taskToEdit.description,
 				taskId:$scope.taskToEdit.taskId,
 				lastDate:$scope.taskToEdit.lastDate,
 				tasksOf:$scope.taskToEdit.tasksOf,
 				status:$scope.taskToEdit.status
 		};
		$http.put('http://localhost:8080/todo/updateTasks', dataObj);
		$http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
 	    .then(function (response) {$scope.task = response.data;});
	}
	$scope.deleteTask=function(taskToEdit)
	{
		console.log("delete task called");
		taskId:$scope.taskToEdit.taskId;
		console.log("task_id:"+taskToEdit.taskId);
		$http['delete']('http://localhost:8080/todo/deleteTasks/'+taskToEdit.taskId);
		 $http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
	 	    .then(function (response) {$scope.tasks = response.data;});
	}
	$scope.compend=function(task)
	{$http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
		   .then(function (response) {
		    	
		    	$scope.tasks = response.data;
		    	
		    	console.log("data:"+response.data);
		    	console.log("compend:"+task);
		    	$scope.taskstatus=task
		    });
		console.log("inside compendtask");
		
	}
	$scope.completedTask=function()
	{
		console.log("in approvetask");
		var edit=
			{
	taskId:$scope.taskstatus.taskId,
				
				taskName:$scope.taskstatus.taskName,
				description:$scope.taskstatus.description,
				lastDate:$scope.taskstatus.lastDate,
				tasksOf:$scope.taskstatus.tasksOf,
			    status:true
			}
		$http.put("http://localhost:8080/todo/updateTasks",edit);
		 $http.get("http://localhost:8080/todo/viewMyTasks/"+$rootScope.uname)
		    .then(function (response) {
		    	
		    	$scope.tasks= response.data;
		    	
		    	console.log("data:"+response.data);
		    });
	}
	$http.get("http://localhost:8080/todo/viewMyTask/"+$rootScope.uname)
    .then(function (response) {
    	
    	$scope.completedtasks= response.data;
    	
    	console.log("data:"+response.data);
    });
	
	});

todo.controller('loginController',['$scope','$http','$location','$rootScope',function($scope,$http,$location,$rootScope){
	$rootScope.login=false;
	$rootScope.register=false;
	$rootScope.home=true;
	console.log("in login()");
	$scope.login=function(){
		var logData={
			name:$scope.name,
			password:$scope.password
	}
		$http.post("http://localhost:8080/todo/authenticate",logData).then(function(response){
			console.log("result   data:"+response.data);
			var r=response.data.toString();
			console.log("response:"+r);
		     
			if(r==1)
				{
			
				$rootScope.task=false;
		
			    $rootScope.login=false;
				$rootScope.register=false;
				$rootScope.logout=true;
			    console.log('logout:'+$rootScope.logout);
				console.log("logout.....:"+response.data);
				console.log("uname from root scope:"+$rootScope.uname);
				$rootScope.uname=$scope.name;
				console.log("uname:"+$rootScope.uname);
				$location.path('/userHome');
				}
			if(r==0)
				{
				$scope.name="";
				$scope.password="";
				$scope.message="name/password incorrect";
				$location.path('/login');
				}
		
			});  
				 }
			}]
			);
todo.controller("userHomeController",function($scope,$http,$rootScope)	
		{	
	    
	     $rootScope.login=false;
	 	$rootScope.register=false;
	 	$rootScope.task=true;
	 	$rootScope.logout=true;
	 	
	 	
	console.log("in userHome controller");

	    });

todo.controller("logoutController",function($scope,$http,$rootScope)	
		{	
	    
	     $rootScope.login=true;
	 	$rootScope.register=false;
	 	$rootScope.task=false;
	 
	 	
	console.log("in logout controller");

	    });




