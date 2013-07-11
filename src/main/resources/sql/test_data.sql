    INSERT INTO User(uuid, created, email, enabled, firstName, lastLogin, lastName, login, modified, password, role) VALUES (null, NOW(), 'admin@checkers.in.ua', true, 'Admin', NOW(), 'Admin', 'admin', NOW(), 'admin', 'ROLE_ADMIN' );
    INSERT INTO User(uuid, created, email, enabled, firstName, lastLogin, lastName, login, modified, password, role) VALUES (null, NOW(), 'user@checkers.in.ua', true, 'User', NOW(), 'User', 'user', NOW(), 'user', 'ROLE_USER' );

    INSERT INTO Game(gauid, board, created, description, lastStep, modified, name, state, type, black_uuid, white_uuid, blackUuid, whiteUuid) VALUES (null, '10x10', NOW(), 'Ogogo', NOW(), NOW(), 'Just a game', 'open', 'long', null, 1, null, 1);
