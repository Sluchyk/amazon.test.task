package com.example.amazon.test.task.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportOptions {
        private String dateGranularity;
        private String asinGranularity;
    }
}
