package com.saji.stocks.candles.services;

import com.saji.stocks.candle.pojo.StocksMetaData;
import com.saji.stocks.candle.services.CandleStickService;
import com.saji.stocks.finance.yahoo.histquotes.HistoricalQuote;
import com.saji.stocks.finance.yahoo.quotes.stock.StockQuote;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandleStickServiceTest {


    @Test
    public void determineMetaData(){
        StocksMetaData metaData = new StocksMetaData();
        List<HistoricalQuote> list = new ArrayList<HistoricalQuote>();
        HistoricalQuote h1= new HistoricalQuote();
        h1.setOpen(BigDecimal.valueOf(10));
        h1.setHigh(BigDecimal.valueOf(20));
        h1.setLow(BigDecimal.valueOf(5));
        h1.setClose(BigDecimal.valueOf(12));
        h1.setDate(Calendar.getInstance());
        list.add(h1);
        HistoricalQuote h2= new HistoricalQuote();
        h2.setOpen(BigDecimal.valueOf(100));
        h2.setHigh(BigDecimal.valueOf(200));
        h2.setLow(BigDecimal.valueOf(50));
        h2.setClose(BigDecimal.valueOf(120));
        h2.setDate(Calendar.getInstance());
        list.add(h2);
        StockQuote quote = new StockQuote("Test");
        quote.setOpen(BigDecimal.valueOf(200));
        CandleStickService.determineMetaData(metaData,list,quote);
        Assert.assertEquals(metaData.getLow(),BigDecimal.valueOf(150));
      //  Assert.assertEquals(metaData.getAvgLow(),BigDecimal.valueOf(195.0));
    }
}
