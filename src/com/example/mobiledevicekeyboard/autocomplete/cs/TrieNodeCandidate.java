package com.example.mobiledevicekeyboard.autocomplete.cs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.example.mobiledevicekeyboard.autocomplete.Candidate;

public class TrieNodeCandidate implements Candidate {

	private final String fragment;
	private int occurrences;
	private Map<Character, TrieNodeCandidate> children;
	
	/**
	 * The node is built with the fragment of the word so far. Fragments and
	 * words are treated identically in case this does turn out to be a word
	 * someday (i.e. the user used a plural form that added 's' until now)
	 * @param Fragment The fragment of the word used up to this character.
	 */
	public TrieNodeCandidate(String Fragment) {
		fragment = Fragment;
		occurrences = 0;
		children = new HashMap<>();
	}
	
	/**
	 * Called to add the remainder of a word beneath the trie node representing
	 * this candidate. Recurses until nothing more of the word remains.
	 * @param Fragment The remainder of the word that is the suffix that 
	 * comes after the fragment represented by this node.
	 */
	public synchronized void addRemainingFragment(String Fragment) {
		//First check if there is nothing remaining, meaning the word was
		//represented by this candidate instance. If so, then increment
		//the occurrence count of this candidate so it's confidence is 
		//properly represented, then stop processing further.
		if(Fragment.length() == 0) {
			occurrences++;
			return;
		}
		//if there are more characters to process, then they are children
		//under this trie node. Continue down the trie.
		char selectedChild = Fragment.charAt(0);
		synchronized(children) {	//ensure we aren't reading and writing to children (or double writing to children)
			if(!children.containsKey(selectedChild))
				children.put(selectedChild, new TrieNodeCandidate(fragment + selectedChild));
			children.get(selectedChild).addRemainingFragment(Fragment.substring(1));
		}
	}
	
	/**
	 * In a search, this will find the node which any candidates will be under.
	 * @param Fragment The fragment of code the user has typed so far
	 * @return The candidate representing the fragment the user typed, 
	 * or null if the fragment does not exist.
	 */
	public TrieNodeCandidate getStartNode(String Fragment) {
		//if there's nothing more to the fragment, then this node is where
		//we start finding candidates from
		if(Fragment.length() == 0) return this;
		synchronized(children) {
			//If the trie doesn't have the next character in the fragment, then we have
			//nothing we can guess (no candidates) so return null
			if(!children.containsKey(Fragment.charAt(0))) return null;
			//otherwise continue down the trie
			return children.get(Fragment.charAt(0)).getStartNode(Fragment.substring(1));
		}
	}
	
	public void getCandidates(PriorityQueue<Candidate> PreviousCandidates) {
		//first see if this node is a candidate
		//if so, place it in the list of candidates
		if(occurrences > 0) PreviousCandidates.add(this);
		
		//recurse down all children so they can add themselves if necessary
		//and they can add their own children as well
		synchronized(children) {
			for(TrieNodeCandidate child : children.values()) {
				child.getCandidates(PreviousCandidates);
			}
		}
		
		//PreviousCandidates now has everything for this node
	}
	
	@Override
	public String getWord() {
		return fragment;
	}

	@Override
	public Integer getConfidence() {
		return occurrences;
	}

}
