-- MySQL test queryes for FogDB

-- ----------------------------------
-- queryes for single tables 
-- ----------------------------------
SELECT * FROM cities LIMIT 0, 1500;
SELECT * FROM users;
SELECT * FROM measurement_units;
SELECT * FROM orders;
SELECT * FROM roof;
SELECT * FROM roof_pitch;
SELECT * FROM user_proposition;
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