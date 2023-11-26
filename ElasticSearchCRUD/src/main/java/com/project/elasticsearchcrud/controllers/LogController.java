package com.project.elasticsearchcrud.controllers;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.project.elasticsearchcrud.models.Log;
import com.project.elasticsearchcrud.services.ElasticSearchService;
import com.project.elasticsearchcrud.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/logs")
    public Iterable<Log> getAll() {
        return logService.getLogs();
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {

        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        return searchResponse.hits().hits().toString();
    }

    //matchAllProducts video content
    @GetMapping("/matchAllLogs")
    public List<Log> matchAllProducts() throws IOException {
        SearchResponse<Log> searchResponse =  elasticSearchService.matchAllLogServices();
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Log>> listOfHits= searchResponse.hits().hits();
        List<Log> listOfLogs  = new ArrayList<>();
        for(Hit<Log> hit : listOfHits){
            Log hitLog = hit.source();
            if(hitLog != null) hitLog.setId(hit.id());
            listOfLogs.add(hitLog);
        }
        return listOfLogs;
    }
    @GetMapping("/boolQuery")
    public List<Log> boolQuery(@RequestParam String levelName, @RequestParam String message) throws IOException {

        SearchResponse<Log> searchResponse =  elasticSearchService.boolQueryImpl(levelName, message);
        System.out.println(searchResponse.hits().hits().toString());
        List<Hit<Log>> listOfHits= searchResponse.hits().hits();
        List<Log> listOfProducts  = new ArrayList<>();
        for(Hit<Log> hit : listOfHits){
            Log hitLog = hit.source();
            if(hitLog != null) hitLog.setId(hit.id());
            listOfProducts.add(hitLog);
        }
        return listOfProducts;
    }

    @PostMapping("/logs")
    public ResponseEntity<Log> insertLog(@RequestBody Log log) {
        Log savedLog = logService.ingestLog(log);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

}
