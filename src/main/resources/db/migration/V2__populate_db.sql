INSERT INTO worker(NAME, BIRTHDAY, LEVEL, SALARY)
VALUES ('Dima','2003-01-24','Trainee',600),
       ('Nastya','2003-02-01','Trainee',650),
       ('Oleg','1999-06-24','Middle',2000),
       ('Mykola','1980-02-06','Middle',2500),
       ('Artem','1970-06-24','Senior',4000),
       ('Nika','1990-05-16','Junior',600),
       ('Alex','2003-02-01','Junior',650),
       ('Jef','1997-09-12','Middle',2000),
       ('Pasha','2001-09-13','Senior',4500),
       ('Sveta','1986-06-29','Middle',2000);

INSERT INTO client(NAME)
VALUES ('Katya'),
       ('Natali'),
       ('Georg'),
       ('Oleksandr'),
       ('Petro');

INSERT INTO project(client_id, start_date, finish_date)
VALUES (1,'2024-06-01','2024-09-05'),
       (3,'2023-12-14','2024-02-05'),
       (2,'2019-11-25','2023-04-09'),
       (5,'2020-08-11','2022-01-05'),
       (4,'2021-12-01','2023-02-05'),
       (4,'2022-05-08','2023-09-05'),
       (2,'2024-06-01',NULL),
       (3,'2021-11-01','2024-01-02'),
       (5,'2022-01-30',NULL),
       (1,'2022-02-22','2023-12-23');

INSERT INTO project_worker (project_id, worker_id)
SELECT
    project.id, worker.id
FROM
    (SELECT id FROM     project ORDER BY RANDOM() LIMIT 10) AS project,
    (SELECT id FROM worker ORDER BY RANDOM() LIMIT FLOOR(RANDOM()*5) + 1) AS worker;
