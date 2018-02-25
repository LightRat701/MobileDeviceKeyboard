package com.example.mobiledevicekeyboard.autocomplete.cs;

import java.util.Comparator;

import com.example.mobiledevicekeyboard.autocomplete.Candidate;

/**
 * Allows sorting of candidates based on their confidence (number of occurrences).
 * Higher confidence goes first, so comparator works in reverse
 * @author Chris
 *
 */
public class CandidateComparator implements Comparator<Candidate> {

	@Override
	public int compare(Candidate arg0, Candidate arg1) {
		//this goes counter to a normal comparator because we want a 
		//max priority queue (highest number first) not sort in 
		//numerically ascending order (lowest number first)
		if(arg0.getConfidence() > arg1.getConfidence())
			return -1;
		if(arg0.getConfidence() == arg1.getConfidence())
			return 0;
		//if arg0.getConfidence < arg1.getConfidence
		//by this point it's the only other option
		return 1;
	}

}
