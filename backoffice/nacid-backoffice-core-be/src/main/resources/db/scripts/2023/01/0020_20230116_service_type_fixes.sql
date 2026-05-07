--liquibase formatted sql

--changeset ggeorgiev:0020
alter table regprof.regprof_application drop column regprof_service_type_id;
drop table nomenclatures.regprof_service_type;
--will be inserted by the migration code
--INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_replication_flag) VALUES ('SERVICE_TYPE', 'Вид услуга', 1);
--INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SERVICE_TYPE', 'S', 'Стандартна', 1, 1);
--INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SERVICE_TYPE', 'E', 'Бърза', 1, 1);
alter table common.application add column service_type varchar(10);
ALTER TABLE common.application
    ADD CONSTRAINT apn_service_type_check
        CHECK (nomenclatures.exists_refdata('SERVICE_TYPE', service_type));

create table nomenclatures.cfg_service_type (
                                                id serial constraint cst_pk primary key,
                                                ate_code        varchar(20) not null
                                                    constraint cas_ate_fk
                                                        references nomenclatures.application_type,
                                                ase_code        varchar(4)
                                                    constraint cas_ase_fk
                                                        references nomenclatures.application_subtype,
                                                execution_days integer,
                                                liability_code varchar(20)
);
create unique index cst_application_type_subtype_uk
    on nomenclatures.cfg_service_type (ate_code, ase_code);