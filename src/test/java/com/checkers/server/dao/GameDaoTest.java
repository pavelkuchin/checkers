package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.exceptions.ApplicationException;
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
    public void newGame() throws ApplicationException {
        //Creating new game from Game bean
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setBlack(userDao.getUser(1L));
        game.setWhite(userDao.getUser(2L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Assert.assertNotNull("Game's object doesn't persist.", game.getGauid());
    }

    @Test
    public void joinGame() throws ApplicationException {
        //Creating new game from Game bean
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setWhite(userDao.getUser(2L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        gameDao.joinGame(game.getGauid(), 1L);

        game = gameDao.getGame(game.getGauid());

        Assert.assertNotNull("You can't join to game", game.getBlack());
    }

    @Test
    public void closeGame() throws ApplicationException {
        //Creating new game from Game bean
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("game");
        game.setType("offline");
        game.setBoard("8x8");

        game.setWhite(userDao.getUser(2L));
        game.setBlack(userDao.getUser(1L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        gameDao.closeGame(game.getGauid(), 1L);

        game = gameDao.getGame(game.getGauid());

        Assert.assertEquals("Game doesn't close", "close", game.getState());
    }

    @Test(expected=ApplicationException.class)
    public void delGame() throws ApplicationException {
        //Creating new game from Game bean
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setWhite(userDao.getUser(1L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        gameDao.delGame(game.getGauid());

        gameDao.getGame(game.getGauid());
    }

    @Test
    public void newGameLongAsObject(){
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setBlackUuid(1L);
        game.setWhiteUuid(2L);

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Assert.assertNotNull("GameLongAsObject's object doesn't persist.", game.getGauid());
        Assert.assertNotNull("Game's object is null in GameLongAsObject method.", game);
        Assert.assertNotNull("Game's object doesn't persist in GameLongAsObject method.", game.getGauid());
    }

    @Test
    public void modGame() throws ApplicationException {
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setBlackUuid(1L);
        game.setWhiteUuid(2L);

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Game chGame = gameDao.getGame(game.getGauid());

        chGame.setDescription("Changed description");
        chGame.setName("Changed name");

        gameDao.modGame(chGame);

        game = gameDao.getGame(chGame.getGauid());

        Assert.assertEquals("Description has not been changed", "Changed description", game.getDescription());
        Assert.assertEquals("Name has not been changed", "Changed name", game.getName());
    }

    @Test
    public void getGame() throws ApplicationException {
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name");
        game.setDescription("Game description");

        game.setState("open");
        game.setType("offline");
        game.setBoard("8x8");

        game.setBlackUuid(1L);
        game.setWhiteUuid(2L);

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Game obtainedGame = gameDao.getGame(game.getGauid());

        Assert.assertNotNull("Obtained game is null", obtainedGame);
        Assert.assertEquals("Obtained game gauid is incorrect", game.getGauid(), obtainedGame.getGauid());
    }

    @Test
    public void getUserGames() throws ApplicationException {
        //Creating some new user
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy@gmail.com");

        user.setEnabled(true);

        user.setRole("ROLE_USER");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        //Creating three games for this user in the loop
        for(int i = 0; i < 3; i++){
            Game game = new Game();

            game.setGauid(null);

            game.setName("Game name");
            game.setDescription("Game description");

            game.setState("open");
            game.setType("offline");
            game.setBoard("8x8");

            game.setBlackUuid(1L);
            game.setWhiteUuid(user.getUuid());

            game.setModified(new Date());
            game.setCreated(new Date());
            game.setLastStep(new Date());

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

    @Test
    public void getGamesFiltered(){
        //Creating three games for this user in the loop
        for(int i = 0; i < 3; i++){
            Game game = new Game();

            game.setGauid(null);

            game.setName("Game name"+i);
            game.setDescription("Game description"+i);

            game.setState("open");
            game.setType("offline");
            game.setBoard("8x8");

            game.setBlackUuid(null);
            game.setWhiteUuid(2L);

            game.setModified(new Date());
            game.setCreated(new Date());
            game.setLastStep(new Date());

            gameDao.newGame(game);
        }

        List<Game> games = gameDao.getGamesFiltered("state", "open");

        Assert.assertNotNull("Filtered Games list is null.", games);
        Assert.assertNotEquals("Not all filtered games are returned (or more than you need).", 3, games.size());
    }

}
