import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTest {

    @Test
    public void testCreateARoverInPositionZeroZeroAndDirectionNorth(){
        MarsRover marsRover = new MarsRover(new Point(0,0), "N");
        Assertions.assertEquals(marsRover.actualPosition(), new Point(0,0));
        Assertions.assertEquals(marsRover.actualDirection(), "N");
    }

    @Test
    public void  testCreateARoverInPositionOneZeroAndDirectionSouth(){
        MarsRover marsRover = new MarsRover(new Point(1,0), "S");
        Assertions.assertEquals(marsRover.actualPosition(), new Point(1,0));
        Assertions.assertEquals(marsRover.actualDirection(), "S");
    }

    @Test
    public void testRoverTurnsRigthWhenInputIsRight(){
        MarsRover marsRover = new MarsRover(new Point(0,0), "N");
        marsRover.navigate("R");
        Assertions.assertEquals(marsRover.actualDirection(), "E");
    }

    @Test
    public void testRoverTurnsLefthWhenInputIsLeft(){
        MarsRover marsRover = new MarsRover(new Point(0,0), "N");
        marsRover.navigate("L");
        Assertions.assertEquals(marsRover.actualDirection(), "W");
    }

    @Test
    public void testMarsRoverMovesTakingDirectionIntoAccountSouthFoward() {
        MarsRover marsRover = new MarsRover(new Point(1,1), "S");

        marsRover.navigate("F");

        Assertions.assertEquals(marsRover.actualPosition().getY(), 0);
        Assertions.assertEquals(marsRover.actualPosition().getX(), 1);

    }

    @Test
    public void testMarsRoverMovesTakingDirectionIntoAccountSouthBackward() {
        MarsRover marsRover= new MarsRover(new Point(5,5), "S");

        marsRover.navigate("B");

        Assertions.assertEquals(marsRover.actualPosition().getY(), 6);
    }

    @Test
    public void testMarsRoverMovesAtWorldsBoundaryLessY() {
        MarsRover marsRover = new MarsRover(new Point(0,0), "S", 10);

        marsRover.navigate("F");

        Assertions.assertEquals(marsRover.actualPosition().getY(), 9);
    }

    @Test
    public void testMarsRoverMovesAtWorldsBoundaryGreatherThatYMaximus() {
        MarsRover marsRover = new MarsRover(new Point(9,9), "N", 10);

        marsRover.navigate("F");

        Assertions.assertEquals(marsRover.actualPosition().getY(), 0);
    }

    @Test
    public void testMarsRoverMultipleCommandSequence(){
        MarsRover marsRover = new MarsRover(new Point(0,0), "N", 10);

        marsRover.navigate("FRFLFRFRFFFRFBBB");
        Assertions.assertEquals(marsRover.actualPosition().getX(), 4);
        Assertions.assertEquals(marsRover.actualPosition().getY(), 9);

    }

    @Test
    public void testMarsRoverWhenHaveObstacles() {
        MarsRover marsRover = new MarsRover(new Point(0,0), "N", 10);

        marsRover.loadObstacle( new Point( 2, 2 ) );

        marsRover.navigate("FRFLFRFRFFFRFBBB");

        Assertions.assertEquals(2, marsRover.getObstacleFound().getX());
        Assertions.assertEquals(2, marsRover.getObstacleFound().getY());

        Assertions.assertEquals(marsRover.actualPosition().getX(), 1);
        Assertions.assertEquals(marsRover.actualPosition().getY(), 2);

    }
}
