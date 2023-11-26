package com.project.elasticsearchcrud.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.Instant;
import java.util.Date;

@Data
@Document(indexName = "logs")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Log {
    @Id
    private String id;
    private String level;
    private String message;
    private String resourceId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date timestamp;
    private String traceId;
    private String spanId;
    private String commit;
    private Metadata metadata;
}
