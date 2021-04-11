//package com.saji.stocks.candle.util;
//
//
//
//import com.saji.stocks.candle.lib.TriPredicate;
//import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;
//
//import java.math.BigDecimal;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
//public class OldCandleUtil {
//
//    //	private static final Predicate<HistoricalQuote> ALL_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW_LARGER_THAN_BODY
////			.and(LOWER_SHADOW_LARGER_THAN_BODY);
//
//
//
//    public static final TriPredicate TWO_BEARISH_GAPPING = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate THREE_BEARISH_CROWS = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate THREE_BULLISH_SOLDIERS = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate THREE_LINE_STRIKE = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate TWEEZER_BOTTOMS = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate TWEEZER_TOPS = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate DOJI_STAR = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate RISING_WINDOW = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate<HistoricalQuote,HistoricalQuote,HistoricalQuote> EVENING_STAR = (last,middle,first)-> {
//        return BEARISH.test(last) && BULLISH.test(first) &&
//                (middle.getOpen().compareTo(last.getOpen()) > 0
//                        || middle.getClose().compareTo(last.getOpen()) > 0);
//    };
//    public static final TriPredicate MORNING_STAR = (last,middle,first) -> {
//        return BULLISH.test(last) && BEARISH.test(first) &&
//                (middle.getOpen().compareTo(last.getOpen()) < 0
//                        || middle.getClose().compareTo(last.getOpen()) < 0);
//    };
//    public static final TriPredicate ABANDONED_BABY = (last,middle,first) -> {
//        return false;
//    };
//
//
//    public static final TriPredicate HANGING_MAN = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate HAMMER = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate INVERTED_BEARISH_HAMMER = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate INVERTED_HAMMER = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate SHOOTING_STAR = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate BULLISH_BODY = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate SHAVEN_BOTTOM = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate SHAVEN_HEAD = (last,middle,first) -> {
//        return false;
//    };
//
////    public static final TriPredicate BEARISH_3_METHOD_FORMATION = (last,middle,first) -> {
////        return false;
////    };
////    public static final TriPredicate BULLISH_3_METHOD_FORMATION = (last,middle,first) -> {
////        return false;
////    };
//
//    public static final TriPredicate BULLISH_HARAMI = (last,lastBefore,empty) -> {
//        return BULLISH.test(last) && BEARISH.test(lastBefore)
//                && lastBefore.getClose().compareTo(last.getOpen()) > 0
//                && lastBefore.getOpen().compareTo(last.getClose()) > 0;
//    };
//    public static final TriPredicate BULLISH_HARAMI_CROSS = (last,middle,first) -> {
//        return false;
//    };
//    public static final TriPredicate<HistoricalQuote,HistoricalQuote,HistoricalQuote> DARK_CLOUD_COVER = (last,lastBefore,empty) -> {
//
//        return BEARISH.test(lastBefore) &&
//                last.getOpen().compareTo(lastBefore.getLow()) < 0;
//    };
//
//    public static final TriPredicate<HistoricalQuote,HistoricalQuote,HistoricalQuote> PIERCING_PATTERN = (last,lastBefore,empty) -> {
//
//        return BULLISH.test(lastBefore) &&
//                last.getOpen().compareTo(lastBefore.getHigh()) > 0;
//    };
//
//    public static final TriPredicate<HistoricalQuote,HistoricalQuote,HistoricalQuote> BEARISH_ENGULFING = (last, lastBefore,empty) -> {
//
//        return BULLISH.test(lastBefore) &&
//                BEARISH.test(last) &&
//                !(lastBefore.getOpen().compareTo(last.getOpen()) > 0) &&
//                !(lastBefore.getClose().compareTo(last.getClose()) > 0);
//    };
//    public static final TriPredicate<HistoricalQuote,HistoricalQuote,HistoricalQuote> BULLISH_ENGULFING = (last,lastBefore,empty) -> {
//
//        return BEARISH.test(lastBefore) &&
//                BULLISH.test(last) &&
//                !(lastBefore.getOpen().compareTo(last.getOpen()) > 0) &&
//                !(lastBefore.getClose().compareTo(last.getClose()) > 0);
//    };
//
//    private static final Predicate<HistoricalQuote> ALL_SHADOW = UPPER_SHADOW.and(LOWER_SHADOW);
//    public static final Predicate<HistoricalQuote> LONG_LOWER_SHADOW = (HistoricalQuote) -> ALL_SHADOW
//            .and(val -> body(val).multiply(BigDecimal.valueOf(3)).compareTo(tail(val)) < 0).test(HistoricalQuote);
//    public static final Predicate<HistoricalQuote> LONG_UPPER_SHADOW = (HistoricalQuote) -> ALL_SHADOW
//            .and(val -> body(val).multiply(BigDecimal.valueOf(3)).compareTo(wick(val)) < 0).test(HistoricalQuote);
//
//
//
//    private static final Predicate<HistoricalQuote> ANY_SHADOW = UPPER_SHADOW.or(LOWER_SHADOW);
//    private static final Predicate<HistoricalQuote> NO_SHADOW = ANY_SHADOW.negate();
//
//    public static final Predicate<HistoricalQuote> MARUBOZU = (HistoricalQuote) -> {
//        return NO_SHADOW.and(DOJI.negate()).test(HistoricalQuote);
//    };
//    public static final Predicate<HistoricalQuote> BULLISH_MARUBOZU = HistoricalQuote -> MARUBOZU.and(BULLISH).test(HistoricalQuote);
//    public static final Predicate<HistoricalQuote> BEARISH_MARUBOZU = HistoricalQuote -> MARUBOZU.and(BEARISH).test(HistoricalQuote);
//    public static final Predicate<List<HistoricalQuote>> LONG_LINE = (HistoricalQuotes) -> {
//        BigDecimal avg = averageDistance(HistoricalQuotes, 25);
//        final HistoricalQuote ohlc = HistoricalQuotes.get(0);
//        return size(ohlc).compareTo(avg.multiply(BigDecimal.valueOf(0.7))) >= 0;
//    };
//    public static final Predicate<List<HistoricalQuote>> SHORT_LINE = LONG_LINE.negate();
//
//
//    private static final Map<String, BigDecimal> CACHE_RESULT = new LinkedHashMap<String, BigDecimal>(21, .75F, true) {
//
//        @Override
//        public boolean removeEldestEntry(Map.Entry<String, BigDecimal> eldest) {
//            return size() > 20;
//        }
//    };
//
//    public static final Predicate<List<HistoricalQuote>> LONG_HistoricalQuote = (HistoricalQuotes) -> {
//        final int avgPeriod = 10;
//
//        if (HistoricalQuotes.size() < avgPeriod) {
//            return false;
//        }
//
//        return body(HistoricalQuotes.get(0)).compareTo(averageBody(HistoricalQuotes, avgPeriod).multiply(BigDecimal.valueOf(3))) >= 0;
//    };
//
//    private static final Predicate<HistoricalQuote> UPPER_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW.and((HistoricalQuote) -> {
//        return (HistoricalQuote.getHigh().subtract(max(HistoricalQuote.getOpen(), HistoricalQuote.getClose()))).compareTo(body(HistoricalQuote)) > 0;
//    });
//    private static final Predicate<HistoricalQuote> LOWER_SHADOW_LARGER_THAN_BODY = LOWER_SHADOW.and((HistoricalQuote) -> (min(HistoricalQuote.getOpen(), HistoricalQuote.getClose()).subtract(HistoricalQuote.getLow())).compareTo(body(HistoricalQuote)) > 0);
//    public static final Predicate<HistoricalQuote> SPINNING_TOP = (HistoricalQuote) -> {
//        return LOWER_SHADOW_LARGER_THAN_BODY.and(UPPER_SHADOW_LARGER_THAN_BODY).and(EQUAL_SHADOW).test(HistoricalQuote);
//    };
//    private static final Predicate<HistoricalQuote> ANY_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW_LARGER_THAN_BODY
//            .or(LOWER_SHADOW_LARGER_THAN_BODY);
//    private static final Predicate<HistoricalQuote> NO_SHADOW_LARGER_THAN_BODY = ANY_SHADOW_LARGER_THAN_BODY.negate();
//    public static final Predicate<List<HistoricalQuote>> LONG_BEARISH_HistoricalQuote = (HistoricalQuotes) -> {
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        return BEARISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((e) -> LONG_HistoricalQuote.test(HistoricalQuotes))
//                .and((t) -> LONG_LINE.test(HistoricalQuotes)).test(HistoricalQuotes.get(0));
//    };
//    public static final Predicate<List<HistoricalQuote>> BEARISH_HistoricalQuote = (HistoricalQuotes) -> {
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        return BEARISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((t) -> LONG_LINE.test(HistoricalQuotes))
//                .and((e) -> LONG_HistoricalQuote.negate().test(HistoricalQuotes)).test(HistoricalQuotes.get(0));
//    };
//    public static final Predicate<List<HistoricalQuote>> LONG_BULLISH_HistoricalQuote = (HistoricalQuotes) -> {
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        return BULLISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((e) -> LONG_HistoricalQuote.test(HistoricalQuotes))
//                .and((t) -> LONG_LINE.test(HistoricalQuotes)).test(HistoricalQuotes.get(0));
//    };
//    public static final Predicate<List<HistoricalQuote>> BULLISH_HistoricalQuote = (HistoricalQuotes) -> {
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
//        return BULLISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((t) -> LONG_LINE.test(HistoricalQuotes))
//                .and((e) -> LONG_HistoricalQuote.negate().test(HistoricalQuotes)).test(HistoricalQuotes.get(0));
//    };
//
//    private static BigDecimal cached(final String method, final List<HistoricalQuote> HistoricalQuotes, final int period,
//                                     final Supplier<BigDecimal> exec) {
//        String key = method + "_" + HistoricalQuotes.hashCode() + "_" + period;
//        BigDecimal value = CACHE_RESULT.get(key);
//        if (value == null) {
//            value = exec.get();
//            CACHE_RESULT.put(key, value);
//        }
//        return value;
//    }
//
////    public static BigDecimal body(HistoricalQuote HistoricalQuotestick) {
////        BigDecimal value;
////        if (BULLISH.test(HistoricalQuotestick)) {
////            value = HistoricalQuotestick.getClose().subtract(HistoricalQuotestick.getOpen());
////        } else if (BEARISH.test(HistoricalQuotestick)) {
////            value = HistoricalQuotestick.getOpen().subtract(HistoricalQuotestick.getClose());
////        } else {
////            value = BigDecimal.valueOf(0L);
////        }
////        return value;
////    }
//
//    public static BigDecimal wick(HistoricalQuote HistoricalQuotestick) {
//        return HistoricalQuotestick.getHigh().subtract(max(HistoricalQuotestick.getOpen(), HistoricalQuotestick.getClose()));
//    }
//
//    public static BigDecimal tail(HistoricalQuote HistoricalQuotestick) {
//        return min(HistoricalQuotestick.getOpen(), HistoricalQuotestick.getClose()).subtract(HistoricalQuotestick.getLow());
//    }
//
//    private static final BigDecimal max(BigDecimal val1, BigDecimal val2) {
//        return val1.max(val2);
//    }
//
//    private static final BigDecimal min(BigDecimal val1, BigDecimal val2) {
//        return val1.min(val2);
//    }
//
//    private static BigDecimal size(final HistoricalQuote HistoricalQuote) {
//        return HistoricalQuote.getHigh().subtract(HistoricalQuote.getLow());
//    }
//
//    private static BigDecimal averageBody(List<HistoricalQuote> HistoricalQuotes, int period) {
//        return cached("averageBody", HistoricalQuotes, period, () -> {
//            BigDecimal sum = BigDecimal.valueOf(0);
//            for (int i = 0; i < HistoricalQuotes.size(); i++) {
//                final HistoricalQuote ohlc = HistoricalQuotes.get(i);
//                sum = sum.add(body(ohlc));
//            }
//
//            return sum.divide(min(BigDecimal.valueOf(HistoricalQuotes.size()), BigDecimal.valueOf(period)));
//        });
//    }
//
//    private static BigDecimal averageDistance(List<HistoricalQuote> list, int period) {
//        return cached("averageDistance", list, period, () -> {
//            double exponent = 2.0 / (period + 1);
//            int start = Math.min(list.size() - 1, period);
//            HistoricalQuote ohlc = list.get(start);
//            BigDecimal ema = size(ohlc);
//            for (int i = start - 1; i >= 0; i--) {
//                ohlc = list.get(i);
//                ema = size(ohlc).multiply(BigDecimal.valueOf(exponent))
//                        .add(ema.multiply(BigDecimal.valueOf(1 - exponent)));
//            }
//
//            return ema;
//        });
//
//    }
//}
