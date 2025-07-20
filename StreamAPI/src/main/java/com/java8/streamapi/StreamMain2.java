package com.java8.streamapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamMain2 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(8);
		list.add(4);
		list.add(6);
		list.add(2);
		list.add(7);
		list.add(6);
		list.add(6);

		/*
		 * This is not completely using JAVA8 , also instead of defining comparator for
		 * reverse order we can use "Comparator.reverseOrder()" for desending order.
		 */
		// get 3 element after sorting
		System.out.println(list.stream().sorted((l1, l2) -> l2 - l1).toList().get(2));
		System.out.println(list.stream().sorted(Comparator.reverseOrder()).toList().get(2));

		list.stream().sorted(Comparator.reverseOrder()).skip(2).limit(1).forEach(System.out::println);

		// sum of numbers
		System.out.println(list.stream().reduce(Integer::sum).get());

		// avg of numbers
		list.stream().mapToInt(e -> e).average().ifPresentOrElse(System.out::println, () -> System.out.println("Not element found"));

		// square
		System.out.println(list.stream().mapToInt(e -> e * e).average().getAsDouble());

		// starts with
		list.stream().map(String::valueOf).filter(e -> e.startsWith("3")).map(Integer::valueOf)
				.forEach(System.out::println);

		// duplicate
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > 1).forEach(e -> System.out.println(e.getKey()));

		list.stream().filter(e -> Collections.frequency(list, e) > 1).collect(Collectors.toSet())
				.forEach(System.out::println);

		Set<Integer> set = new HashSet<>();
		list.stream().filter(e -> !set.add(e)).collect(Collectors.toSet()).forEach(System.out::println);

		// max
		System.out.println(list.stream().sorted(Comparator.reverseOrder()).findFirst());
		System.out.println(list.stream().max(Comparator.naturalOrder()));
		System.out.println(list.stream().max(Comparator.comparing(Integer::valueOf)));

		// reversing a list using stream
		list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
			Collections.reverse(l);
			return l;
		}));

		//sum of first 2 distinct numbers
		System.out.println(list.stream().distinct().limit(2).reduce(Integer::sum));
		
		// secLowest
		System.out.println("Second Lowest " + list.stream().sorted().distinct().skip(1).findFirst());

		// secHighest
		System.out.println("Second Highest " + list.stream().sorted(Comparator.reverseOrder()).distinct().skip(1).findFirst());

		Optional<Integer> opyInt= Optional.empty();
		System.out.println(opyInt);
	}
}
