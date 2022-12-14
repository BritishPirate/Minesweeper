public class Coords {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coords(){
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ')';
    }

}
