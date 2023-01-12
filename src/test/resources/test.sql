-- SET FOREIGN_KEY_CHECKS=0;
INSERT INTO users( username,password,enabled) VALUES ('testplayer1','Pa55w.rd',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'testplayer1','owner');
INSERT INTO users(username,password,enabled) VALUES ('testplayer2','Pa55w.rd',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'testplayer2','owner');
INSERT INTO users(username,password,enabled) VALUES ('testplayer3','Pa55w.rd',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'testplayer3','owner');

INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay)
    VALUES (1, 'Test Game 1', FALSE, 'testplayer1', 0, FALSE, 0, '20220812 10:34:09', null, 0);
INSERT INTO games(id, game_name, active, creator_username, dice_roll, has_rolled_dice, player_turn, start, end, num_cards_to_pay)
    VALUES (2, 'Test Game 2', FALSE, 'testplayer2', 0, FALSE, 0, '20220918 10:34:09', null, 0);

INSERT INTO players(id, user) VALUES (1, 'testplayer1');
INSERT INTO players(id, user) VALUES (2, 'testplayer2');
INSERT INTO players(id, user) VALUES (3, 'testplayer3');

INSERT INTO game_statistics(id, game_id, game_creator_name, duration, year, month) VALUES (1, 1, 'testplayer1', 1000.00, 2022, 08);
INSERT INTO game_statistics(id, game_id, game_creator_name, duration, year, month) VALUES (2, 2, 'testplayer2', 1400.00, 2022, 09);

INSERT INTO player_points_maps(game_statistics_id, points, player) VALUES (1, 140, 1);
INSERT INTO player_points_maps(game_statistics_id, points, player) VALUES (1, 100, 2);
INSERT INTO player_points_maps(game_statistics_id, points, player) VALUES (2, 90, 1);
INSERT INTO player_points_maps(game_statistics_id, points, player) VALUES (2, 150, 2);
INSERT INTO player_points_maps(game_statistics_id, points, player) VALUES (2, 50, 3);

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

