package com.saji.stocks.candle.services;

import com.saji.stocks.candle.constants.Pairs;
import com.saji.stocks.candle.lib.FivePredicate;
import com.saji.stocks.candle.lib.TriPredicate;
import com.saji.stocks.candle.pojo.StocksMetaData;
import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;
import com.saji.stocks.finance.yahoo.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CandleStickService {

    private static final Predicate<StocksMetaData> isCandleTypeNonDetermined = val -> null == val.getCandleType() || null == val.getIndication();
    private static Function<HistoricalQuote, BigDecimal> LOSS = val -> val.getOpen().subtract(val.getLow());
    private static Function<HistoricalQuote, BigDecimal> GAIN = val -> val.getHigh().subtract(val.getOpen());

    private static void determineCandleType(HistoricalQuote candle, StocksMetaData metaData) {

        Pairs.singlePairs.entrySet().forEach(x -> {
            for (Predicate<HistoricalQuote> y : x.getValue()) {
                if (y.test(candle)) {
                    metaData.setCandleType(Pairs.singles.get(y));
                    metaData.setIndication(x.getKey());
                    break;
                }
            }
        });
    }

    private static void determineCandleType(HistoricalQuote two, HistoricalQuote one, StocksMetaData metaData) {
        Pairs.dualPairs.entrySet().forEach(x -> {
            for (BiPredicate<HistoricalQuote, HistoricalQuote> y : x.getValue()) {
                if (y.test(two, one)) {
                    metaData.setCandleType(Pairs.duals.get(y));
                    metaData.setIndication(x.getKey());
                    break;
                }
            }
        });
        if (isCandleTypeNonDetermined.test(metaData)) {
            determineCandleType(one, metaData);
        }
    }

    private static void determineCandleType(HistoricalQuote three, HistoricalQuote two, HistoricalQuote one, StocksMetaData metaData) {

        Pairs.triPairs.entrySet().forEach(x -> {
            for (TriPredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote> y : x.getValue()) {
                if (y.test(three, two, one)) {
                    metaData.setCandleType(Pairs.triples.get(y));
                    metaData.setIndication(x.getKey());
                    break;
                }
            }
        });
        if (isCandleTypeNonDetermined.test(metaData)) {
            determineCandleType(two, one, metaData);
        }
    }

    private static void determineCandleType(HistoricalQuote five, HistoricalQuote four, HistoricalQuote three, HistoricalQuote two, HistoricalQuote one, StocksMetaData metaData) {

        Pairs.multiplePairs.entrySet().forEach(x -> {
            if (isCandleTypeNonDetermined.test(metaData)) {
                for (FivePredicate<HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote, HistoricalQuote> y : x.getValue()) {
                    if (y.test(five, four, three, two, one)) {
                        metaData.setCandleType(Pairs.multiples.get(y));
                        metaData.setIndication(x.getKey());
                        break;
                    }
                }
            }
        });
        if (isCandleTypeNonDetermined.test(metaData)) {
            determineCandleType(three, two, one, metaData);
        }
    }

    public static void determineMetaData(StocksMetaData metaData, List<HistoricalQuote> list, StockQuote quote) {
        BigDecimal gain, maxGain = BigDecimal.valueOf(Integer.MIN_VALUE), loss, maxLow = BigDecimal.valueOf(Integer.MIN_VALUE), open = quote.getOpen(), size = BigDecimal.valueOf(list.size());
        Double totalLoss = 0D, totalGains = 0D;
        int size1 = list.size();
        CompletableFuture.runAsync(() -> {
            determineCandleType(list.get(size1 - 5), list.get(size1 - 4), list.get(size1 - 3), list.get(size1 - 2), list.get(size1 - 1), metaData);
        });
        for (HistoricalQuote candle : list) {
            if(candle.getOpen()==null||candle.getLow()==null||candle.getClose()==null||candle.getHigh()==null) {continue;}
            else {
                gain = GAIN.apply(candle);
                loss = LOSS.apply(candle);
                if (gain.compareTo(maxGain) > 0) {
                    maxGain = gain;
                }
                if (loss.compareTo(maxLow) > 0) {
                    maxLow = loss;
                }
                totalGains += gain.doubleValue();
                totalLoss += loss.doubleValue();
            }
            metaData.setLow(open.subtract(maxLow));
            metaData.setHigh(open.add(maxGain));
            metaData.setAvgHigh(open.add(BigDecimal.valueOf(totalGains / size1)));
            metaData.setAvgLow(open.subtract(BigDecimal.valueOf(totalLoss / size1)));
        }

    }
}
