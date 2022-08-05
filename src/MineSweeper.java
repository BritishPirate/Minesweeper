import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class MineSweeper {
    

    public static void main(String[] args){
        Board board = new Board(10, 10);
        board.generateGrid(3);
        System.out.println(board);
        board.revealSquare(new Coords(0, 0));
        System.out.println(board);

        GUIController.startScreenSetup();
    }


}
