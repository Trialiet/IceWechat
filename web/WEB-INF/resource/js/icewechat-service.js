/**
 * Created by Trialiet on 2017-3-1.
 */
var service = angular.module('wechat.service', []);

service.factory('SidebarService', ['$http', '$q', function($http, $q){
    return{
        get : function () {
            var defered = $q.defer();
            $http.get('http://localhost:8080/sidebarMenu/get').then(function (result) {
                defered.resolve(result.data);
            }, function (error) {
                defered.reject(error);
                console.log(error);
            });
            return defered.promise;
        }
    }
}]);

// service.factory('SidebarMenus', function () {
//     alert('service');
//     var sidebarMenus = {};
//     sidebarMenus.query = function () {
//         alert("query");
//         $http.get('localhost:8080/sidebarMenu/get').success(function (data) {
//             alert('$http called');
//             return data;
//         })
//     }
//     return sidebarMenus;
// })