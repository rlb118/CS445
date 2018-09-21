/*
    Robert Blake
    CS 445
    Assignment 3
*/

import java.io.*;
import java.util.*;

/**
    The application of Assignment 3, Assig3 makes use of the WordTile and WordInPhrase classes, and uses them to
    accept and read input and to keep track of changes and details with both the words and the letters.

*/


public class Assig3
{
  /**
     Main method that runs the program
     @param args Standard main parameter
  */
  
  
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      Scanner readFile;
      File fileName;
      String fileString;
      
      String phrase;
      String[] containedWords;
      WordInPhrase[] wordsInPhrase;
      
      int startRow = 0, startCol = 0;
      
      WordTile board;
      
      while (true)
      {
         try
         {
            System.out.println("Please enter filename:");
            fileString = keyboard.nextLine();
            fileName = new File(fileString);
            readFile = new Scanner(fileName);
              
            break;
         }
         catch (IOException e)
         {
            System.out.println("Problem " + e);
         }
      }
      
      String [] dimensions = (readFile.nextLine()).split(" ");
      int rows = Integer.parseInt(dimensions[0]);
      int cols = Integer.parseInt(dimensions[1]);
   	
      char [][] tile = new char[rows][cols];
   
      for (int i = 0; i < rows; i++)
      {
         String rowString = readFile.nextLine();
         for (int j = 0; j < rowString.length(); j++)
         {
            tile[i][j] = Character.toLowerCase(rowString.charAt(j));
         }
      }
      
      
      
      do
      {
         board = new WordTile(tile);
         board.showBoard();
         System.out.println("Enter the phrase (Separated by single spaces)");
         phrase = keyboard.nextLine().toLowerCase();
         
      
               
         containedWords = phrase.split(" ");
         wordsInPhrase = new WordInPhrase[containedWords.length];
         for(int wordNum = 0; wordNum < containedWords.length; wordNum++)
         {
            wordsInPhrase[wordNum] = new WordInPhrase(containedWords[wordNum]);
         }
         startRow = 0;
         startCol = 0;
         boolean phraseFound = false;
         while(!phraseFound)
         {
            wordsInPhrase[0].setStart(new int[]{startRow, startCol});
            
            for(int i = 1; i < 5 && !phraseFound; i++)
            {
               
               if(isWord(board, wordsInPhrase[0], startRow, startCol, 0, i))
               {
                  phraseFound = wordSearch(board, wordsInPhrase, wordsInPhrase[0].getEnd()[0], wordsInPhrase[0].getEnd()[1], 1);
               }
            
            }
            startCol++;
            if(startCol >= board.getWidth())
            {
               startCol = 0;
               startRow++;
            }
            if(startRow == board.getHeight())
            {
               System.out.println("Phrase not found");
               break;
            }
            
         }
         if(phraseFound)
         {
            for(int wNum = 0; wNum < wordsInPhrase.length; wNum++)
            {
               System.out.println("Word: " + wordsInPhrase[wNum].getWord());
               System.out.println("Starts at: " + Arrays.toString(wordsInPhrase[wNum].getStart()));
               System.out.println("Ends at: " + Arrays.toString(wordsInPhrase[wNum].getEnd()));
            }
         }
      }while(!phrase.equals(""));
      
   
   }
   
   /**
       Searching method. Recursively searches for words around the end of the previous word using backtracking.
       @param board The WordTile that is being searched
       @param phrase The array of words that are being searched for
       @param row the row that the search is currently checking
       @param col the column that the search is currently checking
       @param wordNum the number of the word currently being searched for
       @return If the full phrase has been found, returns true. Otherwise, false
   */
   
   public static boolean wordSearch(WordTile board, WordInPhrase[] phrase, int row, int col, int wordNum)
   {
      if(wordNum >= phrase.length)
      {
         return true;
      }
      else
      {
         boolean result = false;
         if(board.allowed(row,col + 1))
         {
            if(board.getLetter(row,col + 1) == phrase[wordNum].charAt(0))
            {
               phrase[wordNum].setStart(new int[]{row, col + 1});
               if(isWord(board, phrase[wordNum], row, col + 1, 0, 1))
               {
                  result = wordSearch(board, phrase, phrase[wordNum].getEnd()[0], phrase[wordNum].getEnd()[1], wordNum + 1);
               }
            }
         }
         if(board.allowed(row + 1,col))
         {
            if(!result && board.getLetter(row + 1,col) == phrase[wordNum].charAt(0))
            {
               phrase[wordNum].setStart(new int[]{row + 1, col});
               if(isWord(board, phrase[wordNum], row + 1, col, 0, 2))
               {
                  result = wordSearch(board, phrase, phrase[wordNum].getEnd()[0], phrase[wordNum].getEnd()[1], wordNum + 1);
               }
            }
         }
         if(board.allowed(row,col - 1))
         {
            if(!result && board.getLetter(row,col - 1) == phrase[wordNum].charAt(0))
            {
               phrase[wordNum].setStart(new int[]{row, col - 1});
               if(isWord(board, phrase[wordNum], row, col - 1, 0, 3))
               {
                  result = wordSearch(board, phrase, phrase[wordNum].getEnd()[0], phrase[wordNum].getEnd()[1], wordNum + 1);
               }
            }
         }
         
         if(board.allowed(row - 1,col))
         {
            phrase[wordNum].setStart(new int[]{row - 1, col});
            if(!result && board.getLetter(row - 1,col) == phrase[wordNum].charAt(0))
            {
               if(isWord(board, phrase[wordNum], row - 1, col, 0, 4))
               {
                  result = wordSearch(board, phrase, phrase[wordNum].getEnd()[0], phrase[wordNum].getEnd()[1], wordNum + 1);
               }
            }
         }
          
          
         return result;
      }
      
   }
   
   /**
       Word Checking method. Recursively checks to see if the word is complete
       @param board The WordTile that is being searched
       @param word the word that is being checked
       @param row the row that the search is currently checking
       @param col the column that the search is currently checking
       @param letterNum the current letter in the that is being checked
       @param direction Determines which direction the check takes. 1 = right, 2 = down, 3 = left, 4 = up
       @return If the full word has been found, returns true. Otherwise, false
   */
     
   
   public static boolean isWord(WordTile board, WordInPhrase word, int row, int col, int letterNum, int direction)
   {
      if(letterNum < word.length() && board.allowed(row,col))
      {
         
         boolean result = false;
         if(board.getLetter(row,col) == word.charAt(letterNum))
         {
            if(letterNum == word.length() - 1)
            {
               word.setEnd(new int[]{row,col});
            }
            switch(direction)
            {
               case 1:
                  result = isWord(board, word, row, col + 1, letterNum + 1, direction);
                  break;
                 
               case 2:
                  result = isWord(board, word, row + 1, col, letterNum + 1, direction);
                  break;
                     
               case 3:
                  result = isWord(board, word, row, col - 1, letterNum + 1, direction);
                  break;
                     
               case 4:
                  result = isWord(board, word, row - 1, col, letterNum + 1, direction);
                  break;
            }
         }
         
         
         return result;
      }
      else  if (!board.allowed(row,col) && letterNum < word.length())
      {
         return false;
      }
      else
      {
         if(word.getStart()[0] <= word.getEnd()[0])
         {
            for(int r = word.getStart()[0]; r <= word.getEnd()[0]; r++)
            {
               if(word.getStart()[1]  <= word.getEnd()[1])
               {
                  for(int c = word.getStart()[1]; c <= word.getEnd()[1]; c++)
                  {
                     board.setUsed(r,c);
                  }
               }
               else
               {
                  for(int c = word.getStart()[1]; c >= word.getEnd()[1]; c--)
                  {
                     board.setUsed(r,c);
                  }
               }
            }
         }
         else
         {
            for(int r = word.getStart()[0]; r >= word.getEnd()[0]; r--)
            {
               if(word.getStart()[1]  <= word.getEnd()[1])
               {
                  for(int c = word.getStart()[1]; c <= word.getEnd()[1]; c++)
                  {
                     board.setUsed(r,c);
                  }
               }
               else
               {
                  for(int c = word.getStart()[1]; c >= word.getEnd()[1]; c--)
                  {
                     board.setUsed(r,c);
                  }
               }
            }
         }
         return true;
      }
      
   }
   
   
}