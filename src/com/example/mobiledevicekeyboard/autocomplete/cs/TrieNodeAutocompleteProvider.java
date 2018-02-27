package com.example.mobiledevicekeyboard.autocomplete.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.Candidate;

public class TrieNodeAutocompleteProvider implements AutocompleteProvider {

	private final String WORD_DELIMITER = "\\s";
	private final String REMOVED_CHAR_REGEX = "[^\\w^\\-^ ]";
	private TrieNodeCandidate root;
	
	public TrieNodeAutocompleteProvider() {
		root = new TrieNodeCandidate("");
	}
	
	@Override
	public List<Candidate> getWords(String Fragment) {
		List<Candidate> retval = new ArrayList<Candidate>();

		//keep case consistent since case doesn't change the word
		Fragment = Fragment.toLowerCase();
		//start by finding the correct node to start searching for options in
		//if no such node exists, then there are no candidates, and return the empty list
		TrieNodeCandidate startNode = root.getStartNode(Fragment);
		if(startNode == null) return retval;
		
		//any nodes under the start node that have a confidence (occurrence > 0) is
		//a possibility, get all of them
		PriorityQueue<Candidate> candidates = new PriorityQueue<>(new CandidateComparator());
		startNode.getCandidates(candidates);
		//priority queue puts thing in order by their confidence
		//but ultimately we want a list - so convert to the list
		while(!candidates.isEmpty())
			retval.add(candidates.poll());
		return retval;
	}

	@Override
	public void train(String Passage) {
		//keep case consistent since case doesn't change the word
		Passage = Passage.toLowerCase();
		//remove punctuation since that's not part of the word
		//TODO what about hyphenated words
		Passage = Passage.replaceAll(REMOVED_CHAR_REGEX,  "");
		for(String word : Passage.split(WORD_DELIMITER)) {
			root.addRemainingFragment(word);
		}
	}

}
