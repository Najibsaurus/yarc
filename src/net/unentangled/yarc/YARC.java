package net.unentangled.yarc;

/**
 * Interface for ArabicToRoman and RomanToArabic classes
 *
 *@author    <a href="mailto:mebadran@gmail.com">Mohammed Badran</a>
 *@version   1.2, 28/10/2005
 */
public interface YARC {
   // minimum allowable arabic value
   int MIN_ARABIC = 1;

   // maximum allowable arabic value
   int MAX_ARABIC = 4999;

   // minimum allowable roman value
   String MIN_ROMAN = "I";

   // maximum allowable roman value (4999)
   String MAX_ROMAN = "MMMMCMXCIX";

   // maximum allowable roman value + 1
   String LIMIT_ROMAN = "MMMMM";

   // legal roman digits
   String ROMAN_DIGITS = "MDCLXVI";

   // digits within roman numeral levels
   String THOUS_LOW = "M";
   String HUNDS_LOW = "C";
   String HUNDS_MIDDLE = "D";
   String HUNDS_HIGH = "M";
   String TENS_LOW = "X";
   String TENS_MIDDLE = "L";
   String TENS_HIGH = "C";
   String ONES_LOW = "I";
   String ONES_MIDDLE = "V";
   String ONES_HIGH = "X";

   // multipliers for numeral levels
   int THOUS_MULTIPLIER = 1000;
   int HUNDS_MULTIPLIER = 100;
   int TENS_MULTIPLIER = 10;
   int ONES_MULTIPLIER = 1;
}
