'use strict';

/* App Module */

var smartIrrigatorWeb = angular.module('smartIrrigatorWeb', [
    'ngRoute',
    'smartIrrigatorWebControllers',
    'smartIrrigatorWebServices'
]);

smartIrrigatorWeb.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'partials/execution-list.html',
                controller: 'HomeCtrl'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);
