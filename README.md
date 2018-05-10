# Levenshtein-sequences

Implement a program in Java to list all possible Levenshtein (i.e., edit distance) sequences from one string to another with optimal number of inserts/deletes/replaces and also handle various error handling .

## Input Example:
paris
alice

## Output Example:
There are total of two sequences:
1) paris delete p -> aris replace r by l -> alis replace s by c -> alic insert e -> alice
2) paris delete p -> aris replace r by l -> alis insert c -> alics replace s by e -> alice

## Methodology

Used backtracking of the path for finding all the solutions of levenshtein sequence.
* Pose this problem as a DFS problem and try to create a path from root to leaf and store the path in an Arraylist and then store all paths using another arraylist. 
* Use a recursive method to visit all the nodes until desired node is reached and measure the distance between the desired leaf and the root and show the path from the leaf to the node.
