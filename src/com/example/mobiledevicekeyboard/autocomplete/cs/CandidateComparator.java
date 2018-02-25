package com.example.mobiledevicekeyboard.autocomplete.cs;

import java.util.Comparator;

import com.example.mobiledevicekeyboard.autocomplete.Candidate;

public class CandidateComparator implements Comparator<Candidate> {

	@Override
	public int compare(Candidate arg0, Candidate arg1) {
		if(arg0.getConfidence() < arg1.getConfidence())
			return -1;
		if(arg0.getConfidence() == arg1.getConfidence())
			return 0;
		//if arg0.getConfidence > arg1.getConfidence
		//by this point it's the only other option
		return 1;
	}

}
