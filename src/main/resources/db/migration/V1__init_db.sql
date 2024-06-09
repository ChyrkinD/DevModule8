CREATE TYPE LEVELS AS ENUM ('Trainee', 'Junior', 'Middle', 'Senior');
CREATE TABLE IF NOT EXISTS worker(
                                     ID BIGSERIAL PRIMARY KEY ,
                                     NAME VARCHAR CHECK (LENGTH(NAME) >=2 AND LENGTH(NAME) <= 1000) NOT NULL,
                                     BIRTHDAY DATE CHECK ( EXTRACT(YEAR FROM BIRTHDAY) >1900),
                                     LEVEL LEVELS NOT NULL,
                                     SALARY INT CHECK ( SALARY >= 100 AND SALARY <= 100000)
);



CREATE TABLE IF NOT EXISTS client(
                                     ID BIGSERIAL PRIMARY KEY,
                                     NAME VARCHAR CHECK ( LENGTH(NAME) >= 2 AND LENGTH(NAME) <=1000 ) NOT NULL
);

CREATE TABLE IF NOT EXISTS project(
                                      ID BIGSERIAL PRIMARY KEY,
                                      CLIENT_ID INT,
                                      START_DATE DATE,
                                      FINISH_DATE DATE
);

CREATE TABLE IF NOT EXISTS project_worker(
                                             PROJECT_ID BIGINT,
                                             WORKER_ID BIGINT,
                                             PRIMARY KEY (PROJECT_ID,WORKER_ID),
                                             FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
                                             FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);