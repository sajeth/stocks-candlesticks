package com.saji.stocks.candle.services;

import com.saji.stocks.candle.constants.CandleTypes;
import com.saji.stocks.candle.constants.Pairs;
import com.saji.stocks.candle.pojo.Candle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class CandleStickService {

    private static String determineCandleType(Candle candle) {
        Optional<Map.Entry<CandleTypes, Predicate<Candle>>> output = Pairs.singlecandles.entrySet().stream().filter(
                val -> val.getValue().test(candle)
        ).findAny();
        return output.isPresent() ? output.get().getKey().name() : "Not found";
    }

    public static String determineCandleType(List<Candle> list) {
        Optional<Map.Entry<CandleTypes, Predicate<List<Candle>>>> output = Pairs.multiplecandles.entrySet().stream()
                .filter(val -> val.getValue().test(list)).findAny();
        return output.isPresent() ? output.get().getKey().name() : determineCandleType(list.get(list.size() - 1));
    }
}
