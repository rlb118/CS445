/**
    The WordTile class handles the actual board that the letters are on. It manages both the letters themselves and their current state
*/

public class WordTile
{
   private final int UNUSED = 0;
   private final int USED = 1;
   
   private char[][] board;
   private int[][] letterStatus;
   
   /**
      the 1-arg constructor, it creates the board based on the given array of characters and sets all of the letters to "unused"
      @param theBoard the array of characters that the board will display
   */
   
   public WordTile(char[][] theBoard)
   {
      board = new char[theBoard.length][theBoard[0].length];
      
      for(int inner = 0; inner < board.length; inner++)
      {
         for(int outer = 0; outer < board[0].length; outer++)
         {
            board[inner][outer] = Character.toLowerCase(theBoard[inner][outer]);
         }
      }
      
      
      letterStatus = new int[board.length][board[0].length];
      for(int row = 0; row < letterStatus.length; row++)
      {
         for(int col = 0; col < letterStatus.length; col++)
         {
            letterStatus[row][col] = UNUSED;
         }
      }
   }
  
  
  /**
      Determines if the requested row and column are within the bounds of the board
      @param row requested row to be checked
      @param column requested column to be checked
      @returns true if within bounds, false otherwise
  */ 
   private boolean legal(int row, int column)
   {
      if(row < 0 || row >= board.length)
      {
         return false;
      }
      if(column < 0 || column >= board[0].length)
      {
         return false;
      }
      return true;
      
   }
   /**
       Determines if the requested coordinates are valid to be checked
       @param row row being checked
       @param column column being checked
       @returns true if unused, false otherwise
   */
   
   public boolean allowed(int row, int column)
   {
      if(legal(row,column))
      {
         return letterStatus[row][column] == 0;
      }
      return false;
   }
   
   /**
       marks the specified coordinate as used
       @param row row of coordinate being modified
       @param column column of coordinate being modified
   */
   
   public void setUsed(int row, int column)
   {
      letterStatus[row][column] = USED;
      board[row][column] = Character.toUpperCase(board[row][column]);
   }
   
   /**
       Returns letter at specified coordinates
       @param row requested row
       @param coloumn requested column
       @return the letter at specified coordinates
   
   */
   
   public char getLetter(int row, int column)
   {
      return board[row][column];
   }
   
   /**
       Outputs the board 
   */
   
   public void showBoard()
   {
      for(int row = 0; row < letterStatus.length; row++)
      {
         for(int col = 0; col < letterStatus.length; col++)
         {
            System.out.print(board[row][col]);
         }
         System.out.println();
      }
   }
   
   /**
       Returns the number of columns on the board
       @return the number of coloumns
   */
   
   public int getWidth()
   {
      return board[0].length;
   }
   
   /**
       Returns the number of rows on the board
       @return the number of rows
   */
   
   public int getHeight()
   {
      return board.length;
   }
   
   
   
   

}