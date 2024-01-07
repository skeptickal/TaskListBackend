CREATE SCHEMA IF NOT EXISTS task_data;

CREATE TABLE IF NOT EXISTS tasks_table (
    ID SERIAL PRIMARY KEY,
    TASKNAME VARCHAR(255) NOT NULL,
    STATUS VARCHAR(20) NOT NULL
);

INSERT INTO tasks_table (TASKNAME, STATUS) VALUES
    ('Welcome to Task List!', 'TODO'),
    ('This is an example Task! Add some more!', 'TODO');

GRANT INSERT, SELECT, UPDATE, DELETE ON tasks_table TO PUBLIC;
