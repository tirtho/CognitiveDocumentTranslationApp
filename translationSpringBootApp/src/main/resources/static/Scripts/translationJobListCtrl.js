/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

'use strict';
angular.module('cognitiveDocumentTranslationApp')
    .controller('translationJobListCtrl', ['$scope', '$location', 'translationJobListSvc', function ($scope, $location, translationJobListSvc) {
        $scope.error = '';
        $scope.loadingMessage = '';
        $scope.translationJobList = null;
        $scope.editingInProgress = false;
        $scope.newTodoCaption = '';

        $scope.editInProgressTodo = {
            description: '',
            id: 0,
            finish: false
        };

        $scope.finishSwitch = function (translationJob) {
            translationJobListSvc.putItem(translationJob).error(function (err) {
                translationJob.finished = !translationJob.finished;
                $scope.error = err;
                $scope.loadingMessage = '';
            })
        };

        $scope.editSwitch = function (translationJob) {
            translationJob.edit = !translationJob.edit;
            if (translationJob.edit) {
                $scope.editInProgressTodo.sourceFilePath = translationJob.sourceFilePath;
                $scope.editInProgressTodo.jobId = translationJob.jobId;
                $scope.editInProgressTodo.finished = translationJob.finished;
                $scope.editingInProgress = true;
            } else {
                $scope.editingInProgress = false;
            }
        };

        $scope.populate = function () {
            translationJobListSvc.getItems().success(function (results) {
                $scope.translationJobList = results;
            }).error(function (err) {
                $scope.error = err;
                $scope.loadingMessage = '';
            })
        };

    }]);
