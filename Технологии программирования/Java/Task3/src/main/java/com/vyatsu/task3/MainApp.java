package com.vyatsu.task3;

public class MainApp {
    public static void main(String[] args) {
        String[][] rarr = {
                {"1", "2", "3", "4"},
                {"4", "3", "2", "1"},
                {"1", "3", "2", "4"},
                {"4", "2", "3", "1"}
        };
        String[][] warr1 = {
                {"1", "2", "3", "4"},
                {"4", "3", "2", "1"},
                {"1", "3", "2", "4"}
        };
        String[][] warr2 = {
                {"1", "2", "3", "4"},
                {"4", "s", "2", "1"},
                {"1", "3", "2", "4"},
                {"4", "2", "3", "1"}
        };

        MyArrMethod.Calc(rarr);

    }
}
