-- init.sql

CREATE SCHEMA IF NOT EXISTS task_data;

CREATE TABLE IF NOT EXISTS task_data.task_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL);

INSERT INTO task_data.task_table (name) VALUES
    ('Task 1'),
    ('Task 2');
