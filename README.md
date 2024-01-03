# Simulator to ingest messages to Kafka and File System for the [Flink SQL Demo project](https://github.com/Zabi82/flinksql-demo)

This project contains Java program to simulate Taxi Booking Events in JSON format to Kafka and also 
batched events to File System. A booking event will have fields such as rideId, pickupLocation, 
dropOffLocation, PickupDateTime etc. There are different themes to simulate the booking pattern for a 
regular day, a normal weekend, Friday evening, long weekend etc. This can be configured in DataIngester class 
by using different enums from LocationTheme.java

A sample event published will be as follows

```

{
 "ride_id":"0fe1175c-ea99-4608-9efd-e219ff11ed20",
 "pickupDateTime":1704253351625,
 "pickupLocation":"78 AIRPORT BOULEVARD JEWEL CHANGI AIRPORT SINGAPORE 819666",
 "dropOffLocation":"50 STAMFORD ROAD SINGAPORE MANAGEMENT UNIVERSITY (LEE KONG CHIAN SCH OF BUSINESS) SINGAPORE 178899"
}

```

## Prerequisites

* JDK 11 or higher
* Appropriate Maven version
* Kafka setup as per the [Flink SQL Demo project](https://github.com/Zabi82/flinksql-demo)

## Configuration Options

* Kafka broker details can be updated in TaxiBookingDataKafkaProducer.java
* File system directory can be configured in TaxiBookingDataFileProducer.java. By default it's 
configured to write t $HOME/flinksql/input directory
* Simulation theme can be changed in DataIngester.java by using any of the LocationTheme enum values available

## How to start the simulator ?

Launch the program DataIngester from your IDE.
