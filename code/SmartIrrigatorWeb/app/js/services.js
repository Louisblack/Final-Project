'use strict';

/* Services */

var smartIrrigatorWebServices = angular.module('smartIrrigatorWebServices', ['ngResource']);

smartIrrigatorWebServices.service('ExecutionService', ['$http',
    function($http){
        $http.defaults.useXDomain = true;
        this.getExecutions = function(pageNumber, callback) {
            // Hardcoded domain for test only
            $http.get('http://localhost:8888/executions/' + pageNumber).
                success(function(data){
                    callback(data);
                }).
                error(function(data) {

                });
        };

        this.getDetail = function(ids, callback) {
            $http.get('http://localhost:8888/detail/'+ ids).
                success(function(data){
                    callback(data);
                }).
                error(function(data) {

                });
        };

    }]);
