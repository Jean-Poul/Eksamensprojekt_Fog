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
INSERT INTO roof (roof_type,roof_material) VALUES ('fladt','Plasttrapezplader'),
												  ('fladt','Plastmo Ecolite blåtonet'),
												  ('rejst','Betontagsten - Rød'),
                                                  ('rejst','Betontagsten - Teglrød'),
                                                  ('rejst','Betontagsten - Brun'),
                                                  ('rejst','Betontagsten – Sort'),
                                                  ('rejst','Betontagsten – B&C protector: Sort'),
                                                  ('rejst','Betontagsten – B&C protector: Skifer'),
                                                  ('rejst','Betontagsten – B&C protector: Koralrød'),
                                                  ('rejst','Betontagsten – B&C protector DANFLOCK: Sort'),
                                                  ('rejst','Betontagsten – B&C protector DANFLOCK: Skifer'),
                                                  ('rejst','Betontagsten – B&C protector DANFLOCK: Koralrød'),
                                                  ('rejst','Eternittag B6 - Grå'),
                                                  ('rejst','Eternittag B6 - Sort'),
                                                  ('rejst','Eternittag B6 - Mokka (brun)'),
                                                  ('rejst','Eternittag B6 – Rødbrun'),
                                                  ('rejst','Eternittag B6 - Teglrød'),
                                                  ('rejst','Eternittag B7 - Grå'),
                                                  ('rejst','Eternittag B7 - Sort'),
                                                  ('rejst','Eternittag B7 - Mokka (brun)'),
                                                  ('rejst','Eternittag B7 - Rødbrun'),
                                                  ('rejst','Eternittag B7 - Teglrød'),
                                                  ('rejst','Eternittag B7 - Rødflammet'),
                                                  ('rejst','Gammel Dansk – Rød'),
                                                  ('rejst','Gammel Dansk – Mocca'),
                                                  ('rejst','Gammel Dansk – Blådæmpet'),
                                                  ('rejst','Gammel Dansk – Sortglaseret'),
                                                  ('rejst','Gammel Dansk – Engoberet sort'),
                                                  ('rejst','Gammel Dansk – Engoberet gul'),
                                                  ('rejst','Nortegl – Engoberet sort'),
                                                  ('rejst','Nortegl – Naturrød'),
                                                  ('rejst','Nortegl – Engoberet gul'),
                                                  ('rejst','Nortegl – Glaseret sort'),
                                                  ('rejst','Nortegl – Engoberet flammet rød'),
                                                  ('rejst','Hollander – Naturrød'),
                                                  ('rejst','Hollander – Engoberet sortbrun'),
                                                  ('rejst','Hollander – engoberet glaseret sort'),
                                                  ('rejst','Hollander – engoberet antracit'),
                                                  ('rejst','Hollander – engoberet brun'),
                                                  ('rejst','Hollander – engoberet glaseret mat sort'),
                                                  ('rejst','KDN VH – Naturrød'),
                                                  ('rejst','KDN VH – Glaserede: sort'),
                                                  ('rejst','KDN VH – Glaserede: sølvsort'),
                                                  ('rejst','KDN VH – Glaserede: matsort'),
                                                  ('rejst','KDN VH – Glaserede: lys brun'),
                                                  ('rejst','KDN VH – Glaserede: mørk brun'),
                                                  ('rejst','KDN VH – Glaserede: vinrød'),
                                                  ('rejst','KDN VH – Glaserede: mørk rød'),
                                                  ('rejst','Turmalin – Naturrød'),
                                                  ('rejst','Turmalin – Engoberet rød'),
                                                  ('rejst','Turmalin – Engoberet sort'),
                                                  ('rejst','Turmalin – Engoberet lys grå'),
                                                  ('rejst','Turmalin – Ædelengoberet kastanje'),
                                                  ('rejst','Turmalin – mørk grå'),
                                                  ('rejst','Turmalin – sort'),
                                                  ('rejst','Dantegl NOVA – Engoberet naturrød'),
                                                  ('rejst','Dantegl NOVA – Engoberet sort'),
                                                  ('rejst','Dantegl NOVA – Ædelengoberet sort');
UNLOCK TABLES;

LOCK TABLES roof_pitch WRITE;
INSERT INTO roof_pitch (pitch) VALUES ('15'),
									  ('20'),
                                      ('25'),
                                      ('30'),
                                      ('35'),
                                      ('40'),
                                      ('45');
UNLOCK TABLES;