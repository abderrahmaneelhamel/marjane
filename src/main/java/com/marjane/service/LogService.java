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
        // Add any business logic or validation if needed
        return logRepository.save(logEntry);
    }

    public LogEntry updateLog(Long id, LogEntry logEntry) {
        // Add any business logic or validation if needed
        LogEntry existingLog = getLogById(id);
        existingLog.setAction(logEntry.getAction());
        existingLog.setTimestamp(logEntry.getTimestamp());
        // Update other fields as needed
        return logRepository.save(existingLog);
    }

    public void deleteLog(Long id) {
        // Add any business logic or validation if needed
        logRepository.deleteById(id);
    }
}
