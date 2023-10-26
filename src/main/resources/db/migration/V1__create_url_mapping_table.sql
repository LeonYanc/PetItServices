-- V1__create_url_mapping_table.sql
CREATE TABLE url_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    short_url VARCHAR(255) NOT NULL,
    long_url TEXT NOT NULL,
    username VARCHAR(255) NOT NULL
);
