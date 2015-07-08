'use strict';

/* Controllers */

var smartIrrigatorWebControllers = angular.module('smartIrrigatorWebControllers', []);

smartIrrigatorWebControllers.controller('HomeCtrl', ['$scope', 'ExecutionService',

    function ($scope, ExecutionService) {

        ExecutionService.getExecutions(1, function(executions) {
            $scope.executions = executions;
        });

    }]);


