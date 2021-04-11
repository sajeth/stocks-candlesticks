package com.saji.stocks.candle.pojo;

import com.saji.stocks.candle.constants.CandleTypes;
import com.saji.stocks.candle.constants.Indication;

import java.math.BigDecimal;

public class StocksMetaData {


    private CandleTypes candleType;
    private Indication indication;
    private String pattern;
    private BigDecimal low;
    private BigDecimal avgLow;
    private BigDecimal high;
    private BigDecimal avgHigh;


    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getAvgLow() {
        return avgLow;
    }

    public void setAvgLow(BigDecimal avgLow) {
        this.avgLow = avgLow;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getAvgHigh() {
        return avgHigh;
    }

    public void setAvgHigh(BigDecimal avgHigh) {
        this.avgHigh = avgHigh;
    }

    public CandleTypes getCandleType() {
        return candleType;
    }

    public void setCandleType(CandleTypes candleType) {
        this.candleType = candleType;
    }

    public Indication getIndication() {
        return indication;
    }

    public void setIndication(Indication indication) {
        this.indication = indication;
    }

}

