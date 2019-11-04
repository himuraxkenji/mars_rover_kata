public class MarsRover {

    private Integer worldSize;
    private Point position;
    private String direction;
    private Point obstacle = null;
    private Point foundObstacle = null;

    public MarsRover(Point position, String direction) {
        this.position = position;
        this.direction = direction;
        this.worldSize = 10;
    }
    public MarsRover(Point position, String direction, Integer worldSize) {
        this.position = position;
        this.direction = direction;
        this.worldSize = worldSize;
    }

    public Point actualPosition() {
        return this.position;
    }

    public String actualDirection() {
        return this.direction;
    }

    public void navigate(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); i++) {
            if(this.foundObstacle != null)
                break;
            switch (commandsSequence.charAt(i)){
                case 'L':
                    turn("L");
                    break;
                case 'R':
                    turn("R");
                    break;
                case 'F':
                    move("F");
                    break;
                case 'B':
                    move("B");
                    break;

            }
        }
    }


    private void turn(String direction) {
        String directionals = "WNESWN";
        int index = directionals.indexOf(this.direction.toUpperCase());
        int newIndex = (direction == "R") ? index + 1: index - 1;
        this.direction = directionals.charAt(newIndex)  + "";
    }


    public void move( String direction ) {

        int fallOver = (direction == "F") ? +1 : -1;

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        if (this.direction.charAt(0) == 'N')
            this.position.setY(this.position.getY() + fallOver);
        if (this.direction.charAt(0) == 'E') {
            this.position.setX(this.position.getX() + fallOver);
        }
        if (this.direction.charAt(0) == 'S')
            this.position.setY(this.position.getY() - fallOver);
        if (this.direction.charAt(0) == 'W')
            this.position.setX(this.position.getX() - fallOver);

        if (this.position.getX() >= this.worldSize)
            this.position.setX(this.position.getX() % this.worldSize);
        if (this.position.getY() >= this.worldSize)
            this.position.setY(this.position.getY() % this.worldSize);
        if (this.position.getX() == -1)
            this.position.setX(this.position.getX() + this.worldSize);
        if (this.position.getY() == -1)
            this.position.setY(this.position.getY() + this.worldSize);


        if (this.obstacle != null && this.position.getX() == this.obstacle.getX() && this.position.getY() == this.obstacle.getY()) {
            this.foundObstacle= new Point(this.position.getX(), this.position.getY());
            this.position.setX(currentX);
            this.position.setY(currentY);
        }
    }
    public void loadObstacle(Point obstacle){
        this.obstacle = obstacle;
    }


    public Point getObstacleFound() {
        return this.foundObstacle;
    }
}