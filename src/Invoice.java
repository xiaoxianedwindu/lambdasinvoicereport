public class Invoice 
{
   private final int partNumber; 
   private final String partDescription;
   private int quantity;
   private double price;

   // constructor
   public Invoice(int partNumber, String partDescription, int quantity,
      double price)
   {
      if (quantity < 0) // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");

      if (price < 0.0) // validate price
         throw new IllegalArgumentException(
            "Price per item must be >= 0");

      this.partNumber = partNumber;
      this.partDescription = partDescription;
      this.quantity = quantity;
      this.price = price;
   } // end constructor

   // get part number
   public int getPartNumber()
   {
      return partNumber; // should validate
   } 

   // get description
   public String getPartDescription()
   {
      return partDescription;
   } 

   // set quantity
   public void setQuantity(int quantity)
   {
      if (quantity < 0) // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");

      this.quantity = quantity;
   } 

   // get quantity
   public int getQuantity()
   {
      return quantity;
   }

   // set price per item
   public void setPrice(double price)
   {
      if (price < 0.0) // validate price
         throw new IllegalArgumentException(
            "Price per item must be >= 0");

      this.price = price;
   } 

   // get price per item
   public double getPrice()
   {
      return price;
   } 
   
   //get total for item
   public double getTotal() {
	   return price * quantity;
   }
   
   

   // return String representation of Invoice object
   @Override
   public String toString()
   {
      return String.format(
         "Part #: %-2d  Description: %-15s  Quantity: %-4d  Price: $%,6.2f", 
         getPartNumber(), getPartDescription(), 
         getQuantity(), getPrice());
   } 
} // end class Invoice



/**************************************************************************
 * (C) Copyright 1992-2015 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
