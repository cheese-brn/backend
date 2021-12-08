insert into data_type
values (1,
        'String');

insert into data_type
values (2,
        'Interval');

insert into rod_strain
values (1,
        111111,
        'Lactococcus');

insert into rod_strain
values (2,
        222222,
        'Lactobacillus');

insert into rod_strain
values (3,
        333333,
        'Bifidobacterium');

insert into rod_strain
values (4,
        444444,
        'Propionibacterium');

insert into rod_strain
values (5,
        555555,
        'Streptococcus');

insert into vid_strain
values (1,
        111111,
        'lactis',
        1);

insert into vid_strain
values (2,
        222222,
        'casei',
        2);

insert into vid_strain
values (3,
        333333,
        'longum',
        3);

insert into vid_strain
values (4,
        444444,
        'freudenreichii',
        4);

insert into vid_strain
values (5,
        555555,
        'thermophilus',
        5);

insert into vid_strain
values (6,
        666666,
        'fermentum',
        2);

insert into property
values (1,
        111111,
        'описание',
        'Наличие плазмид',
        false);

insert into property
values (2,
        222222,
        'описание',
        'Форма и расположение клеток',
        false);

insert into property
values (3,
        333333,
        'описание',
        'Кислотообразование в молоке',
        true);

insert into property
values (4,
        444444,
        'описание',
        'Форма и величина колоний',
        false);

insert into property
values (5,
        555555,
        'описание',
        'Аммиак из аргинина',
        false);

insert into property
values (6,
        666666,
        'описание',
        'Утилизация цитрата',
        false);

insert into property
values (7,
        777777,
        'описание',
        'Ферментация углеводов',
        false);

insert into property
values (9,
        999999,
        'описание',
        'Характер сгустка',
        false);

insert into property
values (10,
        101010,
        'описание',
        'Предельная кислотность',
        false);

insert into property
values (11,
        11111111,
        'описание',
        'Устойчивость к желчи',
        false);

insert into property
values (12,
        121212,
        'описание',
        'Температура роста',
        false);

insert into property
values (13,
        131313,
        'описание',
        'Антагонизм к кишечной палочке',
        false);

insert into subproperty
values (1,
        '',
        1,
        1);

insert into subproperty
values (2,
        '',
        2,
        1);

insert into subproperty
values (3,
        'Температура',
        3,
        1);

insert into subproperty
values (4,
        'Время',
        3,
        1);

insert into subproperty
values (5,
        'Поверхностные',
        4,
        1);

insert into subproperty
values (6,
        'Глубинные',
        4,
        1);

insert into subproperty
values (7,
        '',
        5,
        1);

insert into subproperty
values (8,
        '',
        6,
        1);

insert into subproperty
values (9,
        'Ферментирует',
        7,
        1);

insert into subproperty
values (10,
        'Не ферментирует',
        7,
        1);

insert into subproperty
values (13,
        '',
        9,
        1);

insert into subproperty
values (15,
        '',
        10,
        1);

insert into subproperty
values (16,
        '',
        11,
        1);

insert into subproperty
values (17,
        '',
        12,
        1);

insert into subproperty
values (18,
        'Зона подавления роста',
        13,
        1);

insert into subproperty
values (19,
        'Зона угнетения роста',
        13,
        1);


insert into strain
values (1,
        'Может использоваться в исследованиях в качестве индикаторного штамма с селективным маркером',
        '977',
        'C15',
        'Культивирование в отсутствии лактозы (Я.Р.Каган, И.Я.Сергеева)',
        'Лактозоотрицательный мутант штамма L. lactis 977.2',
        1);

insert into strain
values (2,
        'Дикий тип',
        '977',
        '2',
        'Исходный, выделен из природных источников',
        'Заквасочный фонд АФ ВНИИМС',
        1);

insert into strain
values (3,
        'Дикий тип',
        '169',
        '2',
        'Исходный, выделен из природных источников',
        'Заквасочный фонд АФ ВНИИМС',
        1);

insert into strain
values (4,
        'отобран по вкусовым качествам молочного сгустка (чистый кисломолочный)',
        'ФР',
        '2',
        'Выделен в 2007 г. (Сергеева И.Я.)',
        'Импортный (французский) сыр «Conte»',
        2);

insert into strain
values (5,
        'отобран по вкусовым качествам молочного сгустка (сладко-сливочный вкус)',
        'ИМ 4',
        '6',
        'Выделен в 2007 г. (Сергеева И.Я.)',
        'Кисломолочный продукт «Имунеле»',
        2);

insert into strain
values (6,
        'отобран по вкусовым качествам молочного сгустка (сладко-сливочный вкус)',
        'T4',
        '',
        'Выделен из сухого коммерч.препарата «Бифидумбактерин» производства ' ||
        'Тюменского фармзавода (серия С577 ББК51; И.Я.Сергеева, 1992 г.)',
        '-',
        3);

insert into strain
values (7,
        'используется в составе микробного комплекса бакконцентрата «Биоантибут-А-Углич» для защиты сыров с' ||
        ' высокой температурой второго нагревания от маслянокислого брожения.',
        'sp.globosum 11',
        '2',
        'Выделен из советского сыра (Карагужинский МСЗ; М.А.Алексеева, Е.Ф.Отт, 1975 г.)',
        'Дикий тип',
        4);

insert into strain
values (8,
        'Депонирован в ВНИИгенетика под № В-2163 (28.10.80). Защтщен а.с. №' ||
        '  С 1984 г. входит в состав пром. препарата для сыроделия («многошт. к-ра пкб»)',
        'sp.globosum X',
        '3',
        'Выделен из советского сыра (Карагужинский МСЗ;  М.А.Алексеева, Е.Ф.Отт, 1975 г.)',
        'Дикий тип',
        4);

insert into strain
values (9,
        'устойчивость к стрептомицину 5 мкг/мл, к пенициллину - 0,01 ЕД. Промышленный штамм,' ||
        ' используется в заквасках для сыров с высокой температурой второго нагревания ',
        '124',
        '4',
        '-',
        'Коллекция ВНИИМС (Углич)',
        5);

insert into strain
values (10,
        'устойчивость к стрептомицину 10 мкг/мл, к пенициллину - 0,001 ЕД. Промышленный штамм, ' ||
        'используется в заквасках для сыров с высокой температурой второго нагревания',
        '125',
        '4',
        '-',
        'Коллекция ВНИИМС (Углич)',
        5);

insert into strain
values (11,
        'используется в составе микробного комплекса бакконцентрата «Биоантибут-А-Углич» для защиты сыров' ||
        ' с высокой температурой второго нагревания от маслянокислого брожения.',
        '44',
        '1',
        'Выделен в 80-х г.г. (Анищенко И.П.)',
        'Сыр «Советский»',
        6);

insert into strain
values (12,
        'Зона ингибирования E.coli 125 (метод перпендик.штрихов) 4 мм. Гетерофермент.тип брожения (газ из глюкозы). ' ||
        'Может использоваться в исследованиях как индикаторный штамм с селективным маркером',
        '44',
        'СУ',
        'Пассирование в среде с возрастающими конц-ями стрептомицина (Я.Р.Каган, И.Я.Сергеева)',
        'Strr мутант шт. L.fermentum 44-1',
        6);

insert into dependency_table
values (1,
        3,
        4);

insert into fact_param
values (1,
        'Нд',
        1,
        1,
        1);

insert into fact_param
values (2,
        'Кокки, диплококки',
        1,
        2,
        2);

insert into fact_param
values (3,
        'Круглые каплевидные с ровным краем диам. 2 мм',
        1,
        4,
        5);

insert into fact_param
values (4,
        'дискообразные',
        1,
        4,
        6);

insert into fact_param
values (5,
        'Образует',
        1,
        5,
        7);

insert into fact_param
values (6,
        'Не утилизирует',
        1,
        6,
        8);

insert into fact_param
values (7,
        'глюкозу, галактозу, мальтозу, манит (слабо)',
        1,
        7,
        9);

insert into fact_param
values (8,
        'лактозу, сахарозу',
        1,
        7,
        10);

insert into fact_param
values (9,
        '-',
        1,
        9,
        13);

insert into fact_param
values (10,
        'Нд',
        1,
        12,
        17);

insert into fact_param_func
values (1,
        '34',
        '24',
        'Без глюкозы',
        1,
        1);

insert into fact_param_func
values (2,
        '50',
        '168',
        'Без глюкозы',
        1,
        1);

insert into fact_param_func
values (3,
        '54',
        '24',
        'С глюкозой',
        1,
        1);

insert into fact_param_func
values (4,
        '90',
        '168',
        'С глюкозой',
        1,
        1);

insert into fact_param
values (11,
        'Нд',
        2,
        1,
        1);

insert into fact_param
values (12,
        'Кокки, диплококки',
        2,
        2,
        2);

insert into fact_param
values (13,
        'Круглые каплевидные с ровным краем диам. 2 мм',
        2,
        4,
        5);

insert into fact_param
values (14,
        'дискообразные',
        2,
        4,
        6);

insert into fact_param
values (15,
        'Образует',
        2,
        5,
        7);

insert into fact_param
values (16,
        'Не утилизирует',
        2,
        6,
        8);

insert into fact_param
values (17,
        'глюкозу, галактозу, мальтозу, манит (слабо)',
        2,
        7,
        9);

insert into fact_param
values (18,
        'сахарозу',
        2,
        7,
        10);

insert into fact_param
values (19,
        'Плотный ровный',
        2,
        9,
        13);

insert into fact_param
values (20,
        'Нд',
        2,
        12,
        17);

insert into fact_param_func
values (5,
        '100',
        '24',
        '',
        2,
        1);

insert into fact_param_func
values (6,
        '102',
        '168',
        '',
        2,
        1);

insert into fact_param
values (21,
        'Нд',
        3,
        1,
        1);

insert into fact_param
values (22,
        'Кокки, диплококки',
        3,
        2,
        2);

insert into fact_param
values (23,
        'белые круглые блестящие диам. 1-2 мм',
        3,
        4,
        5);

insert into fact_param
values (24,
        'дискообразные',
        3,
        4,
        6);

insert into fact_param
values (25,
        'Образует',
        3,
        5,
        7);

insert into fact_param
values (26,
        'Не утилизирует',
        3,
        6,
        8);

insert into fact_param
values (27,
        'глюкозу, лактозу, галактозу, сахарозу, мальтозу',
        3,
        7,
        9);

insert into fact_param
values (28,
        'маннит',
        3,
        7,
        10);

insert into fact_param
values (29,
        'Плотный ровный',
        3,
        9,
        13);

insert into fact_param
values (30,
        'Нд',
        3,
        12,
        17);

insert into fact_param_func
values (7,
        '110',
        '24',
        '',
        3,
        1);

insert into fact_param_func
values (8,
        '116',
        '168',
        '',
        3,
        1);

insert into fact_param
values (31,
        'Нд',
        4,
        1,
        1);

insert into fact_param
values (32,
        'Короткие тонкие палочки со слабой зернистостью в цепочках ',
        4,
        2,
        2);

insert into fact_param
values (33,
        'на АГМ: круглые выпуклые серые диам. 1,0-1,5 мм',
        4,
        4,
        5);

insert into fact_param
values (34,
        'в полужидком АГМ: в форме серовато-белых гречишных зерен величиной 1-3 мм',
        4,
        4,
        6);

insert into fact_param
values (35,
        'Не образует',
        4,
        5,
        7);

insert into fact_param
values (36,
        'Утилизирует с образованием газа',
        4,
        6,
        8);

insert into fact_param
values (37,
        'лактозу, манит, галактозу',
        4,
        7,
        9);

insert into fact_param
values (38,
        'рамнозу, арабинозу, раффинозу, сахарозу, мальтозу, ксилозу',
        4,
        7,
        10);

insert into fact_param
values (39,
        'Рыхлый',
        4,
        9,
        13);

insert into fact_param
values (40,
        '132 ºТ',
        4,
        10,
        15);

insert into fact_param
values (41,
        '20% (40% слабо)',
        4,
        11,
        16);

insert into fact_param
values (42,
        '30 ºС',
        4,
        12,
        17);

insert into fact_param
values (43,
        'Отсутствует',
        4,
        13,
        18);

insert into fact_param
values (44,
        'Отсутствует',
        4,
        13,
        19);

insert into fact_param_func
values (9,
        '78',
        '',
        '',
        4,
        1);

insert into fact_param
values (45,
        'Нд',
        5,
        1,
        1);

insert into fact_param
values (46,
        'Короткие тонкие палочки одиночные и в цепочках',
        5,
        2,
        2);

insert into fact_param
values (47,
        'на АГМ: круглые выпуклые серые диам. 1,0-1,5 мм',
        5,
        4,
        5);

insert into fact_param
values (48,
        'в полужидком АГМ: в форме серовато-белых гречишных зерен величиной 1-3 мм',
        5,
        4,
        6);

insert into fact_param
values (49,
        'Не образует',
        5,
        5,
        7);

insert into fact_param
values (50,
        'Утилизирует с образованием газа',
        5,
        6,
        8);

insert into fact_param
values (51,
        'лактозу, манит, мальтозу, галактозу',
        5,
        7,
        9);

insert into fact_param
values (52,
        'рамнозу, арабинозу, раффинозу, сахарозу, ксилозу',
        5,
        7,
        10);

insert into fact_param
values (53,
        'Ровный',
        5,
        9,
        13);

insert into fact_param
values (54,
        '136 ºТ',
        5,
        10,
        15);

insert into fact_param
values (55,
        '20% - 40% средняя',
        5,
        11,
        16);

insert into fact_param
values (56,
        '30 ºС',
        5,
        12,
        17);

insert into fact_param
values (57,
        'Отсутствует',
        5,
        13,
        18);

insert into fact_param
values (58,
        'Отсутствует',
        5,
        13,
        19);

insert into fact_param_func
values (10,
        '98',
        '',
        '',
        5,
        1);

insert into fact_param
values (59,
        'Нд',
        6,
        1,
        1);

insert into fact_param
values (60,
        'Тонкие плейоморфные, часто изогнутые наподобие веточек, палочки, образуют бифуркации',
        6,
        2,
        2);

insert into fact_param
values (61,
        '(ГМС, анаэроб.условия): круглые выпуклые под цвет среды, диам. 1-3 мм',
        6,
        4,
        5);

insert into fact_param
values (62,
        'гречишн.зерна или двояковыпуклые линзы. светлокоричневые, до 3 мм',
        6,
        4,
        6);

insert into fact_param
values (63,
        'Нд',
        6,
        5,
        7);

insert into fact_param
values (64,
        'Нд',
        6,
        6,
        8);

insert into fact_param
values (65,
        'арабинозу, ксилозу, лактозу, лактулозу, мальтозу, сахарозу',
        6,
        7,
        9);

insert into fact_param
values (66,
        'рибозу, маннит',
        6,
        7,
        10);

insert into fact_param
values (67,
        'Равномерный нежный со специфическим привкусом бифидобактерий, с небольшим отделением сыворотки',
        6,
        9,
        13);

insert into fact_param
values (68,
        'Оптим. 37 ºС',
        6,
        12,
        17);

insert into fact_param_func
values (11,
        '90 ºТ',
        '24',
        '',
        6,
        1);

insert into fact_param_func
values (12,
        '180 ºТ',
        '168',
        '',
        6,
        1);

insert into fact_param
values (69,
        'Нд',
        7,
        1,
        1);

insert into fact_param
values (70,
        'Мелкие кокки',
        7,
        2,
        2);

insert into fact_param
values (71,
        '(Лакт.агар, анаэроб.усл.): круглые выпуклые с ровным краем с глянцевой поверхностью светло-кремовые, диам. 1-2 мм',
        7,
        4,
        5);

insert into fact_param
values (72,
        'круглые и чечевички, светло-коричневые',
        7,
        4,
        6);

insert into fact_param
values (73,
        'лактозу, маннозу, глицерин',
        7,
        7,
        9);

insert into fact_param
values (74,
        'маннит, инулин, фруктозу, мальтозу, раффинозу, сорбит, рамнозу, ксилозу, декстрин, дульцит',
        7,
        7,
        10);

insert into fact_param
values (75,
        'Оптим. 30 °С, не растет при 8-10 °С, растет при 12 °С (на 25 сутки), 15 °С (на 15 сутки)',
        7,
        12,
        17);

insert into fact_param
values (76,
        'Нд',
        8,
        1,
        1);

insert into fact_param
values (77,
        'Мелкие кокки',
        8,
        2,
        2);

insert into fact_param
values (78,
        '(Лакт.агар, анаэроб.усл.): круглые выпуклые с ровным краем с глянцевой поверхностью светло-кремовые, диам. 1-2 мм',
        8,
        4,
        5);

insert into fact_param
values (79,
        'круглые и чечевички, светло-коричневые',
        8,
        4,
        6);

insert into fact_param
values (80,
        'лактозу, ксилозу (Щ), глицерин',
        8,
        7,
        9);

insert into fact_param
values (81,
        'маннит, инулин, фруктозу, мальтозу, маннозу, раффинозу, сорбит, рамнозу, декстрин, дульцит, крахмал',
        8,
        7,
        10);

insert into fact_param
values (82,
        'Оптим. 30 °С, не растет при 8-10 и при 15 °С',
        8,
        12,
        17);

insert into fact_param
values (83,
        'Крупные диплококки в цепочках разной длины',
        9,
        2,
        2);

insert into fact_param
values (84,
        'мелкие круглые плоские серые',
        9,
        4,
        5);

insert into fact_param
values (85,
        'чечевички (плотный агар) или рыхлые комочки (полужидкий агар)',
        9,
        4,
        6);

insert into fact_param
values (86,
        'Нд',
        9,
        5,
        7);

insert into fact_param
values (87,
        'в аэробных условиях (-), в анаэробных условиях (+)',
        9,
        6,
        8);

insert into fact_param
values (88,
        'лактозу и глюкозу',
        9,
        7,
        9);

insert into fact_param
values (89,
        'галактозу',
        9,
        7,
        10);

insert into fact_param
values (90,
        'Плотный ровный слизистый',
        9,
        9,
        13);

insert into fact_param
values (91,
        '20 °С (-), 30 °С (+), 45 °С (+)',
        9,
        12,
        17);

insert into fact_param_func
values (13,
        '80 ºТ',
        '24',
        '',
        9,
        1);

insert into fact_param_func
values (14,
        '124 ºТ',
        '168',
        '',
        9,
        1);

insert into fact_param
values (92,
        'Крупные овальные диплококки в длинных цепочках',
        10,
        2,
        2);

insert into fact_param
values (93,
        'мелкие круглые плоские светлые',
        10,
        4,
        5);

insert into fact_param
values (94,
        'чечевички (плотный агар) или рыхлые комочки (полужидкий агар)',
        10,
        4,
        6);

insert into fact_param
values (95,
        'Нд',
        10,
        5,
        7);

insert into fact_param
values (96,
        'Нд',
        10,
        6,
        8);

insert into fact_param
values (97,
        'лактозу, глюкозу, сахарозу',
        10,
        7,
        9);

insert into fact_param
values (98,
        'галактозу, мальтозу, арабинозу, ксилозу, манит, сорбит, маннозу, раффинозу',
        10,
        7,
        10);

insert into fact_param
values (99,
        'Плотный ровный не вязкий',
        10,
        9,
        13);

insert into fact_param
values (100,
        '20 °С (-), 30 °С (+), 45 °С (+)',
        10,
        12,
        17);

insert into fact_param_func
values (15,
        '96 ºТ',
        '24',
        '',
        10,
        1);

insert into fact_param_func
values (16,
        '122 ºТ',
        '168',
        '',
        10,
        1);

insert into fact_param
values (101,
        'Нд',
        11,
        1,
        1);

insert into fact_param
values (102,
        'Палочки тонкие, короткие, образуют  цепочки и пары',
        11,
        2,
        2);

insert into fact_param
values (103,
        'на АГМ: круглые выпуклые белые диам. 1-3 мм',
        11,
        4,
        5);

insert into fact_param
values (104,
        'в виде мелких белых лодочек',
        11,
        4,
        6);

insert into fact_param
values (105,
        'Образует',
        11,
        5,
        7);

insert into fact_param
values (106,
        'Утилизирует с образованием газа',
        11,
        6,
        8);

insert into fact_param
values (107,
        'глюкозу (газ), лактозу, сахарозу, мальтозу',
        11,
        7,
        9);

insert into fact_param
values (108,
        'маннит',
        11,
        7,
        10);

insert into fact_param
values (109,
        '-',
        11,
        9,
        13);

insert into fact_param
values (110,
        '-',
        11,
        10,
        15);

insert into fact_param
values (111,
        '-',
        11,
        11,
        16);

insert into fact_param
values (112,
        '37 ºС',
        11,
        12,
        17);

insert into fact_param
values (113,
        '-',
        11,
        13,
        18);

insert into fact_param
values (114,
        'величиной 4-6 мм',
        11,
        13,
        19);

insert into fact_param_func
values (17,
        '50 ºТ',
        '120',
        '',
        11,
        1);

insert into fact_param
values (115,
        'Нд',
        12,
        1,
        1);

insert into fact_param
values (116,
        'Короткие тонкие палочки в цепочках различной длины  ',
        12,
        2,
        2);

insert into fact_param
values (117,
        'на обогащ.АГМ: выпуклые белые однородные, диам.до 1 мм',
        12,
        4,
        5);

insert into fact_param
values (118,
        '-',
        12,
        4,
        6);

insert into fact_param
values (119,
        'Образует',
        12,
        5,
        7);

insert into fact_param
values (120,
        'Утилизирует',
        12,
        6,
        8);

insert into fact_param
values (121,
        'лактозу, мальтозу, лактулозу, арабинозу, сахарозу, маннит',
        12,
        7,
        9);

insert into fact_param
values (122,
        'ксилозу',
        12,
        7,
        10);

insert into fact_param
values (123,
        '-',
        12,
        9,
        13);

insert into fact_param
values (124,
        'Оптим. 37 ºС, пределы 30-40 ºС',
        12,
        12,
        17);

insert into fact_param_func
values (18,
        '50 ºТ',
        'предельное',
        '',
        12,
        1);
