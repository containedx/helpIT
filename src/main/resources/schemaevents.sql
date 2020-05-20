create table if not exists events (
    id BIGINT AUTO_INCREMENT,
    name varchar(100),
    date varchar(30),
    foundation varchar(30),
    description varchar(200)
);