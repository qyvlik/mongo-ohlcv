package io.github.qyvlik.mongoohlcv.service;

import io.github.qyvlik.mongoohlcv.entity.KLine;
import io.github.qyvlik.mongoohlcv.entity.Tick;
import io.github.qyvlik.mongoohlcv.request.KlineRequest;
import org.bson.types.Decimal128;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TickServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TickService tickService;

    @Test
    public void saveTicks() {
        Tick tick = TickBuilder.builder(
                101377485095L,
                "btc_usdt",
                new BigDecimal("7821.75"),
                new BigDecimal("0.179"),
                Tick.SELL,
                1560302946337L
        );
        tickService.saveTicks(tick);


        tick = TickBuilder.builder(
                101377485024L,
                "btc_usdt",
                new BigDecimal("7821.69"),
                new BigDecimal("0.0396"),
                Tick.SELL,
                1560302946002L
        );
        tickService.saveTicks(tick);

        tick = TickBuilder.builder(
                101377485018L,
                "btc_usdt",
                new BigDecimal("7821.69"),
                new BigDecimal("0.0717"),
                Tick.SELL,
                1560302945977L
        );
        tickService.saveTicks(tick);

        tick = TickBuilder.builder(
                101377484926L,
                "btc_usdt",
                new BigDecimal("7821.68"),
                new BigDecimal("0.0574"),
                Tick.SELL,
                1560302945352L
        );
        tickService.saveTicks(tick);
    }

    @Test
    public void findKLines() {
        KlineRequest request = new KlineRequest();
        request.setFrom(1560302945352L);
        request.setTo(1560302946337L + 1);
        request.setPeriod(60);
        request.setSymbol("btc_usdt");
        List<KLine> kLines = tickService.findKLines(request);
        logger.info("findKLines:{}", kLines);
    }

    public static class TickBuilder {
        public static Tick builder(Long dealId, String symbol, BigDecimal price, BigDecimal quantity, Integer direction, Long timestamp) {
            Tick tick = new Tick();
            tick.setDealId(dealId);
            tick.setSymbol(symbol);
            tick.setPrice(new Decimal128(price));
            tick.setQuantity(new Decimal128(quantity));
            tick.setDirection(direction);
            tick.setTimestamp(timestamp);
            return tick;
        }
    }
}
