package com.example.mobiledevicekeyboard.autocomplete.training;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;

/**
 * Thread that provides training for an AutocompleteProvider
 * Trains with the lyrics to Yellow Submarine, by The Beatles
 * Lyrics from https://www.azlyrics.com/lyrics/beatles/yellowsubmarine.html
 * @author Chris
 *
 */
public class YellowSubmarineTrainingFile implements Runnable {

	AutocompleteProvider autoc;
	
	public YellowSubmarineTrainingFile(AutocompleteProvider a) {
		autoc = a;
	}
	
	@Override
	public void run() {
		for(String line : lyrics) {
			autoc.train(line);	//provide the line
			try {
				//emulate the process of searching through old documents
				//and allow the user to end up with different results as 
				//the algorithm is trained
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//Don't really care if this fails
				//the only interruption will be the program exiting at the moment
				//and if that happens, the autocomplete provider will be reset anyway
				//at least until it is saved to a file or database
			}
		}
	}


	private final String[] lyrics = {
			"In the town where I was born",
			"Lived a man who sailed to sea",
			"And he told us of his life",
			"In the land of submarines",
			"So we sailed up to the sun",
			"Till we found the sea of green",
			"And we lived beneath the waves",
			"In our yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"And our friends are all on board",
			"Many more of them live next door",
			"And the band begins to play",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"{Full speed ahead, Mr. Boatswain [pronounced bo'sun], full speed ahead!",
			"Full speed it is, Sgt.!",
			"Cut the cable, drop the cable!",
			"Aye, sir, aye!",
			"Captain, captain! [pronounced cap'n, cap'n]}",
			"As we live a life of ease (A life of ease)",
			"Everyone of us (Everyone of us) has all we need (Has all we need)",
			"Sky of blue (Sky of blue) and sea of green (Sea of green)",
			"In our yellow (In our yellow) submarine (Submarine, ha, ha)",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine",
			"We all live in a yellow submarine",
			"Yellow submarine, yellow submarine "
	};
}
