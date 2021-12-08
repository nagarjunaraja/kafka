package com.nagarjuna.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SampleKafkaConsumer {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "testgroup");
		props.put("auto.offset.reset", "earliest");

		Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("replicated-topic"));
		consumer.poll(0);
		consumer.seekToBeginning(consumer.assignment());

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : consumerRecords)
				System.out.println(record.value());
		}

	}
}