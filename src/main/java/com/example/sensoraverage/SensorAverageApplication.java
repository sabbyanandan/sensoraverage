package com.example.sensoraverage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableBinding(Processor.class)
public class SensorAverageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorAverageApplication.class, args);
	}

	@StreamListener
	@Output(Processor.OUTPUT)
	public Flux<Average> calculateAverage(@Input(Processor.INPUT) Flux<Sensor> data) {
		return data.window(Duration.ofSeconds(3)).flatMap(
				window -> window.groupBy(sensorData -> sensorData.getId()).flatMap(group -> calculateAverage(group)));
	}

	private Mono<Average> calculateAverage(GroupedFlux<Integer, Sensor> group) {
		return group
				.reduce(new Accumulator(0, 0),
						(a, d) -> new Accumulator(a.getCount() + 1, a.getTotalValue() + d.getTemperature()))
				.map(accumulator -> new Average(group.key(), (accumulator.getTotalValue()) / accumulator.getCount()));
	}
}
