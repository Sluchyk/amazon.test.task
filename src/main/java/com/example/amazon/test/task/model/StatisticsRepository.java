package com.example.amazon.test.task.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<StatisticEntity,String> {

}
