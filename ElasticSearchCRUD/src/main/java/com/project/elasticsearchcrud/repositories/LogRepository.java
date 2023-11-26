package com.project.elasticsearchcrud.repositories;

import com.project.elasticsearchcrud.models.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ElasticsearchRepository<Log, String> {
}
