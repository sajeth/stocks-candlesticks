package com.saji.stocks.candle.util;

import com.saji.stocks.candle.lib.FivePredicate;
import com.saji.stocks.candle.lib.TriPredicate;
import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class CandleUtil {
    private static Function<HistoricalQuote, BigDecimal> head = val -> val.getHigh().subtract(val.getOpen().max(val.getClose()));
    private static Function<HistoricalQuote, BigDecimal> tail = val -> val.getOpen().min(val.getClose()).subtract(val.getLow());
    private static Function<HistoricalQuote, BigDecimal> body = val -> val.getOpen().subtract(val.getClose()).abs();

    private static final BiPredicate<BigDecimal, BigDecimal> IS_FIRST_GREATER = (val1, val2) -> val1.compareTo(val2) > 0;
    private static final BiPredicate<BigDecimal, BigDecimal> IS_FIRST_LESSER = (val1, val2) -> val1.compareTo(val2) < 0;
    private static final BiPredicate<BigDecimal, BigDecimal> ARE_EQUAL = (val1, val2) -> val1.compareTo(val2) == 0;

    public static final Predicate<HistoricalQuote> BULLISH = val -> val.getClose().compareTo(val.getOpen()) > 0;
    public static final Predicate<HistoricalQuote> BEARISH = val -> val.getOpen().compareTo(val.getClose()) > 0;
    private static Predicate<HistoricalQuote> LONG_BODY = val -> body.apply(val).compareTo(tail.apply(val).add(head.apply(val))) > 0;
    public static final Predicate<HistoricalQuote> LONG_BEARISH_CANDLE = val -> BEARISH.and(LONG_BODY).test(val);
    public static final Predicate<HistoricalQuote> LONG_BULLISH_CANDLE = val -> BULLISH.and(LONG_BODY).test(val);
    public static final BiPredicate<HistoricalQuote, HistoricalQuote> BEARISH_BODY_VALIDATION = (val1, val2) -> {

        if (BEARISH.test(val2)) {
            return !IS_FIRST_GREATER.test(val2.getOpen(), val1.getOpen()) && !IS_FIRST_GREATER.test(val2.getClose(), val1.getClose());
        } else {
            return !IS_FIRST_GREATER.test(val2.getClose(), val1.getOpen()) && !IS_FIRST_GREATER.test(val2.getOpen(), val1.getClose());
        }
    };
    public static final BiPredicate<HistoricalQuote, HistoricalQuote> BULLISH_BODY_VALIDATION = (val1, val2) -> {

        if (BULLISH.test(val2)) {
            return !IS_FIRST_GREATER.test(val2.getOpen(), val1.getOpen()) && !IS_FIRST_GREATER.test(val2.getClose(), val1.getClose());
        } else {
            return !IS_FIRST_GREATER.test(val2.getClose(), val1.getOpen()) && !IS_FIRST_GREATER.test(val2.getOpen(), val1.getClose());
        }
    };

    public static final Predicate<HistoricalQuote> DOJI = val -> body.apply(val).abs().equals(BigDecimal.ZERO);
    private static final Predicate<HistoricalQuote> UPPER_SHADOW = val -> IS_FIRST_GREATER.test(head.apply(val), BigDecimal.ZERO);
    private static final Predicate<HistoricalQuote> LOWER_SHADOW = val -> IS_FIRST_GREATER.test(tail.apply(val), BigDecimal.ZERO);
    private static final Predicate<HistoricalQuote> EQUAL_SHADOW = val -> ARE_EQUAL.test(head.apply(val), tail.apply(val));
    public static final Predicate<HistoricalQuote> DRAGONFLY_DOJI = HistoricalQuote -> DOJI.and(LOWER_SHADOW).and(UPPER_SHADOW.negate())
            .test(HistoricalQuote);
    public static final Predicate<HistoricalQuote> GRAVESTONE_DOJI = HistoricalQuote -> DOJI.and(UPPER_SHADOW).and(LOWER_SHADOW.negate())
            .test(HistoricalQuote);
    public static final Predicate<HistoricalQuote> NEUTRAL_DOJI = (HistoricalQuote) -> {
        return DOJI.and(EQUAL_SHADOW).and(val -> IS_FIRST_LESSER.test(head.apply(val), (BigDecimal.ONE)))
                .and(val -> tail.apply(val).compareTo(BigDecimal.ONE) < 0).test(HistoricalQuote);
    };
    public static final Predicate<HistoricalQuote> LONG_LEGGED_DOJI = (HistoricalQuote) -> {
        return DOJI.and(EQUAL_SHADOW).and(val -> head.apply(val).compareTo(BigDecimal.ONE) > 0)
                .and(val -> tail.apply(val).compareTo(BigDecimal.ONE) > 0).test(HistoricalQuote);
    };
    public static final Predicate<HistoricalQuote> ANY_DOJI = (val) -> {
        return DOJI.or(LONG_LEGGED_DOJI).or(NEUTRAL_DOJI).or(GRAVESTONE_DOJI).or(DRAGONFLY_DOJI).test(val);
    };

    private static Predicate<HistoricalQuote> SHORT_BODY = val -> !ANY_DOJI.test(val) && body.apply(val).compareTo(tail.apply(val).add(head.apply(val))) < 0;
    private static Predicate<HistoricalQuote> LONG_WICK = val -> !ANY_DOJI.test(val) && (BigDecimal.valueOf(2L).multiply(body.apply(val))).compareTo(tail.apply(val).add(head.apply(val))) < 0;
    public static final TriPredicate THREE_WHITE_SOLDIERS = (last, middle, first) -> {
        return !(BULLISH.test(last) || BULLISH.test(middle) || BULLISH.test(first));
    };
    public static final TriPredicate THREE_BLACK_CROWS = (last, middle, first) -> !(BEARISH.test(last) || BEARISH.test(middle) || BEARISH.test(first));

    public static final TriPredicate EVENING_DOJI_STAR = (last, middle, first) -> BEARISH.test(last) && BULLISH.test(first) && (ANY_DOJI).test(middle) && IS_FIRST_LESSER.test(last.getOpen(), first.getClose());

    public static final TriPredicate MORNING_DOJI_STAR = (last, middle, first) -> BULLISH.test(last) && BEARISH.test(first) && ANY_DOJI.test(middle) && IS_FIRST_GREATER.test(first.getOpen(), middle.getHigh());

    public static final TriPredicate ON_NECKLINE = (last, middle, first) -> (BEARISH.test(last) && BULLISH.test(middle) && !IS_FIRST_GREATER.test(middle.getClose(), last.getClose())) || (BEARISH.test(middle) && BULLISH.test(first) && !IS_FIRST_GREATER.test(first.getClose(), middle.getClose()));
    public static final TriPredicate FALLING_WINDOW = (last, middle, first) -> IS_FIRST_GREATER.test(first.getLow(), middle.getHigh()) || IS_FIRST_GREATER.test(middle.getLow(), first.getHigh());
    public static final TriPredicate BEARISH_HARAMI = (last, middle, first) -> (BEARISH.test(last) && BULLISH.test(middle) && !IS_FIRST_GREATER.test(middle.getHigh(), last.getOpen())
            && !IS_FIRST_GREATER.test(middle.getLow(), last.getClose()) ||
            (BEARISH.test(middle) && BULLISH.test(first)
                    && !IS_FIRST_GREATER.test(first.getHigh(), middle.getOpen())
                    && !IS_FIRST_GREATER.test(first.getLow(), middle.getClose())));
    public static final TriPredicate BULLISH_HARAMI = (last, middle, first) -> (BULLISH.test(last) && BEARISH.test(middle) && !IS_FIRST_GREATER.test(middle.getHigh(), last.getClose())
            && !IS_FIRST_GREATER.test(middle.getLow(), last.getClose()) ||
            (BULLISH.test(middle) && BEARISH.test(first)
                    && !IS_FIRST_GREATER.test(first.getHigh(), middle.getClose())
                    && !IS_FIRST_GREATER.test(first.getLow(), middle.getOpen())));
    public static final BiPredicate<HistoricalQuote, HistoricalQuote> BEARISH_HARAMI_CROSS = (middle, first) -> LONG_BULLISH_CANDLE.test(middle) && ANY_DOJI.test(first) && !IS_FIRST_LESSER.test(first.getLow(), middle.getOpen()) && !IS_FIRST_GREATER.test(first.getHigh(), middle.getClose());
    public static final BiPredicate<HistoricalQuote, HistoricalQuote> BULLISH_HARAMI_CROSS = (middle, first) -> LONG_BEARISH_CANDLE.test(middle) && ANY_DOJI.test(first) && !IS_FIRST_LESSER.test(first.getLow(), middle.getClose()) && !IS_FIRST_GREATER.test(first.getHigh(), middle.getOpen());
    public static final BiPredicate<HistoricalQuote, HistoricalQuote> BEARISH_PIN = (middle, first) -> BULLISH.test(middle) && LONG_BODY.test(middle) && BEARISH.test(first) && IS_FIRST_GREATER.test(first.getHigh(), middle.getHigh()) && !IS_FIRST_GREATER.test(first.getClose(), middle.getClose()) && IS_FIRST_GREATER.test(first.getLow(), middle.getLow());
    public static final FivePredicate BEARISH_3_METHOD = (five, four, three, two, one) -> BEARISH.test(five) && BEARISH.test(one) && BEARISH_BODY_VALIDATION.test(five, two) && BEARISH_BODY_VALIDATION.test(five, three) && BEARISH_BODY_VALIDATION.test(five, four);
    public static final FivePredicate BULLISH_3_METHOD = (five, four, three, two, one) -> BULLISH.test(five) && BULLISH.test(one) && BULLISH_BODY_VALIDATION.test(five, two) && BULLISH_BODY_VALIDATION.test(five, three) && BULLISH_BODY_VALIDATION.test(five, four);


}
