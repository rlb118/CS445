

public class LinkedDS<T> implements PrimQ<T>, Reorder
{
     
   protected class Node
   {
      private T data;
      private Node next;
      
           
      protected Node(T stuff, Node nextNode)
      {
         data = stuff;
         next = nextNode;
      }
           
      protected Node(T stuff)
      {
         this(stuff,null);                
      }
           
      protected T getData()
      {
         return data;
      }
           
      protected void setData(T stuff)
      {
         data = stuff; 
      }
           
      protected Node getNextNode()
      {
         return next;
      }
           
      protected void setNext(Node nextNode)
      {
         next = nextNode;
      }
           
   }
      
   protected Node firstNode;
   protected int numOfEntries;
   
      
   public LinkedDS()
   {
      firstNode = null;
      numOfEntries = 0;
   }
   public LinkedDS(LinkedDS oldList)
   {
      if(oldList == null)
      {
         throw new NullPointerException("FUCK ME BITCH");
         
      }
      
      for(int loop = 0; loop < oldList.size(); loop++)
      {
         Node holder = oldList.firstNode;
         for(int lo = 0; lo < loop; lo++)
         {
            holder = holder.getNextNode();
         }
         if(firstNode != null)
         {
            firstNode = new Node((T)holder.getData(), firstNode);
         }
         else
         {
            firstNode = new Node((T)holder.getData());
         }
      }
      reverse();
      numOfEntries = oldList.size();
   }
      
   // Add a new Object to end of the PrimQ<T>.  If
	// all goes well, return true.  
   public boolean addItem(T newEntry)
   {
      firstNode = new Node(newEntry, firstNode);
      numOfEntries++;
      return true;
   }

	// Remove and return the "oldest" item in the PrimQ.  If the PrimQ
	// is empty, return null.
   public T removeItem()
   {
      Node oldNode = firstNode;
      Node secOldNode = firstNode;
      if(empty())
      {
         return null;
      }
      
      while(oldNode.getNextNode() != null)
      {
         oldNode = oldNode.getNextNode();
      }
      if(secOldNode.getNextNode() != null)
      {
         while(secOldNode.getNextNode() != oldNode)
         {
            secOldNode = secOldNode.getNextNode();
         }
         secOldNode.setNext(null);
      }
      if(oldNode == firstNode)
      {
         firstNode = null;
      }
      numOfEntries--;
      return oldNode.getData();
        
   }
		
	// Return true if the PrimQ is empty, and false otherwise
   public boolean empty()
   {
      return firstNode == null;
      
   }
	
	// Return the number of items currently in the PrimQ
   public int size()
   {
      return numOfEntries;
   }

	// Reset the PrimQ to empty status by reinitializing the variables
	// appropriately
   public void clear()
   {
      firstNode = null;
   }
     
      // Logically reverse the data in the Reorder object so that the item
	// that was logically first will now be logically last and vice
	// versa.  The physical implementation of this can be done in 
	// many different ways, depending upon how you actually implemented
	// your physical LinkedDS<T> class
   public void reverse()
   {
      Node preNode = null;
      Node curNode = firstNode;
      Node nextNode = null;
      
      while(curNode != null)
      {
         nextNode = curNode.getNextNode();
         curNode.setNext(preNode);
         preNode = curNode;
         curNode = nextNode; 
         
      }
      firstNode = preNode;
      
   }

	// Remove the logical last item of the DS and put it at the 
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation.  
   public void shiftRight()
   {
      rightRotate(1);
        
   }

	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
   public void shiftLeft()
   {
      leftRotate(1);
   }
	
	// Shift the contents of the DS num places to the left (assume the beginning 
	// is the leftmost node), removing the leftmost num nodes.  For example, if 
	// a list has 8 nodes in it (numbered from 1 to 8), a leftShift of 3 would 
	// shift out nodes 1, 2 and 3 and the old node 4 would now be node 1.  
	// If num <= 0 leftShift should do nothing and if num >= the length of the 
	// list, the result should be an empty list.
   public void leftShift(int num)
   {
      reverse();
      rightShift(num);
      reverse();
        
   }
	
	// Same idea as leftShift above, but in the opposite direction.  For example, 
	// if a list has 8 nodes in it (numbered from 1 to 8) a rightShift of 3 would 
	// shift out nodes 8, 7 and 6 and the old node 5 would now be the last node
	// in the list.  If num <= 0 rightShift should do nothing and if num >= the 
	// length of the list, the result should be an empty list.
   public void rightShift(int num)
   {
      if(num <= 0)
      {
         return;
      }
      if(num >= size())
      {
         clear();
         return;
      }
      for(int loop = 0; loop < num; loop++)
      {
         removeItem();
      }
   
   }
	
	// In this method you will still shift the contents of the list num places to 
	// the left, but rather than removing nodes from the list you will simply change 
	// their ordering in a cyclic way.  For example, if a list has 8 nodes in it 
	// numbered from 1 to 8), a leftRotate of 3 would shift nodes 1, 2 and 3 to the
	// end of the list, so that the old node 4 would now be node 1, and the old nodes 
	// 1, 2 and 3 would now be nodes 6, 7 and 8 (in that order).  The rotation should 
	// work modulo the length of the list, so, for example, if the list is length 8 then
	// a leftRotate of 10 should be equivalent to a leftRotate of 2.  If num < 0, the 
	// rotation should still be done but it will in fact be a right rotation rather than
	// a left rotation.
   public void leftRotate(int num)
   {
   
      rightRotate(size() - num);
   
   }

	// Same idea as leftRotate above, but in the opposite direction.  For example, if a list 
	// has 8 nodes in it (numbered from 1 to 8), a rightRotate of 3 would shift nodes 8, 7 and 
	// 6 to the beginning of the list, so that the old node 8 would now be node 3, the old node 
	// 7 would now be node 2 and the old node 6 would now be node 1.  The behavior for num > the 
	// length of the list and for num < 0 should be analogous to that described above for leftRotate.
   public void rightRotate(int num)
   {
      Node currentNode = firstNode;
      Node numNode;
        
      if(num < 0)
      {
         leftRotate(num);
      }
      while(num > size())
      {
         num -= size();
      }
      for(int count = 1; count < num; count++)
      {
         currentNode = currentNode.getNextNode();
         
      }
      numNode = currentNode;
      
      while(currentNode.getNextNode() != null)
      {
         currentNode = currentNode.getNextNode();
      }
      
      currentNode.setNext(firstNode);
      
      firstNode = numNode.getNextNode();
      
      numNode.setNext(null);
      
   }
   
   
   public String toString()
   {
      String output = "";
      Node holdNode = firstNode;
      while(holdNode != null)
      {
         output += holdNode.getData().toString() + " ";
         holdNode = holdNode.getNextNode();
      }
      return output;
   }
   
   

}