package com.vyatsu.task9.entity;

import com.vyatsu.task9.Breeds;
import com.vyatsu.task9.annotation.Column;
import com.vyatsu.task9.annotation.Table;

@Table(name="Cat")
public class Cat {

    @Column(name = "name", isNullable = false)
    private String name;

    @Column(name = "age", isNullable = false)
    private Integer age;

    @Column(name = "growth", isNullable = false)
    private Integer growth;

    @Column(name = "weight", isNullable = false)
    private Integer weight;

    @Column(name = "breed", isNullable = false)
    private Breeds breed;

    public Cat(String name, Integer age, Integer growth, Integer weight, Breeds breed) {
        this.name = name;
        this.age = age;
        this.growth = growth;
        this.weight = weight;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Breeds getBreed() {
        return breed;
    }

    public void setBreed(Breeds breed) {
        this.breed = breed;
    }
}
