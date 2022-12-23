package com.vyatsu.task9.entity;

import com.vyatsu.task9.Job;
import com.vyatsu.task9.annotation.Column;
import com.vyatsu.task9.annotation.Table;

@Table(name="Oleg")
public class Oleg {

    @Column(name = "name", isNullable = false)
    private String name;

    @Column(name = "age", isNullable = false)
    private Integer age;

    @Column(name = "growth", isNullable = false)
    private Integer growth;

    @Column(name = "weight", isNullable = false)
    private Integer weight;

    @Column(name = "job", isNullable = false)
    private Job job;

    public Oleg(String name, Integer age, Integer growth, Integer weight, Job job) {
        this.name = name;
        this.age = age;
        this.growth = growth;
        this.weight = weight;
        this.job = job;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}