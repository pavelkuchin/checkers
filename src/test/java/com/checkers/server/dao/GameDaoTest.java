package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.beans.proxy.GameProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Tests for {@link GameDaoImpl}
 *
 * @author Pavel Kuchin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-context.xml")
public class GameDaoTest {
    @Autowired
    GameDao gameDao;

    @Autowired
    UserDao userDao;

    @Test
    public void newGame(){
        //Creating new game from Game bean
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("long");
        game.setBoard("8x8");

        game.setBlack(userDao.getUser(1L));
        game.setWhite(userDao.getUser(2L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastLogin(new Date());

        gameDao.newGame(game);

        Assert.assertNotNull("Game's object doesn't persist.", game.getGauid());
    }

    @Test
    public void newGameProxy(){
        //Creating new game from GameProxy bean
        GameProxy game = new GameProxy();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("long");
        game.setBoard("8x8");

        game.setBlack(1L);
        game.setWhite(2L);

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastLogin(new Date());

        Game justGame = gameDao.newGame(game);

        Assert.assertNotNull("GameProxy's object doesn't persist.", justGame.getGauid());
        Assert.assertNotNull("Game's object is null in GameProxy method.", justGame);
        Assert.assertNotNull("Game's object doesn't persist in GameProxy method.", justGame.getGauid());
    }

    @Test
    public void getGame(){
        //Creating new game from GameProxy bean
        GameProxy game = new GameProxy();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("long");
        game.setBoard("8x8");

        game.setBlack(1L);
        game.setWhite(2L);

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastLogin(new Date());

        Game justGame = gameDao.newGame(game);

        Game obtainedGame = gameDao.getGame(justGame.getGauid());

        Assert.assertNotNull("Obtained game is null", obtainedGame);
        Assert.assertEquals("Obtained game gauid is incorrect", justGame.getGauid(), obtainedGame.getGauid());
    }

    @Test
    public void getUserGames(){
        //Creating some new user
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy@gmail.com");

        user.setEnabled(true);

        user.setRole("USER_ROLE");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        //Creating three games for this user in the loop
        for(int i = 0; i < 3; i++){
            GameProxy game = new GameProxy();

            game.setGauid(null);

            game.setName("Game name");
            game.setDescription("Game description");

            game.setState("open");
            game.setType("long");
            game.setBoard("8x8");

            game.setBlack(1L);
            game.setWhite(user.getUuid());

            game.setModified(new Date());
            game.setCreated(new Date());
            game.setLastLogin(new Date());

            gameDao.newGame(game);
        }

        List<Game> userGame = gameDao.getUserGames(user.getUuid());

        Assert.assertNotNull("User games list are null", userGame);
        Assert.assertEquals("Not all user games are returned (or more than you need).", 3, userGame.size());
    }

    @Test
    public void getGames(){
        List<Game> games = gameDao.getGames();

        Assert.assertNotNull("Games list is null.", games);
        Assert.assertNotEquals("Not all games are returned (or more than you need).", 0, games.size());
    }
}
