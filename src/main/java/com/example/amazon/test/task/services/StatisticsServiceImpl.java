package com.example.amazon.test.task.services;

import com.example.amazon.test.task.exceptions.NotFoundException;
import com.example.amazon.test.task.model.SalesAndTrafficByAsin;
import com.example.amazon.test.task.model.SalesAndTrafficByDate;
import com.example.amazon.test.task.model.StatisticEntity;
import com.example.amazon.test.task.model.StatisticsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
@CacheConfig(cacheNames = "statisticsCache")
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository repository;
    private final ResourceLoader resourceLoader;
    @Override
    @Cacheable(key = "#currentDate")
    public SalesAndTrafficByDate getStatisticsByDate(String currentDate) {
        return repository.findAll().get(0).getSalesAndTrafficByDate()
                .stream().filter(date -> date.getDate().equals(currentDate))
                .findFirst()
                .orElseThrow(()->new NotFoundException("Sales and traffic on date: "+currentDate+ " not found"));
    }

    @Override
    @Cacheable(key = "#startDate + '-' + #endDate")
    public List<SalesAndTrafficByDate> getStatisticsByDateRange(String startDate, String endDate) {
        return repository.findAll().get(0).getSalesAndTrafficByDate().stream().filter(date->{
            LocalDate dateValue = LocalDate.parse(date.getDate());
            return (dateValue.isEqual(LocalDate.parse(startDate)) || dateValue.isAfter(LocalDate.parse(startDate)))
                    && (dateValue.isEqual(LocalDate.parse(endDate)) || dateValue.isBefore(LocalDate.parse(endDate)));
        }).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#asin")
    public SalesAndTrafficByAsin getStatisticsByAsin(String asin) {
        return repository.findAll().get(0).getSalesAndTrafficByAsin().stream()
                .filter(obj -> obj.getParentAsin().equals(asin))
                .findFirst().orElseThrow(()-> new  NotFoundException("Statistic by asin: "+asin+ " not found"));
    }

    @Override
    @Cacheable(key = "#asins")
    public List<SalesAndTrafficByAsin> getStatisticsByAsins(List<String> asins) {
        return repository.findAll().get(0).getSalesAndTrafficByAsin().stream()
                .filter(obj-> asins.contains(obj.getParentAsin())).collect(Collectors.toList());
    }

    @Override
    @Cacheable("totalStatisticsByDate")
    public List<SalesAndTrafficByDate> getTotalStatisticsByDate() {
        return repository.findAll().get(0).getSalesAndTrafficByDate();
    }

    @Override
    @Cacheable("totalStatisticsByAsin")
    public List<SalesAndTrafficByAsin> getTotalStatisticsByAsin() {
        return repository.findAll().get(0).getSalesAndTrafficByAsin();
    }
    @CacheEvict(allEntries = true, value = {"statisticsCache", "totalStatisticsByDate", "totalStatisticsByAsin"})
    @Scheduled(fixedRate = 60000)
    @Override
    public void updateDataBase() throws IOException {
        String jsonContent = readJsonFile();
        StatisticEntity entity = new ObjectMapper().readValue(jsonContent, StatisticEntity.class);
        repository.deleteAll();
        repository.save(entity);
    }

    private String readJsonFile() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\bosss\\Downloads\\test_report.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
