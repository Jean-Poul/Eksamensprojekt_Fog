-- Required SQL for FogDB
USE fogdb;

-- -----------------------------------------------------------------------------------------
-- Insert static records to tables in `users`, `measurement_units`, `roof` and `roof_pitch`
-- -----------------------------------------------------------------------------------------
LOCK TABLES users WRITE;
INSERT INTO users (email,password,role) VALUES ('admin@fog.dk','1234','admin');
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