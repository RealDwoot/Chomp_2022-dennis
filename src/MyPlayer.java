import java.awt.*;
import java.util.ArrayList;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;


    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
        System.out.println("001 002 003 011 012 013 022 023 033 111 112 113 122 123 133 222 223 233 333");

        int n = 3;
        print3x3boards(n);

    }

    public int[] checkChips(Chip[][] gameBoard){
        int[] cols = new int[10];

        for (int c = 0; c < 10; c++){
            for (int r = 0; r< 10; r++){
                if (gameBoard[r][c].isAlive == true) {
                    cols[c] = cols[c]+1;
                }
            }
        }

        return cols;
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        columns = checkChips(gameBoard);

        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */

        Point myMove = new Point(row, column);
        return myMove;
    }

    public void print3x3boards(int n) {
        for (int x = 1; x <= n; x++) {
            for (int y = 0; y <= x; y++) {
                for (int z = 0; z <= y; z++) {
                    System.out.println(Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z));
                    System.out.println("–");
                    resultBoards(x,y,z);
                    System.out.println("––––––––––––––––");

                }
            }
        }
    }

    public void resultBoards(int x, int y, int z) {


        for (int w = z; w >= 1; w--) {

            System.out.println(Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z-w));
            System.out.println("||   " + Integer.toString(z-w) + ", " + Integer.toString(2));
        }

        for (int u = y; u >= 1; u--) {

            if(y==z) {
                System.out.println(Integer.toString(x) + " " + Integer.toString(y-u) + " " + Integer.toString(z-u));
            }
            else {
                System.out.println(Integer.toString(x) + " " + Integer.toString(y-u) + " " + Integer.toString(z));
            }

            System.out.println("||   " + Integer.toString(y-u) + ", " + Integer.toString(1));

        }

        for (int v = x; v >= 1; v--) {
            if(x==y && x==z) {
                System.out.println(Integer.toString(x-v) + " " + Integer.toString(y-v) + " " + Integer.toString(z-v));
            }
            else if(x==y) {
                System.out.println(Integer.toString(x-v) + " " + Integer.toString(y-v) + " " + Integer.toString(z));

            }
            else {
                System.out.println(Integer.toString(x-v) + " " + Integer.toString(y) + " " + Integer.toString(z));
            }

            System.out.println("||   " + Integer.toString(x-v) + ", " + Integer.toString(0));

        }

        

    }

    /* If one of the result boards is a losing board, then it’s a winning board
    Losing boards results in only winning boards

    Losing boards:
    1 0 0
    2 1 0
    3 1 1

    How to classify boards as win/lose
    Iterate through boards and find winning boards by checking if one of their result boards is a losing board, if the board is not a winning board then save it as a losing board (adding it the the list of losing boards), then continue to the end?
    How should I order my boards
     */

    /*public void BoardIsWinning(ArrayList<Integer> board) {
        for (i in resultBoards()) {
        if (BoardIsWinning(i) == false) {
            return true;
        } else {
            return false;
        }
        }
    } */


}
