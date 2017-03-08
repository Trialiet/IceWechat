/**
 * Created by Trialiet on 2017-3-8.
 */
var login = angular.module("login", []);

login.controller("loginController", [$http, function ($http, $scope) {
    var data = "{user_name: " + $scope.user_name + ", user_password: " + $scope.user_password + "}";
    $http.post("/login/auth", data)
}])