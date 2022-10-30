-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with password 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,password,enabled) VALUES ('jualopqui1','jualopqui1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jualopqui1','owner');

INSERT INTO users(username,password,enabled) VALUES ('vicruidel1','vicruidel1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'vicruidel1','owner');

INSERT INTO users(username,password,enabled) VALUES ('pabberima','pabberima',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'pabberima','owner');

INSERT INTO users(username,password,enabled) VALUES ('maravimaq','m4r4v1m4q',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'maravimaq','owner');

INSERT INTO users(username,password,enabled) VALUES ('joscasvaz','joscasvaz',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'joscasvaz','owner');

INSERT INTO users(username,password,enabled) VALUES ('thokriale','thokriale',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'thokriale','owner');

INSERT INTO achievements(id, name, threshold, description, image) VALUES
(1, 'viciado', 10.0, 'Si juegas 10 partidas o mas, consideramos que ya estas enganchado.', 'https://bit.ly/certifiedGamer');
INSERT INTO achievements(id, name, threshold, description, image) VALUES 
(2, 'triunfador', 20.0, 'Si ganas 20 o mas partidas es que eres todo un triunfador.', 'https://bit.ly/proGamer');

INSERT INTO games(id, game_name) VALUES (1, 'My first game');
INSERT INTO games(id, game_name) VALUES (2, 'Sala DPanitas');
INSERT INTO games(id, game_name) VALUES (3, 'fansPKMN');
INSERT INTO games(id, game_name) VALUES (4, 'VivaElSevillaFC');
INSERT INTO games(id, game_name) VALUES (5, 'Betis KK');

INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (1, 1, 'jualopqui1', 1808.63);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (2, 2, 'pabberima', 1676.09);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (3, 3, 'jualopqui1', 1798.58);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (4, 4, 'joscasvaz', 1738.24);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration) VALUES (5, 5, 'thokriale', 1420.69);

INSERT INTO messages (id, game_id, body, date) VALUES (1, 1, 'mensaje', '20120618 10:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (2, 1, 'mensaje2', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (3, 1, 'mensaje3', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (4, 2, 'mensaje4', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (5, 2, 'mensaje5', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (6, 2, 'mensaje6', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (7, 3, 'mensaje7', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (8, 4, 'mensaje8', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (9, 4, 'mensaje9', '20210918 18:34:09');
INSERT INTO messages (id, game_id, body, date) VALUES (10,4, 'mensaje', '20210918 18:34:09');

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

INSERT INTO cards(id, card_type_id, game_id) VALUES (1, 1, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (2, 2, 1);
INSERT INTO cards(id, card_type_id, game_id) VALUES (3, 3, 1);
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

