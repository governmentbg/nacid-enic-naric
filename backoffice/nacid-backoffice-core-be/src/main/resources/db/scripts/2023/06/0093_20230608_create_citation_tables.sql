--liquibase formatted sql

--changeset mnakova:0093
CREATE TABLE IF NOT EXISTS libserv.publication_period_rows
(
    id serial primary key,
    apn_id integer NOT NULL,
    year smallint NOT NULL,
    CONSTRAINT publication_period UNIQUE (apn_id, year),
    CONSTRAINT publication_period_rows_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.inquiry (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS libserv.publication_rows
(
    id serial primary key,
    publication_period_id integer NOT NULL,
    publication text,
    sort_publication_field text,
    CONSTRAINT publication_rows_period_fk FOREIGN KEY (publication_period_id)
        REFERENCES libserv.publication_period_rows (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS libserv.citation_rows
(
    id serial primary key,
    publication_id integer NOT NULL,
    citation text,
    sort_citation_field text,
    CONSTRAINT citation_rows_publication_fk FOREIGN KEY (publication_id)
        REFERENCES libserv.publication_rows (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

