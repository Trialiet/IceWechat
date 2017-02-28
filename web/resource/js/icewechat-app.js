/**
 * Created by Trialiet on 2016/11/11.
 */
var app = angular.module('wechat', ['ui.router']);

//配置angular-ui-route路由
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/")
        .otherwise('/');
    $stateProvider
        .state('menu-manage', {
            url:'/menu-manage',
            templateUrl:'menu-manage.html',
            controller:'menuController'
        })
        .state('common-config', {
            url:'/common-config', 
            templateUrl: 'common-config.html',
            controller: 'commonController'
        });
});

app.controller('commonController', function ($scope) {
    
});

app.controller('menuController', function ($scope) {
    $http
});