--liquibase formatted sql

--changeset ndimov:core_0070

create table if not exists portal.bologna_compatibility
(
    id      integer              not null
    primary key,
    name    varchar(255)         not null,
    name_en varchar(255)         not null,
    active  boolean default true not null
    );

INSERT INTO portal.bologna_compatibility (id, name, name_en, active) VALUES (1, 'Участник в Болонския процес', 'Participant in the Bologna process', true) ON CONFLICT DO NOTHING;
INSERT INTO portal.bologna_compatibility (id, name, name_en, active) VALUES (2, 'Съвместими, но не участващи в Болонския процес', 'Compatible but not participating in the Bologna Process', true) ON CONFLICT DO NOTHING;
INSERT INTO portal.bologna_compatibility (id, name, name_en, active) VALUES (3, 'Извън Болонския процес', 'Outside the Bologna Process', true) ON CONFLICT DO NOTHING;

ALTER TABLE portal.country_details DROP COLUMN IF EXISTS system_type;
ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS bologna_compatibility integer;
ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS last_update date;
ALTER TABLE portal.country_details ADD COLUMN IF NOT EXISTS official_sources varchar(2000);


ALTER TABLE portal.country_details DROP CONSTRAINT IF EXISTS fk_portal_bologna_compatibility;

ALTER TABLE portal.country_details ADD CONSTRAINT fk_portal_bologna_compatibility
    FOREIGN KEY (bologna_compatibility)
        REFERENCES portal.bologna_compatibility (id);

ALTER TABLE portal.country_details ALTER COLUMN system_summary TYPE VARCHAR(2000);
ALTER TABLE portal.country_details ALTER COLUMN system_summary_en TYPE VARCHAR(2000);

ALTER TABLE portal.country_details ALTER COLUMN eqf_description_short TYPE VARCHAR(2000);
ALTER TABLE portal.country_details ALTER COLUMN eqf_description_short_en TYPE VARCHAR(2000);

DROP TABLE IF EXISTS portal.system_type;

UPDATE portal.country_details SET active='false' where 1=1;

UPDATE portal.country_details SET active='true' where code='IE' OR code='DE';

INSERT INTO portal.country_details (code, region, flag_url, system_summary, system_summary_en, eqf_status, eqf_description_short, eqf_description_short_en, active, eqf_description, eqf_description_en, credits_label, bologna_compatibility, last_update, official_sources) VALUES ('IE', 1, 'https://flagcdn.com/w160/ie.png', e'<div style="font-size: 14px; line-height: 1.3;">
  Ирландия е изцяло интегрирана в <strong>Процеса от Болоня</strong> и разполага с <strong>10-степенна Национална квалификационна рамка (NFQ)</strong>.
  NFQ е официално съотнесена към <strong>Европейската квалификационна рамка (EQF)</strong>. Висшите училища имат висока автономия,
  прилагат вътрешно осигуряване на качеството и подлежат на задължителен външен контрол от
  <strong>Quality and Qualifications Ireland (QQI)</strong> съгласно националното законодателство.
</div>', e'<div style="font-size: 14px; line-height: 1.3;">
  Ireland is fully integrated into the <strong>Bologna Process</strong> and has a <strong>10-level National Framework of Qualifications (NFQ)</strong>.
  The NFQ is officially referenced to the <strong>European Qualifications Framework (EQF)</strong>. Higher education institutions enjoy a high degree of autonomy,
  apply internal quality assurance systems, and are subject to mandatory external quality assurance by
  <strong>Quality and Qualifications Ireland (QQI)</strong> in accordance with national legislation.
</div>
', 'full', e'<h2 style="margin: 0 0 4px; font-size: 18px; align: center">
    Нива на висше образование
  </h2>
<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Нива по NFQ: 7 (обикновен бакалавър), 8 (почетен бакалавър)</li>
    <li>Нива по EQF: 6 (NFQ 7), 6–7 (NFQ 8)</li>
    <li>
      Продължителност:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Обикновен бакалавър (NFQ 7): 3 години</li>
        <li>Почетен бакалавър (NFQ 8): 3–4 години</li>
      </ul>
    </li>
    <li>Кредити: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по NFQ: 9</li>
    <li>Ниво по EQF: 7</li>
    <li>Продължителност: 1–2 години</li>
    <li>Кредити: 60–120 ECTS</li>
    <li>Включва обучителни и изследователски магистърски програми.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска степен (PhD / професионален докторат)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по NFQ: 10</li>
    <li>Ниво по EQF: 8</li>
    <li>Продължителност: обичайно 3–4 години (редовно обучение)</li>
    <li>Кредити: Не се прилагат ECTS; степента се основава на самостоятелна научна дейност.</li>
  </ul>
</section>', e'<h2 style="margin: 0 0 4px; font-size: 18px; align: center">
    Levels of Higher Education
  </h2>
<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>NFQ levels: 7 (Ordinary Bachelor), 8 (Honours Bachelor)</li>
    <li>EQF levels: 6 (NFQ 7), 6–7 (NFQ 8)</li>
    <li>
      Duration:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Ordinary Bachelor (NFQ 7): 3 years</li>
        <li>Honours Bachelor (NFQ 8): 3–4 years</li>
      </ul>
    </li>
    <li>Credits: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NFQ level: 9</li>
    <li>EQF level: 7</li>
    <li>Duration: 1–2 years</li>
    <li>Credits: 60–120 ECTS</li>
    <li>Includes taught and research master’s programmes.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral Degree (PhD / Professional Doctorate)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NFQ level: 10</li>
    <li>EQF level: 8</li>
    <li>Duration: typically 3–4 years (full-time study)</li>
    <li>Credits: ECTS are not applied; the degree is based on independent research.</li>
  </ul>
</section>', true, null, null, 'ECTS', 1, '2026-01-26', e'<div style="font-size: 12px; line-height: 1.3;">
  <span>
    1) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications" target="_blank" rel="noopener noreferrer">
      Quality and Qualifications Ireland (QQI) – National Framework of Qualifications (NFQ)
    </a>
    &nbsp;|&nbsp;
    2) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications/nfq-level-indicators" target="_blank" rel="noopener noreferrer">
      QQI – NFQ level indicators and award-type descriptors
    </a>
    &nbsp;|&nbsp;
    3) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications/eqf-referencing" target="_blank" rel="noopener noreferrer">
      QQI – Referencing of the Irish NFQ to the EQF (official report)
    </a>
    &nbsp;|&nbsp;
    4) <a href="https://europass.europa.eu/en/eqf-referencing-reports" target="_blank" rel="noopener noreferrer">
      European Commission – Europass – EQF referencing reports (Ireland)
    </a>
    &nbsp;|&nbsp;
    5) <a href="https://www.ehea.info/page-ireland" target="_blank" rel="noopener noreferrer">
      European Higher Education Area (EHEA) – Ireland country profile (degree cycles &amp; ECTS)
    </a>
    &nbsp;|&nbsp;
    6) <a href="https://eurydice.eacea.ec.europa.eu/national-education-systems/ireland/higher-education" target="_blank" rel="noopener noreferrer">
      Eurydice (European Commission) – Higher education system and degree structure in Ireland
    </a>
  </span>
</div>
') ON CONFLICT DO NOTHING;

UPDATE portal.country_details SET region = 1, flag_url = 'https://flagcdn.com/w160/ie.png', system_summary = e'<div style="font-size: 14px; line-height: 1.3;">
  Ирландия е изцяло интегрирана в <strong>Процеса от Болоня</strong> и разполага с <strong>10-степенна Национална квалификационна рамка (NFQ)</strong>.
  NFQ е официално съотнесена към <strong>Европейската квалификационна рамка (EQF)</strong>. Висшите училища имат висока автономия,
  прилагат вътрешно осигуряване на качеството и подлежат на задължителен външен контрол от
  <strong>Quality and Qualifications Ireland (QQI)</strong> съгласно националното законодателство.
</div>', system_summary_en = e'<div style="font-size: 14px; line-height: 1.3;">
  Ireland is fully integrated into the <strong>Bologna Process</strong> and has a <strong>10-level National Framework of Qualifications (NFQ)</strong>.
  The NFQ is officially referenced to the <strong>European Qualifications Framework (EQF)</strong>. Higher education institutions enjoy a high degree of autonomy,
  apply internal quality assurance systems, and are subject to mandatory external quality assurance by
  <strong>Quality and Qualifications Ireland (QQI)</strong> in accordance with national legislation.
</div>
', eqf_status = 'full', eqf_description_short = e'<h2 style="margin: 0 0 4px; font-size: 18px; align: center">
    Нива на висше образование
  </h2>
<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Нива по NFQ: 7 (обикновен бакалавър), 8 (почетен бакалавър)</li>
    <li>Нива по EQF: 6 (NFQ 7), 6–7 (NFQ 8)</li>
    <li>
      Продължителност:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Обикновен бакалавър (NFQ 7): 3 години</li>
        <li>Почетен бакалавър (NFQ 8): 3–4 години</li>
      </ul>
    </li>
    <li>Кредити: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по NFQ: 9</li>
    <li>Ниво по EQF: 7</li>
    <li>Продължителност: 1–2 години</li>
    <li>Кредити: 60–120 ECTS</li>
    <li>Включва обучителни и изследователски магистърски програми.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска степен (PhD / професионален докторат)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по NFQ: 10</li>
    <li>Ниво по EQF: 8</li>
    <li>Продължителност: обичайно 3–4 години (редовно обучение)</li>
    <li>Кредити: Не се прилагат ECTS; степента се основава на самостоятелна научна дейност.</li>
  </ul>
</section>', eqf_description_short_en = e'<h2 style="margin: 0 0 4px; font-size: 18px; align: center">
    Levels of Higher Education
  </h2>
<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>NFQ levels: 7 (Ordinary Bachelor), 8 (Honours Bachelor)</li>
    <li>EQF levels: 6 (NFQ 7), 6–7 (NFQ 8)</li>
    <li>
      Duration:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Ordinary Bachelor (NFQ 7): 3 years</li>
        <li>Honours Bachelor (NFQ 8): 3–4 years</li>
      </ul>
    </li>
    <li>Credits: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NFQ level: 9</li>
    <li>EQF level: 7</li>
    <li>Duration: 1–2 years</li>
    <li>Credits: 60–120 ECTS</li>
    <li>Includes taught and research master’s programmes.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral Degree (PhD / Professional Doctorate)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>NFQ level: 10</li>
    <li>EQF level: 8</li>
    <li>Duration: typically 3–4 years (full-time study)</li>
    <li>Credits: ECTS are not applied; the degree is based on independent research.</li>
  </ul>
</section>', active = true, eqf_description = null, eqf_description_en = null, credits_label = 'ECTS', bologna_compatibility = 1, last_update = '2026-01-26', official_sources = e'<div style="font-size: 12px; line-height: 1.3;">
  <span>
    1) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications" target="_blank" rel="noopener noreferrer">
      Quality and Qualifications Ireland (QQI) – National Framework of Qualifications (NFQ)
    </a>
    &nbsp;|&nbsp;
    2) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications/nfq-level-indicators" target="_blank" rel="noopener noreferrer">
      QQI – NFQ level indicators and award-type descriptors
    </a>
    &nbsp;|&nbsp;
    3) <a href="https://www.qqi.ie/what-we-do/the-national-framework-of-qualifications/eqf-referencing" target="_blank" rel="noopener noreferrer">
      QQI – Referencing of the Irish NFQ to the EQF (official report)
    </a>
    &nbsp;|&nbsp;
    4) <a href="https://europass.europa.eu/en/eqf-referencing-reports" target="_blank" rel="noopener noreferrer">
      European Commission – Europass – EQF referencing reports (Ireland)
    </a>
    &nbsp;|&nbsp;
    5) <a href="https://www.ehea.info/page-ireland" target="_blank" rel="noopener noreferrer">
      European Higher Education Area (EHEA) – Ireland country profile (degree cycles &amp; ECTS)
    </a>
    &nbsp;|&nbsp;
    6) <a href="https://eurydice.eacea.ec.europa.eu/national-education-systems/ireland/higher-education" target="_blank" rel="noopener noreferrer">
      Eurydice (European Commission) – Higher education system and degree structure in Ireland
    </a>
  </span>
</div>
' WHERE code = 'IE';

UPDATE portal.country_details SET region = 1, flag_url = 'https://flagcdn.com/w160/de.png', system_summary = e'<div style="font-size: 14px; line-height: 1.3;">
  Германия е изцяло интегрирана в <strong>Процеса от Болоня / Европейското пространство за висше образование (ЕПВО)</strong>
  (<strong>пълноправен член от 1999 г.</strong>). Страната разполага с <strong>Национална квалификационна рамка –
  Германската квалификационна рамка (DQR)</strong> с <strong>8 нива</strong>, официално съотнесена към
  <strong>Европейската квалификационна рамка (EQF)</strong>. Образователната система е <strong>федерална</strong>:
  провинциите (<em>Länder</em>) имат основна компетентност за висшето образование, а университетите са
  <strong>широко автономни</strong> в рамките на провинциалното законодателство. Осигуряването на качеството включва
  <strong>външна акредитация</strong> на образователни програми и <strong>вътрешни системи за качество</strong> съгласно
  правната рамка на <strong>Akkreditierungsrat</strong> и междупровинциалния договор.
</div>
', system_summary_en = e'<div style="font-size: 14px; line-height: 1.3;">
  Germany is fully aligned with the <strong>Bologna Process / European Higher Education Area (EHEA)</strong>
  (<strong>full member since 1999</strong>). It has a <strong>National Qualifications Framework</strong>,
  the <strong>German Qualifications Framework (DQR)</strong> with <strong>8 levels</strong>, officially referenced to the
  <strong>European Qualifications Framework (EQF)</strong>. The education system is <strong>federal</strong>:
  the <em>Länder</em> hold primary responsibility for higher education, while universities enjoy
  <strong>substantial autonomy</strong> within Land legislation. Quality assurance includes
  <strong>external accreditation</strong> of study programmes and <strong>internal quality assurance systems</strong>
  under the legal framework of the <strong>Accreditation Council</strong> and the
  <strong>interstate treaty</strong>.
</div>
', eqf_status = 'partial', eqf_description_short = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Нива на висше образование
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Бакалавърска степен
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>Ниво по DQR: 6</li>
    <li>Ниво по EQF: 6</li>
    <li>
      Продължителност:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Стандартна продължителност: 3–4 години</li>
      </ul>
    </li>
    <li>Кредити: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Магистърска степен
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по DQR: 7</li>
    <li>Ниво по EQF: 7</li>
    <li>Продължителност: 1–2 години</li>
    <li>Кредити: 60–120 ECTS</li>
    <li>Обикновено завършва общо с минимум 300 ECTS заедно с бакалавърската степен.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Докторска степен (Dr. / PhD)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>Ниво по DQR: 8</li>
    <li>Ниво по EQF: 8</li>
    <li>Продължителност: обичайно 3–4 години (изследователска форма)</li>
    <li>Кредити: Не се прилагат ECTS; степента се основава на самостоятелна научна и изследователска дейност.</li>
  </ul>
</section>
', eqf_description_short_en = e'<h2 style="margin: 0 0 4px; font-size: 18px; text-align: center;">
  Levels of Higher Education
</h2>

<section style="font-size: 14px; line-height: 1.2;">
  <h3 style="margin: 0 0 4px; font-size: 14px;">
    Bachelor’s Degree
  </h3>
  <ul style="margin: 2px 0 0 20px; padding: 0;">
    <li>DQR level: 6</li>
    <li>EQF level: 6</li>
    <li>
      Duration:
      <ul style="margin: 2px 0 2px 20px; padding: 0;">
        <li>Standard duration: 3–4 years</li>
      </ul>
    </li>
    <li>Credits: 180–240 ECTS</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Master’s Degree
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>DQR level: 7</li>
    <li>EQF level: 7</li>
    <li>Duration: 1–2 years</li>
    <li>Credits: 60–120 ECTS</li>
    <li>Usually completed with a total of at least 300 ECTS together with the Bachelor’s degree.</li>
  </ul>

  <h3 style="margin: 5px 0 0; font-size: 14px;">
    Doctoral Degree (Dr. / PhD)
  </h3>
  <ul style="margin: 2px 0 2px 20px; padding: 0;">
    <li>DQR level: 8</li>
    <li>EQF level: 8</li>
    <li>Duration: typically 3–4 years (research-based)</li>
    <li>Credits: ECTS are not applied; the degree is based on independent scientific and research work.</li>
  </ul>
</section>
', active = true, eqf_description = 'Германската национална квалификационна рамка (DQR – Deutscher Qualifikationsrahmen) е напълно съвместима с Европейската квалификационна рамка (EQF), като използва 8 нива и насърчава сравнимостта между академичните и професионалните квалификации. Образователната система е федерално организирана – всяка от 16-те федерални провинции (Länder) има известна автономия. Системата обхваща предучилищно, осн', eqf_description_en = e'The German National Qualifications Framework (DQR – Deutscher Qualifikationsrahmen) is fully aligned with the European Qualifications Framework (EQF) with 8 levels, supporting comparability between academic and vocational qualifications. Germany’s education system is decentralized: each of the 16 federal states (Länder) has legislative authority in education.
The system includes early childhood, ', credits_label = 'ECTS', bologna_compatibility = 1, last_update = '2026-01-27', official_sources = e'<div style="font-size: 12px; line-height: 1.3;">
  <span>
    1) <a href="https://ehea.info/page-full_members" target="_blank" rel="noopener noreferrer">
      EHEA (official) – Full members (Germany: full member since 1999)
    </a>
    &nbsp;|&nbsp;
    2) <a href="https://eurydice.eacea.ec.europa.eu/eurypedia/germany/overview" target="_blank" rel="noopener noreferrer">
      Eurydice (European Commission network, official) – Germany overview (federal distribution of responsibilities)
    </a>
    &nbsp;|&nbsp;
    3) <a href="https://www.dqr.de/dqr/en/the-dqr/dqr-and-eqf/dqr-and-eqf_node.html" target="_blank" rel="noopener noreferrer">
      DQR (official portal) – DQR and EQF (referencing of DQR levels to EQF levels)
    </a>
    &nbsp;|&nbsp;
    4) <a href="https://www.dqr.de/dqr/shareddocs/downloads/media/content/german_eqf_referencing_report.pdf?__blob=publicationFile&amp;v=2" target="_blank" rel="noopener noreferrer">
      German EQF Referencing Report (official PDF via DQR portal)
    </a>
    &nbsp;|&nbsp;
    5) <a href="https://akkreditierungsrat.de/en/accreditation-system/legal-basis/legal-basis-foundation" target="_blank" rel="noopener noreferrer">
      German Accreditation Council (Akkreditierungsrat, official) – Legal basis of the accreditation system
    </a>
    &nbsp;|&nbsp;
    6) <a href="https://www.kmk.org/fileadmin/Dateien/veroeffentlichungen_beschluesse/2016/2016_12_08-Studienakkreditierungsstaatsvertrag-englisch.pdf" target="_blank" rel="noopener noreferrer">
      KMK (Standing Conference, official) – Interstate Study Accreditation Treaty (English PDF)
    </a>
  </span>
</div>' WHERE code = 'DE';



UPDATE portal.country_qf SET country_code = 'DE', level = 6, name = 'Ниво 6', name_en = 'Level 6', name_native = 'Bachelor und gleichgestellte Hochschulabschlüsse', description_en = 'Bachelor’s degrees and equivalent higher education qualifications', description_bg = 'Бакалавър', eqf_level_id = 6, credits = '180–240', bologna_cycle_id = 1, duration = '3–4', bg_level_id = 1 WHERE id = 19;
UPDATE portal.country_qf SET country_code = 'DE', level = 7, name = 'Ниво 7', name_en = 'Level 7', name_native = 'Master und gleichgestellte Abschlüsse (Diplom)', description_en = 'Master’s degrees and equivalent higher education qualifications ', description_bg = 'Магистър', eqf_level_id = 7, credits = '60–120', bologna_cycle_id = 2, duration = '1–2', bg_level_id = 2 WHERE id = 20;
UPDATE portal.country_qf SET country_code = 'DE', level = 8, name = 'Ниво 8', name_en = 'Level 8', name_native = 'Doktorat und äquivalente künstlerische Abschlüsse', description_en = 'Doctorate and equivalent arts degrees', description_bg = 'Доктор', eqf_level_id = 8, credits = null, bologna_cycle_id = 3, duration = '3–5', bg_level_id = 3 WHERE id = 21;

INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (115, 'IE', 7, 'Ниво 7', 'Level 7', 'Ordinary Bachelor', 'Ordinary Bachelor', 'Oбикновен бакалавър', 6, '180–240', 1, '3', 1) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (116, 'IE', 8, 'Ниво 8', 'Level 8', 'Honours Bachelor ', 'Honours Bachelor ', 'Почетен бакалавър', 6, '180–240', 1, '3-4', 1) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (117, 'IE', 9, 'Ниво 9', 'Level 9', 'Master’s Degree', 'Master’s Degree', 'Магистърска степен', 7, '60-120', 2, '1-2', 2) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf (id, country_code, level, name, name_en, name_native, description_en, description_bg, eqf_level_id, credits, bologna_cycle_id, duration, bg_level_id) VALUES (118, 'IE', 10, 'Ниво 10', 'Level 10', 'Doctoral Degree (PhD / Professional Doctorate)', 'Doctoral Degree (PhD / Professional Doctorate)', 'Докторска степен (PhD / професионален докторат)', 8, null, 3, '3-4', 3) ON CONFLICT DO NOTHING;

INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (258, 115, 6, 'Обикновен бакалавър - обикновено професионално ориентиран', 'Ordinary Bachelor - Typically vocationally oriented', 'Ordinary Bachelor', 1, '3', '180-240') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (259, 116, 6, 'Бакалавър с отличие - Академично ориентиран', 'Honours Bachelor - Academically oriented', 'Honours Bachelor', 1, '3-4', '180-240') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (261, 117, 7, 'Магистърска степен по изследвания', 'Research Master’s Degree', 'Research Master’s Degree', 2, '1-2', '100-120') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (262, 117, 7, 'Диплома за следдипломна квалификация', 'Postgraduate Diploma', 'Postgraduate Diploma', 3, '1-2', '100-120') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (260, 117, 7, 'Преподавател магистърска степен', 'Taught Master’s Degree', 'Taught Master’s Degree', 1, '1-2', '100-120') ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (264, 118, 8, 'Професионална докторска степен', 'Professional Doctorate', 'Professional Doctorate', 2, '3-4', null) ON CONFLICT DO NOTHING;
INSERT INTO portal.country_qf_level (id, country_qf_id, eqf_level, name_bg, name_en, name_native, display_order, duration, credits) VALUES (263, 118, 8, 'Доктор по философия (PhD)', 'PhD (Doctor of Philosophy)', 'PhD (Doctor of Philosophy)', 1, '3-4', null) ON CONFLICT DO NOTHING;








