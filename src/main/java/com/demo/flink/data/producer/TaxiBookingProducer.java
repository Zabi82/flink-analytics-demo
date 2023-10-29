package com.demo.flink.data.producer;

import com.demo.flink.constant.LocationTheme;

public interface TaxiBookingProducer {
    void produceTaxiBookingMessage(LocationTheme locationTheme, int delay_ms);
}
