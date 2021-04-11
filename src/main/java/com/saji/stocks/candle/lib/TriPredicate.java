package com.saji.stocks.candle.lib;


import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;

@FunctionalInterface
public interface TriPredicate<x, y, z> {

    public boolean test(HistoricalQuote x, HistoricalQuote y, HistoricalQuote z);
}
