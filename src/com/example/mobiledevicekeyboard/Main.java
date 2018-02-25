package com.example.mobiledevicekeyboard;

import java.util.List;

import com.example.mobiledevicekeyboard.autocomplete.Candidate;
import com.example.mobiledevicekeyboard.autocomplete.cs.TrieNodeAutocompleteProvider;

public class Main {

	public static void main(String[] args) {
		TrieNodeAutocompleteProvider autoc = new TrieNodeAutocompleteProvider();
		
		//provided example
		autoc.train("The third thing that I need to tell you is that this thing does not think thoroughly.");

		printCandidateList(autoc.getWords("thi"));
		printCandidateList(autoc.getWords("nee"));
		printCandidateList(autoc.getWords("th"));
	}
	
	public static void printCandidateList(List<Candidate> candidates) {
		for(Candidate c : candidates) {
			System.out.printf("%s (%d)%n", c.getWord(), c.getConfidence());
		}
		System.out.println("___________");
	}

}
