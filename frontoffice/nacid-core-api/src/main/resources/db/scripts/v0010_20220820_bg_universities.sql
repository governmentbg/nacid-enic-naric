--liquibase formatted sql

--changeset veizov:core_0010
--validCheckSum: 8:115415ab6fca41715d7feb514a79a5fe
--validCheckSum: 8:c0ec4d1638398881c110a35be0dc2e9f
--validCheckSum: 8:a825917b509e6985a27caae85791da20
--validCheckSum: 8:e6dfc02007942247a628a22b42b7c419
create table nomenclatures.national_university
(
    eik             varchar(20)  not null,
    name            varchar(255) not null,
    name_en         varchar(255),
    settlement_code varchar(5)   not null,
    address         varchar(255) not null,
    address_en      varchar(255),
    zip_code        varchar(10),
    website         varchar(255),
    logo_rel_path   varchar(255),
    active          int      not null default 1,
    constraint national_university_pkey
        primary key (eik)
);

-- 1
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000455464', 'Аграрен университет - Пловдив', 'Agricultural University - Plovdiv', '56784', 'бул. Менделеев 12', '12 Mendeleev Blvd.', '4000', 'http://www.au-plovdiv.bg', '/uni_logos/agri_pld.png');

-- 2
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('115013887', 'Академия за музикално, танцово и изобразително изкуство „Проф. Асен Диамандиев“ – Пловдив','Academy of Music, Dance and Fine Arts - "prof. Asen Diamandiev" - Plovdiv', '56784', 'ул. "Тодор Самодумов" 2', '2, Todor Samodumov Str.', '4000', 'https://www.artacademyplovdiv.com/', '/uni_logos/АМТИИ.jpg');

-- 3
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('129001232', 'Академия на Министерство на вътрешните работи','Academy of Ministry of Interior', '68134', 'бул. "Александър Малинов" 1', 'bul. Aleksandar Malinov 1', '1715', 'http://www.academy.mvr.bg', '/uni_logos/academy-mvr.png');

-- 4
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000019449', 'Американски университет в България','American University in Bulgaria', '04279', 'пл. "Г. Измирлиев" № 1', 'sq. G. Izmirliev 1', '2700', 'http://www.aubg.edu', '/uni_logos/american_uni.jpg');

-- 5
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000049741', 'Бургаски свободен университет','Burgas Free University', '07079', 'ул. "Сан Стефано", 62', '62, "San Stefano" Bul.', '8001', 'http://www.bfu.bg', '/uni_logos/fb_logo.png');

-- 6
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('103010965', 'Варненски Свободен Университет "Черноризец Храбър"', 'Varna Free University "Chernorizets Hrabar"', '10135', 'k.к. “Чайка”, ул. „Янко Славчев“ № 84', 'Chayka Resort, 84 Yanko Slavchev Street', '9007', 'http://www.vfu.bg', '/uni_logos/vfu.png');

-- 7
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('104025653', 'Великотърновски университет "Св. св. Кирил и Методий"','"St. Cyril and St. Methodius" University of Veliko Tarnovo (AU)', '10447', 'ул."Теодосий Търновски", №2', '2 Theodosiy Tarnovski Str.', '5003', 'https://www.uni-vt.bg', '/uni_logos/vt-kiril-metodii.jpg');

-- 8
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('129011005', 'Висше военновъздушно училище "Георги Бенковски"','"Georgi Benkovski" Air Force Academy', '22215', 'ул "Св.Св. Кирил и Методий" № 1', '1, "St. st. Ciril and Methodius"str', '5855', 'http://www.af-acad.bg/', '/uni_logos/af-acad.png');

-- 9
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('129004492', 'Висше военноморско училище "Н.Й.Вапцаров"','Nikola Vaptsarov Naval Academy', '10135', 'ул. „Васил Друмев" № 73', '73 Vasil Drumev Street', '9002', 'http://www.naval-acad.bg/', '/uni_logos/naval-acad.png');

-- 10
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('131207254', 'Висше строително училищe "Любен Каравелов"','“L. Karavelov” Civil Engineering Higher School', '68134', 'ул. "Суходолска" 175', '175 Suhodolska, Str.', '1373', 'http://www.vsu.bg/', '/uni_logos/vsu.png');

-- 11
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('131209472', 'Висше транспортно училище "Тодор Каблешков"','Todor Kableshkov University of Transport', '68134', 'ул. "Гео Милев" № 158', '158 Geo Milev Str.', '1574', 'http://vtu.bg/', '/uni_logos/vtu.jpg');

-- 12
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000678346', 'Висше училище по агробизнес и развитие на регионите - Пловдив','University of agribusiness and rural development', '56784', 'бул. Дунав 78', '78, Dunav Blvd.', '4000', 'http://uard.bg', '/uni_logos/logo_uard.jpg');

-- 13
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('131191259', 'Висше училище по застраховане и финанси - София (ВУЗФ)','VUZF University', '68134', 'кв. "Овча купел" ул. "Гусла" № 1', 'Ovcha kupel Distr. 1, Gusla Street', '1618', 'http://www.vuzf.bg/', '/uni_logos/VUZF.png');

-- 14
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('124140232', 'Висше училище по мениджмънт - Варна','Varna University of Management - Varna', '10135', 'ул. Оборище №13А', '13A, Oborishte str.', '9000', 'http://www.vum.bg', '/uni_logos/vum.jpg');

-- 15
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('115817429', 'Висше училище по сигурност и икономика - Пловдив','Higher School of Security and Economics', '56784', 'бул. „Кукленско шосе“ 13', '13, Kuklensko shose', '4000', 'https://www.vusi.bg/', '/uni_logos/vusi.jpg');

-- 16
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670545', 'Висше училище по телекомуникации и пощи','University of Telecommunications and Post', '68134', 'ул. Академик Стефан Младенов № 1', '1 Akademik Stefan Mladenov Str.', '1700', 'https://www.utp.bg/', '/uni_logos/utp.png');

-- 17
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('129003305', 'Военна академия "Г. С. Раковски" - София','"G. S. Rakovski" National Defense College', '68134', 'бул."Евлоги и Христо Георгиеви" 82', '82 "Evlogi and Hristo Georgievi" blvd.', '1504', 'http://rnda.armf.bg/', '/uni_logos/rnda.png');

-- 18
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('175912725', 'Европейски политехнически университет','European Polytechnical University', '55871', 'ул. „Св.св. Кирил и Методий“ 23', '23 “Sv. sv. Kiril and Metodiy” Str.', '2300', 'http://www.epubg.eu/', '/uni_logos/epubg.jpg');

-- 19
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('115636489', 'Европейско висше училище по икономика и мениджмънт - Пловдив','European Higher School of Economics and Management', '56784', 'ул. Задруга 18', '18, Zadruga str.', '4004', 'http://www.ehsem.bg', '/uni_logos/ehsem.jpg');

-- 20
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000083619', 'Икономически университет - Варна','University of Economics - Varna', '10135', 'бул."Княз Борис I" №77', '77 "Knyaz Boris I" Blvd', '9002', 'http://www.ue-varna.bg/', '/uni_logos/uevarna.jpg');

-- 21
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('831651134', 'Колеж по мениджмънт, търговия и маркетинг - София','College of Management, Trade and Marketing', '68134', 'ул. "Софийски герой" №1', '1, Sofiiski geroy Str.', '1612', 'https://www.mtmcollege.org/', '/uni_logos/mtmcollege.png');

-- 22
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('101651748', 'Колеж по туризъм - Благоевград','College of Tourism - Blagoevgrad', '04279', 'ул. Брегалница №2', '2 Bregalnica str.', '2700', 'http://cotur.bg/', '/uni_logos/cotur.png');

-- 23
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670634', 'Лесотехнически университет','University of Forestry', '68134', 'Бул. Климент Охридски 10', '10, Kliment Ohridsky Blvd.', '1797', 'http://ltu.bg', '/uni_logos/ltu.jpg');

-- 24
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000405689', 'Медицински университет - Плевен','Pleven Medical University', '56722', 'ул. "Климент Охридски" 1', '1 Kliment Ohridski Str.', '5800', 'http://www.mu-pleven.bg', '/uni_logos/mu-pleven.png');

-- 25
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000455471', 'Медицински университет - Пловдив','Medical University - Plovdiv', '56784', 'ул."В. Априлов" 15-А', '15A "V. Aprilov" Str.', '4002', 'http://www.meduniversity-plovdiv.bg', '/uni_logos/meduniversity-plovdiv.jpg');

-- 26
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('831385737', 'Медицински университет - София','Medical University - Sofia', '68134', 'бул. "Акад. Иван Евст. Гешов" № 15', '15, Acad. Ivan Evst. Geshov Blvd.', '1431', 'http://www.mu-sofia.bg', '/uni_logos/mu-sofia.jpg');

-- 27
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000083633', 'Медицински университет "Проф. д-р Параскев Стоянов" - Варна','Medical University "Prof. Dr. Paraskev Stoyanov" - Varna', '10135', 'ул."Марин Дринов" 55', '55 "Marin Drinov" Str.', '9002', 'http://www.mu-varna.bg', '/uni_logos/mu-varna.jpg');

-- 28
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('122037746', 'Международно висше бизнес училище','International Business School', '05815', 'ул. “Гурко“ 14', '14, Gurko str.', '2140', 'https://ibsedu.bg/', '/uni_logos/ibsedu.png');

-- 29
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670659', 'Минно-геоложки университет "Св. Иван Рилски"','University of Mining and Geology "St. Ivan Rilski"', '68134', 'Студентски град, ул. "Проф. Боян Каменов"', 'Studentski grad, "Prof. Boyan Kamenov" Street', '1700', 'http://www.mgu.bg', '/uni_logos/mgu.jpg');

-- 30
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('129009094', 'Национален военен университет "Васил Левски"','Vasil Levski National Military University', '10447', 'бул. "България" № 76', '76 Bulgaria Blvd.', '5000', 'http://www.nvu.bg', '/uni_logos/nvu.jpg');

-- 31
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670723', 'Национална академия за театрално и филмово изкуство "Кръстьо Сарафов"','National Academy for Theatre and Film Arts', '68134', 'ул."Раковски" 108А', '108A "Rakovski" Str.', '1000', 'http://www.natfiz.bg', '/uni_logos/natfiz.jpg');

-- 32
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670709', 'Национална музикална академия "Проф. Панчо Владигеров"','National Academy of Music " Prof. Pantcho Vladigerov"', '68134', 'бул. „Евлоги и Христо Георгиеви” № 94', '94 Evlogy&Hristo Georgievi Blvd.', '1505', 'http://www.nma.bg', '/uni_logos/nma.jpg');

-- 33
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670627', 'Национална спортна академия','National Sports Academy', '68134', 'Студентски град, ул. Акад. Стефан Младенов 21', 'Studentski grad, Akad. Stefan Mladenov Str. 21', '1700', 'http://www.nsa.bg', '/uni_logos/nsa.png');

-- 34
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670716', 'Национална Художествена Академия','National Academy of Art', '68134', 'ул."Шипка" 1', '1 "Shipka" Str.', '1000', 'https://nha.bg/', '/uni_logos/nha.jpg');

-- 35
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000677803', 'Нов български университет','New Bulgarian University', '68134', 'бул."Монтевидео" 21', '21 Montevideo Str.', '1618', 'https://nbu.bg', '/uni_logos/nbu.png');

-- 36
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000455457', 'Пловдивски Университет "Паисий Хилендарски"','Plovdiv University "Paisii Hilendarski"', '56784', 'ул."Цар Асен" 24', '24 "Tsar Asen" Str.', '4000', 'http://www.uni-plovdiv.bg', '/uni_logos/uni-plovdiv.png');

-- 37
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000522685', 'Русенски университет "Ангел Кънчев"','University of Ruse "Angel Kanchev"', '63427', 'ул."Студентска" 8', '8 "Studentska" Str.', '7017', 'http://www.uni-ruse.bg', '/uni_logos/uni-ruse.png');

-- 38
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670680', 'Софийски университет "Св. Климент Охридски"','Sofia University "St. Kliment Ohridski"', '68134', 'бул. "Цар Освободител" 15', '15 Tsar Osvoboditel Blvd.', '1504', 'https://www.uni-sofia.bg', '/uni_logos/uni-sofia.jpeg');

-- 39
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000124026', 'Стопанска академия "Димитър А. Ценов" - Свищов','Dimitar A. Tsenov Academy of Economics', '65766', 'ул."Емануил Чакъров" 2', 'Emanuil Chakarov 2 str.', '5250', 'http://www.uni-svishtov.bg', '/uni_logos/uni-svishtov.png');

-- 40
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('131012820', 'Театрален колеж "Любен Гройс"','Theater College "Luben Groys"', '68134', 'бул. "Евлоги и Христо Георгиеви" № 169', '169 "Evlogi i Hristo Georgievi" Boulevard', '1504', 'http://lgroys-college.com/', '/uni_logos/lgroys-college.jpg');

-- 41
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000083626', 'Технически университет - Варна','Technical University of Varna', '10135', 'ул."Студентска", 1', '1, "Studentska" Str.', '9010', 'http://www.tu-varna.bg', '/uni_logos/tu-varna.jpg');

-- 42
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000210319', 'Технически университет - Габрово','Technical University - Gabrоvo (AU)', '14218', 'ул."Хаджи Димитър" 4', '4 "H. Dimitar" Str.', '5300', 'http://www.tugab.bg', '/uni_logos/tugab.png');

-- 43
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('831917834', 'Технически университет - София','Technical University - Sofia', '68134', 'бул. "Св. Климент Охридски" 8', '8, "St. Kliment Ohridski" Blvd.', '1756', 'https://www.tu-sofia.bg/', '/uni_logos/tu-sofia.jpg');

-- 44
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('123024538', 'Тракийски Университет - Стара Загора','Trakia University - Stara Zagora', '68850', 'Студентски град - Малка Верея', 'Sudent`s campus', '6000', 'http://www.uni-sz.bg', '/uni_logos/uni-sz.jpg');

-- 45
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000044541', 'Университет "Проф. д-р Асен Златаров" - Бургас','University "Prof. A. Zlatarov" (AU)', '07079', 'ул."Проф.Якимов" 1', '1 "Prof. Yakimov" Str.', '8010', 'http://www.btu.bg', '/uni_logos/btu.png');

-- 46
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670602', 'Университет за национално и световно стопанство','University of National and World Economy', '68134', 'ул. „8-ми декември“, 1700 Студентски Комплекс', '"8-mi dekemvri" street, 1700 Studentski Kompleks', '1100', 'https://www.unwe.bg/', '/uni_logos/unwe.png');

-- 47
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670616', 'Университет по архитектура, строителство и геодезия','University of Architecture, Building and Geodesy (AU)', '68134', 'бул."Христо Смирненски" 1', '1 "Hhristo Smirnenski" Blvd.', '1046', 'http://uacg.bg', '/uni_logos/uacg.png');

-- 48
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670552', 'Университет по библиотекознание и информационни технологии','University of Library Studies and Information Technologies', '68134', 'бул. "Цариградско шосе" № 119', '119, Tsarigradsko Shose Blvd.', '1784', 'http://www.unibit.bg', '/uni_logos/unibit.jpg');

-- 49
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000455440', 'Университет по Хранителни Технологии','University of Food Technologies', '56784', 'бул."Марица" 26', '26 "Maritsa" blv', '4002', 'http://uft-plovdiv.bg/', '/uni_logos/uft-plovdiv.jpg');

-- 50
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000670673', 'Химикотехнологичен и металургичен университет - София','University of Chemical Technology and Metallurgy', '68134', 'бул. "Кл.Охридски" 8', '8 "Kl. Ohridski" Blvd.', '1756', 'http://www.uctm.edu', '/uni_logos/uctm.jpg');

-- 51
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000934863', 'Шуменски университет "Епископ Константин Преславски"','Konstantin Preslavsky University of Shumen', '83510', 'ул. Университетска 115', '115 Universitetska str.', '9700', 'http://shu.bg/', '/uni_logos/shubg.jpg');

-- 52
INSERT INTO nomenclatures.national_university (eik, name, name_en, settlement_code, address, address_en, zip_code, website, logo_rel_path)
VALUES ('000017149', 'Югозападен университет "Неофит Рилски"','South-West University "Neofit Rilski"', '04279', 'ул."Иван Михайлов" 66', '66 "Ivan Mihailov" Str.', '2700', 'http://www.swu.bg', '/uni_logos/swubg.png');
