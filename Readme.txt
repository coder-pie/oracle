#System requirements:
Java 7.

#Scrabbler Usage:
1. scrabbler <string> - list all permutations of string in dictionary, no duplicates
2. scrabbler prefix <string> - list all words with this prefix
3. scrabbler suffix <string> - list all words with this suffix

#Run instructions:
open terminal and run the following:
java -jar Scrabble.jar <args>


#example run:
java -jar Scrabble.jar dab
java -jar Scrabble.jar prefix dab
java -jar Scrabble.jar suffix dab


#Includes
readme.txt
3 simple unit tests to test each option.

# algorithm
Load the file in memory to avoid frequent file IO.
data structure used: Hashset- no duplictes, direct search access O(1).

Permute method takes the input string and iterate over each subset of the string.
jumble method is called by permute to for each of these subsets. 
This method shuffles each set using recursion call to itself and matches with words in dictionary.

prefix method converts to dictionary to treeset to get a reduced subset of the words
then iterates over this smaller subset to find matches. Uses flag to terminate.

Suffix method iterates over the dictionary to fix all words ending with give suffix.
