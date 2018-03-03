package com.example.mobiledevicekeyboard;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.Candidate;
import com.example.mobiledevicekeyboard.autocomplete.cs.TrieNodeAutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.training.AlbuquerqueTrainingFile;
import com.example.mobiledevicekeyboard.autocomplete.training.AmericanPieTrainingFile;
import com.example.mobiledevicekeyboard.autocomplete.training.YellowSubmarineTrainingFile;

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
		
		//whitespace
		autoc.train("Hello\nThis is\ta test of the whitespace system.");
		
		printCandidateList("Hel", autoc);
		printCandidateList("i", autoc);
		printCandidateList("a", autoc);
		
		//debatable if this is a legit input, but it provides no suggestions
		//which is what was intended
		printCandidateList("is\ta", autoc);	
		
		(new Thread(new AmericanPieTrainingFile(autoc))).start();
		(new Thread(new YellowSubmarineTrainingFile(autoc))).start();
		(new Thread(new AlbuquerqueTrainingFile(autoc))).start();
		
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//don't care if we are interrupted because of quit
			}
			printCandidateList("th", autoc);
			printCandidateList("sing", autoc);
			printCandidateList("a", autoc);
		}
	}
	
	public static void printCandidateList(String Fragment, AutocompleteProvider a) {
		System.out.println("Candidates for " + Fragment);
		for(Candidate c : a.getWords(Fragment)) {
			System.out.printf("%s (%d)%n", c.getWord(), c.getConfidence());
		}
		System.out.println("___________");
	}

}
