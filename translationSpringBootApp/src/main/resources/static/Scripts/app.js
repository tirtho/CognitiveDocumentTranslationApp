/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

'use strict';
angular.module('cognitiveDocumentTranslationApp', ['ngRoute'])
    .config(['$routeProvider',  function ($routeProvider) {
        $routeProvider.when('/Home', {
            controller: 'homeCtrl',
            templateUrl: 'Views/Home.html',
        }).when('/TranslationJobList', {
            controller: 'translationJobListCtrl',
            templateUrl: 'Views/TranslationJobList.html',
        }).otherwise({redirectTo: '/Home'});
    }]);
