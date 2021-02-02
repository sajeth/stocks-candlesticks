package com.saji.stocks.candle.util;


import com.saji.stocks.candle.pojo.Candle;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CandleUtil {
    public static final Predicate<Candle> BULLISH = Candlestick -> Candlestick.getClose()
            .compareTo(Candlestick.getOpen()) > 0;

    public static final Predicate<Candle> BEARISH = Candlestick -> Candlestick.getOpen()
            .compareTo(Candlestick.getClose()) > 0;
    //	private static final Predicate<Candle> ALL_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW_LARGER_THAN_BODY
//			.and(LOWER_SHADOW_LARGER_THAN_BODY);
    public static final Predicate<List<Candle>> THREE_WHITE_SOLDIERS = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle middle = candles.get(candles.size() - 2);
        Candle first = candles.get(candles.size() - 3);
        return !(BULLISH.test(last) || BULLISH.test(middle) || BULLISH.test(first));
    };
    public static final Predicate<List<Candle>> THREE_BLACK_CROWS = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle middle = candles.get(candles.size() - 2);
        Candle first = candles.get(candles.size() - 3);
        return !(BEARISH.test(last) || BEARISH.test(middle) || BEARISH.test(first));
    };
    public static final Predicate<List<Candle>> EVENING_DOJI_STAR = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> FALLING_WINDOW = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> MORNING_DOJI_STAR = (Candles) -> {
        return false;
    };

    public static final Predicate<List<Candle>> ON_NECKLINE = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> TWO_BEARISH_GAPPING = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> THREE_BEARISH_CROWS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> THREE_BULLISH_SOLDIERS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> THREE_LINE_STRIKE = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> TWEEZER_BOTTOMS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> TWEEZER_TOPS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> DOJI_STAR = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> RISING_WINDOW = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> EVENING_STAR = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle middle = candles.get(candles.size() - 2);
        Candle first = candles.get(candles.size() - 3);
        return BEARISH.test(last) && BULLISH.test(first) &&
                (middle.getOpen().compareTo(last.getOpen()) > 0
                        || middle.getClose().compareTo(last.getOpen()) > 0);
    };
    public static final Predicate<List<Candle>> MORNING_STAR = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle middle = candles.get(candles.size() - 2);
        Candle first = candles.get(candles.size() - 3);
        return BULLISH.test(last) && BEARISH.test(first) &&
                (middle.getOpen().compareTo(last.getOpen()) < 0
                        || middle.getClose().compareTo(last.getOpen()) < 0);
    };
    public static final Predicate<List<Candle>> ABANDONED_BABY = (Candles) -> {
        return false;
    };

    public static final Predicate<Candle> DOJI = (Candle) -> {
        return body(Candle).abs().equals(BigDecimal.valueOf(0));
    };

    public static final Predicate<List<Candle>> HANGING_MAN = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> HAMMER = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> INVERTED_BEARISH_HAMMER = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> INVERTED_HAMMER = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> SHOOTING_STAR = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> BULLISH_BODY = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> SHAVEN_BOTTOM = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> SHAVEN_HEAD = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> BEARISH_HARAMI = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BEARISH.test(last) && BULLISH.test(lastBefore)
                && last.getClose().compareTo(lastBefore.getOpen()) > 0
                && last.getOpen().compareTo(lastBefore.getClose()) > 0;
    };
    public static final Predicate<List<Candle>> BEARISH_HARAMI_CROSS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> BEARISH_3_METHOD_FORMATION = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> BULLISH_3_METHOD_FORMATION = (Candles) -> {
        return false;
    };

    public static final Predicate<List<Candle>> BULLISH_HARAMI = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BULLISH.test(last) && BEARISH.test(lastBefore)
                && lastBefore.getClose().compareTo(last.getOpen()) > 0
                && lastBefore.getOpen().compareTo(last.getClose()) > 0;
    };
    public static final Predicate<List<Candle>> BULLISH_HARAMI_CROSS = (Candles) -> {
        return false;
    };
    public static final Predicate<List<Candle>> DARK_CLOUD_COVER = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BEARISH.test(lastBefore) &&
                last.getOpen().compareTo(lastBefore.getLow()) < 0;
    };

    public static final Predicate<List<Candle>> PIERCING_PATTERN = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BULLISH.test(lastBefore) &&
                last.getOpen().compareTo(lastBefore.getHigh()) > 0;
    };

    public static final Predicate<List<Candle>> BEARISH_ENGULFING = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BULLISH.test(lastBefore) &&
                BEARISH.test(last) &&
                !(lastBefore.getOpen().compareTo(last.getOpen()) > 0) &&
                !(lastBefore.getClose().compareTo(last.getClose()) > 0);
    };
    public static final Predicate<List<Candle>> BULLISH_ENGULFING = (candles) -> {
        Candle last = candles.get(candles.size() - 1);
        Candle lastBefore = candles.get(candles.size() - 2);
        return BEARISH.test(lastBefore) &&
                BULLISH.test(last) &&
                !(lastBefore.getOpen().compareTo(last.getOpen()) > 0) &&
                !(lastBefore.getClose().compareTo(last.getClose()) > 0);
    };
    private static final Predicate<Candle> UPPER_SHADOW = Candlestick -> Candlestick.getHigh()
            .compareTo(Candlestick.getOpen().max(Candlestick.getClose())) > 0;
    private static final Predicate<Candle> LOWER_SHADOW = (
            Candlestick) -> Candlestick.getOpen().min(Candlestick.getClose()).compareTo(Candlestick.getLow()) > 0;
    private static final Predicate<Candle> ALL_SHADOW = UPPER_SHADOW.and(LOWER_SHADOW);
    public static final Predicate<Candle> LONG_LOWER_SHADOW = (Candle) -> ALL_SHADOW
            .and(val -> body(val).multiply(BigDecimal.valueOf(3)).compareTo(tail(val)) < 0).test(Candle);
    public static final Predicate<Candle> LONG_UPPER_SHADOW = (Candle) -> ALL_SHADOW
            .and(val -> body(val).multiply(BigDecimal.valueOf(3)).compareTo(wick(val)) < 0).test(Candle);

    public static final Predicate<Candle> BEARISH_PIN = Candle -> (DOJI.negate()).and(BEARISH).and(LONG_UPPER_SHADOW)
            .test(Candle);
    public static final Predicate<Candle> BULLISH_PIN = Candle -> (DOJI.negate()).and(BULLISH).and(LONG_LOWER_SHADOW)
            .test(Candle);

    private static final Predicate<Candle> ANY_SHADOW = UPPER_SHADOW.or(LOWER_SHADOW);
    private static final Predicate<Candle> NO_SHADOW = ANY_SHADOW.negate();

    public static final Predicate<Candle> MARUBOZU = (Candle) -> {
        return NO_SHADOW.and(DOJI.negate()).test(Candle);
    };
    public static final Predicate<Candle> BULLISH_MARUBOZU = Candle -> MARUBOZU.and(BULLISH).test(Candle);
    public static final Predicate<Candle> BEARISH_MARUBOZU = Candle -> MARUBOZU.and(BEARISH).test(Candle);
    public static final Predicate<List<Candle>> LONG_LINE = (Candles) -> {
        BigDecimal avg = averageDistance(Candles, 25);
        final Candle ohlc = Candles.get(0);
        return size(ohlc).compareTo(avg.multiply(BigDecimal.valueOf(0.7))) >= 0;
    };
    public static final Predicate<List<Candle>> SHORT_LINE = LONG_LINE.negate();

    public static final Predicate<Candle> DRAGONFLY_DOJI = Candle -> DOJI.and(LOWER_SHADOW).and(UPPER_SHADOW.negate())
            .test(Candle);
    public static final Predicate<Candle> GRAVESTONE_DOJI = Candle -> DOJI.and(UPPER_SHADOW).and(LOWER_SHADOW.negate())
            .test(Candle);

    private static final Map<String, BigDecimal> CACHE_RESULT = new LinkedHashMap<String, BigDecimal>(21, .75F, true) {

        @Override
        public boolean removeEldestEntry(Map.Entry<String, BigDecimal> eldest) {
            return size() > 20;
        }
    };

    public static final Predicate<List<Candle>> LONG_Candle = (Candles) -> {
        final int avgPeriod = 10;

        if (Candles.size() < avgPeriod) {
            return false;
        }

        return body(Candles.get(0)).compareTo(averageBody(Candles, avgPeriod).multiply(BigDecimal.valueOf(3))) >= 0;
    };
    private static final Predicate<Candle> EQUAL_SHADOW = Candlestick -> Candlestick.getHigh().subtract(max(Candlestick.getOpen(), Candlestick.getClose()))
            .compareTo(min(Candlestick.getOpen(), Candlestick.getClose()).subtract(Candlestick.getLow())) == 0;
    public static final Predicate<Candle> NEUTRAL_DOJI = (Candle) -> {
        return DOJI.and(EQUAL_SHADOW).and(val -> wick(val).compareTo(BigDecimal.valueOf(1)) < 0)
                .and(val -> tail(val).compareTo(BigDecimal.valueOf(1)) < 0).test(Candle);
    };
    public static final Predicate<Candle> LONG_LEGGED_DOJI = (Candle) -> {
        return DOJI.and(EQUAL_SHADOW).and(val -> wick(val).compareTo(BigDecimal.valueOf(1)) > 0)
                .and(val -> tail(val).compareTo(BigDecimal.valueOf(1)) > 0).test(Candle);
    };
    private static final Predicate<Candle> UPPER_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW.and((Candle) -> {
        return (Candle.getHigh().subtract(max(Candle.getOpen(), Candle.getClose()))).compareTo(body(Candle)) > 0;
    });
    private static final Predicate<Candle> LOWER_SHADOW_LARGER_THAN_BODY = LOWER_SHADOW.and((Candle) -> (min(Candle.getOpen(), Candle.getClose()).subtract(Candle.getLow())).compareTo(body(Candle)) > 0);
    public static final Predicate<Candle> SPINNING_TOP = (Candle) -> {
        return LOWER_SHADOW_LARGER_THAN_BODY.and(UPPER_SHADOW_LARGER_THAN_BODY).and(EQUAL_SHADOW).test(Candle);
    };
    private static final Predicate<Candle> ANY_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW_LARGER_THAN_BODY
            .or(LOWER_SHADOW_LARGER_THAN_BODY);
    private static final Predicate<Candle> NO_SHADOW_LARGER_THAN_BODY = ANY_SHADOW_LARGER_THAN_BODY.negate();
    public static final Predicate<List<Candle>> LONG_BEARISH_Candle = (Candles) -> {
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        return BEARISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((e) -> LONG_Candle.test(Candles))
                .and((t) -> LONG_LINE.test(Candles)).test(Candles.get(0));
    };
    public static final Predicate<List<Candle>> BEARISH_Candle = (Candles) -> {
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│▓│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        return BEARISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((t) -> LONG_LINE.test(Candles))
                .and((e) -> LONG_Candle.negate().test(Candles)).test(Candles.get(0));
    };
    public static final Predicate<List<Candle>> LONG_BULLISH_Candle = (Candles) -> {
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        return BULLISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((e) -> LONG_Candle.test(Candles))
                .and((t) -> LONG_LINE.test(Candles)).test(Candles.get(0));
    };
    public static final Predicate<List<Candle>> BULLISH_Candle = (Candles) -> {
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░┌┴┐░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░│░│░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░└┬┘░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        // ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        return BULLISH.and(ALL_SHADOW).and(NO_SHADOW_LARGER_THAN_BODY).and((t) -> LONG_LINE.test(Candles))
                .and((e) -> LONG_Candle.negate().test(Candles)).test(Candles.get(0));
    };

    private static BigDecimal cached(final String method, final List<Candle> Candles, final int period,
                                     final Supplier<BigDecimal> exec) {
        String key = method + "_" + Candles.hashCode() + "_" + period;
        BigDecimal value = CACHE_RESULT.get(key);
        if (value == null) {
            value = exec.get();
            CACHE_RESULT.put(key, value);
        }
        return value;
    }

    public static BigDecimal body(Candle Candlestick) {
        BigDecimal value;
        if (BULLISH.test(Candlestick)) {
            value = Candlestick.getClose().subtract(Candlestick.getOpen());
        } else if (BEARISH.test(Candlestick)) {
            value = Candlestick.getOpen().subtract(Candlestick.getClose());
        } else {
            value = BigDecimal.valueOf(0L);
        }
        return value;
    }

    public static BigDecimal wick(Candle Candlestick) {
        return Candlestick.getHigh().subtract(max(Candlestick.getOpen(), Candlestick.getClose()));
    }

    public static BigDecimal tail(Candle Candlestick) {
        return min(Candlestick.getOpen(), Candlestick.getClose()).subtract(Candlestick.getLow());
    }

    private static final BigDecimal max(BigDecimal val1, BigDecimal val2) {
        return val1.max(val2);
    }

    private static final BigDecimal min(BigDecimal val1, BigDecimal val2) {
        return val1.min(val2);
    }

    private static BigDecimal size(final Candle Candle) {
        return Candle.getHigh().subtract(Candle.getLow());
    }

    private static BigDecimal averageBody(List<Candle> candles, int period) {
        return cached("averageBody", candles, period, () -> {
            BigDecimal sum = BigDecimal.valueOf(0);
            for (int i = 0; i < candles.size(); i++) {
                final Candle ohlc = candles.get(i);
                sum = sum.add(body(ohlc));
            }

            return sum.divide(min(BigDecimal.valueOf(candles.size()), BigDecimal.valueOf(period)));
        });
    }

    private static BigDecimal averageDistance(List<Candle> list, int period) {
        return cached("averageDistance", list, period, () -> {
            double exponent = 2.0 / (period + 1);
            int start = Math.min(list.size() - 1, period);
            Candle ohlc = list.get(start);
            BigDecimal ema = size(ohlc);
            for (int i = start - 1; i >= 0; i--) {
                ohlc = list.get(i);
                ema = size(ohlc).multiply(BigDecimal.valueOf(exponent))
                        .add(ema.multiply(BigDecimal.valueOf(1 - exponent)));
            }

            return ema;
        });

    }
}
