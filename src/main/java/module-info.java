/**
 * @author saji 06-Apr-2018
 */
module stocks.candlesticks {
    requires transitive stocks.finance;
    requires transitive stocks.analysis;
    exports com.saji.stocks.candle.util to stocks.mongo;
    exports com.saji.stocks.candle.services to stocks.mongo;
    exports com.saji.stocks.candle.pojo to stocks.mongo, stocks.redis, stocks.batch, stocks.core, com.fasterxml.jackson.databind;
}