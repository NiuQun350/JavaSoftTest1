package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Launcher launcher;

    @BeforeEach
    public void setUp() {
        launcher = new Launcher().withMapFile("/border.txt");
        launcher.launch();
    }

    @AfterEach
    public void endGame() {
        launcher.dispose();
    }
    /**
     * test game starts normally
     */
    @Test
    public void testStart() {
        Game game = launcher.getGame();
        assertThat(game.isInProgress()).isEqualTo(false);
        game.start();
        assertThat(game.isInProgress()).isEqualTo(true);
    }
    /**
     * test game from start to stop
     */
    @Test
    public void testStart2Stop() {
        Game game = launcher.getGame();
        game.start();
        assertThat(game.isInProgress()).isEqualTo(true);
        game.stop();
        assertThat(game.isInProgress()).isEqualTo(false);
    }
    /**
     * test game from stop to start
     */
    @Test
    public void testStop2Start() {
        Game game = launcher.getGame();
        game.start();
        game.stop();
        assertThat(game.isInProgress()).isEqualTo(false);
        game.start();
        assertThat(game.isInProgress()).isEqualTo(true);
    }
    /**
     * test game winning status
     */
    @Test
    public void testWin() {
        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);
        game.start();
        // Player move one space to the right and eats the last pellet
        game.move(player, Direction.EAST);
        // Judge to win
        // Judge the end of the game and player is alive
        assertThat(game.isInProgress()).isEqualTo(false);
        assertThat(player.isAlive()).isEqualTo(true);
    }

    /**
     * test game failure status
     */
    @Test
    public void testLoss() throws InterruptedException {
        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);
        game.start();
        // Sleep is for the monster to move and eat the player
        Thread.sleep(8000L);
        // Judge to loss
        // Judge that the game is over and the palyer is dead
        assertThat(game.isInProgress()).isEqualTo(false);
        assertThat(player.isAlive()).isEqualTo(false);
    }
}
