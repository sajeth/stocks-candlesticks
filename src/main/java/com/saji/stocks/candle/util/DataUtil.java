package com.saji.stocks.candle.util;

import com.saji.stocks.analysis.core.BarSeries;
import com.saji.stocks.analysis.core.BaseBarSeries;
import com.saji.stocks.candle.pojo.StocksMetaData;
import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;

import java.time.ZonedDateTime;
import java.util.List;

public class DataUtil {
    public static StocksMetaData analyseData(BarSeries barSeries, StocksMetaData stocksMetaData) {
        //stocksMetaData.setSma200(new SMABuy(barSeries,200).execute());
        return stocksMetaData;
    }

    public static BarSeries loadData(List<HistoricalQuote> history, String symbol) {

        BarSeries barSeries = new BaseBarSeries(symbol);
        history.forEach(val -> {
            barSeries.addBar(ZonedDateTime.ofInstant(val.getDate().toInstant(), java.time.ZoneId.systemDefault()), val.getOpen().doubleValue(), val.getHigh().doubleValue(), val.getLow().doubleValue(), val.getClose().doubleValue(), val.getVolume().doubleValue());
        });
        return barSeries;
    }


}
