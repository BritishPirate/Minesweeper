import java.util.ArrayList;
import java.util.Collections;

public class Board {
    Square[][] squareGrid;

    public Board(int width, int height){
        squareGrid = new Square[width][];
        for(int i = 0; i < width; i++){
            squareGrid[i] = new Square[height];
            for(int j = 0; j < height; j++){
                squareGrid[i][j] = new Square(new Coords(j, i));
            }
        }
    }

    public void generateGrid(int numOfBombs){
        generateBombs(numOfBombs);
        squareGrid[0][0].setUpLinks(this);
        //setUpLinks(new Coords(0,0));
        squareGrid[0][0].initialFilling();
    }

    private void setFillings(){
        for(int i = 0; i < squareGrid.length; i++){
            for(int j = 0; j < squareGrid[i].length; j++){
                Square sq = squareGrid[i][j];
                sq.filling = (sq.filling == -3 ? sq.aq.getAdjBombCount() : sq.filling);
            }
        }
    }

    private void generateBombs(int numOfBombs){ //Random bomb location generation.
        ArrayList<Coords> list = new ArrayList<Coords>();
        for (int i=0; i<squareGrid.length; i++) {
            for (int j=0; j<squareGrid[i].length; j++){
                list.add(new Coords(j, i)); // Generates all possible coordinates into a list
            }
        }
        Collections.shuffle(list); //Shuffles the list
        for(int i = 0; i < numOfBombs; i++){
            Coords temp = list.get(i);
            squareGrid[temp.y][temp.x].filling = -2; //Picks the first n out of the list
        }
    }

    public void linkSquares(int x, int y){
        for(int i = 0; i<y; i++ ){
            for(int j = 0; j<x; j++){
                Coords coords = new Coords(j, i);
                squareGrid[i][j].setPos(coords);
                setUpLinks(coords);
            }
        }
    }

    public void setUpLinks(Coords pos){
        Square sq = squareGrid[pos.y][pos.x];
        AdjacentSquares aq = new AdjacentSquares( pos, squareGrid);

        sq.setAq(aq);

        for (int i = 0; i < aq.size; i++){
            Square square = aq.getSquare(i);
            if(square != null && square.aq == null) setUpLinks(square.pos);
        }
    }


    private Square checkSquare(Square[][] board, Coords coords){ // This checks whether a square exists in a given spot and if not then it returns null
        boolean bool1 = coords.y < board.length && coords.y>=  0; //Check if the square is in the correct y range
        boolean bool2 = (bool1 ? coords.x < board[coords.y].length && coords.x >= 0 : false); //If the square is in the correct y range, check x range
        boolean bool3 = (bool1 && bool2 ?  board[coords.y][coords.x] != null : false); //If the square is in both ranges, check  if it's not null
        return (bool1 && bool2 && bool3 ? board[coords.y][coords.x] : null); //If square is not null and in the correct ranges then return it, else return null.
    }

    @Override
    public String toString(){
        String ret = "";
        for(Square[] squareLine : squareGrid){
            for(Square square : squareLine){
                ret += (!square.revealed ? "-" : square.filling) + " | ";
            }
            ret += '\n';
        }
        return ret;
    }

    public void revealSquare(Coords pos){
        squareGrid[pos.y][pos.x].revealSquare();
    }

}
