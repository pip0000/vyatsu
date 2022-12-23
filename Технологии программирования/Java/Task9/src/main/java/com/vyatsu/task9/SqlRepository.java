package com.vyatsu.task9;

import com.vyatsu.task9.annotation.Column;
import com.vyatsu.task9.annotation.Table;
import org.postgresql.util.PSQLException;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class SqlRepository<Entity> {

    private final PostgresConnection postgresConnection;

    private SqlRepository(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    public static <Entity> SqlRepository<Entity> init(PostgresConnection postgresConnection, String className) throws ClassNotFoundException, SQLException {
        SqlRepository<Entity> sqlRepository = new SqlRepository<>(postgresConnection);

        StringBuilder sqlQuery = new StringBuilder("CREATE TABLE ");//Строка для запроса

        Class<?> clazz = Class.forName(className);
        Table table = clazz.getAnnotation(Table.class);

        if (Objects.isNull(table)) {
            System.out.println("Ошибка: В сущности должна быть аннотация 'Column'!");
        } else {
            sqlQuery.append(table.name()).append("\s").append("(").append("\n");

            for (Field field : clazz.getDeclaredFields()) {//цикл по всем аннотациям column
                Column annotation = field.getDeclaredAnnotation(Column.class);

                if (annotation != null) {
                    String typeName = field.getType().getSimpleName();
                    String fieldName = annotation.name();
                    String nullParameter = annotation.isNullable() ? "NULL" : "NOT NULL";

                    switch (typeName) {
                        case "String", "Breeds", "Job" -> sqlQuery.append(fieldName).append("\s").append("TEXT").append("\s").append(nullParameter);
                        case "Integer" -> sqlQuery.append(fieldName).append("\s").append("INTEGER").append("\s").append(nullParameter);
                        default -> System.out.println("Ошибка: Другие типы данных не поддерживаются!");
                    }

                    sqlQuery.append(",\n");
                }
            }

            sqlQuery = new StringBuilder(sqlQuery.substring(0, sqlQuery.length() - 2)).append("\n);");
        }
        try {
            sqlRepository.executeQuery(sqlQuery.toString());
        } catch (PSQLException e) {
            System.out.println("Таблица уже существует, но вы можете продолжать работу!");
        }
        return sqlRepository;
    }

    public void insert(Entity entity) throws IllegalAccessException, SQLException {
        Class<?> clazz = entity.getClass();
        Table table = clazz.getAnnotation(Table.class);

        if (Objects.isNull(table)) {
            System.out.println("Ошибка: В сущности должна быть аннотация 'Column'!");
        } else {
            StringBuilder query = new StringBuilder("INSERT INTO ").append(table.name()).append("\s") //строка для запроса
                    .append("VALUES").append("\s").append("(").append("\s");

            for (Field field : clazz.getDeclaredFields()) {
                Column column = field.getDeclaredAnnotation(Column.class);
                field.setAccessible(true);

                if (column != null) {
                    Object fieldValue = field.get(entity);

                    if (fieldValue instanceof String) {
                        query.append("'").append((String) fieldValue).append("',\s");
                    } else if (fieldValue instanceof Integer) {
                        query.append(fieldValue).append(",\s");
                    } else if (fieldValue instanceof Enum) {
                        query.append("'").append(((Enum<?>) fieldValue).name()).append("',\s");
                    }
                }
            }

            query = new StringBuilder(query.substring(0, query.length() - 2)).append(");");
            executeQuery(query.toString());
        }
    }

    public void executeQuery(String query) throws SQLException {
        PreparedStatement preparedStatement = postgresConnection.preparedStatement(query);
        preparedStatement.execute();
    }
}
