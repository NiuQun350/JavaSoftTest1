package nl.tudelft.jpacman.npc.ghost;


import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * 测试Clyde的行为.
 */
public class ClydeTest {
    private static MapParser mapParser;
    private static PlayerFactory playerFactory;
    /**
    * 实现这个ClydeTest测试类时，需要构建游戏运行的所有所需对象.
     * 这些对象包括PacManSprites（用于角色显示）、
     * PlayerFactory（用于构造Player）、GhostFactory（提供给LevelFactory）
     * ，BoardFactory（游戏场景）、GhostMapParser（地图解析）。
    * */
    @BeforeAll
    static void beforeAll() {
        //生成对象角色
        PacManSprites pacManSprites = new PacManSprites();
        playerFactory = new PlayerFactory(pacManSprites);
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(
            pacManSprites,
            ghostFactory,
            mock(PointCalculator.class));
        //生成游戏场景
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostMapParser ghostMapParser = new GhostMapParser(
            levelFactory,
            boardFactory,
            ghostFactory);
        mapParser = ghostMapParser;
    }

    /**
     * 测试当Clyde与Player的距离小于SHYNESS时的动作.
     */
    @DisplayName("Clyde离Player距离小于8个方块")
    @Test
    void when_Distance_Less_SHYNESS() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#.#....C.....P",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Clyde对象,并检查是否初始化成功
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clyde.hasSquare()).isEqualTo(true);
        assertThat(clyde.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);

        //观察clyde的下一步去哪
        Optional<Direction> nextAiMove = clyde.nextAiMove();

        //断言测试，此时clyde应该往西走
        assertThat(nextAiMove.get()).isEqualTo(Direction.WEST);
    }

    /**
     * 测试当Clyde与Player的距离大于SHYNESS时的动作.
     */
    @DisplayName("Clyde离Player距离大于8个方块")
    @Test
    void when_Distance_Greater_SHYNESS() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#...C........P",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Clyde对象,并检查是否初始化成功
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clyde.hasSquare()).isEqualTo(true);
        assertThat(clyde.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);

        //观察clyde的下一步去哪
        Optional<Direction> nextAiMove = clyde.nextAiMove();

        //断言测试，此时clyde应该往东走
        assertThat(nextAiMove.get()).isEqualTo(Direction.EAST);

    }

    /**
     * 测试当Clyde与Player无可达路径时，Clyde的操作.
     */
    @DisplayName("Clyde到Player无可达路径")
    @Test
    void when_Unable_To_Reach() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#...C..#.....P",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Clyde对象,并检查是否初始化成功
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clyde.hasSquare()).isEqualTo(true);
        assertThat(clyde.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);

        //观察clyde的下一步去哪
        Optional<Direction> nextAiMove = clyde.nextAiMove();

        //断言测试，不存在路径即下一可选择的方向列表中为空，即Optional.empty
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }

    /**
     * 断言测试当player不存在时的clyde的行为.
     */
    @DisplayName("当player不存在时，Clyde应该无法判断从何处出发")
    @Test
    void when_Not_Exist_Player() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#...#..C......",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Clyde对象,并检查是否初始化成功
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(clyde.hasSquare()).isEqualTo(true);
        assertThat(clyde.getDirection()).isEqualTo(Direction.EAST);

        //观察clyde的下一步去哪
        Optional<Direction> nextAiMove = clyde.nextAiMove();

        //断言测试，此时clyde无法移动
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }
}
