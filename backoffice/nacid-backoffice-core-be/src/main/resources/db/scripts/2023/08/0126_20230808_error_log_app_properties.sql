--liquibase formatted sql

--changeset veizov:0126
insert into common.application_properties
values ('ERROR_LOG_MSG_DMS_ACCEPT_ABDOCS_REGISTRATION',
        'Възникна проблем при автоматично въвеждане в деловодната система на подадена заявка.\nИдентификатор на заявката във фронт офиса: {0}',
        'ErrorLog описание - грешка при автоматично въвеждане в деловодната система на подадена заявка от DMS');

insert into common.application_properties
values ('ERROR_LOG_MSG_DMS_ACCEPT_FO_UPDATE',
        'Възникна проблем при ъпдейт на заявка във фронт офиса с входящ номер и дата на входиране след приемане в деловодната система.\nИдентификатор на заявката във фронт офиса: {0}',
        'ErrorLog описание - грешка при ъпдейт на заявка във фронт офиса след приемане на DMS заявка');

insert into common.application_properties
values ('ERROR_LOG_MSG_DMS_ACCEPT_RECEIPT_INSERT',
        'Възникна проблем при запазване на разписка за приета заявка в деловодната система.\nИдентификатор на заявката във фронт офиса: {0}',
        'ErrorLog описание - грешка при запазване на разписка за приета заявка в деловодната система');
