

angular.module('frontendServices', [])
.factory('NewUserService', ['$http', '$q', '$rootScope', function($http, $q, $rootScope) {
	service = {}
	service.userData = null;
	service.groupMembers = null;
	
	    
    service.getUserInfo = function() {
        var deferred = $q.defer();
        
        if(service.userData){
        	console.log("Get cached userdata");
        	deferred.resolve(service.userData);
        }
        else{
        	console.log("Get HTTP userdata");

        	$http.get('/api/user')
	            .then(function (response) {
	                if (response.status == 200) {
	                    deferred.resolve(response.data);
	                	service.userData = response.data;
	                	console.log("RD ");
	                	console.log(response.data);
	
	                }
	                else {
	                    deferred.reject('Error retrieving user info ' + response.status);
	                }
	        });
        }
        return deferred.promise;
    }

    searchGroupMembers = function(groupname) {
        var deferred = $q.defer();

        if(service.groupMembers){
        	console.log("Get cached Group data");
        	deferred.resolve(service.groupMembers);
        }
        else{

	        $http.get('/api/groupmembers/',{
	            params: {
	                groupname: groupname
	            }
	        })
	        .then(function (response) {
	            if (response.status == 200) {
	                deferred.resolve(response.data);
                	service.groupMembers = response.data;
	            }
	            else {
	                deferred.reject('Error retrieving group members');
	            }
	        });
        }
        
        return deferred.promise;
    }
    
    /* Return service */
	return service;
	
}
])
    .service('MealService', ['$http', '$q', function($http, $q) {
        return {
        	        	
        	getMealDetails: function(mealId, scope){
                var deferred = $q.defer();

                $http.get('/api/meal/'+mealId)
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                        console.log("RETRIEVED MEAL DETAIL ", response.data)
                    }
                    else {
                        deferred.reject('Error retrieving meal details for mealId = ' + mealId);
                    }
                });                
                // console.log("SERVICE GET MEAL DETAILS");
                return deferred.promise;

        	},
        	
            getCategories: function() {
                var deferred = $q.defer();
                $http.get('/api/categories')
                    .then(function (response) {
                        if (response.status == 200) {
                        	userData = response.data;
                            deferred.resolve(response.data);
                        }
                        else {
                            deferred.reject('Error retrieving user info ' + response.status);
                        }
                });

                return deferred.promise;
            },
        	
            searchMyMeals: function(username, pageNumber) {
                var deferred = $q.defer();

                function prepareTime(time) {
                    return time ? '1970/01/01 ' + time : null;
                }

                $http.get('/api/meal/',{
                    params: {
                        username: username,
                        pageNumber: pageNumber
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error retrieving list of meals');
                    }
                });

                return deferred.promise;
            },
            searchGroupMeals: function(groupname, pageNumber) {
                var deferred = $q.defer();

                function prepareTime(time) {
                    return time ? '1970/01/01 ' + time : null;
                }

                $http.get('/api/meal/',{
                    params: {
                        groupname: groupname,
                        pageNumber: pageNumber
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error retrieving list of meals');
                    }
                });

                return deferred.promise;
            },            
            searchMeals: function(fromDate, fromTime, toDate, toTime, pageNumber) {
                var deferred = $q.defer();

                function prepareTime(time) {
                    return time ? '1970/01/01 ' + time : null;
                }

                $http.get('/api/meal/',{
                    params: {
                        fromDate: fromDate,
                        toDate: toDate,
                        fromTime: prepareTime(fromTime),
                        toTime: prepareTime(toTime),
                        pageNumber: pageNumber
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error retrieving list of meals');
                    }
                });

                return deferred.promise;
            },

            deleteMeals: function(deletedMealIds) {
                var deferred = $q.defer();

                $http({
                    method: 'DELETE',
                    url: '/api/meal',
                    data: deletedMealIds,
                    headers: {
                        "Content-Type": "application/json"
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve();
                    }
                    else {
                        deferred.reject('Error deleting meals');
                    }
                });

                return deferred.promise;
            },

            saveMeals: function(dirtyMeals) {
                var deferred = $q.defer();

                $http({
                    method: 'POST',
                    url: '/api/meal',
                    data: dirtyMeals,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve();
                    }
                    else {
                    deferred.reject("Error saving meals: " + response.data);
                    }
                });

                return deferred.promise;
            },
            saveMeal: function(meal, scope) {
                var deferred = $q.defer();

                $http({
                    method: 'POST',
                    url: '/api/newmeal',
                    data: meal,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve();
                    }
                    else {
                    deferred.reject("Error saving meal: " + response.data);
                    }
                });

                return deferred.promise;
            },
            rankMeal: function(rank) {
                var deferred = $q.defer();

                $http({
                    method: 'POST',
                    url: '/api/newrank',
                    data: rank,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                    deferred.reject("Error ranking meal: " + response.data);
                    }
                });

                return deferred.promise;
            }

            
        }
            
       
    }])
    .service('UserService', ['$http','$q', function($http, $q) {
        return {
        	userData: null,
            getUserInfo: function() {
                var deferred = $q.defer();
                // console.log("IN USER");
                $http.get('/api/user')
                    .then(function (response) {
                        if (response.status == 200) {
                            deferred.resolve(response.data);
                        	userData = deferred.promise;

                        }
                        else {
                            deferred.reject('Error retrieving user info ' + response.status);
                        }
                });

                return deferred.promise;
            },
            searchGroupMembers: function(groupname) {
                var deferred = $q.defer();

  
                $http.get('/api/groupmembers/',{
                    params: {
                        groupname: groupname
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                        deferred.resolve(response.data);
                    }
                    else {
                        deferred.reject('Error retrieving group members');
                    }
                });

                return deferred.promise;
            },            

            logout: function () {
                $http({
                    method: 'POST',
                    url: '/logout'
                })
                .then(function (response) {
                    if (response.status == 200) {
                    	window.location.replace('/resources/public/login.html');
                    //window.location.reload();
                    }
                    else {
                        console.log("Logout failed!");
                    }
                });
            }
        };
    }]);