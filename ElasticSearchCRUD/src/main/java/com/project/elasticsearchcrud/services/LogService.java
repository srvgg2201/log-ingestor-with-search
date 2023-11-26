package com.project.elasticsearchcrud.services;

import com.project.elasticsearchcrud.models.Log;
import com.project.elasticsearchcrud.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Log ingestLog(Log log) {
        return logRepository.save(log);
    }

    public Iterable<Log> ingestLogs(Iterable<Log> logs) {
        return logRepository.saveAll(logs);
    }

    public Iterable<Log> getLogs() {
        return logRepository.findAll();
    }


}
