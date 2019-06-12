package io.github.qyvlik.mongoohlcv.entity;


import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
        @CompoundIndex(name = "tick_unique_index",
                def = "{'symbol': 1, 'dealId': 1}", unique = true),
        @CompoundIndex(name = "tick_kline_index",
                def = "{'symbol': 1, 'timestamp': 1, 'price': 1}"),
})
public class Tick {

    public static final int BUY = 0;
    public static final int SELL = 1;

    @Id
    private String id;
    private Long dealId;
    private String symbol;
    private Decimal128 price;
    private Decimal128 quantity;
    private Integer direction;
    private Long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Decimal128 getPrice() {
        return price;
    }

    public void setPrice(Decimal128 price) {
        this.price = price;
    }

    public Decimal128 getQuantity() {
        return quantity;
    }

    public void setQuantity(Decimal128 quantity) {
        this.quantity = quantity;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Tick{" +
                "dealId=" + dealId +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", direction=" + direction +
                ", timestamp=" + timestamp +
                '}';
    }
}
