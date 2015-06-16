'use strict';

/* Services */

var smartIrrigatorWebServices = angular.module('smartIrrigatorWebServices', ['ngResource']);

smartIrrigatorWebServices.factory('ExecutionService', ['$http',
    function($http){

        this.getExecutions = function(pageNumber) {

        };

        this.getDetail = function(ids) {

        };

    }]);
