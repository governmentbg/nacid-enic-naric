--liquibase formatted sql

--changeset ggeorgiev:0087 splitStatements:false
alter table rudi.training_course drop column joint_degree_flag;