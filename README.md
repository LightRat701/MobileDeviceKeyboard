This repository represents a work in progress. 
It has not been integrated into the main routine or tested.
This code is not yet intended to be functional, and is being committed for backup and history reasons only.




Question topics
Assuming removing punctuation
Should order of return value of AutocompleteProvider.getWords() must be sorted by confidence.
Can/should we cache last AutocompleteProvider.getWords() results to reduce the starting list - simply remove unnecessary iterations of traversing the trie - resets if a totally new word is started
How to handle hyphenated words
How to handle things that aren't letters (such as numbers)
How simultaneous are uses? 
	-Does training occur between uses (user writes passage using autocomplete, user commits passage, user writes next passage with updated autocomplete)
	-Does training possibly occur in separate thread from uses that we need synchronization