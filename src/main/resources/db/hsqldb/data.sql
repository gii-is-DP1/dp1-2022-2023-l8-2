-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled, profile_image) VALUES ('admin1','4dm1n',TRUE, 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with password 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('jualopqui1','Juan','Lopez','jualopqui1',TRUE, 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/384.png');
INSERT INTO authorities(id,username,authority) VALUES (4,'jualopqui1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('vicruidel1','Victoria','del Carmen','vicruidel1',TRUE, 'https://i.pinimg.com/originals/dc/ab/b7/dcabb7fbb2f763d680d20a3d228cc6f9.jpg');
INSERT INTO authorities(id,username,authority) VALUES (5,'vicruidel1','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('pabberima','Pablo','Bermudez','pabberima',TRUE, 'https://ih1.redbubble.net/image.1834971469.6913/flat,750x1000,075,f.jpg');
INSERT INTO authorities(id,username,authority) VALUES (6,'pabberima','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('maravimaq','Maria del Mar','Avila','m4r4v1m4q', TRUE, 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png');
INSERT INTO authorities(id,username,authority) VALUES (7,'maravimaq','admin');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('joscasvaz','Jose Ignacio','Castro Vazquez','joscasvaz',TRUE, 'https://www.alacesta.com/1682-thickbox_default/escudo-betis.jpg');
INSERT INTO authorities(id,username,authority) VALUES (8,'joscasvaz','owner');

INSERT INTO users(username,first_name,last_name,password,enabled, profile_image) VALUES ('thokriale','Thomas','Kristan','thokriale',TRUE, 'https://thumbs.dreamstime.com/b/foto-de-perfil-un-empresario-montado-en-una-tabla-surf-aislado-sobre-fondo-blanco-216887924.jpg');
INSERT INTO authorities(id,username,authority) VALUES (9,'thokriale','owner');

INSERT INTO metrics(id, name) VALUES (1, 'gamesPlayed');
INSERT INTO metrics(id, name) VALUES (2, 'gamesWon');
INSERT INTO metrics(id, name) VALUES (3, 'points');

INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES
(1, 'viciado', 10.0, 'Si juegas 10 partidas o mas, consideramos que ya estas enganchado.', 'https://bit.ly/certifiedGamer', 1);
INSERT INTO achievements(id, name, threshold, description, image, metric_id) VALUES
(2, 'triunfador', 20.0, 'Si ganas 20 o mas partidas es que eres todo un triunfador.', 'https://bit.ly/proGamer', 1);

INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (1, 'My first game', TRUE, 'pabberima', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (2, 'Sala DPanitas', TRUE,'thokriale', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (3, 'fansPKMN', TRUE,'joscasvaz', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (4, 'VivaElSevillaFC', TRUE,'maravimaq', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (5, 'Betis KK', TRUE,'jualopqui1', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (6, 'Game 6', FALSE, 'pabberima', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (7, 'Game 7', FALSE,'thokriale', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (8, 'Game 8', FALSE,'joscasvaz', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (9, 'Game 9', FALSE,'maravimaq', 0, FALSE, 0, '20221218 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay) VALUES (10, 'Game 10', FALSE,'jualopqui1', 0, FALSE, 0, '20221218 10:34:09', null, 0);

INSERT INTO game_statistics(game_id, game_creator_name, duration, year, month) VALUES (6, 'pabberima', 1808.63, 2021, 07);
INSERT INTO game_statistics(game_id, game_creator_name, duration, year, month) VALUES (7, 'thokriale', 1676.09, 2021, 08);
INSERT INTO game_statistics(game_id, game_creator_name, duration, year, month) VALUES (8, 'joscasvaz', 1798.58, 2021, 09);
INSERT INTO game_statistics(game_id, game_creator_name, duration, year, month) VALUES (9, 'maravimaq', 1738.24, 2021, 09);
INSERT INTO game_statistics(game_id, game_creator_name, duration, year, month) VALUES (10, 'jualopqui1', 1420.69, 2021, 09);

INSERT INTO players(id, user, game_id) VALUES (1, 'pabberima', 1);
INSERT INTO players(id, user, game_id) VALUES (2, 'thokriale', null);
INSERT INTO players(id, user, game_id) VALUES (3, 'joscasvaz', 5);
INSERT INTO players(id, user, game_id) VALUES (4, 'maravimaq', 1);
INSERT INTO players(id, user, game_id) VALUES (5, 'vicruidel1', 5);
INSERT INTO players(id, user, game_id) VALUES (6, 'jualopqui1', 5);
INSERT INTO players(id, user, game_id) VALUES (7, 'admin1', 5);

INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (1, 1, 140, 1);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (2, 1, 95, 2);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (3, 2, 130, 2);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (4, 2, 125, 4);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (5, 2, 100, 6);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (6, 3, 50, 3);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (7, 3, 85, 6);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (8, 4, 105, 4);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (9, 4, 100, 1);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (10, 5, 130, 6);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (11, 5, 120, 3);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (12, 5, 65, 2);
INSERT INTO player_points_maps(id, game_statistics_id, points, player) VALUES (13, 5, 80, 1);

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
INSERT INTO cards(id, card_type_id, game_id) VALUES (15, 1, 5);

INSERT INTO player_achievements(player_id, achievement_id) VALUES (1, 1);
INSERT INTO player_achievements(player_id, achievement_id) VALUES (1, 2);
INSERT INTO player_achievements(player_id, achievement_id) VALUES (2, 1);

INSERT INTO friends(user_id, friend_id) VALUES ('jualopqui1', 'joscasvaz');
INSERT INTO friends(user_id, friend_id) VALUES ('jualopqui1', 'thokriale');
INSERT INTO friends(user_id, friend_id) VALUES ('joscasvaz', 'thokriale');
INSERT INTO friends(user_id, friend_id) VALUES ('maravimaq', 'pabberima');
INSERT INTO friends(user_id, friend_id) VALUES ('joscasvaz', 'pabberima');



INSERT INTO friends(user_id, friend_id) VALUES ('joscasvaz', 'jualopqui1');
INSERT INTO friends(user_id, friend_id) VALUES ('thokriale', 'jualopqui1');
INSERT INTO friends(user_id, friend_id) VALUES ('thokriale', 'joscasvaz');
INSERT INTO friends(user_id, friend_id) VALUES ('pabberima', 'maravimaq');
INSERT INTO friends(user_id, friend_id) VALUES ('pabberima', 'joscasvaz');

SET FOREIGN_KEY_CHECKS=0;
