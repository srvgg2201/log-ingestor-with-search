package com.project.elasticsearchcrud.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.project.elasticsearchcrud.models.Log;
import com.project.elasticsearchcrud.utils.ElasticSearchUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
        System.out.println("Query: " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Log> matchAllLogServices() throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplier();
        //SearchResponse<Log> searchResponse = elasticsearchClient.search(s->s.index("logs").query(supplier.get()),Log.class);
        SearchResponse<Log> searchResponse = elasticsearchClient.search(s->s.index("logs").query(
                        .query(q -> q
                        .bool(b -> b
                                .filter()))
                ,Log.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Log> boolQueryImpl(String levelName, String message) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierQueryForBoolQuery(levelName, message);
        SearchResponse<Log> searchResponse = elasticsearchClient.search(s->s.index("logs").query(supplier.get()),Log.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }
}
