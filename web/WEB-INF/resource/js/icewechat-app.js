/**
 * Created by Trialiet on 2016/11/11.
 */
var app = angular.module('wechat', ['ui.router', 'wechat.service']);

//侧边栏菜单
app.controller('sidebarController', function ($scope, SidebarService) {
    $scope.fetchSidebarMenu = function () {
        var success = function (data) {
            $scope.sidebarMenus = data;
            return data;
        }
        var error = function (err) {
            console.log(err);
        }
        SidebarService.get().then(success, error);
    }
    $scope.fetchSidebarMenu();
})

//配置angular-ui-route路由
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/")
        .otherwise('/');
    $stateProvider
        .state('menu-manage', {
            url:'/menu-manage',
            templateUrl:'manage/menu-manage',
            controller:'menuController'
        })
        .state('common-config', {
            url:'/common-config', 
            templateUrl: 'manage/common-config',
            controller: 'commonController'
        });
});
//基础配置
app.controller('commonController', function ($scope) {
    
});
//自定义菜单
app.controller('menuController', function ($scope) {
    $scope.message = "Menu Controller"
});
