package com.bootcamp.demo_yahoofinance.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoteResponseDto {
    private QuoteResponse quoteResponse; 

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class QuoteResponse {
        private List<Result> result;
        private Object error;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @Setter
    public static class Result {
        private String language;
        private String region;
        private String quoteType;
        private String typeDisp;
        private String quoteSourceName;
        private boolean triggerable;
        private String customPriceAlertConfidence;
        private String currency;
        private Long gmtOffSetMilliseconds;
        private String market;
        private boolean esgPopulated;
        private String marketState;
        private String shortName;
        private String longName;
        private List<CorporateActions> corporateActions;
        private Long regularMarketTime;
        private String exchange;
        private String messageBoardID;
        private String exchangeTimezoneName;
        private String exchangeTimezoneShortName;
        private Double regularMarketChangePercent;
        private Double regularMarketPrice;
        private boolean hasPrePostMarketData;
        private Long firstTradeDateMilliseconds;
        private Long priceHint;
        private Double regularMarketChange;
        private Long regularMarketDayHigh;
        private String regularMarketDayRange;
        private Long regularMarketDayLow;
        private Long regularMarketVolume;
        private Double regularMarketPreviousClose;
        private Double bid;
        private Double ask;
        private Long bidSize;
        private Long askSize;
        private String fullExchangeName;
        private String financialCurrency;
        private Long regularMarketOpen;
        private Long averageDailyVolume3Month;
        private Long averageDailyVolume10Day;
        private Double fiftyTwoWeekLowChange;
        private Double fiftyTwoWeekLowChangePercent;
        private String fiftyTwoWeekRange;
        private Double fiftyTwoWeekHighChange;
        private Double fiftyTwoWeekHighChangePercent;
        private Double fiftyTwoWeekLow;
        private Double fiftyTwoWeekHigh;
        private Double fiftyTwoWeekChangePercent;
        private Long earningsTimestamp;
        private Long earningsTimestampStart;
        private Long earningsTimestampEnd;
        private Long earningsCallTimestampStart;
        private Long earningsCallTimestampEnd;
        private boolean isEarningsDateEstimate;
        private Double trailingAnnualDividendRate;
        private Double trailingPE;
        private Double dividendRate;
        private Double trailingAnnualDividendYield;
        private Double dividendYield;
        private Double epsTrailingTwelveMonths;
        private Double epsForward;
        private Double epsCurrentYear;
        private Double priceEpsCurrentYear;
        private Long sharesOutstanding;
        private Double bookValue;
        private Double fiftyDayAverage;
        private Double fiftyDayAverageChange;
        private Double fiftyDayAverageChangePercent;
        private Double twoHundredDayAverage;
        private Double twoHundredDayAverageChange;
        private Double twoHundredDayAverageChangePercent;
        private Long marketCap;
        private Double forwardPE;
        private Double priceToBook;
        private Long sourceInterval;
        private Long exchangeDataDelayedBy;
        private String averageAnalystRating;
        private boolean tradeable;
        private boolean cryptoTradeable;
        private String symbol;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Setter
    public static class CorporateActions {
        private String headers;
        private String message;
        private Meta meta;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Setter
        public static class Meta {
            private String eventType;
            private String dateEpochMs;
            private Double amount;
        }
    }

    
}

