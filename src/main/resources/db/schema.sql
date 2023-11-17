DROP TABLE IF EXISTS REGIONS;
CREATE TABLE REGIONS(
    id BIGINT NOT NULL,
    region_name varchar(255) NOT NULL,
    cut_region_name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);