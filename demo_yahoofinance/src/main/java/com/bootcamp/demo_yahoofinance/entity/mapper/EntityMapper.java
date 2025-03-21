package com.bootcamp.demo_yahoofinance.entity.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;
import com.bootcamp.demo_yahoofinance.dto.DataDTO;
import com.bootcamp.demo_yahoofinance.entity.TStockPriceEntity;

@Component
public class EntityMapper {

    public TStockPriceEntity map(DataDTO dto) {
        // 假設 dto.getRegularMarketTime() 返回的是毫秒時間戳
        long regularMarketTimeStamp = dto.getRegularMarketTime();

        // 將毫秒時間戳轉換為 ZonedDateTime，並設置為香港時區
        ZonedDateTime regularMarketTime = Instant.ofEpochMilli(regularMarketTimeStamp)
            .atZone(ZoneId.of("Asia/Hong_Kong"));

        return TStockPriceEntity.builder()
            .symbol(dto.getSymbol())
            .regularMarketTime(regularMarketTime) // 使用轉換後的 ZonedDateTime
            .regularMarketPrice(dto.getRegularMarketPrice())
            .regularMarketChangePercent(dto.getRegularMarketChangePercent())
            .bid(dto.getBid())
            .ask(dto.getAsk())
            .build();
    }
}