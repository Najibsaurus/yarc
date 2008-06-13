package net.unentangled.yarc;

/**
 * Converts Arabic numerals to their Roman numeral equivalents.
 * The numeral must be a positive integer value between 1 and MAX_VALUE.
 * Roman numerals are represented as per modern conventions.
 *
 *@author    <a href="mailto:mebadran@gmail.com">Mohammed Badran</a>
 *@version   1.2, 28/10/2005
 */
public class ArabicToRoman implements YARC
{
   private StringBuffer roman;   // resulting roman numeral

   /**
    * Class constructor
    */
   public ArabicToRoman()
   {
      roman = new StringBuffer();
   }

   /**
    * Conversion manager
    *
    *@param subject  Arabic numeral subject to be converted
    *@return         Roman numeral equivalent
    *@throws         NumberFormatException   If numeral is not a positive
    *                Arabic integer value between 1 and MAX_VALUE
    */
   public String getRoman(int subject)
   {
      // check for invalid range
      if (subject < 1 || subject > MAX_ARABIC)
      {
         throw new NumberFormatException(
            "Arabic numeral must be between " + MIN_ARABIC +
            " and " + MAX_ARABIC);
      }

      convert(subject);

      return roman.toString();
   }

   /**
    * Conversion algorithm - part 1
    * Converts the thousands and passes along the levels
    * remaining to be converted
    *
    *@param arabic  Arabic integer to be converted
    */
   private void convert(int arabic)
   {
      // calculate the number of thousands, hundreds, tens and ones
      int thousands = arabic / THOUS_MULTIPLIER;
      int hundreds = (arabic % THOUS_MULTIPLIER) / HUNDS_MULTIPLIER;
      int tens = (arabic % HUNDS_MULTIPLIER) / TENS_MULTIPLIER;
      int ones = arabic % TENS_MULTIPLIER;

      // convert the thousands first
      while (thousands > 0)
      {
         roman.append(THOUS_LOW);
         thousands--;
      }

      // next, convert the remaining levels
      calculateLevel(hundreds, HUNDS_LOW, HUNDS_MIDDLE, HUNDS_HIGH);
      calculateLevel(tens, TENS_LOW, TENS_MIDDLE, TENS_HIGH);
      calculateLevel(ones, ONES_LOW, ONES_MIDDLE, ONES_HIGH);
   }

   /**
    * Conversion algorithm - part 2
    * Calculates the level's Roman value with the
    * formula that corresponds to the level's format
    *
    *@param num     Number of hundreds/tens/ones in the level
    *@param low     Low Roman numeral for the level
    *@param middle  Middle Roman numeral for the level
    *@param high    High Roman numeral for the level
    */
   private void calculateLevel(int num, String low, String middle, String high)
   {
      switch (num)
      {
         // 1-3 hundreds/tens/ones will be converted to the same amount
         // (1-3) of the low roman numeral for the level
         case 1:
         case 2:
         case 3:
            for (int i = num; i > 0; i--)
            {
               roman.append(low);
            }
            break;

         // 4 will be converted to a low and a middle for the level
         case 4:
            roman.append(low);
            roman.append(middle);
            break;

         // 5 will be converted to a middle
         case 5:
            roman.append(middle);
            break;

         // 6-8 will be converted to a middle and 1-3 lows
         case 6:
         case 7:
         case 8:
            roman.append(middle);
            for (int i = num; i > 5; i--)
            {
               roman.append(low);
            }
            break;

         // and 9 will be converted to a low and a high
         case 9:
            roman.append(low);
            roman.append(high);
            break;

         default:
            break;
      }
   }
}
