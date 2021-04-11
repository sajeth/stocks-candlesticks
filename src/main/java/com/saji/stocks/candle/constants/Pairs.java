package com.saji.stocks.candle.constants;


import com.saji.stocks.candle.lib.FivePredicate;
import com.saji.stocks.candle.lib.TriPredicate;
import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class Pairs {
    public static final Map<Indication, List<Predicate<HistoricalQuote>>> singlePairs = new HashMap<>();
    public static final Map<Indication, List<BiPredicate<HistoricalQuote, HistoricalQuote>>> dualPairs = new HashMap<>();
    public static final Map<Indication, List<TriPredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote>>> triPairs = new HashMap<>();
    public static final Map<Indication, List<FivePredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote>>> multiplePairs = new HashMap<>();
    public static final Map<Predicate<HistoricalQuote>, CandleTypes> singles = new HashMap<>();
    public static final Map<BiPredicate<HistoricalQuote, HistoricalQuote>, CandleTypes> duals = new HashMap<>();
    public static final Map<TriPredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote>, CandleTypes> triples = new HashMap<>();
    public static final Map<FivePredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote>, CandleTypes> multiples = new HashMap<>();

    static {
        // singles.put();
        List<Predicate<HistoricalQuote>> sellList = new ArrayList<>();

        singlePairs.put(Indication.BULLISH_REVERSAL, sellList);
        List<Predicate<HistoricalQuote>> buyList = new ArrayList<>();

        singlePairs.put(Indication.BEARISH_REVERSAL, buyList);
        //---------------------------------------------------------------------
        // duals.put();
        List<BiPredicate<HistoricalQuote, HistoricalQuote>> sellList1 = new ArrayList<>();

        dualPairs.put(Indication.BULLISH_REVERSAL, sellList1);
        List<BiPredicate<HistoricalQuote, HistoricalQuote>> buyList1 = new ArrayList<>();

        dualPairs.put(Indication.BEARISH_REVERSAL, buyList1);

        //---------------------------------------------------------------------
        // triples.put();
        List<TriPredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote>> sellList2 = new ArrayList<>();

        triPairs.put(Indication.BEARISH_REVERSAL, sellList2);
        List<TriPredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote>> buyList2 = new ArrayList<>();

        triPairs.put(Indication.BEARISH_REVERSAL, buyList2);
        //---------------------------------------------------------------------
        //multiples.put();
        List<FivePredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote>> sellList3 = new ArrayList<>();

        multiplePairs.put(Indication.BULLISH_REVERSAL, sellList3);
        List<FivePredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote>> buyList3 = new ArrayList<>();

        multiplePairs.put(Indication.BEARISH_REVERSAL, buyList3);
    }

}
