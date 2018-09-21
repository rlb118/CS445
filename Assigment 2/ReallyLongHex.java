/** CS 0445 Spring 2018 (Adapted  from Dr. John Ramirez's assignment code)
 This is a partial implementation of the ReallyLongHex class.  You need to
 complete the implementations of the remaining methods.  Also, for this class
 to work, you must complete the implementation of the LinkedDS class.
 See additional comments below.
*/
public class ReallyLongHex 	extends LinkedDS<Character> 
							implements Comparable<ReallyLongHex>
{
	// Instance variables are inherited.  You may not add any new instance variables
	
	// Default constructor
   private ReallyLongHex()
   {
      super();
   }

	// Note that we are adding the digits here in the END. This results in the 
    // MOST significant digit first in the chain.  
    // It is assumed that String s is a valid representation of an
	// unsigned integer with no leading zeros.
   public ReallyLongHex(String s)
   {
      super();
      char c;
   	// Iterate through the String, getting each character and adding it 
        // at the end of the list.  
      for (int i = 0; i < s.length(); i++)
      {
         c = s.charAt(i);
         if ((('0' <= c) && (c <= '9')) || (('A' <= c) && (c <= 'F')))
         {
            this.addItem(c);
         }
         else throw new NumberFormatException("Illegal digit " + c);
      }
   }

	// Simple call to super to copy the nodes from the argument ReallyLongHex
	// into a new one.
   public ReallyLongHex(ReallyLongHex rightOp)
   {
      super(rightOp);
   }
	
	// Method to put digits of number into a String.  We traverse the chain 
    // to add the digits to a StringBuilder. 
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      if (numOfEntries > 0)
      {
         sb.append("0x");
         for (Node curr = firstNode; curr != null; 
         		curr = curr.getNextNode())
         {
            sb.append(curr.getData());
         }
      }
      return sb.toString();
   }

	// You must implement the methods below.  See the descriptions in the
	// assignment sheet

   public ReallyLongHex add(ReallyLongHex rightOp)
   {
      
      return rightOp;
   }
	
   public ReallyLongHex subtract(ReallyLongHex rightOp)
   {	
      return rightOp;
   }

   public int compareTo(ReallyLongHex rOp)
   {
      return 0;
   }

   public boolean equals(Object rightOp)
   {
      return false;
   }

   public void mult16ToThe(int num)
   {
   }

   public void div16ToThe(int num)
   {
   }
}
