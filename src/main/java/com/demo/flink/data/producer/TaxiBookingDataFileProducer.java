package com.demo.flink.data.producer;

import com.demo.flink.constant.LocationTheme;
import com.demo.flink.model.TaxiBooking;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaxiBookingDataFileProducer implements TaxiBookingProducer {


    public static final String INPUT_FOLDER = "/Users/zabeer/flinksql/input/";

    public static TaxiBookingProducer INSTANCE = new TaxiBookingDataFileProducer();

    private TaxiBookingDataFileProducer() {

    }

    @Override
    public void produceTaxiBookingMessage(LocationTheme locationTheme, int delay_ms) {


            long count = 0;
            long batchSize = 10;
            List<TaxiBooking> taxiBookingList = new ArrayList<>();
            try {
                while (true) {

                    TaxiBooking booking = (count % 10 == 0) ? TaxiBooking.newRandomBooking() : TaxiBooking.newRandomBooking(locationTheme);
                    System.out.printf("Produced message %s \n", booking);
                    taxiBookingList.add(booking);
                    if ((count + 1) % batchSize == 0) {

                        File file = new File(INPUT_FOLDER + UUID.randomUUID() + ".json");

                        try (FileWriter writer = new FileWriter(file)) {

                            //write to a new file and reset the list

                            taxiBookingList.forEach(b -> {
                                try {
                                    writer.write(new ObjectMapper().writeValueAsString(b));
                                    writer.write("\n");
                                } catch (IOException e) {
                                    System.out.println("Exception occurred " + e);
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (Exception e) {
                            System.out.println("Exception occurred " + e);
                            e.printStackTrace();
                        }
                        System.out.println("Wrote batch to file ");
                        taxiBookingList = new ArrayList<>();

                    }

                    Thread.sleep(delay_ms);
                    count++;

                }
            }
            catch(Exception e) {
                System.out.println("Exception occurred " + e);
                e.printStackTrace();
            }




    }
}
