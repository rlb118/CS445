/*
    Robert Blake
    Assignment 4
    Main Program
*/

import java.util.*;
import java.io.*;


public class SortTesting
{
   private static PrintWriter testFile;
  
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      int arraySize;
      int numOfTrials;
      String fileName;
      
      
      System.out.println("How large should the arrays be?");
      arraySize = keyboard.nextInt();
      keyboard.nextLine();
      
      System.out.println("How many trials should be done?");
      numOfTrials = keyboard.nextInt();
      keyboard.nextLine();
      
      System.out.println("What is the name of the file you would like to output to?");
      fileName = keyboard.nextLine();
      
      File outputFile = new File(fileName);
      testFile = new PrintWriter(outputFile);
      
      sortingRuns("Random", arraySize, numOfTrials);
      sortingRuns("Sorted", arraySize, numOfTrials);
      sortingRuns("Reversed", arraySize, numOfTrials);
      
      
      
      testFile.close();
      
   }
   
   private static Integer[] arrayGenRan(int size)
   {
      Integer[] generated = new Integer[size];
      Random rnGenerator = new Random();
       
      for(int index = 0; index < size; index++)
      {
         generated[index] = new Integer(rnGenerator.nextInt(1000));
      }
       
      return generated;
       
       
   }
   private static Integer[] arrayGenRev(int size)
   {
      Integer[] generated = new Integer[size];
       
      for(int index = size; index > 0; index--)
      {
         generated[size - index] = new Integer(index);
      }
       
      return generated;
       
       
   }
   
   private static Integer[] arrayGenSort(int size)
   {
      Integer[] generated = new Integer[size];
       
      for(int index = 0; index < size; index++)
      {
         generated[index] = new Integer(index);
      }
       
      return generated;
       
       
   }

   
   public static void printArray(Integer[] arToPrint)
   {
      System.out.print("[");
      for(int count = 0; count < arToPrint.length; count++)
      {
         System.out.print(arToPrint[count].toString() + ", ");
      }
      System.out.println("]");
   }
   
   public static void sortingRuns(String mode, int arraySize, int numOfTrials)
   {
      Quick QS1 = new Quick();
      TextMergeQuick QS2 = new TextMergeQuick();   
      TextMergeQuick QS3 = new TextMergeQuick();
      TextMergeQuick QS4 = new TextMergeQuick();
      TextMergeQuick QS5 = new TextMergeQuick();
      TextMergeQuick QS6 = new TextMergeQuick();
      
      TextMergeQuick MS1 = new TextMergeQuick();
      TextMergeQuick MS2 = new TextMergeQuick();
      
      
      long totalQ1 = 0;
      long totalQ2 = 0;
      long totalQ3 = 0;
      long totalQ4 = 0;
      long totalQ5 = 0;
      long totalQ6 = 0;
      
      long totalM1 = 0;
      long totalM2 = 0;
      
      
      
      
      
      
      
      for(int testNum = 0; testNum < numOfTrials; testNum++)
      {
      
         Integer[] baseArray;
         if(mode.equals("Random"))
         {
            baseArray = arrayGenRan(arraySize);
         }
         else if(mode.equals("Sorted"))
         {
            baseArray = arrayGenSort(arraySize);
         }
         else
         {
            baseArray = arrayGenRev(arraySize);
         }
         
         Integer[] test1 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test2 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test3 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test4 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test5 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test6 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test7 = Arrays.copyOf(baseArray, arraySize);
         Integer[] test8 = Arrays.copyOf(baseArray, arraySize);
         
         
         
         
         
         long start = System.nanoTime();
         QS1.quickSort(test1, arraySize);
         long end = System.nanoTime();
         long elapsed = end - start;
         totalQ1 += elapsed;
         
         
         
         
         start = System.nanoTime();
      
         QS2.quickSort(test2, arraySize);
         end = System.nanoTime();
         elapsed = end - start;
         totalQ2 += elapsed;
         
         start = System.nanoTime();
      
         QS3.quickSortChangeBase(test3, 0, arraySize -1, 20);
         end = System.nanoTime();
         elapsed = end - start;
         totalQ3 += elapsed;
         
         start = System.nanoTime();
      
         QS4.quickSortChangeBase(test4, 0, arraySize -1, 100);
         end = System.nanoTime();
         elapsed = end - start;
         totalQ4 += elapsed;
         
         start = System.nanoTime();
      
         QS5.quickSortRandom(test5, 0, arraySize - 1);
         end = System.nanoTime();
         elapsed = end - start;
         totalQ5 += elapsed;
         
         start = System.nanoTime();
      
         QS6.quickSortIter(test5);
         end = System.nanoTime();
         elapsed = end - start;
         totalQ6 += elapsed;
         
         
         
         start = System.nanoTime();
      
         MS1.mergeSort(test7, arraySize);
         end = System.nanoTime();
         elapsed = end - start;
         totalM1 += elapsed;
         
         
         start = System.nanoTime();
      
         MS2.iterativeMergeSort(test8, arraySize);
         end = System.nanoTime();
         elapsed = end - start;
         totalM2 += elapsed;
         
         
         
         if(arraySize <= 20)
         {
            System.out.println("Algorithm: Simple Quick Sort");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test1);
            System.out.println();
            
            System.out.println("Algorithm: Quick Sort base case 5");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test2);
            System.out.println();
            
            System.out.println("Algorithm: Quick Sort base case 20");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test3);
            System.out.println();
            
            System.out.println("Algorithm: Quick Sort base case 100");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test4);
            System.out.println();
            
            System.out.println("Algorithm: Quick Sort Random Pivot");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test5);
            System.out.println();
            
            System.out.println("Algorithm: Quick Sort Iterative");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test6);
            System.out.println();
            
            System.out.println("Algorithm: Recursive Merge Sort");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test7);
            System.out.println();
            
            System.out.println("Algorithm: Recursive Merge Sort");
            System.out.println("Array Size: " + arraySize);
            System.out.println("Configuration: " + mode);
            System.out.print("Initial Data: ");
            printArray(baseArray);
            System.out.print("Data in array after sorting: ");
            printArray(test8);
            System.out.println();
            
         
         
            
         
         }
         
         
         
         
      
      }
      
      testFile.println("Algorithm: Simple Quick Sort");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Number of trials" + numOfTrials);
      testFile.println("Average Time: " + (double)totalQ1 /(double)numOfTrials/1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS1.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS1.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
      testFile.println("Algorithm: Quick Sort base case 5");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalQ2 /(double)numOfTrials/1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS2.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS2.getTotalDataMoves() / numOfTrials);
      testFile.println();
            
      testFile.println("Algorithm: Quick Sort base case 20");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalQ3 /(double)numOfTrials/1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS3.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS3.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
      testFile.println("Algorithm: Quick Sort base case 100");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalQ4 /(double)numOfTrials/1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS4.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS4.getTotalDataMoves() / numOfTrials);
      testFile.println();      
      
      testFile.println("Algorithm: Quick Sort Random Pivot");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalQ5 /(double)numOfTrials /1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS5.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS5.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
      testFile.println("Algorithm: Quick Sort Random Pivot");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalQ6 /(double)numOfTrials /1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + QS6.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + QS6.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
      
      
      testFile.println("Algorithm: Recursive Merge Sort");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalM1 /(double)numOfTrials /1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + MS1.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + MS1.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
      testFile.println("Algorithm: Iterative Merge Sort");
      testFile.println("Array Size: " + arraySize);
      testFile.println("Configuration: " + mode);
      testFile.println("Average Time: " + (double)totalM2 /(double)numOfTrials /1000000000 + " seconds");
      testFile.println("Average Number of Comparisons: " + MS2.getTotalComparisons() / numOfTrials);
      testFile.println("Average Number of Data Moves: " + MS2.getTotalDataMoves() / numOfTrials);
      testFile.println();
      
   }
   
   
}