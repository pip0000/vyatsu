package com.vyatsu.task8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        String[] words = { "Orange", "Apple", "Apple", "Apple", "Grape", "Grape", "Grape" };
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Степан",20,20_000),
                new Person("Александр",35,50_000),
                new Person("Михаил",26,35_000),
                new Person("Арсений",21,20_000),
                new Person("Степан",24,35_000)
        ));

        //1 задание
        System.out.println(
                Stream.of(words)
                        .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                        .entrySet().stream()
                        .filter(o -> o.getValue() == Stream.of(words)
                                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                                .values().stream().mapToInt(v->v.intValue()).max().getAsInt())
                        .map(Map.Entry::getKey)
                        .collect(Collectors.joining(", ","Самые частые слова ",""))
        );

        //2 задание
                System.out.println(
                        persons.stream()
                                .mapToDouble( person -> person.getSalary())
                                .average()
                                .getAsDouble()
                );

        //3 задание
        System.out.println(
                persons.stream()
                        .sorted((o1,o2) -> o2.getAge() - o1.getAge())
                        .map(person -> person.getName())
                        .limit(3)
                        .collect(Collectors.joining(", ","Имена 3 старших сотрудников ","."))
        );
    }
}
