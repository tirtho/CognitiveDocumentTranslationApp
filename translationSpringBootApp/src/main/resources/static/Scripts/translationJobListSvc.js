/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

'use strict';
angular.module('cognitiveDocumentTranslationApp')
    .factory('translationJobListSvc', ['$http', function ($http) {
        return {
            getItems: function () {
                return $http.get('api/translationJobList');
            }
        };
    }]);
