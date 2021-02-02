package com.saji.stocks.candle.constants;

import com.saji.stocks.candle.pojo.Candle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.saji.stocks.candle.util.CandleUtil.BEARISH;
import static com.saji.stocks.candle.util.CandleUtil.BEARISH_ENGULFING;
import static com.saji.stocks.candle.util.CandleUtil.BEARISH_HARAMI;
import static com.saji.stocks.candle.util.CandleUtil.BEARISH_MARUBOZU;
import static com.saji.stocks.candle.util.CandleUtil.BEARISH_PIN;
import static com.saji.stocks.candle.util.CandleUtil.BULLISH;
import static com.saji.stocks.candle.util.CandleUtil.BULLISH_ENGULFING;
import static com.saji.stocks.candle.util.CandleUtil.BULLISH_HARAMI;
import static com.saji.stocks.candle.util.CandleUtil.BULLISH_MARUBOZU;
import static com.saji.stocks.candle.util.CandleUtil.BULLISH_PIN;
import static com.saji.stocks.candle.util.CandleUtil.DARK_CLOUD_COVER;
import static com.saji.stocks.candle.util.CandleUtil.DRAGONFLY_DOJI;
import static com.saji.stocks.candle.util.CandleUtil.EVENING_STAR;
import static com.saji.stocks.candle.util.CandleUtil.GRAVESTONE_DOJI;
import static com.saji.stocks.candle.util.CandleUtil.LONG_LEGGED_DOJI;
import static com.saji.stocks.candle.util.CandleUtil.MORNING_STAR;
import static com.saji.stocks.candle.util.CandleUtil.NEUTRAL_DOJI;
import static com.saji.stocks.candle.util.CandleUtil.PIERCING_PATTERN;
import static com.saji.stocks.candle.util.CandleUtil.SPINNING_TOP;
import static com.saji.stocks.candle.util.CandleUtil.THREE_BLACK_CROWS;
import static com.saji.stocks.candle.util.CandleUtil.THREE_WHITE_SOLDIERS;

public final class Pairs {
    public static final Map<Indication, List<Predicate<Candle>>> singleindications = new HashMap<>();
    public static final Map<Indication, List<Predicate<List<Candle>>>> multipleindications = new HashMap<>();
    public static final Map<CandleTypes, Predicate<Candle>> singlecandles = new HashMap<>();
    public static final Map<CandleTypes, Predicate<List<Candle>>> multiplecandles = new HashMap<>();

    static {
        singlecandles.put(CandleTypes.SPINNING_TOP, SPINNING_TOP);
        singlecandles.put(CandleTypes.BEARISH_MARUBOZU, BEARISH_MARUBOZU);
        singlecandles.put(CandleTypes.BULLISH_MARUBOZU, BULLISH_MARUBOZU);
        singlecandles.put(CandleTypes.NEUTRAL_DOJI, NEUTRAL_DOJI);
        singlecandles.put(CandleTypes.LONG_LEGGED_DOJI, LONG_LEGGED_DOJI);
        singlecandles.put(CandleTypes.DRAGONFLY_DOJI, DRAGONFLY_DOJI);
        singlecandles.put(CandleTypes.GRAVESTONE_DOJI, GRAVESTONE_DOJI);
        singlecandles.put(CandleTypes.BEARISH_PIN, BEARISH_PIN);
        singlecandles.put(CandleTypes.BULLISH_PIN, BULLISH_PIN);
        singlecandles.put(CandleTypes.BULLISH, BULLISH);
        singlecandles.put(CandleTypes.BEARISH, BEARISH);
        List<Predicate<Candle>> reversals = new ArrayList<>();
        reversals.add(DRAGONFLY_DOJI);
        reversals.add(GRAVESTONE_DOJI);
        reversals.add(LONG_LEGGED_DOJI);
        reversals.add(NEUTRAL_DOJI);
        reversals.add(SPINNING_TOP);
        reversals.add(BEARISH_PIN);
        reversals.add(BULLISH_PIN);
        singleindications.put(Indication.REVERSAL, reversals);
        List<Predicate<Candle>> buy = new ArrayList<>();
        buy.add(BULLISH_MARUBOZU);
        buy.add(BULLISH_PIN);
        singleindications.put(Indication.BUY, buy);
        List<Predicate<Candle>> sell = new ArrayList<>();
        sell.add(BEARISH_MARUBOZU);
        sell.add(BEARISH_PIN);
        singleindications.put(Indication.SELL, sell);


        multiplecandles.put(CandleTypes.BEARISH_ENGULFING, BEARISH_ENGULFING);
        multiplecandles.put(CandleTypes.BULLISH_ENGULFING, BULLISH_ENGULFING);
        multiplecandles.put(CandleTypes.DARK_CLOUD_COVER, DARK_CLOUD_COVER);
        multiplecandles.put(CandleTypes.PIERCING_PATTERN, PIERCING_PATTERN);
        multiplecandles.put(CandleTypes.BEARISH_HARAMI, BEARISH_HARAMI);
        multiplecandles.put(CandleTypes.BULLISH_HARAMI, BULLISH_HARAMI);
        multiplecandles.put(CandleTypes.EVENING_STAR, EVENING_STAR);
        multiplecandles.put(CandleTypes.MORNING_STAR, MORNING_STAR);
        multiplecandles.put(CandleTypes.THREE_WHITE_SOLDIERS, THREE_WHITE_SOLDIERS);
        multiplecandles.put(CandleTypes.THREE_BLACK_CROWS, THREE_BLACK_CROWS);
        List<Predicate<List<Candle>>> sellList = new ArrayList<>();
        sellList.add(BEARISH_ENGULFING);
        sellList.add(DARK_CLOUD_COVER);
        sellList.add(BEARISH_HARAMI);
        sellList.add(EVENING_STAR);
        sellList.add(THREE_BLACK_CROWS);
        multipleindications.put(Indication.SELL, sellList);
        List<Predicate<List<Candle>>> buyList = new ArrayList<>();
        buyList.add(BULLISH_ENGULFING);
        buyList.add(PIERCING_PATTERN);
        buyList.add(BULLISH_HARAMI);
        buyList.add(MORNING_STAR);
        buyList.add(THREE_WHITE_SOLDIERS);
        multipleindications.put(Indication.BUY, buyList);

    }

}
