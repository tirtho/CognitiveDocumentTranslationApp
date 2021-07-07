// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.samples.dao;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.samples.model.TranslationJob;

@Repository
public interface TranslationJobRepository extends CosmosRepository<TranslationJob, String> {
}
