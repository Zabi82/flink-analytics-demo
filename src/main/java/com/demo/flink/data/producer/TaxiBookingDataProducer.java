package com.demo.flink.data.producer;

import com.demo.flink.constant.LocationTheme;
import com.demo.flink.model.TaxiBooking;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class TaxiBookingDataProducer {

	public static final String TAXI_RIDES_TOPIC = "taxi_ride";

	public void produceTaxiBookingMessage(LocationTheme locationTheme, int delay_ms) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class);


		try(KafkaProducer<String, TaxiBooking> kafkaProducer = new KafkaProducer<>(props)) {
			long count = 0;
			while (true) {

				TaxiBooking booking = (count % 2 == 0) ? TaxiBooking.newRandomBooking() : TaxiBooking.newRandomBooking(locationTheme);
				ProducerRecord<String, TaxiBooking> record = new ProducerRecord<>(TAXI_RIDES_TOPIC, booking.getRide_id(), booking);
				//call back handler lambda for to evaluate the result of sending a record
				kafkaProducer.send(record, (recordMetaData, exception) -> {
					if (recordMetaData != null) {
						System.out.printf("Produced message with key = %s and value = %s in topic = %s and partition = %s with offset = %s \n",
								booking.getRide_id(), booking, recordMetaData.topic(), recordMetaData.partition(), recordMetaData.offset());
					} else if (exception != null) {
						System.out.println("Exception occurred " + exception);
						exception.printStackTrace();
					}
				});
				Thread.sleep(delay_ms);
				count++;
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		 new TaxiBookingDataProducer().produceTaxiBookingMessage(LocationTheme.LONG_WEEKEND, 500);
	}
}