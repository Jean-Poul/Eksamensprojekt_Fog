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

-- ---------------------------------
-- query for item list in one order
-- ---------------------------------
SELECT o.orders_id,material_type,quantity,unit,description,total_price FROM user_proposition u
INNER JOIN orders o on u.user_proposition_id = o.user_proposition_id
INNER JOIN orderline ol on o.orders_id = ol.orders_id
INNER JOIN item_list il on ol.item_list_id = il.item_list_id
WHERE ol.orders_id = '1';

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

-- ------------------------------
-- Update quantity in orderline
-- ------------------------------
UPDATE orderline SET quantity = '12' WHERE orderline_id = '2';

-- ---------------------
-- insert into orders
-- ---------------------
INSERT INTO orders (oc_width,oc_length,ots_width,ots_length,roof_type,roof_material,pitch) VALUES ('','','','','','','');

-- #################################################################
-- Queries for Admin-page CRUD
-- #################################################################
-- CRUD for table measurement_units --
-- create:
INSERT INTO measurement_units (units,c_width,c_length,ts_width,ts_length) VALUES ('','','','','');
-- read:
SELECT * FROM measurement_units ORDER BY units ASC;
-- update:
UPDATE measurement_units SET units = '', c_width = '', c_length = '', ts_width = '', ts_length = '' WHERE measurement_units_id = '';
-- delete:
DELETE FROM measurement_units WHERE measurement_units_id = '';

-- CRUD for table item_list --
-- create:
INSERT INTO item_list (material_type,material,description,amounts,unit,price_per_unit) VALUES ('','','','','','');
-- read:
SELECT * FROM item_list ORDER BY item_list_id ASC;
-- update:
UPDATE item_list SET material_type = '', material = '', description = '', amounts = '', unit = '', price_per_unit = '' WHERE item_list_id = '';
-- delete:
DELETE FROM item_list WHERE item_list_id = '';

-- CRUD for table rafter_spacing --
-- create:
INSERT INTO rafter_spacing (category,beam_dimension,beam_spacing,rafter_length) VALUES ('','','','');
-- read:
SELECT * FROM rafter_spacing ORDER BY category ASC, beam_dimension ASC;
-- update:
UPDATE rafter_spacing SET category = '', beam_dimension = '', beam_spacing = '', rafter_length = '' WHERE rafter_spacing_id = '';
-- delete:
DELETE FROM rafter_spacing WHERE rafter_spacing_id = '';

-- CRUD for table roof --
-- create:
INSERT INTO roof (roof_type,roof_category,roof_material) VALUES ('','','');
-- read:
SELECT * FROM roof ORDER BY roof_type ASC, roof_category ASC, roof_material ASC;
-- update:
UPDATE roof SET roof_type = '', roof_category = '', roof_material = '' WHERE roof_id = '';
-- delete:
DELETE FROM roof WHERE roof_id = '';

-- CRUD for table roof_pitch --
-- create:
INSERT INTO roof_pitch (pitch,factor) VALUES ('','');
-- read:
SELECT * FROM roof_pitch ORDER BY pitch ASC;
-- update:
UPDATE roof_pitch SET pitch = '', factor = '' WHERE roof_pitch_id = '';
-- delete:
DELETE FROM roof_pitch WHERE roof_pitch_id = '';

-- CRUD for table standart_dimensions
-- create: 
-- No create for standart_dimension
-- read:
SELECT * FROM standard_dimensions;
-- update:
UPDATE standard_dimensions SET bottom_lathspan = '', bottom_laths = '', top_lath_gap = '', avg_lath_span = '', roof_tile_length = '', roof_tile_width = '', roof_trapez_length = '', roof_trapez_width = '', shed_claddeing_board_dim = '', beam_dimension_heavy = '', beam_dimension_light = '' WHERE standard_dimensions_id = ''; 
-- delete: 
-- No delete for standart_dimension