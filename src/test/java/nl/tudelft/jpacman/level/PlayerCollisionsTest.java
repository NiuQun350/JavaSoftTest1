package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PlayerCollisionsTest {

    private static PlayerCollisions playerCollisions;
    private static PointCalculator pointCalculator;

    @BeforeAll
    static void beforeAll() {
        pointCalculator = mock(PointCalculator.class);
        playerCollisions = new PlayerCollisions(pointCalculator);
    }

    @Test
    @DisplayName("玩家死亡，游戏结束")
    void player_VS_ghost() {
        Ghost ghost = mock(Ghost.class);
        Player player = mock(Player.class);
        playerCollisions.collide(player,ghost);
        verify(pointCalculator).collidedWithAGhost(player,ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    @Test
    @DisplayName("玩家死亡，游戏结束")
    void ghost_VS_player() {
        Ghost ghost = mock(Ghost.class);
        Player player = mock(Player.class);
        playerCollisions.collide(ghost,player);
        verify(pointCalculator).collidedWithAGhost(player,ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    @Test
    @DisplayName("糖豆被吃，点数加10")
    @SuppressWarnings({"magicnumber", "methodlength", "PMD.JUnitTestContainsTooManyAsserts"})
    void pointIncrease() {
        Pellet pellet = mock(Pellet.class);
        Player player = mock(Player.class);
        playerCollisions.collide(player,pellet);
        verify(pointCalculator).consumedAPellet(player,pellet);
        verify(pellet).leaveSquare();

    }

    @Test
    @DisplayName("什么都没发生")
    void nothingHappened() {
        Pellet pellet = mock(Pellet.class);
        Ghost ghost1 = mock(Ghost.class);
        Ghost ghost2 = mock(Ghost.class);
        Player player = mock(Player.class);
        playerCollisions.collide(ghost1,pellet);
        verifyZeroInteractions(ghost1,pellet);
        playerCollisions.collide(ghost1,ghost2);
        verifyZeroInteractions(ghost1,ghost2);
    }
}
