package com.example.amazon.test.task.services;

import com.example.amazon.test.task.model.SalesAndTrafficByAsin;
import com.example.amazon.test.task.model.SalesAndTrafficByDate;
import java.io.IOException;
import java.util.List;

public interface StatisticsService {
    SalesAndTrafficByDate getStatisticsByDate(String date);

    List<SalesAndTrafficByDate> getStatisticsByDateRange(String startDate, String endDate);

    SalesAndTrafficByAsin getStatisticsByAsin(String asin);

    List<SalesAndTrafficByAsin> getStatisticsByAsins(List<String> asins);

    List<SalesAndTrafficByDate> getTotalStatisticsByDate();

    List<SalesAndTrafficByAsin> getTotalStatisticsByAsin();
    void updateDataBase() throws IOException;
}
