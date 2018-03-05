package com.example.mobiledevicekeyboard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.Candidate;
import com.example.mobiledevicekeyboard.autocomplete.cs.TrieNodeAutocompleteProvider;
import com.example.mobiledevicekeyboard.autocomplete.training.AlbuquerqueTrainingFile;
import com.example.mobiledevicekeyboard.autocomplete.training.AmericanPieTrainingFile;
import com.example.mobiledevicekeyboard.autocomplete.training.YellowSubmarineTrainingFile;

public class Main {

	public static final String STORE_FILE = "dictionary.dat";
	
	public static void main(String[] args) {
		AutocompleteProvider autoc;
		//try to load the autocomplete entries from an existing file
		//this will load faster than training all of the documents/phrases each time
		//if the file doesn't exist, then we have a blank autocomplete dictionary
		autoc = LoadAutocompleteEntries();
		if(autoc == null)
			autoc = new TrieNodeAutocompleteProvider(); 
		
		//provided example
		//this is now loaded up in the save file
		//so training this example again will just double these words in the candidate list 
		//autoc.train("The third thing that I need to tell you is that this thing does not think thoroughly.");

		//Demonstrate the provided example
		printCandidateList("thi", autoc);
		printCandidateList("nee", autoc);
		printCandidateList("th", autoc);
		
		//overwrite the existing dictionary with the current dictionary
		//since we haven't trained anything more, this ultimately isn't causing anything 
		//different to occur between runs
		SaveAutocompleteEntries(autoc);
		
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
	
	/**
	 * Provide single line method of outputting the results
	 * @param Fragment What the user has requested so far.
	 * @param a The data structure containing the suggestions.
	 */
	public static void printCandidateList(String Fragment, AutocompleteProvider a) {
		System.out.println("Candidates for " + Fragment);
		for(Candidate c : a.getWords(Fragment)) {
			System.out.printf("%s (%d)%n", c.getWord(), c.getConfidence());
		}
		System.out.println("___________");
	}

	/**
	 * Saves the list of suggestions for fragments to a file for use in future runs.
	 * @param autoc The data structure containing the suggestions. Must implement Serializable.
	 */
	public static void SaveAutocompleteEntries(AutocompleteProvider autoc) {
		ObjectOutputStream oos = null;
		try {
			//AutocompleteProvider and encapsulated classes must implement serializable
			//for this to work
			oos = new ObjectOutputStream(
					new FileOutputStream(STORE_FILE));
			oos.writeObject(autoc);
		} catch (IOException e) {
			System.err.println("Error saving autocomplete suggestions." + e.getMessage());
			e.printStackTrace();
		} finally {
			if(oos != null)
				try {
					oos.close();
				} catch (IOException e) {
					//not sure what to do with this
					//only thing I can think of is this fails if it's already closed
					//in which case we don't need to do anything more
				}
		}
	}
	
	/**
	 * Loads the list of suggestions for fragments from a file for use in this run.
	 * Currently set to use the TrieNodeAutocompleteProvider implementation, which 
	 * implements serializable, which allows the data structure to be formed.
	 * @return A TrieNodeAutocompleteProvider containing suggestions for word fragments.
	 */
	public static AutocompleteProvider LoadAutocompleteEntries() {
		AutocompleteProvider retval = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(
					new FileInputStream(STORE_FILE));
			retval = (TrieNodeAutocompleteProvider) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			//if we can't find the file, then we're assuming it doesn't exist
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					//not sure what to do with this
					//only thing I can think of is this fails if it's already closed
					//in which case we don't need to do anything more
				}
			}
		}
		return retval;
	}
}
