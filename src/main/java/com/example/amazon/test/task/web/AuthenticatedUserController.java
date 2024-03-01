package com.example.amazon.test.task.web;

import com.example.amazon.test.task.services.StatisticsService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthenticatedUserController {
    private final StatisticsService statisticsService;

    @GetMapping("/byDate")
    public ResponseEntity<?> getStatisticsByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(statisticsService.getStatisticsByDate(date));
    }

    @GetMapping("/byDateRange")
    public ResponseEntity<?> getStatisticsByDateRange(@RequestParam("startDate") String startDate,
                                                      @RequestParam("endDate") String endDate) {
        return ResponseEntity.ok(statisticsService.getStatisticsByDateRange(startDate, endDate));
    }

    @GetMapping("/byAsin")
    public ResponseEntity<?> getStatisticsByAsin(@RequestParam("asin") String asin) {
        return ResponseEntity.ok(statisticsService.getStatisticsByAsin(asin));
    }

    @GetMapping("/byAsins")
    public ResponseEntity<?> getStatisticsByAsins(@RequestParam("asins") List<String> asins) {
        return ResponseEntity.ok(statisticsService.getStatisticsByAsins(asins));
    }
    @GetMapping("/totalByDate")
    public ResponseEntity<?> getTotalStatisticsByDate() {
        return ResponseEntity.ok(statisticsService.getTotalStatisticsByDate());
    }

    @GetMapping("/totalByAsin")
    public ResponseEntity<?> getTotalStatisticsByAsin() {
        return ResponseEntity.ok(statisticsService.getTotalStatisticsByAsin());
    }
}
