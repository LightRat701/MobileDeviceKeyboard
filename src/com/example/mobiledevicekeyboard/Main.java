package com.example.mobiledevicekeyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		
		//start asynchronous training threads to show how training can occur
		//simultaneously with requests
		(new Thread(new AmericanPieTrainingFile(autoc))).start();
		(new Thread(new YellowSubmarineTrainingFile(autoc))).start();
		(new Thread(new AlbuquerqueTrainingFile(autoc))).start();
		
		/*
		//first (automatic) example of how results change with other training threads
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
		*/
		
		//get fragments from the user for testing through console input
		//using the more complicated version that allows use within an IDE
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fragment = "";
		do {
			System.out.println("Enter a word fragment to get suggestions for. Enter nothing to exit. ");
			try {
				fragment = br.readLine();
			} catch (IOException e) {
				System.err.println("Exception reading user input fragment: " + e.getMessage());
				e.printStackTrace();
			}
			if(!fragment.equals(""))
				printCandidateList(fragment, autoc);
		} while (!fragment.equals(""));
		
		System.out.println("Application Terminating");
	}
	
	public static void printCandidateList(String Fragment, AutocompleteProvider a) {
		System.out.println("Candidates for " + Fragment);
		for(Candidate c : a.getWords(Fragment)) {
			System.out.printf("%s (%d)%n", c.getWord(), c.getConfidence());
		}
		System.out.println("___________");
	}

}
