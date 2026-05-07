--liquibase formatted sql

--changeset murlev:core_0050
update nomenclatures.reference_data set name = 'За подписване' where domain = 'FO_APP_STATUS' and code = 'FIN';

