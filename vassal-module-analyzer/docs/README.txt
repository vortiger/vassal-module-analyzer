The vassal-module-analyzer project contains some tools for analyzing VASSAL modules.

It is an open-source project available at http://code.google.com/p/vassal-module-analyzer/

Here is how to run it.
1) Place your module in src/test/resources
2) Edit src/test/java/com/kenstevens/vassal/VassalModuleLoaderTest.java and change FILENAME to point to your module
3) Run src/test/java/com/kenstevens/vassal/PieceVisitorTest as a junit test.  This will produce
   a file named all_pieces.txt that displays the entire module code in a human-readable,
   searchable format.
4) Run src/test/java/com/kenstevens/vassal/FieldPairVisitorTest as a junit test.  This will produce
   a file named by_keys.txt that organizes all places in the code that send or listen for
   a key-stroke, organized by key-stroke.

Using all_pieces.txt combined with by_keys.txt, it is possible to trace through everything that
happens when a particular event is triggered.

For example, I used this to fix a bug in the Through the Ages Module that was undercharging civil actions
when players took a Wonder card.
