package com.feeham.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
		flatMapCase1();
		flatMapCase2();
		task2();
	}

private static void flatMapCase1(){
	List<String> sentences = Arrays.asList("Water of sea is blue.",
			"But in Cox's bazar, its gray.", "Lets go St Martin!");
	int totalWordsCount = (int) sentences.stream()
			.flatMap(sentence ->
					Arrays.stream(sentence.split(" ")))
			.count();
	System.out.println(totalWordsCount);
}

private static void flatMapCase2(){
	List<List<Integer>> nestedList = Arrays.asList(
			Arrays.asList(1, 2, 3),
			Arrays.asList(4, 5, 6),
			Arrays.asList(1, 2, 3));
	List<Integer> unique = nestedList.stream()
			.flatMap(list -> list.stream())
			.sorted()
			.collect(Collectors.toList());
	System.out.println(unique);
}

	private static void task2(){
		Flux<String> flux1 = Flux.just("Water", "of", "the", "sea", "is", "blue.");
		Flux<String> flux2 = Flux.just("But", "in", "Cox's", "Bazar,", "it's", "gray.");
		Flux<String> flux3 = Flux.just("Let's", "go", "to", "St.", "Martin!");

		// Merge with
		flux1.mergeWith(flux2).mergeWith(flux3)
				.map(String :: toUpperCase)
				.flatMap(s -> Flux.fromArray(s.split(" ")))
				.reduce((s1, s2) -> s1 + " " + s2)
				.subscribe(System.out::println);

		// Concatenate
		flux1.concatWith(flux2).concatWith(flux3)
				.map(String :: toUpperCase)
				.flatMap(s -> Flux.fromArray(s.split(" ")))
				.reduce((s1, s2) -> s1 + " " + s2)
				.subscribe(System.out::println);

		// Zip
		flux1.zipWith(flux2, (str1, str2) -> str1 + " " + str2)
				.zipWith(flux3, (str1, str2) -> str1 + " " + str2)
				.map(String :: toUpperCase)
				.flatMap(s -> Flux.fromArray(s.split(" ")))
				.reduce((s1, s2) -> s1 + " " + s2)
				.subscribe(System.out::println);
	}
}