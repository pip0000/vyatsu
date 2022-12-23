package com.vyatsu.task9;

import java.sql.SQLException;

public class Main10 {
    public static void main(String[] args)throws SQLException, ClassNotFoundException, IllegalAccessException {
        PostgresConnection postgresConnection = PostgresConnection.connect("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=2536");

    }
}
