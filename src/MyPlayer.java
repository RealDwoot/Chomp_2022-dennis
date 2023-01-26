import java.awt.*;
import java.util.ArrayList;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
   public  ArrayList<Board> resultBoardList = new ArrayList<>();
    public  ArrayList<Board> losingBoardList = new ArrayList<>();



    public MyPlayer() {
        columns = new int[10];
        Board f = new Board();
        f.boardState.add(1);
        f.boardState.add(0);
        f.boardState.add(0);
        f.moveToMakeCol = 0;
        f.moveToMakeRow = 0;
        losingBoardList.add(f);
        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
        System.out.println("001 002 003 011 012 013 022 023 033 111 112 113 122 123 133 222 223 233 333");

        int n = 3;
        print3x3boards(n);

        System.out.println("losign bords");
        for(int x = 0; x< losingBoardList.size(); x++)
        {
            System.out.println(losingBoardList.get(x).boardState.get(0) + ", "+ losingBoardList.get(x).boardState.get(1)+", "+ losingBoardList.get(x).boardState.get(2)+ " c: " +losingBoardList.get(x).moveToMakeCol+ " r: " +losingBoardList.get(x).moveToMakeRow);

        }

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

        int column = 1;
        int row = 1;

        for(int x = 0; x<resultBoardList.size(); x++){
            System.out.println(resultBoardList.get(x).boardState.get(0) + ", "+ resultBoardList.get(x).boardState.get(1)+", "+ resultBoardList.get(x).boardState.get(2)+ " c: " +resultBoardList.get(x).moveToMakeCol+ " r: " +resultBoardList.get(x).moveToMakeRow);
        }

        for(int x = 0; x<resultBoardList.size(); x++){

            if(resultBoardList.get(x).boardState.get(0) == columns[0] && resultBoardList.get(x).boardState.get(1) == columns[1] && resultBoardList.get(x).boardState.get(2) == columns[2]){
                row = resultBoardList.get(x).moveToMakeRow;
                column = resultBoardList.get(x).moveToMakeCol;
                System.out.println("match r:"+ row +" c:" +column);
            }
        }

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
                    Board temp = new Board();
                    temp.boardState.add(x);
                    temp.boardState.add(y);
                    temp.boardState.add(z);

                    resultBoards(temp);
                    System.out.println("––––––––––––––––");

                }
            }
        }
    }

    public ArrayList<Board> resultBoards(Board b) {
        Board board = new Board();
        board.boardState.add( b.boardState.get(0));
        board.boardState.add( b.boardState.get(1));
        board.boardState.add( b.boardState.get(2));

        Board newBoard = new Board();
        newBoard.boardState.add( b.boardState.get(0));
        newBoard.boardState.add( b.boardState.get(1));
        newBoard.boardState.add( b.boardState.get(2));
        boolean foundLosing = false;
        int x = b.boardState.get(0);
        int y = b.boardState.get(1);
        int z = b.boardState.get(2);

        for (int w = z-1; w >= 0; w--) {


          //  System.out.println(Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z-w));
           // System.out.println("||   " + Integer.toString(z-w) + ", " + Integer.toString(2));

            newBoard.boardState.set(0, x);
            newBoard.boardState.set(1, y);
            newBoard.boardState.set(2, w);
            System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));

            //if(!foundLosing){
           // if(BoardIsWinning(newBoard)){
            for (int ii = 0; ii < losingBoardList.size(); ii++) {
                //System.out.println("erhgo");
                if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(2) == newBoard.boardState.get(2)) {
                    System.out.println("found2");
                    System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
                    System.out.println(board.boardState.get(0) + " ," + board.boardState.get(1) + " ," + board.boardState.get(2));

                    foundLosing = true;
                    for (int i = 0; i < board.boardState.size(); i++) {
                        if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
                            //continue;
                        } else {
                            board.moveToMakeCol = i;
                            board.moveToMakeRow = newBoard.boardState.get(i);
                        }
                    }
                    System.out.println("r: " + board.moveToMakeRow + "c: " + board.moveToMakeRow);

                }
            }

          //  resultBoardList.add(newBoard);
        }

        x = b.boardState.get(0);
        y = b.boardState.get(1);
        z = b.boardState.get(2);
        for (int u = y-1; u >= 0; u--) {

            if(z>u) {
                z = u;
            }
              //  System.out.println(Integer.toString(x) + " " + Integer.toString(y-u) + " " + Integer.toString(z-u));

//                newBoard.boardState.set(0, x);
//                newBoard.boardState.set(1, y-u);
//                newBoard.boardState.set(2, z-u);
//                System.out.println("j");
//                System.out.println(y-u);
//                System.out.println(newBoard.boardState.size());
//                System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
//
//                // if(!foundLosing){
//                //if(BoardIsWinning(newBoard)){
//                for (int ii = 0; ii < losingBoardList.size(); ii++) {
//                    //System.out.println("erhgo");
//                    if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1)) {
//
//                        System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
//                        System.out.println(board.boardState.get(0) + " ," + board.boardState.get(1) + " ," + board.boardState.get(2));
//
//                        foundLosing = true;
//                        for (int i = 0; i < board.boardState.size(); i++) {
//                            if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
//                                // continue;
//                            } else {
//                                board.moveToMakeCol = i;
//                                board.moveToMakeRow = newBoard.boardState.get(i);
//                                break;
//                            }
//                        }
//                        System.out.println("r: " + board.moveToMakeRow + "c: " + board.moveToMakeRow);
//                    }
//                }
//                //resultBoardList.add(newBoard);


            //else {
               // System.out.println(Integer.toString(x) + " " + Integer.toString(y-u) + " " + Integer.toString(z));

                newBoard.boardState.set(0, x);
                newBoard.boardState.set(1, u);
                newBoard.boardState.set(2, z);
                System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));

                //  if(!foundLosing){
                //if(BoardIsWinning(newBoard)){
                for (int ii = 0; ii < losingBoardList.size(); ii++) {
                    //System.out.println("erhgo");
                    System.out.println("losing");
                    System.out.println(losingBoardList.get(ii).boardState.get(0) + " ," + losingBoardList.get(ii).boardState.get(1) + " ," + losingBoardList.get(ii).boardState.get(2));
                    System.out.println("reduced");
                    System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));

                    if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(2) == newBoard.boardState.get(2)) {
                        System.out.println("found1");

                        System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
                        System.out.println(board.boardState.get(0) + " ," + board.boardState.get(1) + " ," + board.boardState.get(2));

                        foundLosing = true;
                        for (int i = 0; i < board.boardState.size(); i++) {
                            if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
                                //continue;
                            } else {
                                board.moveToMakeCol = i;
                                board.moveToMakeRow = newBoard.boardState.get(i);
                                break;
                            }
                        }
                        System.out.println("r: " + board.moveToMakeRow + " c: " + board.moveToMakeRow);

                    }
                }
               // resultBoardList.add(newBoard);
            }

        //}
        x = b.boardState.get(0);
        y = b.boardState.get(1);
        z = b.boardState.get(2);

        for (int v = x-1; v >= 1; v--) {
            if(z>v) {
                z = v;
            }
            if(y>v) {
                y = v;
            }

//            if(x==y && x==z) {
//               // System.out.println(Integer.toString(x-v) + " " + Integer.toString(y-v) + " " + Integer.toString(z-v));
//
//                newBoard.boardState.set(0, x-v);
//                newBoard.boardState.set(1, y-v);
//                newBoard.boardState.set(2, z-v);
//                System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
//
//                for (int ii = 0; ii < losingBoardList.size(); ii++) {
//                    //System.out.println("erhgo");
//                    if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1)) {
//
//                        // if(BoardIsWinning(newBoard)){
//                        System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
//                        System.out.println(board.boardState.get(0) + " ," + board.boardState.get(1) + " ," + board.boardState.get(2));
//
//                        foundLosing = true;
//                        for (int i = 0; i < board.boardState.size(); i++) {
//                            if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
//                                // continue;
//                            } else {
//                                board.moveToMakeCol = i;
//                                board.moveToMakeRow = newBoard.boardState.get(i);
//                                break;
//                            }
//                        }
//                        System.out.println("r: " + board.moveToMakeRow + "c: " + board.moveToMakeRow);
//
//                    }
//                }
//
//               // resultBoardList.add(newBoard);
//            }
//            else if(x==y) {
//              //  System.out.println(Integer.toString(x-v) + " " + Integer.toString(y-v) + " " + Integer.toString(z));
//
//                newBoard.boardState.set(0, x-v);
//                newBoard.boardState.set(1, y-v);
//                newBoard.boardState.set(2, z);
//                System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
//
//                //if(!foundLosing){
//                //if(BoardIsWinning(newBoard)){
//                for (int ii = 0; ii < losingBoardList.size(); ii++) {
//                    //System.out.println("erhgo");
//                    if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1)) {
//
//                        System.out.println(newBoard.boardState.get(0) + " ,"+newBoard.boardState.get(1)+ " ,"+newBoard.boardState.get(2));
//                    System.out.println(board.boardState.get(0) + " ,"+board.boardState.get(1)+ " ,"+board.boardState.get(2));
//
//                    foundLosing = true;
//                    for (int i = 0; i < board.boardState.size(); i++) {
//                        System.out.println("hi");
//
//                        if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
//                            //continue;
//                        }
//                        else {
//                            board.moveToMakeCol = i;
//                            board.moveToMakeRow = newBoard.boardState.get(i);
//                            break;
//                        }
//                    }
//                    System.out.println("r: "+board.moveToMakeRow+"c: "+ board.moveToMakeRow);
//
//                }
//                }
//
//                //resultBoardList.add(newBoard);
//            }
//            else {
              //  System.out.println(Integer.toString(x-v) + " " + Integer.toString(y) + " " + Integer.toString(z));

                newBoard.boardState.set(0, v);
                newBoard.boardState.set(1, y);
                newBoard.boardState.set(2, z);
                System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));

                //     if(!foundLosing){
                //if(BoardIsWinning(newBoard)){
                 //   foundLosing = ;

                for (int ii = 0; ii < losingBoardList.size(); ii++) {
                    //System.out.println("erhgo");
                    if (losingBoardList.get(ii).boardState.get(0) == newBoard.boardState.get(0) && losingBoardList.get(ii).boardState.get(1) == newBoard.boardState.get(1) && losingBoardList.get(ii).boardState.get(2) == newBoard.boardState.get(2)) {
                        System.out.println("found3");
                        foundLosing = true;

                        System.out.println(newBoard.boardState.get(0) + " ," + newBoard.boardState.get(1) + " ," + newBoard.boardState.get(2));
                        System.out.println(board.boardState.get(0) + " ," + board.boardState.get(1) + " ," + board.boardState.get(2));

                        for (int i = 0; i < board.boardState.size(); i++) {
                           // System.out.println("hi");
                            if (board.boardState.get(i) - newBoard.boardState.get(i) == 0) {
                                // continue;
                            } else {
                             //   System.out.println("hi1");
                                board.moveToMakeCol = i;
                                board.moveToMakeRow = newBoard.boardState.get(i);
                                System.out.println("c: " + i + "r " + newBoard.boardState.get(i));
                                System.out.println("r: " + board.moveToMakeRow + "c: " + board.moveToMakeCol);

                                //  break;
                            }
                        }

                    }
                }

                //resultBoardList.add(newBoard);
            }


        x = b.boardState.get(0);
        y = b.boardState.get(1);
        z = b.boardState.get(2);

        System.out.println(foundLosing);
        if(foundLosing)
        {
            resultBoardList.add(board);
        }
        else{
            //it is a losing board so I don't need to set a moveToMake
            board.moveToMakeRow = 0;
            board.moveToMakeCol = 0;

//            for (int i = 0; i < board.boardState.size(); i++) {
//                if(board.boardState.get(i) == board.boardState.get(0)) {
//                    board.moveToMakeCol = i;
//                    //set move to make col
//                }
//            }
            losingBoardList.add(board);
            resultBoardList.add(board);

        }


//        for (int i = 0; i < resultBoardList.size(); i++) {
//            System.out.println(resultBoardList.get(i).boardState);
//        }
        return resultBoardList;

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

    public boolean BoardIsWinning(Board board) {

        for (int i = 0; i < losingBoardList.size(); i++) {
            //System.out.println("erhgo");
             if (losingBoardList.get(i).boardState.get(0) == board.boardState.get(0) && losingBoardList.get(i).boardState.get(1) == board.boardState.get(1) && losingBoardList.get(i).boardState.get(1) == board.boardState.get(1)) {
                 // we have found a winning board
                 System.out.println("found winning");
                 return true;

            }
        }
        return false;
    }


}
