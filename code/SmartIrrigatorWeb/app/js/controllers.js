'use strict';

/* Controllers */

var smartIrrigatorWebControllers = angular.module('smartIrrigatorWebControllers', []);

smartIrrigatorWebControllers.controller('HomeCtrl', ['$scope', 'ExecutionService',

    function ($scope, ExecutionService) {

        function fixDateForUrl(date) {
            var regex = new RegExp("/", "g");
            return date.replace(regex, "-")
        }

        function loadDetail(index) {
            var date = fixDateForUrl($scope.executions[index].date);
            ExecutionService.getDetail(date, function(details) {
                $scope["expanded" + index] = true;
            })
        };

        $scope.showHideDetail = function(index) {
            if ($scope.isExpanded(index)) {
                $scope["expanded" + index] = false;
            } else {
                loadDetail(index);
            }
        };

        $scope.isExpanded = function(index) {
            return $scope["expanded" + index];
        }

        ExecutionService.getExecutions(1, function(executions) {
            $scope.executions = executions;
        });

    }]);


