--liquibase formatted sql

--changeset murlev:core_0068
create table if not exists nomenclatures.document_receive_option_kind
(
    code   varchar(4)   not null
    primary key,
    name   varchar(255) not null,
    active integer
    );

INSERT INTO nomenclatures.document_receive_option_kind (code, name, active) VALUES ('ORIG', 'Начин на получаване на предоставените оригинални документи', 1);

create table if not exists nomenclatures.document_receive_option
(
    code                    varchar(4)   not null
    constraint document_receive_option_pk
    primary key,
    name                    varchar(255) not null,
    document_recipient_flag integer      not null,
    index                   smallint     not null,
    option_kind_code        varchar(4)   not null
    references nomenclatures.document_receive_option_kind,
    active                  integer
    );

INSERT INTO nomenclatures.document_receive_option (code, name, document_recipient_flag, index, option_kind_code, active) VALUES ('ID', 'С авансово заплатена международна препоръчана пощенска пратка, задължително след консултация с фронт офиса на НАЦИД, на посочения адрес на получател, като давам съгласие документите ми да бъдат пренасяни за служебни цели', 1, 4, 'ORIG', 1);
INSERT INTO nomenclatures.document_receive_option (code, name, document_recipient_flag, index, option_kind_code, active) VALUES ('D', 'Чрез лицензиран пощенски оператор, като вътрешна куриерска пратка, на посоченият адрес на получател, и декларирам, че пощенските разходи са за моя сметка, като давам съгласие документите ми да бъдат пренасяни за служебни цели', 1, 3, 'ORIG', 1);
INSERT INTO nomenclatures.document_receive_option (code, name, document_recipient_flag, index, option_kind_code, active) VALUES ('DEC', 'На място в звеното за административно обслужване на НАЦИД', 0, 2, 'ORIG', 1);
INSERT INTO nomenclatures.document_receive_option (code, name, document_recipient_flag, index, option_kind_code, active) VALUES ('NOOR', 'Не се изисква предоставяне на оригинални документи', 0, 1, 'ORIG', 1);
