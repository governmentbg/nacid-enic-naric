--liquibase formatted sql

--changeset ndimov:core_0074

--GB
UPDATE portal.country_qf_level SET name_bg = 'Магистър (MA, MSc)' WHERE id = 42;

--GR
UPDATE portal.country_qf SET description_en = null, description_bg = null WHERE id = 185;
UPDATE portal.country_qf SET description_en = null, description_bg = null WHERE id = 186;
UPDATE portal.country_qf SET description_en = null, description_bg = null WHERE id = 187;
UPDATE portal.country_qf SET name_native = 'Metaptychio' WHERE id = 186;
INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (125, 'GR', 7, 'Ниво 7', 'Level 7', 'Diploma', null, null, 7, 300, 2, 5, 2) ON CONFLICT DO NOTHING;;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (383, 125, 7, 'Магистър', 'Master', 'Díploma', null, '5', '300') ON CONFLICT DO NOTHING;
UPDATE portal.country_qf_level SET name_native = 'Metaptychio / Μεταπτυχιακό' WHERE id = 381;
UPDATE portal.country_qf_level SET name_native = 'Ptychio / Πτυχίο' WHERE id = 380;
UPDATE portal.country_qf_level SET name_native = 'Didaktoriko / Διδακτορικό Δίπλωμα' WHERE id = 382;
UPDATE portal.country_qf_level SET name_bg = 'Съответстващо на Бакалавър', name_en = 'Referenced to Bachelor' WHERE id = 380;
UPDATE portal.country_qf_level SET name_bg = 'Съответстващо на Магистър', name_en = 'Referenced to Master' WHERE id = 381;
UPDATE portal.country_qf_level SET name_bg = 'Съответстващо на Доктор', name_en = 'Referenced to Doctor (PhD)' WHERE id = 382;
UPDATE portal.country_qf_level SET name_bg = 'Съответстващо на Магистър', name_en = 'Referenced to Master' WHERE id = 383;
UPDATE portal.country_qf SET name_native = 'Ptychio (Πτυχίο)' WHERE id = 185;
UPDATE portal.country_qf SET name_native = 'Metaptychio (Μεταπτυχιακό)' WHERE id = 186;
UPDATE portal.country_qf SET name_native = 'Didaktoriko (Διδακτορικό Δίπλωμα)' WHERE id = 187;
UPDATE portal.country_qf_level SET name_native = 'Díploma (Δίπλωμα)' WHERE id = 383;
UPDATE portal.country_qf SET name_native = 'Diploma (Δίπλωμα)' WHERE id = 125;


--DK
UPDATE portal.country_details SET eqf_description_short = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Нива на висше образование
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Ниво по НКР (Дания): 6</li>
    <li>Ниво по ЕКР: 6</li>
    <li>Цикъл по Болонския процес: Първи</li>
    <li>Продължителност: 3–4 години</li>
    <li>Кредити: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен (Kandidat / Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Дания): 7</li>
    <li>Ниво по ЕКР: 7</li>
    <li>Цикъл по Болонския процес: Втори</li>
    <li>Продължителност: 1–2 години</li>
    <li>Кредити: 60–120 ECTS</li>
    <li>Обикновено завършва общо с минимум 300 ECTS заедно с бакалавърската степен.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска степен (Ph.d.)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Дания): 8</li>
    <li>Ниво по ЕКР: 8</li>
    <li>Цикъл по Болонския процес: Трети</li>
    <li>Продължителност: 3 години</li>
    <li>Кредити: 180 ECTS</li>
  </ul>
</section>
', eqf_description_short_en = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Levels of Higher Education
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>NQF level  (Danmark): 6</li>
    <li>EQF level: 6</li>
    <li>Bologna Process Cycle: First</li>
    <li>Duration: 3–4 years</li>
    <li>Credits: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree (Kandidat / Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Danmark): 7</li>
    <li>EQF level: 7</li>
    <li>Bologna Process Cycle: Second</li>
    <li>Duration: 1–2 years</li>
    <li>Credits: 60–120 ECTS</li>
    <li>Usually completed with a total of at least 300 ECTS together with the Bachelor’s degree.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral Degree (PhD)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Danmark): 8</li>
    <li>EQF level: 8</li>
    <li>Bologna Process Cycle: Third</li>
    <li>Duration: 3 years</li>
    <li>Credits: 180 ECTS</li>
  </ul>
</section>
' WHERE code = 'DK';
UPDATE portal.country_qf SET country_code = 'DK', level = 6, name = 'Ниво 6', name_en = 'Level 6', name_native = 'Bachelor', description_en = 'Bachelor', description_bg = 'Бакалавър', eqf_level_id = 6, credits = '180–210', bologna_cycle_id = 1, duration = '3–3.5', bg_level_id = 1 WHERE id = 40;
UPDATE portal.country_qf SET country_code = 'DK', level = 7, name = 'Ниво 7', name_en = 'Level 7', name_native = 'Master', description_en = 'Master', description_bg = 'Магистър', eqf_level_id = 7, credits = '120', bologna_cycle_id = 2, duration = '2', bg_level_id = 2 WHERE id = 41;
UPDATE portal.country_qf SET country_code = 'DK', level = 8, name = 'Ниво 8', name_en = 'Level 8', name_native = 'Doktorgrad/Ph.D', description_en = 'Doctor (PhD)', description_bg = 'Доктор', eqf_level_id = 8, credits = '180', bologna_cycle_id = 3, duration = '3', bg_level_id = 3 WHERE id = 42;


DELETE FROM portal.country_qf_level WHERE id = 147;
DELETE FROM portal.country_qf_level WHERE id = 148;
DELETE FROM portal.country_qf_level WHERE id = 149;
UPDATE portal.country_qf_level SET country_qf_id = 40, eqf_level = 6, name_bg = 'Бакалавър', name_en = 'Bachelor', name_native = 'Bachelor (BA, B.Sc)', display_order = 1, duration = '3–3.5', credits = '180–210' WHERE id = 142;
UPDATE portal.country_qf_level SET country_qf_id = 41, eqf_level = 7, name_bg = 'Магистър', name_en = 'Master', name_native = 'Master (MA, M.Sc)', display_order = 1, duration = '2', credits = '120' WHERE id = 143;
UPDATE portal.country_qf_level SET country_qf_id = 42, eqf_level = 8, name_bg = 'Доктор', name_en = 'Doctor (PhD)', name_native = 'Doktorgrad (Ph.D)', display_order = 1, duration = '3', credits = '180' WHERE id = 145;

--EE
INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (126, 'EE', 7, 'Ниво 7', 'Level 7', 'Degrees of integrated Bachelor’s and Master’s programmes', 'Master', 'Магистър', 7, '300-360', 2, '5-6', 2) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (363, 126, 7, 'Магистър', 'Master', 'Degrees of integrated Bachelor’s and Master’s programmes', null, '5-6', '300-360') ON CONFLICT DO NOTHING;
UPDATE portal.country_qf_level SET name_native = 'Bakalaureusekraad' WHERE id = 360;
UPDATE portal.country_qf_level SET name_native = 'Magistrikraad' WHERE id = 361;
UPDATE portal.country_qf_level SET name_native = 'Doktorikraad' WHERE id = 362;

--IL
UPDATE portal.country_details SET eqf_description_short = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Нива на висше образование
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Ниво по НКР (Израел): 6</li>
    <li>Ниво по ЕКР: 6 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Първи (сравнимо)</li>
    <li>Продължителност: 3–4 години</li>
    <li>Кредити: Не са посочени</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен (Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Израел): 7</li>
    <li>Ниво по ЕКР: 7 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Втори (сравнимо)</li>
    <li>Продължителност: 1–2 години</li>
    <li>Кредити: Не са посочени</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска степен (Ph.D)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Израел): 8</li>
    <li>Ниво по ЕКР: 8 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Трети (сравнимо)</li>
    <li>Продължителност: 3 години</li>
    <li>Кредити: Не са посочени</li>
  </ul>
</section>
', eqf_description_short_en = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Levels of Higher Education
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>NQF level  (Israel): 6</li>
    <li>EQF level: 6 (referenced)</li>
    <li>Bologna Process Cycle: First (referenced)</li>
    <li>Duration: 3–4 years</li>
    <li>Credits: Not specified</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree (Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Israel): 7</li>
    <li>EQF level: 7 (referenced)</li>
    <li>Bologna Process Cycle: Second (referenced)</li>
    <li>Duration: 1–2 years</li>
    <li>Credits: Not specified</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral Degree (Ph.D)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Israel): 8</li>
    <li>EQF level: 8 (referenced)</li>
    <li>Bologna Process Cycle: Third (referenced)</li>
    <li>Duration: 3 years</li>
    <li>Credits: Not specified</li>
  </ul>
</section>
' WHERE code = 'IL';

UPDATE portal.country_qf SET name_native = 'Bachelor', duration = '3–4' WHERE id = 191;
UPDATE portal.country_qf SET name_native = 'Master', duration = '1–2' WHERE id = 192;
UPDATE portal.country_qf SET name_native = 'Ph.D', duration = '3' WHERE id = 193;
UPDATE portal.country_qf_level SET name_native = 'Bachelor', duration = '3–4' WHERE id = 394;
UPDATE portal.country_qf_level SET name_native = 'Master', duration = '1–2' WHERE id = 395;
UPDATE portal.country_qf_level SET name_native = 'Ph.D', duration = '3' WHERE id = 396;


--IR
UPDATE portal.country_details SET eqf_description_short = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Нива на висше образование
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Ниво по НКР (Иран): 6</li>
    <li>Ниво по ЕКР: 6 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Първи (сравнимо)</li>
    <li>Продължителност: 4–5 години</li>
    <li>Кредити: Не са посочени (няма ECTS)</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен (Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Иран): 7</li>
    <li>Ниво по ЕКР: 7 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Втори (сравнимо)</li>
    <li>Продължителност: 2 години</li>
    <li>Кредити: Не са посочени (няма ECTS)</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска / Професионална докторска степен (Doctorate (Doctor of Philosophy))
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по НКР (Иран): 8</li>
    <li>Ниво по ЕКР: 8 (сравнимо)</li>
    <li>Цикъл по Болонския процес: Трети (сравнимо)</li>
    <li>Продължителност: 4-6 години</li>
    <li>Кредити: Не се прилагат ECTS; степента се основава на научна и/или професионална подготовка.</li>
  </ul>
</section>
', eqf_description_short_en = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Levels of Higher Education
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree (Bachelor)
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>NQF level  (Iran): 6</li>
    <li>EQF level: 6 (referenced)</li>
    <li>Bologna Process Cycle: First (referenced)</li>
    <li>Duration: 4–5 years</li>
    <li>Credits: Not specified (no ECTS)</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree (Master)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Iran): 7</li>
    <li>EQF level: 7 (referenced)</li>
    <li>Bologna Process Cycle: Second (referenced)</li>
    <li>Duration: 2 years</li>
    <li>Credits: Not specified (no ECTS)</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral / Professional Doctorate ( Doctorate (Doctor of Philosophy))
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NQF level  (Iran): 8</li>
    <li>EQF level: 8 (referenced)</li>
    <li>Bologna Process Cycle: Third (referenced)</li>
    <li>Duration: 4-6 years</li>
    <li>Credits: ECTS are not applied; the degree is based on scientific and/or professional training.</li>
  </ul>
</section>
' WHERE code = 'IR';


UPDATE portal.country_qf SET name_native = 'Bachelor', duration = '4–5' WHERE id = 112;
UPDATE portal.country_qf SET name_native = 'Master', duration = '2' WHERE id = 113;
UPDATE portal.country_qf SET name_native = 'Doctorate (Doctor of Philosophy)', duration = '4-6' WHERE id = 114;
UPDATE portal.country_qf_level SET name_bg = 'Бакалавър', name_en = 'Continuous Bachelor', name_native = 'Bachelor (continuous)', duration = '4–5' WHERE id = 253;
UPDATE portal.country_qf_level SET name_bg = 'Бакалавър', name_en = 'Non-continuous Bachelor', name_native = 'Bachelor (non-continuous)', duration = '2+2' WHERE id = 254;
UPDATE portal.country_qf_level SET name_bg = 'Магистърска', name_en = 'Master', name_native = 'Master', duration = '2' WHERE id = 255;
UPDATE portal.country_qf_level SET name_bg = 'Доктор', name_en = 'Doctor (PhD)', name_native = 'Doctorate (Doctor of Philosophy)', duration = '4-6' WHERE id = 256;

ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS details_tables_number integer;
ALTER TABLE portal.country_qf ADD COLUMN IF NOT EXISTS details_tables_index integer;
ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS details_buttons_texts_bg varchar(255);
ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS details_buttons_texts_en varchar(255);

--BE

INSERT INTO portal.country_qf (id, country_code, details_tables_index, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (23, 'BE', 2, 7, 'Ниво 7', 'Level 7', 'Master (fr/nl)', 'Master’s degree', 'Магистър', 7, '60–120', 2, '1–2', 2) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf (id, country_code, details_tables_index, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (24, 'BE', 2, 8, 'Ниво 8', 'Level 8', 'Doctorat(fr) / Doctoraat(nl)', 'Doctorate (PhD)', 'Доктор', 8, '180–240', 3, '4+', 3) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf (id, country_code, details_tables_index, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (22, 'BE', 2, 6, 'Ниво 6', 'Level 6', 'Bachelier(fr) / Bachelor(nl)', 'Bachelor’s degree', 'Бакалавър', 6, '180', 1, '3', 1) ON CONFLICT DO NOTHING;

INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (444, 22, 6, 'Професионален бакалавър', 'Professional Bachelor', 'Bachelier professionnel /  Professionele bachelor', 1, '3', '180') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (445, 22, 6, 'Преходен бакалавър (за магистратура)', 'Transition Bachelor', 'Bachelier de transition / Schakelprogramma', 3, '3', '180') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (446, 22, 6, 'Академичен бакалавър', 'Academic Bachelor', 'Bachelier académique / Academische bachelor', 2, '3', '180') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (447, 23, 7, 'Магистър след магистър ', 'Master after Master', 'Master après master / Voortgezette master', 4, '1', '60') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (448, 23, 7, 'Магистър', 'Master degree', 'Master', 1, '1–2', '60–120') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (449, 23, 7, 'Специализиран магистър', 'Specialisation Master', 'Master de spécialisation / Master-na-master', 2, '1', '60') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (450, 23, 7, 'Допълнителен магистър', 'Complementary Master', 'Master complémentaire / Manama', 3, '1–2', '60–120') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (451, 24, 8, 'Докторска степен ', 'Doctoral degree', 'Doctorat / Doctoraat', 1, '4+', '180–240') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (452, 24, 8, 'Доктор', 'Doctor', 'Doktor', 2, '3', '180') ON CONFLICT DO NOTHING ;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (453, 24, 8, 'Агреже по висше образование', 'Higher Education Teaching Qualification', 'Agrégation de l’enseignement supérieur / Gediplomeerde in het hoger onderwijs', 3, '4+', null) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (454, 24, 8, 'Доктор по философия PhD ', 'PhD (Doctor of Philosophy)', 'PhD / Philosophiae Doctor', 2, '4+', '180–240') ON CONFLICT DO NOTHING ;

