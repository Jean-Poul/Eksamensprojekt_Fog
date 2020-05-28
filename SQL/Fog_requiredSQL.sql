-- Required SQL for FogDB
USE fogdb;

-- ------------------------------------------------------------------------------------------------------------------------
-- Insert static records to tables in `users`, `measurement_units`, `roof`, `roof_pitch`, `rafter_spacing` and `item_list`
-- + One dummy user_proposition, orders record
-- ------------------------------------------------------------------------------------------------------------------------
LOCK TABLES users WRITE;
INSERT INTO users (email,password,role) VALUES ('fogAdmin@fog.dk','Gruppe666/','admin');
UNLOCK TABLES;

LOCK TABLES measurement_units WRITE;
INSERT INTO measurement_units (units,c_width,c_length,ts_width,ts_length) VALUES ('150','0','0','0','1'),
																				 ('180','0','0','0','1'),
                                                                                 ('210','0','0','1','1'),
                                                                                 ('240','1','1','1','1'),
                                                                                 ('270','1','1','1','1'),
                                                                                 ('300','1','1','1','1'),
                                                                                 ('330','1','1','1','1'),
                                                                                 ('360','1','1','1','1'),
                                                                                 ('390','1','1','1','1'),
                                                                                 ('420','1','1','1','1'),
                                                                                 ('450','1','1','1','1'),
                                                                                 ('480','1','1','1','1'),
                                                                                 ('510','1','1','1','1'),
                                                                                 ('540','1','1','1','1'),
                                                                                 ('570','1','1','1','1'),
                                                                                 ('600','1','1','1','1'),
                                                                                 ('630','1','1','1','1'),
                                                                                 ('660','1','1','1','1'),
                                                                                 ('690','1','1','1','1'),
                                                                                 ('720','1','1','1','0'),
                                                                                 ('750','1','1','0','0'),
                                                                                 ('780','0','1','0','0');
UNLOCK TABLES;

LOCK TABLES roof WRITE;
INSERT INTO roof (roof_type,roof_category,roof_material) VALUES ('fladt','let','Plasttrapezplader'),
												  ('fladt','let','Plastmo Ecolite blåtonet'),
												  ('rejst','tung','Betontagsten - Rød'),
                                                  ('rejst','tung','Betontagsten - Teglrød'),
                                                  ('rejst','tung','Betontagsten - Brun'),
                                                  ('rejst','tung','Betontagsten – Sort'),
                                                  ('rejst','tung','Betontagsten – B&C protector: Sort'),
                                                  ('rejst','tung','Betontagsten – B&C protector: Skifer'),
                                                  ('rejst','tung','Betontagsten – B&C protector: Koralrød'),
                                                  ('rejst','tung','Betontagsten – B&C protector DANFLOCK: Sort'),
                                                  ('rejst','tung','Betontagsten – B&C protector DANFLOCK: Skifer'),
                                                  ('rejst','tung','Betontagsten – B&C protector DANFLOCK: Koralrød'),
                                                  ('rejst','let','Eternittag B6 - Grå'),
                                                  ('rejst','let','Eternittag B6 - Sort'),
                                                  ('rejst','let','Eternittag B6 - Mokka (brun)'),
                                                  ('rejst','let','Eternittag B6 – Rødbrun'),
                                                  ('rejst','let','Eternittag B6 - Teglrød'),
                                                  ('rejst','let','Eternittag B7 - Grå'),
                                                  ('rejst','let','Eternittag B7 - Sort'),
                                                  ('rejst','let','Eternittag B7 - Mokka (brun)'),
                                                  ('rejst','let','Eternittag B7 - Rødbrun'),
                                                  ('rejst','let','Eternittag B7 - Teglrød'),
                                                  ('rejst','let','Eternittag B7 - Rødflammet'),
                                                  ('rejst','tung','Gammel Dansk – Rød'),
                                                  ('rejst','tung','Gammel Dansk – Mocca'),
                                                  ('rejst','tung','Gammel Dansk – Blådæmpet'),
                                                  ('rejst','tung','Gammel Dansk – Sortglaseret'),
                                                  ('rejst','tung','Gammel Dansk – Engoberet sort'),
                                                  ('rejst','tung','Gammel Dansk – Engoberet gul'),
                                                  ('rejst','tung','Nortegl – Engoberet sort'),
                                                  ('rejst','tung','Nortegl – Naturrød'),
                                                  ('rejst','tung','Nortegl – Engoberet gul'),
                                                  ('rejst','tung','Nortegl – Glaseret sort'),
                                                  ('rejst','tung','Nortegl – Engoberet flammet rød'),
                                                  ('rejst','tung','Hollander – Naturrød'),
                                                  ('rejst','tung','Hollander – Engoberet sortbrun'),
                                                  ('rejst','tung','Hollander – engoberet glaseret sort'),
                                                  ('rejst','tung','Hollander – engoberet antracit'),
                                                  ('rejst','tung','Hollander – engoberet brun'),
                                                  ('rejst','tung','Hollander – engoberet glaseret mat sort'),
                                                  ('rejst','tung','KDN VH – Naturrød'),
                                                  ('rejst','tung','KDN VH – Glaserede: sort'),
                                                  ('rejst','tung','KDN VH – Glaserede: sølvsort'),
                                                  ('rejst','tung','KDN VH – Glaserede: matsort'),
                                                  ('rejst','tung','KDN VH – Glaserede: lys brun'),
                                                  ('rejst','tung','KDN VH – Glaserede: mørk brun'),
                                                  ('rejst','tung','KDN VH – Glaserede: vinrød'),
                                                  ('rejst','tung','KDN VH – Glaserede: mørk rød'),
                                                  ('rejst','tung','Turmalin – Naturrød'),
                                                  ('rejst','tung','Turmalin – Engoberet rød'),
                                                  ('rejst','tung','Turmalin – Engoberet sort'),
                                                  ('rejst','tung','Turmalin – Engoberet lys grå'),
                                                  ('rejst','tung','Turmalin – Ædelengoberet kastanje'),
                                                  ('rejst','tung','Turmalin – mørk grå'),
                                                  ('rejst','tung','Turmalin – sort'),
                                                  ('rejst','tung','Dantegl NOVA – Engoberet naturrød'),
                                                  ('rejst','tung','Dantegl NOVA – Engoberet sort'),
                                                  ('rejst','tung','Dantegl NOVA – Ædelengoberet sort');
UNLOCK TABLES;

LOCK TABLES roof_pitch WRITE;
INSERT INTO roof_pitch (pitch,factor) VALUES ('15','1'),
											 ('20','0.97'),
											 ('25','0.94'),
											 ('30','0.89'),
											 ('35','0.84'),
											 ('40','0.79'),
											 ('45','0.72');
UNLOCK TABLES;

LOCK TABLES rafter_spacing WRITE;
INSERT INTO rafter_spacing (category,beam_dimension,beam_spacing,rafter_length) VALUES ('let','45 x 120','0.4','2.81'),
																				('let','45 x 120','0.6','2.48'),
                                                                                ('let','45 x 120','0.8','2.26'),
                                                                                ('let','45 x 120','1.0','2.10'),
                                                                                ('let','45 x 120','1.2','1.98'),
																				('let','45 x 195','0.4','4.52'),
                                                                                ('let','45 x 195','0.6','4.02'),
                                                                                ('let','45 x 195','0.8','3.68'),
                                                                                ('let','45 x 195','1.0','3.44'),
                                                                                ('let','45 x 195','1.2','3.24'),
                                                                                ('tung','45 x 120','0.4','2.43'),
                                                                                ('tung','45 x 120','0.6','2.13'),
                                                                                ('tung','45 x 120','0.8','1.93'),
                                                                                ('tung','45 x 120','1.0','1.79'),
                                                                                ('tung','45 x 120','1.2','1.68'),
                                                                                ('tung','45 x 195','0.4','3.94'),
                                                                                ('tung','45 x 195','0.6','3.48'),
                                                                                ('tung','45 x 195','0.8','3.18'),
                                                                                ('tung','45 x 195','1.0','2.95'),
                                                                                ('tung','45 x 195','1.2','2.78');
UNLOCK TABLES;

LOCK TABLES item_list WRITE;
INSERT INTO item_list (material_type,material,description,amounts,unit,price_per_unit) VALUES ('Tilbehør til spær:','universal 190 mm højre','Til montering af spær på rem','1','','23.00'),
																							   ('Tilbehør til spær:','universal 190 mm venstre','Til montering af spær på rem','1','','23.00'),
                                                                                               ('Tilbehør til spær:','Hulplade 1,5 mm – 100 x 300 1,5.','Til samling af spær','2','','18.00'),
                                                                                               ('Tilbehør til spær:','hulbånd 1x20 mm. 10 mtr.','Til vindkryds på spær','1','','237.00'),
                                                                                               ('Tilbehør til dør:','Stalddørsgreb 50x75','til dør i skur i sæt','1','','66.00'),
                                                                                               ('Tilbehør til dør:','T-hængsel 390 mm.','til dør i skur i sæt','2','','61.50'),
                                                                                               ('Tilbehør til tværgående løsholter:','vinkelbeslag 35mm','til montering af løsholter (stk. pr. løsholter - afhænger af højde)','2','','3.50'),
                                                                                               ('Skruepakke som fast følger med:','4,5 x 60 mm. Skruer 200 stk.','Til montering af Stern, vindskeder, vindkryds & vand bræt','1','200','38.50'),
                                                                                               ('Skruepakke som fast følger med:','5,0 x 40 mm. beslagskruer 250 stk.','Til montering af universalbeslag + toplægte','1','250','109.50'),
                                                                                               ('Skruepakke som fast følger med:','5,0 x 100 mm. skruer 200 stk.','til taglægter','1','200','169.00'),
                                                                                               ('Skruepakke som fast følger med:','4,5 x 70 mm. Skruer 200 stk.','til montering af yderste bræt ved beklædning','1','200','70.00'),
                                                                                               ('Skruepakke som fast følger med:','4,5 x 50 mm. Skruer 350 stk.','til montering af inderste bræt ved beklædning','1','350','37.00'),
                                                                                               ('Skruepakke som fast følger med:','plastmo bundskruer 200 stk.','Skruer til tagplader','1','200','204.50'),
                                                                                               ('Tilbehør til stolper:','bræddebolt 10 x 120 mm.','Til montering af rem på stolper (stk. pr stolpe, 4 i samling)','2','stk','6.60'),
                                                                                               ('Tilbehør til stolper:','firkantskiver 40x40x11mm','Til montering af rem på stolper (stk. pr stolpe, 4 i samling)','2','stk','7.35'),
                                                                                               ('Tilbehør til tag:','B & C Toplægte holder','Til montering på toppen af spæret (til toplægte)','1','m','24.50'),
                                                                                               ('Tilbehør til tag:','B & C rygstensbeslag','Til montering af rygsten (pakke med 50 stk)','50','stk','252.00'),
                                                                                               ('Tilbehør til tag:','B & C tagstens bindere & nakkekroge','til tagsten, alle ydersten + hver anden fastgøres. Fast pakke','2','stk','149.95'),
                                                                                               ('Spær','45 x 95 mm','45x95 Tagspær trykimpr.','1','m','12.50'),
                                                                                               ('Spær','45 x 120 mm','45x120 Tagspær trykimpr.','1','m','14.95'),
                                                                                               ('Spær','45 x 195 mm','45x195 Tagspær trykimpr.','1','m','22.50'),
                                                                                               ('Stolper','100 x 100 mm','100x100 Stolpe trykimpr.','1','m','22.50'),
                                                                                               ('Stolper','125 x 125 mm','125x125 Stolpe trykimpr.','1','m','79.50'),
                                                                                               ('Rem','45 x 145 mm','45x145 Rem ubh.','1','m','17.50'),
                                                                                               ('Rem','45 x 195 mm','45x195 Rem ubh.','1','m','22.50'),
                                                                                               ('Lægter, stern og løsholter','38x73','38x73 taglægte T1','1','m','11.50'),
                                                                                               ('Lægter, stern og løsholter','38x73','38x73 toplægte','1','m','11.50'),
                                                                                               ('Lægter, stern og løsholter','38x73','38x73 Lægte til z på bagside af dør ubh.','1','m','11.50'),
                                                                                               ('Lægter, stern og løsholter','45x95','45x95 mm ubh. Løsholte','1','m','9.50'),
                                                                                               ('Lægter, stern og løsholter','25x200','25x200 Bræt til stern trykimpr.','1','m','24.95'),
                                                                                               ('Lægter, stern og løsholter','19x100','19x100 Beklædning af skur','1','m','4.95'),
                                                                                               ('Tag','20,4 x 23,6','20,4 x 23,6 tagsten','1','stk','8.95'),
                                                                                               ('Tag','109 x 240','109 x 240 tagplade','1','stk','42.50'),
                                                                                               ('Beslagspakke','Beslag','Diverse beslag til hele carporten','1','stk','399.95'),
                                                                                               ('Dør kit','Dør kit','Komplet dør sæt til skuret','1','stk','599.95');
                                                                                               

UNLOCK TABLES;

LOCK TABLES standard_dimensions WRITE;
INSERT INTO standard_dimensions (bottom_lathspan,bottom_laths,top_lath_gap,avg_lath_span,roof_tile_length,roof_tile_width,roof_trapez_length,roof_trapez_width,shed_claddeing_board_dim,beam_dimension_heavy,beam_dimension_light) VALUES ('35','2','3','30','25','20','240','109','19x100','125 x 125','100 x 100');
UNLOCK TABLES;

LOCK TABLES user_proposition WRITE;
INSERT INTO user_proposition (name,address,zipcodeCity,phone,email,comments) VALUES ('Hans Hansen','Gasværksvej 21', '3490 Kvistgård', '12116644', 'hanshansen@gmail.com','');
UNLOCK TABLES;

LOCK TABLES orders WRITE;
INSERT INTO orders (user_proposition_id,order_date,status,oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch,coverage,offer_price,total_price) VALUES ('1','2020-05-05 18:26:00','Forespørgsel','450','510','420','300','rejst','Eternittag B6 - Teglrød','25','40','17743.23','24840.52');
UNLOCK TABLES;

LOCK TABLES orderline WRITE;
INSERT INTO orderline (orders_id,item_list_id,quantity,total_price) VALUES('1','32','450.00','4027.50'),
																		  ('1','32','1.00','38.50'),
                                                                          ('1','29','17280.00','1641.60'),
                                                                          ('1','31','380.00','1881.00'),
                                                                          ('1','12','1.00','37.00'),
                                                                          ('1','11','1.00','70.00'),
                                                                          ('1','35','1.00','599.95'),
                                                                          ('1','21','88.84','1998.84'),
                                                                          ('1','27','91.00','1046.50'),
                                                                          ('1','25','14.70','330.75'),
                                                                          ('1','30','10.20','254.49'),
                                                                          ('1','23','20.00','1590.00'),
                                                                          ('1','9','1.00','109.50'),
                                                                          ('1','10','1.00','169.00'),
                                                                          ('1','34','1.00','399.95');
UNLOCK TABLES;