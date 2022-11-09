-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('jualopqui1','Juan','Lopez','jualopqui1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jualopqui1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('vicruidel1','Victoria','Ruiz','vicruidel1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'vicruidel1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('pabberima','Pablo','Bermudez','pabberima',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'pabberima','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('maravimaq','Maria del Mar','Avila','m4r4v1m4q',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'maravimaq','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('joscasvaz','Jose', 'Castro','joscasvaz',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'joscasvaz','owner');

INSERT INTO users(username,first_name,last_name,password,enabled) VALUES ('thokriale','Thomas','Kristan','thokriale',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'thokriale','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');



INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Pablo', 'Bermudez', '2335 Independence La.', 'Waunakee', '688006024', 'pabberima');
INSERT INTO owners VALUES (12, 'Maria del Mar', 'Avila', '2387 S. Fair Way', 'Madison', '644359235', 'maravimaq');
INSERT INTO owners VALUES (13, 'Jose', 'Castro', 'Av Kansas City', 'Sevilla', '612345789', 'joscasvaz');
INSERT INTO owners VALUES (14, 'Juan', 'Lopez', '105 N. Lake St.', 'Sevilla', '92837438', 'jualopqui1');
INSERT INTO owners VALUES (15, 'Thomas', 'Kristan', '323 Friendly St.', 'Sevilla', '12343423', 'thokriale');
INSERT INTO owners VALUES (16, 'Victoria', 'Ruiz', '323 Friendly St.', 'Sevilla', '12343423', 'vicruidel1');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Cotton', '2018-04-24', 2, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Danny', '2017-04-24', 2, 15);


INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO metrics(id, name) VALUES (1, 'gamesPlayed');
INSERT INTO metrics(id, name) VALUES (2, 'victories');
INSERT INTO metrics(id, name) VALUES (3, 'points');

INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES
(1, 'viciado', 10.0, 'Si juegas 10 partidas o mas, consideramos que ya estas enganchado.', 'https://bit.ly/certifiedGamer', 1);
INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES 
(2, 'triunfador', 20.0, 'Si ganas 20 o mas partidas es que eres todo un triunfador.', 'https://bit.ly/proGamer', 1);

INSERT INTO games(id, game_name, active,creator_username) VALUES (1, 'My first game', TRUE, 'pabberima');
INSERT INTO games(id, game_name, active,creator_username) VALUES (2, 'Sala DPanitas', FALSE,'thokriale');
INSERT INTO games(id, game_name, active, creator_username) VALUES (3, 'fansPKMN', FALSE,'joscasvaz');
INSERT INTO games(id, game_name, active, creator_username) VALUES (4, 'VivaElSevillaFC', FALSE,'maravimaq');
INSERT INTO games(id, game_name, active, creator_username) VALUES (5, 'Betis KK', TRUE,'jualopqui1');

INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (1, 1, 'jualopqui1', 1808.63);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (2, 2, 'pabberima', 1676.09);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (3, 3, 'jualopqui1', 1798.58);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (4, 4, 'joscasvaz', 1738.24);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (5, 5, 'thokriale', 1420.69);

INSERT INTO players(id, user, game_id) VALUES (1, 'pabberima', 1);
INSERT INTO players(id, user, game_id) VALUES (2, 'thokriale', 2);
INSERT INTO players(id, user, game_id) VALUES (3, 'joscasvaz', 3);
INSERT INTO players(id, user, game_id) VALUES (4, 'maravimaq', 4);
INSERT INTO players(id, user, game_id) VALUES (5, 'vicruidel1', 4);
INSERT INTO players(id, user, game_id) VALUES (6, 'jualopqui1', 5);


INSERT INTO messages (id, game_id, body, date, player) VALUES (1, 1, 'mensaje', '20120618 10:34:09', 1);
INSERT INTO messages (id, game_id, body, date, player) VALUES (2, 1, 'mensaje2', '20210918 18:34:09', 2);
INSERT INTO messages (id, game_id, body, date, player) VALUES (3, 1, 'mensaje3', '20210918 18:34:09', 3);
INSERT INTO messages (id, game_id, body, date, player) VALUES (4, 2, 'mensaje4', '20210918 18:34:09', 5);
INSERT INTO messages (id, game_id, body, date, player) VALUES (5, 2, 'mensaje5', '20210918 18:34:09', 4);
INSERT INTO messages (id, game_id, body, date, player) VALUES (6, 2, 'mensaje6', '20210918 18:34:09', 2);
INSERT INTO messages (id, game_id, body, date, player) VALUES (7, 3, 'mensaje7', '20210918 18:34:09', 2);
INSERT INTO messages (id, game_id, body, date, player) VALUES (8, 4, 'mensaje8', '20210918 18:34:09', 2);
INSERT INTO messages (id, game_id, body, date, player) VALUES (9, 4, 'mensaje9', '20210918 18:34:09', 3);
INSERT INTO messages (id, game_id, body, date, player) VALUES (10,4, 'mensaje', '20210918 18:34:09', 1);

INSERT INTO card_types(id, name, image) VALUES (1, 'coin', '/resources/images/cards/doblon.png');
INSERT INTO card_types(id, name, image) VALUES (2, 'coup', '/resources/images/cards/copa.png');
INSERT INTO card_types(id, name, image) VALUES (3, 'ruby', '/resources/images/cards/rubi.png');
INSERT INTO card_types(id, name, image) VALUES (4, 'diamond', '/resources/images/cards/diamante.png');
INSERT INTO card_types(id, name, image) VALUES (5, 'necklace', '/resources/images/cards/collar.png');
INSERT INTO card_types(id, name, image) VALUES (6, 'crown', '/resources/images/cards/corona.png');
INSERT INTO card_types(id, name, image) VALUES (7, 'bottle', '/resources/images/cards/botella.png');
INSERT INTO card_types(id, name, image) VALUES (8, 'rum', '/resources/images/cards/ron.png');
INSERT INTO card_types(id, name, image) VALUES (9, 'pistol', '/resources/images/cards/pistola.png');
INSERT INTO card_types(id, name, image) VALUES (10, 'sword', '/resources/images/cards/espada.png');
INSERT INTO card_types(id, name, image) VALUES (11, 'emptyIsland', '/resources/images/cards/isla.png');
INSERT INTO card_types(id, name, image) VALUES (12, 'upsideDown', '/resources/images/cards/reverso.png');

INSERT INTO cards(id, card_type_id, game_id, player_id) VALUES (1, 1, 1, 1);
INSERT INTO cards(id, card_type_id, game_id, player_id) VALUES (2, 2, 1, 2);
INSERT INTO cards(id, card_type_id, game_id, player_id) VALUES (3, 3, 1, 3);
INSERT INTO cards(id, card_type_id, game_id) VALUES (4, 4, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (5, 5, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (6, 6, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (7, 7, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (8, 8, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (9, 9, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (10, 10, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (11, 11, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (12, 12, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (13, 1, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (14, 1, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (15, 2, 1);

INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (4, 100000, 349759.11, 678, 678, 1);
INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (1, 96, 18708.63, 2, 1, 2);
INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (2, 300, 62734.03, 13, 5, 3);
INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (3, 145, 27712.28, 4, 2, 4);
INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (5, 222, 42704.52, 9, 4, 5);
INSERT INTO player_statistics(id, total_points, time_played, games_played, games_won, player_id) VALUES (6, 42, 21.28, 1, 0, 6);

