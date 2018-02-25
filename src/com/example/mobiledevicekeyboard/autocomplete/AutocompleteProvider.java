package com.example.mobiledevicekeyboard.autocomplete;

import java.util.List;

/**
 * Defines the functions the autocomplete functionality must implement in
 * order to be used by the keyboard.
 * @author Chris
 *
 */
public interface AutocompleteProvider {
	
	/**
	 * Provides a list of words that have been used by the user before that 
	 * the user may be typing again based on the letters typed so far.
	 * @param fragment The portion of the word the user has typed so far.
	 * @return The set of words the user has previously typed.
	 */
	List<Candidate> getWords(String fragment);
	
	/**
	 * Incorporates the words typed in the final message that was sent into
	 * the autocomplete candidate list.
	 * @param passage The full message with all words that may be considered
	 * as future autocomplete candidates.
	 */
	void train(String passage);
}
