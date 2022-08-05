public class AdjacentSquares {
    Square n;
    Square ne;
    Square e;
    Square se;
    Square s;
    Square sw;
    Square w;
    Square nw;

    int size = 8;

    //region getters and setters
    public Square getN() {
        return n;
    }

    public Square getNe() {
        return ne;
    }

    public Square getE() {
        return e;
    }

    public Square getSe() {
        return se;
    }

    public Square getS() {
        return s;
    }

    public Square getSw() {
        return sw;
    }

    public Square getW() {
        return w;
    }

    public Square getNw() {
        return nw;
    }

    public int getSize(){
        return size;
    }
    //endregion

    public Square getSquare(int i){
        switch(i){
            case 0:
                return n;
            case 1:
                return ne;
            case 2:
                return e;
            case 3:
                return se;
            case 4:
                return s;
            case 5:
                return sw;
            case 6:
                return w;
            case 7:
                return nw;
            default:
                throw new Error("Index out of bounds for adjacent squares");
        }
    }

    public AdjacentSquares(Coords pos, Square[][] grid){
        setSquare(0, checkSquare(grid, new Coords(   pos.x    , pos.y + 1)));
        setSquare(1, checkSquare(grid, new Coords(pos.x + 1, pos.y + 1)));
        setSquare(2, checkSquare(grid, new Coords(pos.x + 1,    pos.y    )));
        setSquare(3, checkSquare(grid, new Coords(pos.x + 1, pos.y - 1)));
        setSquare(4, checkSquare(grid, new Coords(   pos.x    , pos.y - 1)));
        setSquare(5, checkSquare(grid, new Coords(pos.x - 1, pos.y - 1)));
        setSquare(6, checkSquare(grid, new Coords(pos.x - 1,    pos.y    )));
        setSquare(7, checkSquare(grid, new Coords(pos.x - 1, pos.y + 1)));
    }

    private Square checkSquare(Square[][] board, Coords coords){ // This checks whether a square exists in a given spot and if not then it returns null
        boolean bool1 = coords.y < board.length && coords.y>=  0; //Check if the square is in the correct y range
        boolean bool2 = (bool1 ? coords.x < board[coords.y].length && coords.x >= 0 : false); //If the square is in the correct y range, check x range
        boolean bool3 = (bool1 && bool2 ?  board[coords.y][coords.x] != null : false); //If the square is in both ranges, check  if it's not null
        return (bool1 && bool2 && bool3 ? board[coords.y][coords.x] : null); //If square is not null and in the correct ranges then return it, else return null.
    }
    public void setSquare(int i, Square square){
        switch(i){
            case 0:
                n = square;
                break;
            case 1:
                ne = square;
                break;
            case 2:
                e = square;
                break;
            case 3:
                se = square;
                break;
            case 4:
                s = square;
                break;
            case 5:
                sw = square;
                break;
            case 6:
                w = square;
                break;
            case 7:
                nw = square;
                break;
            default:
                throw new Error("Index out of bounds for adjacent squares");
        }
    }

    public int getAdjBombCount(){
        int bombCount = 0;
        for(int i = 0; i < size; i++){
            if(getSquare(i) != null)
                bombCount += (getSquare(i).filling == -2 ? 1 : 0);
        }
        return bombCount;
    }

    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < size; i++){
            Square sq = getSquare(i);
            if(sq != null)
                ret += "| " + sq.pos + ", " + sq.filling + " |";
            else
                ret += "| NULL |";
        }
        return ret;
    }
}
