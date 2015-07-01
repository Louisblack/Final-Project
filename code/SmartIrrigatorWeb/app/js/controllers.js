'use strict';

/* Controllers */

var smartIrrigatorWebControllers = angular.module('smartIrrigatorWebControllers', []);

smartIrrigatorWebControllers.controller('HomeCtrl', ['$scope', 'ExecutionService',

    function ($scope, ExecutionService) {

        $scope.executions = [
            {"date": "14/06/2015", "didIrrigate": true, "irrigationDuration": 90, "iconClass": "sun_icon"},
            {"date": "13/06/2015", "didIrrigate": false, "irrigationDuration": 0, "iconClass": "rain_icon"},
            {"date": "12/06/2015", "didIrrigate": true, "irrigationDuration": 68, "iconClass": "sun_icon"},
            {"date": "11/06/2015", "didIrrigate": true, "irrigationDuration": 51, "iconClass": "sun_icon"}
        ]

    }]);


