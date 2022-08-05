public class MineSweeper {
    

    public static void main(String[] args){
        Board board = new Board(10, 10);
        board.generateGrid(3);
        System.out.println(board);
        board.revealSquare(new Coords(0, 0));
        System.out.println(board);
    }

    private static void printGrid(){

    }
}
