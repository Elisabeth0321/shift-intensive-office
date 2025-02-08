CREATE TABLE department (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    department_id INT,
    salary        FLOAT NOT NULL,
    is_manager    BOOLEAN,
    FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
