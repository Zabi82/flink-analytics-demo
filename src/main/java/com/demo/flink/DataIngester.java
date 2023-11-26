package com.demo.flink;

import com.demo.flink.constant.LocationTheme;
import com.demo.flink.data.producer.TaxiBookingDataFileProducer;
import com.demo.flink.data.producer.TaxiBookingDataKafkaProducer;
import com.demo.flink.data.producer.TaxiBookingProducer;

public class DataIngester {

    public static void main(String[] args) {
        Thread kafkaProducerThread = new Thread(new TaxiBookingProducerRunnable(TaxiBookingDataKafkaProducer.INSTANCE, LocationTheme.LONG_WEEKEND, 500));
        Thread fileProducerThread = new Thread(new TaxiBookingProducerRunnable(TaxiBookingDataFileProducer.INSTANCE, LocationTheme.LONG_WEEKEND, 1000));
        kafkaProducerThread.start();
        fileProducerThread.start();


    }

    static class TaxiBookingProducerRunnable implements  Runnable {

        private final TaxiBookingProducer producer;
        private final LocationTheme locationTheme;
        private final int delay_ms;

        TaxiBookingProducerRunnable(TaxiBookingProducer producer, LocationTheme locationTheme, int delay_ms) {
            this.producer = producer;
            this.locationTheme = locationTheme;
            this.delay_ms = delay_ms;
        }

        @Override
        public void run() {
            producer.produceTaxiBookingMessage(locationTheme, delay_ms);
        }
    }
}
