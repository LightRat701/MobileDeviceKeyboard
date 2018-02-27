package com.example.mobiledevicekeyboard;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.Candidate;
import com.example.mobiledevicekeyboard.autocomplete.cs.TrieNodeAutocompleteProvider;

public class Main {

	public static void main(String[] args) {
		TrieNodeAutocompleteProvider autoc = new TrieNodeAutocompleteProvider();
		
		//provided example
		autoc.train("The third thing that I need to tell you is that this thing does not think thoroughly.");

		printCandidateList("thi", autoc);
		printCandidateList("nee", autoc);
		printCandidateList("th", autoc);
		
		//hyphen and numbers example
		autoc.train("I have 15 co-workers and 10 are in Colorado");
		
		printCandidateList("1", autoc);
		printCandidateList("co", autoc);
		printCandidateList("co-", autoc);
		
		//no autocomplete entries example
		printCandidateList("supercalafragalisticexpealadocious", autoc);
	}
	
	public static void printCandidateList(String Fragment, AutocompleteProvider a) {
		System.out.println("Candidates for " + Fragment);
		for(Candidate c : a.getWords(Fragment)) {
			System.out.printf("%s (%d)%n", c.getWord(), c.getConfidence());
		}
		System.out.println("___________");
	}

}
