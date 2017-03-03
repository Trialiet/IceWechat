/**
 * Created by Trialiet on 2017-3-1.
 */
var service = angular.module('wechat.service', []);

service.factory('Menus', function () {
    var menus;
    menus.query = function () {
        //TO-DO Fetch the server to get menu data
    }
    return menus;
})