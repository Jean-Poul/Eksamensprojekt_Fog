-- MySQL test queryes for FogDB

-- ----------------------------------
-- queryes for single tables 
-- ----------------------------------
SELECT * FROM cities LIMIT 0, 1500;
SELECT * FROM users;
SELECT * FROM measurement_units;
SELECT * FROM orders;
SELECT * FROM orderline;
SELECT * FROM roof;
SELECT * FROM roof_pitch;
SELECT * FROM user_proposition;
SELECT * FROM rafter_spacing;
SELECT * FROM item_list;
SELECT * FROM standard_dimensions;
UNLOCK TABLES;
-- ----------------------------------
-- queryes for carport width
-- ----------------------------------
SELECT units FROM measurement_units WHERE c_width = '1';

-- ----------------------------------
-- queryes for carport length
-- ----------------------------------
SELECT units FROM measurement_units WHERE c_length = '1';

-- ----------------------------------
-- queryes for tool shed width
-- ----------------------------------
SELECT units FROM measurement_units WHERE ts_width = '1';

-- ----------------------------------
-- queryes for tool shed length
-- ----------------------------------
SELECT units FROM measurement_units WHERE ts_length = '1';

-- ----------------------------------
-- queryes for flat roof
-- ----------------------------------
SELECT roof_material FROM roof WHERE roof_type = 'fladt';

-- ----------------------------------
-- queryes for erected roof
-- ----------------------------------
SELECT roof_material FROM roof WHERE roof_type = 'rejst';

-- ----------------------------------
-- queryes for rafter spacing light
-- ----------------------------------
SELECT * FROM rafter_spacing WHERE category = 'let';

-- ----------------------------------
-- queryes for rafter spacing heavy
-- ----------------------------------
SELECT * FROM rafter_spacing WHERE category = 'tung';

-- ----------------------------------
-- queryes for roof pitch 'pitch'
-- ----------------------------------
SELECT pitch FROM roof_pitch; 

-- ----------------------------------
-- queryes for roof pitch 'factor'
-- ----------------------------------
SELECT factor FROM roof_pitch;

-- ---------------------------------------------
-- queryes for roof pitch 'pitch' and 'factor'
-- ---------------------------------------------
SELECT pitch,factor FROM roof_pitch;

-- ---------------------------------------
-- queryes for zipcode with leading zeros
-- ---------------------------------------
SELECT LPAD(zipcode,4,'0') as zipcode, cityname FROM cities LIMIT 0, 1500;

-- ----------------------------------------------------------------------------
-- queryes for beam dimension and beam spacing using category and rafter lengt
-- ----------------------------------------------------------------------------
-- first ex. is using 'let' roof and a rafter length of 3.4m
SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'let' and rafter_length >= '2.5'
ORDER BY rafter_length ASC LIMIT 1;
-- second ex. is using 'tung' roof and a rafter length of 2.98m
SELECT beam_dimension,beam_spacing FROM rafter_spacing WHERE category = 'tung' and rafter_length >= '2.98'
ORDER BY rafter_length ASC LIMIT 1;

-- ------------------------------------
-- query for item list material_type
-- ------------------------------------ 
SELECT material_type FROM item_list GROUP BY material_type;

-- ---------------------------------
-- query for all user propositions
-- ---------------------------------
SELECT * FROM user_proposition u
INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id;

-- -----------------------------------
-- query for single user proposition
-- -----------------------------------
SELECT * FROM user_proposition u
INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id
WHERE u.user_proposition_id = '1';

-- --------------------------
-- Update status in orders
-- --------------------------
UPDATE orders SET status = 'Tilbud' WHERE orders_id = '1';

-- --------------------------
-- Update user_proposition
-- --------------------------
UPDATE user_proposition SET name = '', address = '', zipcodeCity = '', phone = '', email = '', comments = '' WHERE user_proposition_id = '1'; 

-- --------------------------
-- Update orders
-- --------------------------
UPDATE orders SET oc_width = '', oc_length = '', ots_width = '', ots_length = '', roof_type = '', roof_material = '', pitch = '' WHERE orders_id = '1';

INSERT INTO orders (oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch) VALUES ('','','','','','','');