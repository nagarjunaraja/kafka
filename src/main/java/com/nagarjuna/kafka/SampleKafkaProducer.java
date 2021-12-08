package com.nagarjuna.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SampleKafkaProducer {

	public static void main(String[] args) throws Exception {


		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		ProducerRecord producerRecord = new ProducerRecord("replicated-topic", "user_ui_action","next_page_click");
		 producer.send(producerRecord);
		 producer.close();
	}
}