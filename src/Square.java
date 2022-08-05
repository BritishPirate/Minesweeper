public class Square {
    //region Explanation
    /*
   -3 - Unset
   -2 - bomb
    0 - empty
    1 - 1
    2 - 2
    3 - 3
    4 - 4
    5 - 5
    6 - 6
    7 - 7
    8 - 8
     */
    //endregion
    int filling;
    boolean revealed;
    boolean flag;
    Coords pos;

    AdjacentSquares aq;

    public Square(){
        filling = -3;
        revealed = false;
        flag = false;
    }

    public Square(Coords coords){
        filling = -3;
        pos = coords;
        revealed = false;
        flag = false;
    }
    //region Getters/Setters
    public int getFilling() {
        return filling;
    }


    public boolean isRevealed() {
        return revealed;
    }

    public Coords getPos() {
        return pos;
    }

    public void setPos(Coords pos) {
        this.pos = pos;
    }
    //endregion

    //This sets up my links, aka the 8 squares around this one.
    public void setAq(AdjacentSquares adjSq){
        aq = adjSq;
    }

    public void setUpLinks(Board board){
        aq = new AdjacentSquares( pos, board.squareGrid);

        for (int i = 0; i < aq.size; i++){
            Square square = aq.getSquare(i);
            if(square != null && square.aq == null) square.setUpLinks(board); //Check aq == null to know that the links havent set up for that square yet
        }
    }

    private Square checkSquare(Square[][] board, Coords coords){ // This checks whether a square exists in a given spot and if not then it returns null
        boolean bool1 = coords.y < board.length && coords.y>=  0; //Check if the square is in the correct y range
        boolean bool2 = (bool1 ? coords.x < board[coords.y].length && coords.x >= 0 : false); //If the square is in the correct y range, check x range
        boolean bool3 = (bool1 && bool2 ?  board[coords.y][coords.x] != null : false); //If the square is in both ranges, check  if it's not null
        return (bool1 && bool2 && bool3 ? board[coords.y][coords.x] : null); //If square is not null and in the correct ranges then return it, else return null.
    }

    public void initialFilling(){
        filling = (filling != -2 ? aq.getAdjBombCount() : filling);
        for(int i = 0; i < aq.size; i++){
            Square sq = aq.getSquare(i);
            if(sq != null && sq.filling == -3) sq.initialFilling();
        }
    }

    public boolean revealSquare(){
        if(filling == -2) return true;
        if(flag) return false;
        revealed = true;
        if(filling != 0) return false;
        for(int i = 0; i < aq.size; i++){
            Square square = aq.getSquare(i);
            if(square != null && !square.revealed) square.revealSquare();
        }
        return false;
    }
}
