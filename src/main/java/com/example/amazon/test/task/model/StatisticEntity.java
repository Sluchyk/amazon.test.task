package com.example.amazon.test.task.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "amazon")
public class StatisticEntity {
    @Id
    private String id;

    private ReportSpecification reportSpecification;

    private List<SalesAndTrafficByDate> salesAndTrafficByDate;

    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
