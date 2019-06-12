package io.github.qyvlik.mongoohlcv.entity;

import org.bson.types.Decimal128;

public class KLine {
    private Decimal128 open;                // 开盘价
    private Decimal128 high;                // 最高价
    private Decimal128 low;                 // 最低价
    private Decimal128 close;               // 收盘价
    private Decimal128 volume;              // 成交量
    private Decimal128 turnover;            // 成交额
    private Long ts;                        // 统计开始时间
    private Integer period;                 // 统计周期

    public Decimal128 getOpen() {
        return open;
    }

    public void setOpen(Decimal128 open) {
        this.open = open;
    }

    public Decimal128 getHigh() {
        return high;
    }

    public void setHigh(Decimal128 high) {
        this.high = high;
    }

    public Decimal128 getLow() {
        return low;
    }

    public void setLow(Decimal128 low) {
        this.low = low;
    }

    public Decimal128 getClose() {
        return close;
    }

    public void setClose(Decimal128 close) {
        this.close = close;
    }

    public Decimal128 getVolume() {
        return volume;
    }

    public void setVolume(Decimal128 volume) {
        this.volume = volume;
    }

    public Decimal128 getTurnover() {
        return turnover;
    }

    public void setTurnover(Decimal128 turnover) {
        this.turnover = turnover;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "KLine{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", turnover=" + turnover +
                ", ts=" + ts +
                ", period=" + period +
                '}';
    }
}
