package com.example.mobiledevicekeyboard.autocomplete;

/**
 * Represents a word that the user may be typing. Used to provide options
 * for the autocomplete function of the keyboard.
 * @author Chris
 *
 */
public interface Candidate {
	/**
	 * Provides the full word that is a candidate for autocomplete options.
	 * @return String defining the candidate.
	 */
	String getWord();
	
	/**
	 * Provides the confidence (in occurrences) that the word represented by 
	 * this object is one that the user might be typing. 
	 * @return Number of previous uses of the word represented by this candidate.
	 */
	Integer getConfidence();
	
}
