package net.unentangled.yarc.tests;

import junit.framework.TestCase;
import au.id.badran.yarc.RomanToArabic;

public class RomanToArabicTest extends TestCase
{
   private RomanToArabic converter;

   /**
    * sets up the test fixture before every test case method
    */
   protected void setUp()
   {
      converter = new RomanToArabic();
   }

   /**
    * tears down the test fixture after every test case method
    */
   protected void tearDown()
   {
      converter = null;
   }

   /**
    * tests if a roman numeral in the ones is converted successfully
    */
   public void testOnes() throws NumberFormatException
   {
      assertEquals(converter.getArabic("vi"), 6);
   }

   /**
    * tests if a roman numeral in the tens is converted successfully
    */
   public void testTens() throws NumberFormatException
   {
      assertEquals(converter.getArabic("lxiii"), 63);
   }

   /**
    * tests if a roman numeral in the hundreds is converted successfully
    */
   public void testHundreds() throws NumberFormatException
   {
      assertEquals(converter.getArabic("dlxxv"), 575);
   }

   /**
    * tests if a roman numeral in the thousands is converted successfully
    */
   public void testThousands() throws NumberFormatException
   {
      assertEquals(converter.getArabic("mmv"), 2005);
   }

   /**
    * tests if a roman numeral has illegal digits
    */
   public void testIllegal()
   {
      try
      {
         converter.getArabic("e");
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException ignored)
      {
         // pass
      }

      try
      {
         converter.getArabic("-i");
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException ignored)
      {
         // pass
      }

      try
      {
         converter.getArabic("i.i");
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException ignored)
      {
         // pass
      }
   }

   /**
    * tests if a roman numeral is malformed
    */
   public void testMalformed()
   {
      try
      {
         converter.getArabic("im");
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException ignored)
      {
         // pass
      }
   }

   /**
    * tests if a roman numeral in lower case is converted successfully
    */
   public void testLowerCase() throws NumberFormatException
   {
      assertEquals(converter.getArabic("mmmmcmxcix"), 4999);
   }

   /**
    * tests if a roman numeral in upper case is converted successfully
    */
   public void testUpperCase() throws NumberFormatException
   {
      assertEquals(converter.getArabic("MMMMCMXCIX"), 4999);
   }

   /**
    * tests if a roman numeral in mixed case is converted successfully
    */
   public void testMixedCase() throws NumberFormatException
   {
      assertEquals(converter.getArabic("MmMMcMxCix"), 4999);
   }

   /**
    * tests the minimum allowable input
    */
   public void testMin() throws NumberFormatException
   {
      converter.getArabic("i");
   }

   /**
    * tests the maximum allowable input
    */
   public void testMax() throws NumberFormatException
   {
      converter.getArabic("MMMMCMXCIX");

      try
      {
         converter.getArabic("MMMMM");
         fail("should raise a NumberFormatException");
      }
      catch (NumberFormatException ignored)
      {
         // pass
      }
   }
}
