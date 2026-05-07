--liquibase formatted sql

--changeset ggeorgiev:0098 splitStatements:false;
drop view if exists libserv.vw_publication_citations;
create or replace view libserv.vw_publication_citations as
select row_number() over (partition by ppr.apn_id order by  ppr.year, pr.sort_publication_field, pr.id, cr.sort_citation_field, cr.id ), ppr.year as publication_year, pr.publication, pr.sort_publication_field, cr.citation, cr.sort_citation_field, ppr.id publication_period_id, pr.id publication_id, cr.id citation_id, apn_id
from libserv.publication_period_rows ppr
         join libserv.publication_rows pr on ppr.id = pr.publication_period_id
         join libserv.citation_rows cr on pr.id = cr.publication_id;