create table if not exists events (
    id BIGINT AUTO_INCREMENT,
    name varchar(100),
    date varchar(30),
    foundation varchar(30),
    description varchar(200)
);

create table if not exists foundation (
    id BIGINT AUTO_INCREMENT,
    foundation_name varchar(50),
    foundation_owner_name varchar(50),
    foundation_owner_surname varchar(50)
);

ADD INDEX `foundation_idx` (`foundation` ASC) VISIBLE;
;
ALTER TABLE `helpit`.`event`
    ADD CONSTRAINT `foundation`
        FOREIGN KEY (`foundation`)
            REFERENCES `helpit`.`foundation` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
