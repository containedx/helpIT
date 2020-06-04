create table if not exists events (
    event_id BIGINT AUTO_INCREMENT,
    event_name varchar(100),
    event_date varchar(30),
    event_description varchar(200)
);


ADD INDEX `foundation_idx` (`foundation` ASC) VISIBLE;
;
ALTER TABLE `helpit`.`event`
    ADD CONSTRAINT `foundation`
        FOREIGN KEY (`foundation`)
            REFERENCES `helpit`.`foundation` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


CREATE TABLE `helpit`.`volunteers` (
                                       `event_id` INT NOT NULL,
                                       `user_id` INT NULL,
                                       PRIMARY KEY (`event_id`),
                                       CONSTRAINT `event_id`
                                            FOREIGN KEY (`event_id`)
                                               REFERENCES `helpit`.`event` (`id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);
