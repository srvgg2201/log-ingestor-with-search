package com.project.elasticsearchcrud.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RegexpQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {
    public static Supplier<Query> supplier() {
        return () -> Query.of(q -> q.matchAll(matchAllQuery()));
    }
    public static MatchAllQuery matchAllQuery() {
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierQueryForBoolQuery(String levelName, String message){
        Supplier<Query> supplier = ()->Query.of(q->q.bool(boolQuery(levelName, message)));
        return supplier;
    }

    public static BoolQuery boolQuery(String levelName, String message){

        val boolQuery  = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(levelName, message)).build();
    }

    public static List<Query> termQuery(String levelName, String message){
        final List<Query> terms = new ArrayList<>();
        val termQuery = new MatchQuery.Builder();
        terms.add(Query.of(q->q
                .match(termQuery.field("level").query(levelName).build())));

        return terms;
    }

    public static List<Query> matchQuery(String message){
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q->q.match(matchQuery.field("message").query(message).build())));

        return matches;
    }
}
