/*-
 * ============LICENSE_START=======================================================
 * VID
 * ================================================================================
 * Copyright (C) 2017 - 2019 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.vid.aai.model.AaiGetNetworkCollectionDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Relationship {

	@JsonProperty("related-to")
	public String relatedTo;

	@JsonProperty("related-link")
	public String relatedLink;

	@JsonProperty("relationship-label")
	public String relationshipLabel;

	@JsonProperty("relationship-data")
	public List<RelationshipData> relationshipData;

	@JsonProperty("related-to-property")
	public List<RelatedToProperty> relatedToProperty;


	public String getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}

	public String getRelatedLink() {
		return relatedLink;
	}

	public void setRelatedLink(String relatedLink) {
		this.relatedLink = relatedLink;
	}

	public List<RelationshipData> getRelationDataList() {
		return relationshipData;
	}

	public void setRelationDataList(List<RelationshipData> relationDataList) {
		this.relationshipData = relationDataList;
	}

	public List<RelatedToProperty> getRelatedToPropertyList() {
		return relatedToProperty;
	}

	public void setRelatedToPropertyList(List<RelatedToProperty> relatedToPropertyList) {
		this.relatedToProperty = relatedToPropertyList;
	}

	public String getRelationshipLabel() {
		return relationshipLabel;
	}

	public void setRelationshipLabel(String relationshipLabel) {
		this.relationshipLabel = relationshipLabel;
	}
}
