-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with password 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('jualopqui1','Juan','Lopez','jualopqui1',TRUE, 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/384.png');
INSERT INTO authorities(id,username,authority) VALUES (4,'jualopqui1','owner');

INSERT INTO users(username,password,enabled, profile_image) VALUES ('vicruidel1','vicruidel1',TRUE, 'https://i.pinimg.com/originals/dc/ab/b7/dcabb7fbb2f763d680d20a3d228cc6f9.jpg');
INSERT INTO authorities(id,username,authority) VALUES (5,'vicruidel1','owner');

INSERT INTO users(username,password,enabled, profile_image) VALUES ('pabberima','pabberima',TRUE, 'https://cdn11.bigcommerce.com/s-n2jcqcuzo0/products/1003/images/1823/confederate-flag-sticker-largestickerthe-dixie-shop-14558404__36614.1595394123.386.513.png?');
INSERT INTO authorities(id,username,authority) VALUES (6,'pabberima','owner');

INSERT INTO users(username,password,enabled) VALUES ('maravimaq','m4r4v1m4q',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'maravimaq','admin');

INSERT INTO users(username,password,enabled, profile_image) VALUES ('joscasvaz','joscasvaz',TRUE, 'https://www.alacesta.com/1682-thickbox_default/escudo-betis.jpg');
INSERT INTO authorities(id,username,authority) VALUES (8,'joscasvaz','owner');

INSERT INTO users(username,password,enabled, profile_image) VALUES ('thokriale','thokriale',TRUE, 'https://thumbs.dreamstime.com/b/foto-de-perfil-un-empresario-montado-en-una-tabla-surf-aislado-sobre-fondo-blanco-216887924.jpg');
INSERT INTO authorities(id,username,authority) VALUES (9,'thokriale','owner');

INSERT INTO metrics(id, name) VALUES (1, 'gamesPlayed');
INSERT INTO metrics(id, name) VALUES (2, 'victories');
INSERT INTO metrics(id, name) VALUES (3, 'points');

INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES
(1, 'viciado', 10.0, 'Si juegas 10 partidas o mas, consideramos que ya estas enganchado.', 'https://bit.ly/certifiedGamer', 1);
INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES 
(2, 'triunfador', 20.0, 'Si ganas 20 o mas partidas es que eres todo un triunfador.', 'https://bit.ly/proGamer', 1);

INSERT INTO games(id, game_name, active, creator_username) VALUES (1, 'My first game', TRUE, 'pabberima');
INSERT INTO games(id, game_name, active, creator_username) VALUES (2, 'Sala DPanitas', FALSE,'thokriale');
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
INSERT INTO players(id, user, game_id) VALUES (3, 'joscasvaz', 5);
INSERT INTO players(id, user, game_id) VALUES (4, 'maravimaq', 1);
INSERT INTO players(id, user, game_id) VALUES (5, 'vicruidel1', 5);
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

INSERT INTO player_achievements(player_id, achievement_id) VALUES (1, 1);
INSERT INTO player_achievements(player_id, achievement_id) VALUES (1, 2);
INSERT INTO player_achievements(player_id, achievement_id) VALUES (2, 1);