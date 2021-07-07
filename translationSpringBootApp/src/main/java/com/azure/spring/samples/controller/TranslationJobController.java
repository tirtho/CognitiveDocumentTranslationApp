// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.samples.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azure.spring.samples.dao.TranslationJobRepository;
import com.azure.spring.samples.model.TranslationJob;

@RestController
public class TranslationJobController {

    private static Logger logger = LoggerFactory.getLogger(TranslationJobController.class);

    @Autowired
    private TranslationJobRepository translationJobRepository;

    public TranslationJobController() {
    }

    @RequestMapping("/home")
    public Map<String, Object> home() {
        logger.info("Request '/home' path.");
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("jobId", UUID.randomUUID().toString());
        model.put("content", "home");
        return model;
    }

    /**
     * HTTP GET ALL
     */
    @RequestMapping(value = "/api/translationJobList", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllJobs() {
        logger.info("GET request access '/api/translationJobList' path.");
        try {
            List<TranslationJob> translationJobs = new ArrayList<>();
            Iterable<TranslationJob> iterable = translationJobRepository.findAll();
            if (iterable != null) {
                iterable.forEach(translationJobs::add);
            	logger.info("Found some jobs %s", translationJobs.toString());
            }
            return new ResponseEntity<>(translationJobs, HttpStatus.OK);
        } catch (Exception e) {
        	logger.info("Found Nothing: %s", e);
            return new ResponseEntity<>("Nothing found", HttpStatus.NOT_FOUND);
        }
    }

}
