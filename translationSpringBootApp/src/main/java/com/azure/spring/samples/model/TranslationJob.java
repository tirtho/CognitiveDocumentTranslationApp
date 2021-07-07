// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.samples.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "TranslationJob")
public class TranslationJob {
    @Id
    @PartitionKey   
    private String jobId;
    private Date createDate;
    private String requestId;
    private String jobStatus;
    private String sourceFilePath;
    private String targetFilePath;
    private String sourceUrl;
    private String targetUrl;
    private String translatedFrom;
    private String translatedTo;
    private String documentId;
    private Date translationStartedAtUTC;
    private Date translationLastActionDateUTC;
    private String translationStatus;
    private Integer translationProgress;
    private Integer characterCharged;
    
    public TranslationJob() {
    }

	public TranslationJob(String jobId, Date createDate, String requestId, String jobStatus, String sourceFilePath,
			String targetFilePath, String sourceUrl, String targetUrl, String translatedFrom, String translatedTo,
			String documentId, Date translationStartedAtUTC, Date translationLastActionDateUTC,
			String translationStatus, Integer translationProgress, Integer characterCharged) {
		super();
		this.jobId = jobId;
		this.createDate = createDate;
		this.requestId = requestId;
		this.jobStatus = jobStatus;
		this.sourceFilePath = sourceFilePath;
		this.targetFilePath = targetFilePath;
		this.sourceUrl = sourceUrl;
		this.targetUrl = targetUrl;
		this.translatedFrom = translatedFrom;
		this.translatedTo = translatedTo;
		this.documentId = documentId;
		this.translationStartedAtUTC = translationStartedAtUTC;
		this.translationLastActionDateUTC = translationLastActionDateUTC;
		this.translationStatus = translationStatus;
		this.translationProgress = translationProgress;
		this.characterCharged = characterCharged;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getTranslatedFrom() {
		return translatedFrom;
	}

	public void setTranslatedFrom(String translatedFrom) {
		this.translatedFrom = translatedFrom;
	}

	public String getTranslatedTo() {
		return translatedTo;
	}

	public void setTranslatedTo(String translatedTo) {
		this.translatedTo = translatedTo;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Date getTranslationStartedAtUTC() {
		return translationStartedAtUTC;
	}

	public void setTranslationStartedAtUTC(Date translationStartedAtUTC) {
		this.translationStartedAtUTC = translationStartedAtUTC;
	}

	public Date getTranslationLastActionDateUTC() {
		return translationLastActionDateUTC;
	}

	public void setTranslationLastActionDateUTC(Date translationLastActionDateUTC) {
		this.translationLastActionDateUTC = translationLastActionDateUTC;
	}

	public String getTranslationStatus() {
		return translationStatus;
	}

	public void setTranslationStatus(String translationStatus) {
		this.translationStatus = translationStatus;
	}

	public Integer getTranslationProgress() {
		return translationProgress;
	}

	public void setTranslationProgress(Integer translationProgress) {
		this.translationProgress = translationProgress;
	}

	public Integer getCharacterCharged() {
		return characterCharged;
	}

	public void setCharacterCharged(Integer characterCharged) {
		this.characterCharged = characterCharged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characterCharged == null) ? 0 : characterCharged.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobStatus == null) ? 0 : jobStatus.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((sourceFilePath == null) ? 0 : sourceFilePath.hashCode());
		result = prime * result + ((sourceUrl == null) ? 0 : sourceUrl.hashCode());
		result = prime * result + ((targetFilePath == null) ? 0 : targetFilePath.hashCode());
		result = prime * result + ((targetUrl == null) ? 0 : targetUrl.hashCode());
		result = prime * result + ((translatedFrom == null) ? 0 : translatedFrom.hashCode());
		result = prime * result + ((translatedTo == null) ? 0 : translatedTo.hashCode());
		result = prime * result
				+ ((translationLastActionDateUTC == null) ? 0 : translationLastActionDateUTC.hashCode());
		result = prime * result + ((translationProgress == null) ? 0 : translationProgress.hashCode());
		result = prime * result + ((translationStartedAtUTC == null) ? 0 : translationStartedAtUTC.hashCode());
		result = prime * result + ((translationStatus == null) ? 0 : translationStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationJob other = (TranslationJob) obj;
		if (characterCharged == null) {
			if (other.characterCharged != null)
				return false;
		} else if (!characterCharged.equals(other.characterCharged))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobStatus == null) {
			if (other.jobStatus != null)
				return false;
		} else if (!jobStatus.equals(other.jobStatus))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (sourceFilePath == null) {
			if (other.sourceFilePath != null)
				return false;
		} else if (!sourceFilePath.equals(other.sourceFilePath))
			return false;
		if (sourceUrl == null) {
			if (other.sourceUrl != null)
				return false;
		} else if (!sourceUrl.equals(other.sourceUrl))
			return false;
		if (targetFilePath == null) {
			if (other.targetFilePath != null)
				return false;
		} else if (!targetFilePath.equals(other.targetFilePath))
			return false;
		if (targetUrl == null) {
			if (other.targetUrl != null)
				return false;
		} else if (!targetUrl.equals(other.targetUrl))
			return false;
		if (translatedFrom == null) {
			if (other.translatedFrom != null)
				return false;
		} else if (!translatedFrom.equals(other.translatedFrom))
			return false;
		if (translatedTo == null) {
			if (other.translatedTo != null)
				return false;
		} else if (!translatedTo.equals(other.translatedTo))
			return false;
		if (translationLastActionDateUTC == null) {
			if (other.translationLastActionDateUTC != null)
				return false;
		} else if (!translationLastActionDateUTC.equals(other.translationLastActionDateUTC))
			return false;
		if (translationProgress == null) {
			if (other.translationProgress != null)
				return false;
		} else if (!translationProgress.equals(other.translationProgress))
			return false;
		if (translationStartedAtUTC == null) {
			if (other.translationStartedAtUTC != null)
				return false;
		} else if (!translationStartedAtUTC.equals(other.translationStartedAtUTC))
			return false;
		if (translationStatus == null) {
			if (other.translationStatus != null)
				return false;
		} else if (!translationStatus.equals(other.translationStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TranslationJob [jobId=" + jobId + ", createDate=" + createDate + ", requestId=" + requestId
				+ ", jobStatus=" + jobStatus + ", sourceFilePath=" + sourceFilePath + ", targetFilePath="
				+ targetFilePath + ", sourceUrl=" + sourceUrl + ", targetUrl=" + targetUrl + ", translatedFrom="
				+ translatedFrom + ", translatedTo=" + translatedTo + ", documentId=" + documentId
				+ ", translationStartedAtUTC=" + translationStartedAtUTC + ", translationLastActionDateUTC="
				+ translationLastActionDateUTC + ", translationStatus=" + translationStatus + ", translationProgress="
				+ translationProgress + ", characterCharged=" + characterCharged + "]";
	}

    
}

