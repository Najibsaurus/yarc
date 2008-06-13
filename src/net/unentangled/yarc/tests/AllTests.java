package net.unentangled.yarc.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
   /**
    * JUnit test suite for YARC
    *
    *@return   The test suite
    */
   public static Test suite()
   {
      TestSuite suite = new TestSuite();

      // Arabic to Roman tests
      suite.addTestSuite(ArabicToRomanTest.class);

      // Roman to Arabic tests
      suite.addTestSuite(RomanToArabicTest.class);

      return suite;
   }
}
