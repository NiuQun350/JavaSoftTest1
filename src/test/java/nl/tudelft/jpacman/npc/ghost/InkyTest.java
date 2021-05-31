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
 * 测试Inky的行为.
 */
public class InkyTest {
    private static MapParser mapParser;
    private static PlayerFactory playerFactory;

    /**
     * 初始化所需要的地图解析器和玩家初始化工厂.
     */
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
     * 断言测试当blinky不存在时的inky行为.
     */
    @DisplayName("当blinky不存在时，Inky应该无法判断从何处出发")
    @Test
    void when_Not_Exist_Blinky() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#...#..I.....P",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Inky对象,并检查是否初始化成功
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertThat(inky.hasSquare()).isEqualTo(true);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);

        //观察inky的下一步去哪
        Optional<Direction> nextAiMove = inky.nextAiMove();

        //断言测试，此时inky无法移动
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }

    /**
     * 断言测试当player不存在时的inky行为.
     */
    @DisplayName("当player不存在时，Inky应该无法判断从何处出发")
    @Test
    void when_Not_Exist_Player() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#...#..I.....B",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);
        //获取地图上的Inky对象,并检查是否初始化成功
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertThat(inky.hasSquare()).isEqualTo(true);
        assertThat(blinky.hasSquare()).isEqualTo(true);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST);
        assertThat(blinky.getDirection()).isEqualTo(Direction.EAST);

        //观察inky的下一步去哪
        Optional<Direction> nextAiMove = inky.nextAiMove();

        //断言测试，此时inky无法移动
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }

    /**
     * playerDestination为player移动方向上的第二个方块.
     *             "##############",
     *             "#I...B....*.P.",
     *             "##############"
     * 在此例中就是*，同时在并排延长线上这个射线是循环的,且B->playerDestination的两倍延长线末端就是I的位置
     */
    @DisplayName("当I的位置刚好处在B->playerDestination的两倍延长线时，Inky应该无法判断从何处出发")
    @Test
    void when_At_The_End_Of_The_Extension() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#I...B......P.",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Inky对象,并检查是否初始化成功
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertThat(inky.hasSquare()).isEqualTo(true);
        assertThat(blinky.hasSquare()).isEqualTo(true);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST);
        assertThat(blinky.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);
        //观察inky的下一步去哪
        Optional<Direction> nextAiMove = inky.nextAiMove();
        //断言测试，此时inky无法移动
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }

    /**
     * playerDestination为player移动方向上的第二个方块.
     *             "##############",
     *             "#I........B.P.",
     *             "##############"
     * 在并排延长线上这个射线是循环的
     */
    @DisplayName("当B的位置刚好处在playerDestination位置时，Inky应该无法判断从何处出发")
    @Test
    void when_At_The_Location_of_playerDestination() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#I........B.P.",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Inky对象,并检查是否初始化成功
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertThat(inky.hasSquare()).isEqualTo(true);
        assertThat(blinky.hasSquare()).isEqualTo(true);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST);
        assertThat(blinky.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);
        //观察inky的下一步去哪
        Optional<Direction> nextAiMove = inky.nextAiMove();
        //断言测试，此时inky无法移动
        assertThat(nextAiMove).isEqualTo(Optional.empty());
    }

    /**
     * playerDestination为player移动方向上的第二个方块.
     *             "##############",
     *             "#!I..B....*.P.",
     *             "##############"
     * 在此例中就是*，同时在并排延长线上这个射线是循环的,且B->playerDestination的两倍延长线末端就是!的位置
     */
    @DisplayName("当存在Inky到destination的路径时，Inky应该向destination方向移动")
    @Test
    void when_Exist_path_from_inky_to_destination() {
        List<String> mapString = Lists.newArrayList(
            "##############",
            "#.I..B......P.",
            "##############"
        );
        //生成管理对象模型
        Level level = mapParser.parseMap(mapString);

        //获取地图上的Inky对象,并检查是否初始化成功
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertThat(inky.hasSquare()).isEqualTo(true);
        assertThat(blinky.hasSquare()).isEqualTo(true);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST);
        assertThat(blinky.getDirection()).isEqualTo(Direction.EAST);

        //创建player对象
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.valueOf("WEST"));
        //将player对象赋给P,并从地图上取出来，检查取出来的P是否为刚创建的player对象
        level.registerPlayer(player);
        Player p = Navigation.findUnitInBoard(Player.class, level.getBoard());
        assertThat(p.hasSquare()).isEqualTo(true);
        assertThat(p.getDirection()).isEqualTo(Direction.WEST);
        //观察inky的下一步去哪
        Optional<Direction> nextAiMove = inky.nextAiMove();
        //断言测试，此时inky无法移动
        assertThat(nextAiMove.get()).isEqualTo(Direction.WEST);
    }
}
