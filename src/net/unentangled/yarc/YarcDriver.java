package net.unentangled.yarc;

/**
 * A simple Arabic-Roman/Roman-Arabic command-line driver.
 *
 *@author    <a href="mailto:mebadran@gmail.com">Mohammed Badran</a>
 *@version   1.2, 28/10/2005
 */
public class YarcDriver
{
   /**
    * The main program for the class
    *
    *@param args  The command line arguments
    */
   public static void main(String[] args)
   {
      // first, see if first arg is an int
      try
      {
         int arabic = Integer.parseInt(args[0]);
         ArabicToRoman arabToRom = new ArabicToRoman();

         try
         {
            System.out.println(arabToRom.getRoman(arabic));
         }
         catch (NumberFormatException nfe)
         {
            System.err.println("Error while converting from Arabic to Roman:\n\n    " + nfe + "\n");
         }
      }
      // if not, treat it as a roman numeral
      catch (NumberFormatException nfe)
      {
         RomanToArabic romToArab = new RomanToArabic();

         try
         {
            System.out.println(romToArab.getArabic(args[0]));
         }
         catch (NumberFormatException nfe2)
         {
            System.err.println("Error while converting from Roman to Arabic:\n\n    " + nfe2 + "\n");
         }
      }
      // if no args, display usage message
      catch (ArrayIndexOutOfBoundsException aiobe)
      {
         System.err.println("Usage: java -jar yarc [numeral]");
      }
   }
}
