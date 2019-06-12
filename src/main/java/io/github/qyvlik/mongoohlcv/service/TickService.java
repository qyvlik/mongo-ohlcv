package io.github.qyvlik.mongoohlcv.service;

import io.github.qyvlik.mongoohlcv.entity.KLine;
import io.github.qyvlik.mongoohlcv.entity.Tick;
import io.github.qyvlik.mongoohlcv.request.KlineRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void saveTicks(Tick tick) {
        try {
            tick.setId(tick.getSymbol() + ":" + tick.getDealId());
            mongoTemplate.insert(tick);
        } catch (Exception e) {
            logger.error("saveTicks:{}", tick, e.getMessage());
        }
    }

    public List<KLine> findKLines(KlineRequest request) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("symbol").is(request.getSymbol())
                                .and("timestamp").gte(request.getFrom()).lt(request.getTo())
                ),
                Aggregation.sort(
                        Sort.by("timestamp").ascending()
                ),
                Aggregation.project("price", "quantity", "timestamp", "symbol")
                        .andExpression("timestamp - timestamp % [0]", request.getPeriod() * 1000L).as("ts_group")
                        .andExpression("price * quantity").as("money")
                        .andExpression("[0] - 0", request.getPeriod()).as("period_second")
                // .andExpression("[0]", request.getPeriod()).as("period_second")  // not effect
                ,
                Aggregation.group("ts_group")
                        .first("price").as("open")
                        .max("price").as("high")
                        .min("price").as("low")
                        .last("price").as("close")
                        .sum("quantity").as("volume")
                        .sum("money").as("turnover")
                        .addToSet("ts_group").as("ts")
                        .addToSet("period_second").as("period")
        );

        AggregationResults<KLine> klineResults = mongoTemplate.aggregate(aggregation, Tick.class, KLine.class);
        return klineResults.getMappedResults();
    }
}
