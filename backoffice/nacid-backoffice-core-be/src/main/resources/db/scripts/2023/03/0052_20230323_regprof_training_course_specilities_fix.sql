--liquibase formatted sql

--changeset ggeorgiev:0052
alter table regprof.training_course_specialities drop constraint tcs_rte_fk;
alter table regprof.training_course_specialities add
    CONSTRAINT tcs_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION