// CS 0445 Spring 2018
// Simple version of QuickSort.  This is similar to the version in the Carrano
// text, except that it uses A[last] as the pivot and partitions based on that
// value.  Compare this to the version of Quicksort in the text (I have copied
// it into the file TextMergeQuick.java)

public class Quick
{
   private static long comparisons;
   private static long totalComparisons;
   private static long dataMoves;
   private static long totalDataMoves;
   
   public Quick()
   {
      comparisons = 0;
      totalComparisons = 0;
      dataMoves = 0;
      totalDataMoves = 0;
   }
   
   public static <T extends Comparable<? super T>>
   	   void quickSort(T[] array, int n)
   {
      startStatistics();
      quickSort(array, 0, n-1);
      endStatistics();
   } // end quickSort

   public static <T extends Comparable<? super T>>
   	   void quickSort(T[] array, int first, int last)
   {
      if (first < last)
      {
         comparisons++;
         // create the partition: Smaller | Pivot | Larger
         int pivotIndex = partition(array, first, last);
         
      	// sort subarrays Smaller and Larger
         quickSort(array, first, pivotIndex-1);
         quickSort(array, pivotIndex+1, last);
      } // end if
   }  // end quickSort

   private static <T extends Comparable<? super T>>
           int partition(T[] a, int first, int last)
   {
      int pivotIndex = last;  // simply pick pivot as rightmost element
      T pivot = a[pivotIndex];
   
   	// determine subarrays Smaller = a[first..endSmaller]
   	//                 and Larger  = a[endSmaller+1..last-1]
   	// such that elements in Smaller are <= pivot and 
   	// elements in Larger are >= pivot; initially, these subarrays are empty
   
      int indexFromLeft = first; 
      int indexFromRight = last - 1; 
   
      boolean done = false;
      while (!done)
      {
      	// starting at beginning of array, leave elements that are < pivot; 
      	// locate first element that is >= pivot
         while (a[indexFromLeft].compareTo(pivot) < 0)
         {
            comparisons++;
         
            indexFromLeft++;
         }
      	// starting at end of array, leave elements that are > pivot; 
      	// locate first element that is <= pivot
      
         while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
         {
            comparisons++;
            indexFromRight--;
         }
      
      	// Assertion: a[indexFromLeft] >= pivot and 
      	//            a[indexFromRight] <= pivot.
      
         if (indexFromLeft < indexFromRight)
         {
            comparisons++;
            swap(a, indexFromLeft, indexFromRight);
            
            indexFromLeft++;
            indexFromRight--;
         }
         else 
            done = true;
      } // end while
   
   	// place pivot between Smaller and Larger subarrays
      swap(a, pivotIndex, indexFromLeft);
      
      pivotIndex = indexFromLeft;
   
   	// Assertion:
   	// Smaller = a[first..pivotIndex-1]
   	// Pivot = a[pivotIndex]
   	// Larger  = a[pivotIndex + 1..last]
   
      return pivotIndex; 
   }  // end partition

   private static void swap(Object [] a, int i, int j)
   {
      dataMoves += 3;
      Object temp = a[i];
      a[i] = a[j];
      a[j] = temp; 
   } // end swap
   
   
   
   public static long getComparisons()
   {
      return comparisons;
   
   }
	
   public static long getTotalComparisons()
   {
      return totalComparisons;
   
   }
	
   public static long getDataMoves()
   {
      return dataMoves;
   
   }
	
   public static long getTotalDataMoves()
   {
      return totalDataMoves;
   
   }
	
   private static void startStatistics()
   {
      comparisons = 0;
      dataMoves = 0;
   }
	
   private static void endStatistics()
   {
      totalComparisons += comparisons;
      totalDataMoves += dataMoves;
   }
}