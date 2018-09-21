/**
    The WordInPhrase class is used to keep track of the words being searched for and the starting
    and ending positions of the word on the grid
*/

public class WordInPhrase
{
   char[] letters;
   int[] startingPosition = new int[2];
   int[] endingPosition = new int[2];
   /**
       1-arg constructor
       @param word the word that will be searched for
   */
   public WordInPhrase(String word)
   {
      letters = word.toCharArray();
   }
   
   /**
       Returns the stored word as a string
       @return The stored string
   */
   public String getWord()
   {
      return new String(letters);
   }
   
   /**
       Returns the character at a given position in the word
       @param position the position of the letter in the word
       @return the letter at the requested position
   */
   
   public char charAt(int position)
   {
      return letters[position];
   }
   
   /**
       returns the coordinates on the grid where the word starts
       @return the array holding the starting coordinates of the word
   */
   
   public int[] getStart()
   {
      return startingPosition;
   }
   
   /**
       returns the coordinates on the grid where the word ends
       @return the array holding the ending coordinates of the word
   */
   
   public int[] getEnd()
   {
      return endingPosition;
   }
   /**
       sets the starting coordinates of the word
       @param start the new starting coordinates
   */
   
   public void setStart(int[] start)
   {
      startingPosition = start;
   }
   
   /**
       Sets the ending coordinates of the word
       @param end the new ending coordinates
   */
   
   public void setEnd(int[] end)
   {
      endingPosition = end;
   }
   
   /**
       Returns the length of the word
       @return word length
   */
   
   public int length()
   {
      return letters.length;
   }
   
   
   
}