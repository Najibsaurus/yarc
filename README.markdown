About YARC
==========

  YARC is an acronym for Yet Another Roman [Numeral] Converter.

  YARC does one thing and does it well: converting numerals from Arabic to
  Roman and vice versa. It has a tiny footprint and an accurate conversion
  engine with decent algorithms.

  Any questions or comments to [Mohammed Badran][1].

[1]: mailto:mebadran@gmail.com

How YARC Works
==============

  YARC adheres to modern conventions for Roman numerals, with three
  additional variations: IIII for IV (4), XXXX for XL (40), and CCCC
  for CD (400).

  Valid numeral input ranges from 1/I to 4999/MMMMMCMXCIX, due to a
  limitation in the Roman numeral system. Workarounds are unnecessary,
  as large values are seldom used today.

  Read more about [Roman numerals][2] on [Wikipedia][3].

[2]: http://en.wikipedia.org/wiki/Roman_numerals
[3]: http://en.wikipedia.org/wiki/Main_Page

How to Use YARC
===============

  - Run the YARC driver from the command line:

         % java -jar yarc.jar [numeral]

     The numeral can be either Arabic or Roman, the driver will
     automatically detect its type.

  - Import and use the YARC library in your Java program:

     1. Ensure `yarc.jar` is included in your classpath.

     2. `import net.unentangled.*;`

     3. Generate javadocs for API information (optional).
