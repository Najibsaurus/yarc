package net.unentangled.yarc;

/**
 * Converts Roman numerals to their Arabic integer equivalents.
 * - Numerals are not case-sensitive.
 * - Numerals must be within the range of I to MAX_VALUE.
 * - Numerals are only valid if they adhere to modern conventions
 *   or three popular variations: IIII for IV (4), XXXX for XL (40),
 *   and CCCC for CD (400)
 *
 *@author    <a href="mailto:mebadran@gmail.com">Mohammed Badran</a>
 *@version   1.2, 28/10/2005
 */
public class RomanToArabic implements YARC
{
   private int arabic;  // resulting arabic numeral
   private int index;   // current index in the roman numeral

   /**
    * Class constructor
    */
   public RomanToArabic()
   {
      index = 0;
      arabic = 0;
   }

   /**
    * Conversion manager
    *
    *@param subject  Roman numeral to be converted
    *@return         Arabic integer equivalent
    *@throws         NumberFormatException   If Roman numeral is invalid
    *                or exceeds MAX_VALUE
    */
   public int getArabic(String subject)
   {
      String subjectUpper = subject.toUpperCase();

      // partial error checking follows
      // (we don't know whether the numeral is malformed
      // until we pass it through the algorithm)

      // check for illegal digits
      if (!subjectUpper.matches("^[" + ROMAN_DIGITS + "]+$"))
      {
         throw new NumberFormatException(
            "Roman numeral contains illegal digits");
      }

      // check for invalid range
      if (subjectUpper.startsWith(LIMIT_ROMAN))
      {
         throw new NumberFormatException(
            "Roman numeral must be between " + MIN_ROMAN +
            " and " + MAX_ROMAN);
      }

      return convert(subjectUpper);
   }

   /**
    * Conversion algorithm - part 1
    * Converts the thousands and passes along the levels
    * remaining to be converted
    *
    *@param subject  Roman numeral to be converted
    *@return         Arabic integer equivalent
    *@throws         NumberFormatException   If Roman numeral is malformed
    */
   private int convert(String roman)
   {
      String levelStart = null;

      /*
        basically, we traverse through the string, converting
        the levels in order as we go, and keeping track of
        our position at any given time
      */

      /*
        go through each level (thousands, hundreds, tens, and ones) and
        check if there's a valid roman numeral that starts that level, in
        which case, proceed to calculate the sum of that level
      */

      /*
        convert the thousands first, add to the total so far,
        and update the position index
      */
      do
      {
         try
         {
            levelStart = roman.substring(index, index + 1);

            if (levelStart.equals(THOUS_LOW))
            {
               arabic += THOUS_MULTIPLIER;
               index++;
            }
         }
         catch (StringIndexOutOfBoundsException ignored) {}
      }
      while (index < roman.length() && levelStart.equals(THOUS_LOW));

      // now convert the hundreds
      try
      {
         levelStart = roman.substring(index, index + 1);
         if (levelStart.equals(HUNDS_LOW) || levelStart.equals(HUNDS_MIDDLE))
         {
            calculateLevel(roman,
                           HUNDS_LOW, HUNDS_MIDDLE, HUNDS_HIGH,
                           HUNDS_MULTIPLIER);
         }
      }
      catch (StringIndexOutOfBoundsException ignored) {}

      // convert the tens
      try
      {
         levelStart = roman.substring(index, index + 1);
         if (levelStart.equals(TENS_LOW) || levelStart.equals(TENS_MIDDLE))
         {
            calculateLevel(roman, TENS_LOW, TENS_MIDDLE, TENS_HIGH,
                           TENS_MULTIPLIER);
         }
      }
      catch (StringIndexOutOfBoundsException ignored) {}

      // finally, convert the ones
      try
      {
         levelStart = roman.substring(index, index + 1);
         if (levelStart.equals(ONES_LOW) ||levelStart.equals(ONES_MIDDLE))
         {
            calculateLevel(roman,
                           ONES_LOW, ONES_MIDDLE, ONES_HIGH,
                           ONES_MULTIPLIER);
         }
      }
      catch (StringIndexOutOfBoundsException ignored) {}

      // if we have not reached the end of the string by now,
      // the numeral is malformed (the levels are mixed up)
      if (index != roman.length())
      {
         throw new NumberFormatException("Roman numeral is malformed");
      }

      // otherwise the conversion is successful
      return arabic;
   }

   /**
    * Conversion algorithm - part 2
    * Calculates the level's integer equivalent with the formula that
    * corresponds to the level's format, updates the position index,
    * and adds the result to the total
    *
    *@param roman       Char array of the Roman numeral
    *@param low         Low Roman numeral for the level
    *@param middle      Middle Roman numeral for the level
    *@param high        High Roman numeral for the level
    *@param multiplier  Multiplier for the level
    */
   private void calculateLevel(String roman,
                               String low, String middle, String high,
                               int multiplier)
   {
      // first check for the low-low-low-low format, a
      // popular alternative to the low-middle standard
      if (roman.substring(index).startsWith(low + low + low + low))
      {
         arabic += 4 * multiplier;
         index += 4;    // skip 4 digits
      }

      // now check for the remaining formats

      else if (roman.substring(index).startsWith(low + low + low))
      {
         arabic += 3 * multiplier;
         index += 3;    // skip three digits
      }

      else if (roman.substring(index).startsWith(low + low))
      {
         arabic += 2 * multiplier;
         index += 2;    // skip 2 digits
      }

      else if (roman.substring(index).startsWith(low + middle))
      {
         arabic += (5 * multiplier) - multiplier;
         index += 2;    // skip 2 digits
      }

      else if (roman.substring(index).startsWith(low + high))
      {
         arabic += (10 * multiplier) - multiplier;
         index += 2;    // skip 2 digits
      }

      else if (roman.substring(index).startsWith(low))
      {
         arabic += multiplier;
         index++;       // skip 1 digit
      }

      else if (roman.substring(index).startsWith(middle + low + low + low))
      {
         arabic += (5 * multiplier) + (3 * multiplier);
         index += 4;    // skip 4 digits
      }

      else if (roman.substring(index).startsWith(middle + low + low))
      {
         arabic += (5 * multiplier) + (2 * multiplier);
         index += 3;    // skip 3 digits
      }

      else if (roman.substring(index).startsWith(middle + low))
      {
         arabic += (5 * multiplier) + multiplier;
         index += 2;    // skip 2 digits
      }

      else if (roman.substring(index).startsWith(middle))
      {
         arabic += 5 * multiplier;
         index++;    // skip 1 digit
      }
   }
}
