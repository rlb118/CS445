public class As1Test
{
   public static void main(String[] args)
   {
      LinkedDS<Integer> test = new LinkedDS<Integer>();
      
      for(int i = 0; i < 10; i++)
      {
         test.addItem(i);
      }
      
      System.out.println(test.toString());
      
      
      test.reverse();
      System.out.println();
      System.out.println(test.toString());
      test.removeItem();
      System.out.println(test.toString());
      
      test.leftShift(3);
      System.out.println(test.toString());
      
      test.rightShift(3);
      System.out.println(test.toString());
      
      test.addItem(2);
      test.addItem(1);
      test.addItem(0);
      
      System.out.println(test.toString());
      
      
      test.shiftRight();
      
      System.out.println(test.toString());
      
      test.shiftLeft();
      
      System.out.println(test.toString());
      
      test.rightRotate(4);
      
      System.out.println(test.toString());
      
      
      test.leftRotate(4);
      
      System.out.println(test.toString());
      
      test.removeItem();
      System.out.println(test.toString());
      
   }
}