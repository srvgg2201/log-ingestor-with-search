package com.project.elasticsearchcrud.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Metadata {
    private String parentResourceId;
}
