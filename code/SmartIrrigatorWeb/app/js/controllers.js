'use strict';

/* Controllers */

var smartIrrigatorWebControllers = angular.module('smartIrrigatorWebControllers', []);

smartIrrigatorWebControllers.controller('HomeCtrl', ['$scope', 'ExecutionService', 'WaterNowService',

    function ($scope, ExecutionService, WaterNowService) {

        function fixDateForUrl(date) {
            var regex = new RegExp("/", "g");
            return date.replace(regex, "-")
        }

        function loadDetail(index) {
            var date = fixDateForUrl($scope.executions[index].date);
            ExecutionService.getDetail(date, function(details) {
                $scope.executions[index].executionDetails = details.executions;
                $scope["expanded" + index] = true;

            })
        };

        $scope.waterNow = function() {
            WaterNowService.waterNow(function(response) {
                if (response.success) {
                    BootstrapDialog.alert("Everything went OK!")
                } else {
                    BootstrapDialog.alert(response.errors.map(function(error){
                        return error.message;
                    }).join("\n"))
                }
            });
        };

        $scope.showHideDetail = function(index) {
            if ($scope.isExpanded(index)) {
                $scope["expanded" + index] = false;
                $scope.executions[index].executionDetails = [];
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


