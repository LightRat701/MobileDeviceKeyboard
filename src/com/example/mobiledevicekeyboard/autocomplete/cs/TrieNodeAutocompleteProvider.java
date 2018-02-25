package com.example.mobiledevicekeyboard.autocomplete.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.Candidate;

public class TrieNodeAutocompleteProvider implements AutocompleteProvider {

	private final String WORD_DELIMITER = "\\s";
	private TrieNodeCandidate root;
	
	public TrieNodeAutocompleteProvider() {
		root = new TrieNodeCandidate("");
	}
	
	@Override
	public List<Candidate> getWords(String Fragment) {
		//start by finding the correct node to start searching for options in
		TrieNodeCandidate startNode = root.getStartNode(Fragment);
		//any nodes under the start node that have a confidence (occurrence > 0)
		//is a possibility, get them
		PriorityQueue<Candidate> retval = new PriorityQueue<>(new CandidateComparator());
		startNode.getCandidates(retval);
		//priority queue puts thing in order by their confidence
		//but ultimately we want a list - so convert to the list
		return new ArrayList<Candidate>(retval);
	}

	@Override
	public void train(String Passage) {
		//TODO Remove punctuation
		//TODO Make case consistent
		for(String word : Passage.split(WORD_DELIMITER)) {
			root.addRemainingFragment(word);
		}
	}

}
