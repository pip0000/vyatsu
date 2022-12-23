--Студенты
DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id     SERIAL PRIMARY KEY,
    name   TEXT    NOT NULL,
    series INTEGER NOT NULL,
    number INTEGER NOT NULL,
    UNIQUE (series, number)
);

--Предметы
DROP TABLE IF EXISTS subject;
CREATE TABLE subject
(
    id   SERIAL PRIMARY KEY,
    name text
);

--Успеваемость
DROP TABLE IF EXISTS progress;
CREATE TABLE progress
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER
        CONSTRAINT progress_fk REFERENCES student (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    subject_id INTEGER
        CONSTRAINT subject_fk REFERENCES student (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    mark       INTEGER
        CONSTRAINT valid_mark CHECK ( mark BETWEEN 2 AND 5 )
);

--Заполняем студентов
INSERT INTO student (name, series, number)
VALUES ('Bob1', 1, 1);
INSERT INTO student (name, series, number)
VALUES ('Bob2', 1, 2);
INSERT INTO student (name, series, number)
VALUES ('Bob3', 1, 3);
INSERT INTO student (name, series, number)
VALUES ('Bob4', 2, 1);
INSERT INTO student (name, series, number)
VALUES ('Bob5', 2, 2);
INSERT INTO student (name, series, number)
VALUES ('Bob6', 2, 3);

--Заполняем предметы
INSERT INTO subject
VALUES (DEFAULT, 'Sub1');
INSERT INTO subject
VALUES (DEFAULT, 'Sub2');
INSERT INTO subject
VALUES (DEFAULT, 'Sub3');

--Заполняем успеваемость
--По Sub1
INSERT INTO progress
VALUES (DEFAULT, 1, 1, 2);
INSERT INTO progress
VALUES (DEFAULT, 2, 1, 4);
INSERT INTO progress
VALUES (DEFAULT, 3, 1, 5);
INSERT INTO progress
VALUES (DEFAULT, 4, 1, 3);
INSERT INTO progress
VALUES (DEFAULT, 5, 1, 4);
INSERT INTO progress
VALUES (DEFAULT, 6, 1, 5);

--По Sub2
INSERT INTO progress
VALUES (DEFAULT, 1, 2, 2);
INSERT INTO progress
VALUES (DEFAULT, 2, 2, 5);
INSERT INTO progress
VALUES (DEFAULT, 3, 2, 5);
INSERT INTO progress
VALUES (DEFAULT, 4, 2, 4);
INSERT INTO progress
VALUES (DEFAULT, 5, 2, 3);
INSERT INTO progress
VALUES (DEFAULT, 6, 2, 5);

--По Sub3
INSERT INTO progress
VALUES (DEFAULT, 1, 3, 5);
INSERT INTO progress
VALUES (DEFAULT, 2, 3, 5);
INSERT INTO progress
VALUES (DEFAULT, 3, 3, 2);
INSERT INTO progress
VALUES (DEFAULT, 4, 3, 4);
INSERT INTO progress
VALUES (DEFAULT, 5, 3, 3);
INSERT INTO progress
VALUES (DEFAULT, 6, 3, 5);

--Вывод
SELECT stud.name, subj.name, p.mark
FROM student AS stud
         JOIN progress AS p ON stud.id = p.student_id
         JOIN subject AS subj ON p.subject_id = subj.id;

--Студенты, сдавшие предмет Sub3 на оценку выше 3
SELECT stud.name, subj.name, p.mark
FROM student AS stud
         JOIN progress AS p ON stud.id = p.student_id
         JOIN subject AS subj ON p.subject_id = subj.id
WHERE p.mark >= 3
  AND p.subject_id = 3;

--Средний балл по определенному предмету
SELECT subject.name, AVG(progress.mark)
FROM progress
         JOIN subject ON progress.subject_id = subject.id
GROUP BY subject.name;

--Средний балл по определенному студенту
SELECT student.name, AVG(progress.mark)
FROM progress
         JOIN subject ON progress.subject_id = subject.id
         JOIN student ON student.id = progress.student_id
GROUP BY student.name;

--Три предмета, которые сдали наибольшее кол-во студентов
SELECT subj.name
FROM subject AS subj
         JOIN progress ON subj.id = progress.subject_id
WHERE (SELECT AVG(mark)
       FROM progress
                JOIN subject ON subj.id = progress.subject_id) >= 3
GROUP BY subj.name;

SELECT stud.name FROM student AS stud
JOIN progress AS p ON stud.id = p.student_id
GROUP BY stud.name
HAVING min(p.mark) = 4 OR min(p.mark) = 5;

--Стипендия
SELECT stud.name, subj.name, p.mark
FROM student AS stud
    JOIN progress AS p ON stud.id = p.student_id
    JOIN subject AS subj ON p.subject_id = subj.id
WHERE stud.id NOT IN (SELECT st.id
FROM student AS st
JOIN progress AS pp ON st.id = pp.student_id
WHERE pp.mark < 4)
GROUP BY stud.name, subj.name, p.mark OFFSET 1 LIMIT 3;

