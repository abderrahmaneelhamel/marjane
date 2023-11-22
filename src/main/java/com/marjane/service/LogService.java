package com.marjane.service;

import com.marjane.model.LogEntry;
import com.marjane.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<LogEntry> getAllLogs() {
        return logRepository.findAll();
    }

    public LogEntry getLogById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log entry not found with id: " + id));
    }

    public LogEntry createLog(LogEntry logEntry) {
        return logRepository.save(logEntry);
    }

    public LogEntry updateLog(Long id, LogEntry logEntry) {
        LogEntry existingLog = getLogById(id);
        existingLog.setAction(logEntry.getAction());
        existingLog.setTimestamp(logEntry.getTimestamp());

        return logRepository.save(existingLog);
    }

    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }
}
