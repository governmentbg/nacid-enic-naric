--liquibase formatted sql

--changeset veizov:0073
create table common.config_param
(
    name varchar(100)
        constraint config_param_pk
            primary key,
    value text not null
);

insert into common.config_param values ('ERROR_LOG_MSG_UPDATE_FO_APP_DATA_ON_ACCEPT', 'Данните във фронт офиса не са ъпдейтнати след приемане на заявление!\nИдентифиактор на заявление(ФО): {0}\nИдентифиактор на заявление(БО): {1}\nВходящ номер(БО): {2}');
insert into common.config_param values ('ERROR_LOG_MSG_UPDATE_PAYMENTS_DATA_ON_ACCEPT', 'Данните в приложението за плащане не са ъпдейтнати след приемане на заявление!\nИдентифиактор на заявление(ФО): {0}\nИдентифиактор на заявление(БО): {1}\nВходящ номер(БО): {2}');

