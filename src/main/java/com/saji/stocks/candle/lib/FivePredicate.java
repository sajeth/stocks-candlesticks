package com.saji.stocks.candle.lib;

import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;

@FunctionalInterface
public interface FivePredicate<A, B, C, D, E> {

    public boolean test(HistoricalQuote A, HistoricalQuote B, HistoricalQuote C, HistoricalQuote D, HistoricalQuote E);
}
