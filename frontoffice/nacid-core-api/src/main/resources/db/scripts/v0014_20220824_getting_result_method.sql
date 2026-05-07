--liquibase formatted sql

--changeset veizov:core_0014
--validCheckSum: 8:61b1d473ce74e85933f3f8a7dab83500
--validCheckSum: 8:790b6c39bd47f005216049b8273354f1
create table nomenclatures.getting_result_method
(
    id             varchar(50) not null
        constraint getting_result_method_pk
            primary key,
    name             text,
    name_en          text,
    active           integer     not null default 1,
    contains_address integer     not null default 1
);

INSERT INTO nomenclatures.getting_result_method (id, name, name_en)
VALUES ('NACID','На място в Центъра за административно обслужване на НАЦИД.', 'On site at the centre for administrative service of NACID.');

INSERT INTO nomenclatures.getting_result_method (id, name, name_en)
VALUES ('INTERNAL_COURIER_PARCEL',
        'Чрез лицензиран пощенски оператор, като вътрешна куриерска пратка, на адреса, въведен в електронното заявление, и <b>декларирам</b>, че <b>пощенските разходи са за моя сметка</b>, като давам съгласие документите ми да бъдат пренасяни за служебни цели.',
        'By means of a licensed postal operator, via internal courier parcel, at the address given in the application form.<b>I declare that the postal costs are at my expense.</b> I agree my documents to be transported for official purposes.');

INSERT INTO nomenclatures.getting_result_method (id, name, name_en)
VALUES ('INTERNAL_COURIER_REGISTERED_PARCEL',
        'Чрез лицензиран пощенски оператор, като вътрешна <b>препоръчана</b> пощенска пратка, на адреса, въведен в електронното заявлението, и <b>декларирам, че пощенските разходи са за моя сметка</b>, като давам съгласие документите ми да бъдат пренасяни за служебни цели.',
        'By means of a licensed postal operator, via internal courier <b>registered</b> parcel, at the address given in the application form. <b>I declare that the postal costs are at my expense.</b> I agree my documents to be transported for official purposes.');

INSERT INTO nomenclatures.getting_result_method (id, name, name_en)
VALUES ('INTERNATIONAL_REGISTERED_PARCEL',
        'С авансово заплатена международна препоръчана пощенска пратка, след консултация с Центъра за административно обслужване на НАЦИД, на адреса, въведен в заявленеито, като давам съгласие документите ми да бъдат пренасяни за служебни цели.',
        'Via paid in advance international registered parcel, after consultation with the centre for administrative service of NACID, at the address given in the application form. I agree my documents to be transported for official purposes.');

INSERT INTO nomenclatures.getting_result_method (id, name, name_en)
VALUES ('EMAIL','По електронен път на електронен адрес.', 'Electronically to an email address.');