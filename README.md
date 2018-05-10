# Levenshtein-sequences

Implement a program in Java to list all possible Levenshtein (i.e., edit) sequences from one string to another with optimal number of
inserts/deletes/replaces and also handle various error handling .

Input Example:
paris
alice

Output Example:
There are total of two sequences:
1) paris delete p -> aris replace r by l -> alis replace s by c -> alic insert e -> alice
2) paris delete p -> aris replace r by l -> alis insert c -> alics replace s by e -> alice
