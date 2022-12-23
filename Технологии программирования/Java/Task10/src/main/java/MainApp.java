import java.sql.*;

public class MainApp {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "2536");

            statement = connection.createStatement();

            System.out.println("Оценки по предметам");
            One(statement);
            System.out.println("Студенты, сдавшие предмет Sub3 на оценку выше 3");
            Two(statement);
            System.out.println("Средний балл по определенному предмету");
            Three(statement);
            System.out.println("Средний балл по определенному студенту");
            Four(statement);
            System.out.println("Три предмета, которые сдали наибольшее кол-во студентов");
            Five(statement);
            System.out.println("Стипендия");
            Six(statement);
        }
        catch (SQLException e) {
            System.out.println("Ошибка соединения");
            throw new RuntimeException(e);
        }
    }

    public static void One(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT stud.name, subj.name, p.mark\n" +
                        "FROM student AS stud\n" +
                        "         JOIN progress AS p ON stud.id = p.student_id\n" +
                        "         JOIN subject AS subj ON p.subject_id = subj.id;"

        );

        while (resultSet.next()) {
            String stud= resultSet.getString(1);
            String subj= resultSet.getString(2);
            int p= resultSet.getInt(3);

            System.out.printf("%s %s %d\n", stud, subj, p);
        }
    }

    public static void Two(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT stud.name, subj.name, p.mark\n" +
                        "FROM student AS stud\n" +
                        "         JOIN progress AS p ON stud.id = p.student_id\n" +
                        "         JOIN subject AS subj ON p.subject_id = subj.id\n" +
                        "WHERE p.mark >= 3\n" +
                        "  AND p.subject_id = 3;"

        );

        while (resultSet.next()) {
            String stud= resultSet.getString(1);
            String subj= resultSet.getString(2);
            int p= resultSet.getInt(3);

            System.out.printf("%s %s %d\n", stud, subj, p);
        }
    }

    public static void Three(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT subject.name, AVG(progress.mark)\n" +
                        "FROM progress\n" +
                        "         JOIN subject ON progress.subject_id = subject.id\n" +
                        "GROUP BY subject.name;"

        );

        while (resultSet.next()) {
            String subj = resultSet.getString(1);
            float avg = resultSet.getFloat(2);

            System.out.printf("%s %f\n", subj, avg);
        }
    }

    public static void Four(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT student.name, AVG(progress.mark)\n" +
                        "FROM progress\n" +
                        "         JOIN subject ON progress.subject_id = subject.id\n" +
                        "         JOIN student ON student.id = progress.student_id\n" +
                        "GROUP BY student.name;"

        );

        while (resultSet.next()) {
            String stud = resultSet.getString(1);
            float avg = resultSet.getFloat(2);

            System.out.printf("%s %f\n", stud, avg);
        }
    }

    public static void Five(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT subj.name\n" +
                        "FROM subject AS subj\n" +
                        "         JOIN progress ON subj.id = progress.subject_id\n" +
                        "WHERE (SELECT AVG(mark)\n" +
                        "       FROM progress\n" +
                        "                JOIN subject ON subj.id = progress.subject_id) >= 3\n" +
                        "GROUP BY subj.name;"

        );

        while (resultSet.next()) {
            String subj= resultSet.getString(1);

            System.out.printf("%s\n", subj);
        }
    }

    public static void Six(Statement statement) throws SQLException {
        resultSet = statement.executeQuery(
                "SELECT stud.name, subj.name, p.mark\n" +
                        "FROM student AS stud\n" +
                        "    JOIN progress AS p ON stud.id = p.student_id\n" +
                        "    JOIN subject AS subj ON p.subject_id = subj.id\n" +
                        "WHERE stud.id NOT IN (SELECT st.id\n" +
                        "FROM student AS st\n" +
                        "JOIN progress AS pp ON st.id = pp.student_id\n" +
                        "WHERE pp.mark < 4)\n" +
                        "GROUP BY stud.name, subj.name, p.mark OFFSET 1 LIMIT 3;"

        );

        while (resultSet.next()) {
            String stud= resultSet.getString(1);
            String subj= resultSet.getString(2);
            int p= resultSet.getInt(3);

            System.out.printf("%s %s %d\n", stud, subj, p);
        }
    }
}
