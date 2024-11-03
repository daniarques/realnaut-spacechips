CREATE TABLE spaceship (
    id bigint PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    show_id INT NOT NULL,
    FOREIGN KEY (show_id) REFERENCES show(id)
);