import java.util.Random;

public class MultiDS<T> implements PrimQ<T>, Reorder
{
   private final T[] items;
   private int numOfItems;
   private static final int DEFAULT_CAPACITY = 10;
   private boolean initialized = false;
    
   private static final int MAX_CAPACITY = 100;
    
    
    /**
        Default Constructor. Creates a MultiDS with the default size of 10
    */
   public MultiDS()
   {
      this(DEFAULT_CAPACITY);
   }
    
   /**
       Constructor
       @param size the capacity of the MultiDS
   */
   public MultiDS(int size)
   {
      if(size <= MAX_CAPACITY)
      {
         @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[size]; // Unchecked cast
         items = tempBag;
         numOfItems = 0;
         initialized = true;
      }
      else
      {
         throw new IllegalStateException("Attempted to create MultiDS exceeding maximum capacity.");
      }
         
   }
   
   /**
        adds an item to the MultiDS.
        @param item object being added
        @return returns true if the item was successfully added, false otherwise
   */
   public boolean addItem(T item)
   {
      if(full()) {
         return false;
      } 
      else {
         items[numOfItems] = item;
         numOfItems++;
         return true;
      }
   }
   
   /**
       Removes the top item from the MultiDS
       @return returns the object being removed
   */
   public T removeItem()
   {
      if(numOfItems > 0)
      {
         T item = items[0];
         for(int loop = 1; loop < numOfItems; loop++)
         {
            items[loop - 1] = items[loop];
         }
         numOfItems--;
         return item;
      }
      else
      {
         return null;
      }
      
   }
   /**
        Returns the top object in the MultiDS
        @return The top object 
   */
   public T top()
   {
      if(numOfItems > 0)
      {
         return items[0];
      }
      else
      {
         return null;
      }
   }
   
   /**
       returns the newest item in the MultiDS
       @return newest item
   */
   public T bottom()
   {
      if(numOfItems > 0)
      {
         return items[numOfItems - 1];
      }
      else
      {
         return null;
      }
       
   }
		
   /**
       determines if the MultiDS is at capacity
       @return true if the MultiDS is full, false otherwise
   */
   public boolean full()
   {
      if(numOfItems < items.length)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
	/**
       determines if the MultiDS is empty
       @returns true if empty, false otherwise
   */
   public boolean empty()
   {
      if(numOfItems == 0)
      {
         return true;
      }
      return false;
   }
	
   /**
       Determines the current size of the MultiDS
       @return number of objects in the MultiDS
   */
   public int size()
   {
      return numOfItems;
   }
    /**
        Removes all objects from the MultiDS
    */
   public void clear()
   {
      for(int iter = 0; iter < numOfItems; iter++)
      {
         items[iter] = null;
      }
      numOfItems = 0;
   }
   /**
       Reverses the order of all objects in the multiDS
   */
   public void reverse()
   {
      @SuppressWarnings("unchecked")
         T[] tempBag = (T[]) new Object[items.length];
      for(int counter = 0; counter < numOfItems; counter++)
      {
         tempBag[counter] = items[numOfItems - 1 - counter];
      }
      for(int count = 0; count < numOfItems; count++)
      {
         items[count] = tempBag[count];
      }
   }
    /**
         moves every object in the MultiDS one place to the right,
         with the last one wrapping around to become the first
    */
   public void shiftRight()
   {
      @SuppressWarnings("unchecked")
         T[] tempBag = (T[]) new Object[items.length];
      tempBag[0] = items[numOfItems - 1];
      for(int counter = 1; counter < numOfItems; counter++)
      {
         tempBag[counter] = items[counter - 1];
      }
      for(int count = 0; count < numOfItems; count++)
      {
         items[count] = tempBag[count];
      }
   }
   /**
         moves every object in the MultiDS one place to the left,
         with the first one wrapping around to become the last
    */

   public void shiftLeft()
   {
      @SuppressWarnings("unchecked")
         T[] tempBag = (T[]) new Object[items.length];
      tempBag[numOfItems - 1] = items[0];
      for(int counter = 0; counter < numOfItems - 1; counter++)
      {
         tempBag[counter] = items[counter + 1];
      }
      for(int count = 0; count < numOfItems; count++)
      {
         items[count] = tempBag[count];
      }
   
   }
   /**
       Returns all objects in the MultiDS as a string
   */
   public String toString()
   {
      String itemString = "";
      for(int itemList = 0; itemList < numOfItems; itemList++)
      {
         itemString += items[itemList].toString() + " ";
      }
      return itemString;
      
   }
	/**
       Shuffles all items in the MultiDS at random
   */
   public void shuffle()
   {
      @SuppressWarnings("unchecked")
         T[] tempBag = (T[]) new Object[items.length];
      
      int[] usedSlots = new int[numOfItems];
      Random rng = new Random();
      boolean completed = false;
      int index = 0;
      while(!completed)
      {
         boolean used = false;
         int slotToUse = rng.nextInt(numOfItems);
         if(index < numOfItems)
         {
            for(int num = 0; num < index; num++)
            {
            
               if(usedSlots[num] == slotToUse)
               {
                  used = true;
               }
            }
            if(!used)
            {
               tempBag[slotToUse] = items[index];
               usedSlots[index] = slotToUse;
               index++;
            }
         }
         else
         {
            completed = true;
         }
          
      }
      for(int count = 0; count < numOfItems; count++)
      {
         items[count] = tempBag[count];
      }
      
   }
   
   
}