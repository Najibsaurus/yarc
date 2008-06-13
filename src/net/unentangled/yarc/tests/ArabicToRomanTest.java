package net.unentangled.yarc.tests;

import junit.framework.TestCase;
import au.id.badran.yarc.ArabicToRoman;

public class ArabicToRomanTest extends TestCase
{
   private ArabicToRoman converter;

   /**
    * sets up the test fixture before every test case method
    */
   protected void setUp()
   {
      converter = new ArabicToRoman();
   }

   /**
    * tears down the test fixture after every test case method
    */
   protected void tearDown()
   {
      converter = null;
   }

   /**
    * tests if an arabic numeral in the ones is converted successfully
    */
   public void testOnes() throws NumberFormatException
   {
      assertEquals(converter.getRoman(6), "VI");
   }

   /**
    * tests if an arabic numeral in the tens is converted successfully
    */
   public void testTens() throws NumberFormatException
   {
      assertEquals(converter.getRoman(63), "LXIII");
   }

   /**
    * tests if an arabic numeral in the hundreds is converted successfully
    */
   public void testHundreds() throws NumberFormatException
   {
      assertEquals(converter.getRoman(575), "DLXXV");
   }

   /**
    * tests if an arabic numeral in the thousands is converted successfully
    */
   public void testThousands() throws NumberFormatException
   {
      assertEquals(converter.getRoman(2005), "MMV");
   }

   /**
    * tests the minimum allowable input
    */
   public void testMin() throws NumberFormatException
   {
      try
      {
         converter.getRoman(-1);
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException nfe)
      {
         // pass
      }

      try
      {
         converter.getRoman(0);
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException nfe)
      {
         // pass
      }

      converter.getRoman(1);
   }

   /**
    * tests the maximum allowable input
    */
   public void testMax() throws NumberFormatException
   {
      converter.getRoman(4999);

      try
      {
         converter.getRoman(5000);
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException nfe)
      {
         // pass
      }
   }
}
