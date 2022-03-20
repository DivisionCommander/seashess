package bg.seachess.seachess.main;

import java.io.PrintStream;

public class Desk {
    
    public static final char PLAYER_1_MARK = 'x';
    public static final char PLAYER_2_MARK = 'O';
    public static final char FILLER =  ' ';
    
   private final char[][] desk = new char[3][3];
   
   public Desk()
   {
       rebuildDesk();
   }
   
    
   public void rebuildDesk() {
       for (int row = 0; row < 3; row++) {
           for (int col = 0; col < 3; col++) {
               desk[row][col] = FILLER;
           }
       }
   }


   public void printDesk(PrintStream ps) {
       for (int row = 0; row < ((desk.length * 2) + 1); row++) {

           for (int col = 0; col < ((desk.length * 2) + 1); col++) {
               if (row % 2 == 0) {
                   ps.print("-");
               } else {
                   if (col % 2 == 0) {
                       ps.print("|");
                   } else {
                       ps.print(desk[row / 2][col / 2]);
                   }
               }
           }
           ps.println();
       }
   }
   
   public boolean isValidPosition(byte positionX, byte positionY)
   {
       if(positionX > desk.length || positionY> desk.length)
       {
           return false;
       }
       if(positionX<0 || positionY<0    )
       {
           return false;
       }
       if(desk[positionX][positionY]!=FILLER   )
       {
           return false;
       }
       return true;
   }
   
   public boolean checkVictory(char mark)
   {
       for (byte row = 0; row < desk.length; row++) {
           boolean rowWin = true;
           boolean colWin = true;
           boolean diag1 = true;
           boolean diag2 = true;

           for (byte col = 0; col < desk.length; col++) {
               if (desk[row][col] != mark) {
                   rowWin = false;
               }
               if (desk[col][row] != mark) {
                   colWin = false;
               }
               if (desk[col][col] != mark) {
                   diag1 = false;
               }
               if (desk[col][(desk.length - 1) - col] != mark) {
                   diag2 = false;
               }
           }
           if ((rowWin) || (colWin) || (diag1) || (diag2)) {
               return true;
           }
       }
       return false;
   }


   public boolean isFree(int positionX, int positionY)
   {
       return desk[positionX][positionY]==FILLER;
       
   }
}
