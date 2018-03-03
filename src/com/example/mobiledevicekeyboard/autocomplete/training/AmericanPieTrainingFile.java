package com.example.mobiledevicekeyboard.autocomplete.training;

import com.example.mobiledevicekeyboard.autocomplete.AutocompleteProvider;

/**
 * Thread that provides training for an AutocompleteProvider
 * Trains with the lyrics to American Pie, by Don McLean
 * Lyrics from https://www.azlyrics.com/lyrics/donmclean/americanpie.html
 * @author Chris
 *
 */
public class AmericanPieTrainingFile implements Runnable {

	AutocompleteProvider autoc;
	
	public AmericanPieTrainingFile(AutocompleteProvider a) {
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//Don't really care if this fails
				//the only interruption will be the program exiting at the moment
				//and if that happens, the autocomplete provider will be reset anyway
				//at least until it is saved to a file or database
			}
		}
	}

	private final String[] lyrics = {
			"A long long time ago",
			"I can still remember how",
			"That music used to make me smile",
			"And I knew if I had my chance",
			"That I could make those people dance",
			"And maybe they'd be happy for a while",
			"But February made me shiver",
			"With every paper I'd deliver",
			"Bad news on the doorstep",
			"I couldn't take one more step",
			"I can't remember if I cried",
			"When I read about his widowed bride",
			"Something touched me deep inside",
			"The day the music died",
			"So Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"And them good ole boys were drinking whiskey and rye",
			"Singin' this'll be the day that I die",
			"This'll be the day that I die",
			"Did you write the book of love",
			"And do you have faith in God above",
			"If the Bible tells you so?",
			"Do you believe in rock and roll?",
			"Can music save your mortal soul?",
			"And can you teach me how to dance real slow?",
			"Well, I know that you're in love with him",
			"Cause I saw you dancin' in the gym",
			"You both kicked off your shoes",
			"Man, I dig those rhythm and blues",
			"I was a lonely teenage broncin' buck",
			"With a pink carnation and a pickup truck",
			"But I knew I was out of luck",
			"The day the music died",
			"I started singin'",
			"Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"And them good ole boys were drinking whiskey and rye",
			"Singin' this'll be the day that I die",
			"This'll be the day that I die",
			"Now, for ten years we've been on our own",
			"And moss grows fat on a rolling stone",
			"But, that's not how it used to be",
			"When the jester sang for the king and queen",
			"In a coat he borrowed from James Dean",
			"And a voice that came from you and me", 
			"Oh and while the king was looking down",
			"The jester stole his thorny crown",
			"The courtroom was adjourned",
			"No verdict was returned", 
			"And while Lennon read a book on Marx",
			"The quartet practiced in the park",
			"And we sang dirges in the dark",
			"The day the music died",
			"We were singin'Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"Them good ole boys were drinking whiskey and rye",
			"And singin' this'll be the day that I die",
			"This'll be the day that I dieHelter skelter in a summer swelter",
			"The birds flew off with a fallout shelter",
			"Eight miles high and falling fastIt landed foul on the grass",
			"The players tried for a forward pass",
			"With the jester on the sidelines in a cast",
			"Now the half-time air was sweet perfume",
			"While sergeants played a marching tune",
			"We all got up to dance",
			"Oh, but we never got the chance", 
			"'Cause the players tried to take the field",
			"The marching band refused to yield",
			"Do you recall what was revealed",
			"The day the music died?",
			"We started singin'",
			"Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"Them good ole boys were drinking whiskey and rye",
			"And singin' this'll be the day that I die",
			"This'll be the day that I die",
			"Oh, and there we were all in one place",
			"A generation lost in space",
			"With no time left to start again", 
			"So come on Jack be nimble, Jack be quick",
			"Jack Flash sat on a candlestick",
			"Cause fire is the devil's only friend", 
			"Oh and as I watched him on the stage",
			"My hands were clenched in fists of rage",
			"No angel born in Hell",
			"Could break that Satan's spell", 
			"And as the flames climbed high into the night",
			"To light the sacrificial rite",
			"I saw Satan laughing with delight",
			"The day the music died",
			"He was singin'", 
			"Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"Them good ole boys were drinking whiskey and rye",
			"Singin' this'll be the day that I die",
			"This'll be the day that I die",
			"I met a girl who sang the blues",
			"And I asked her for some happy news",
			"But she just smiled and turned away", 
			"I went down to the sacred store",
			"Where I'd heard the music years before",
			"But the man there said the music wouldn't play", 
			"And in the streets the children screamed",
			"The lovers cried, and the poets dreamed",
			"But not a word was spoken",
			"The church bells all were broken", 
			"And the three men I admire most",
			"The Father, Son, and the Holy Ghost",
			"They caught the last train for the coast",
			"The day the music died",
			"And they were singing",
			"Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"And them good ole boys were drinking whiskey and rye",
			"Singin' this'll be the day that I die",
			"This'll be the day that I die",
			"They were singing",
			"Bye, bye Miss American Pie",
			"Drove my Chevy to the levee but the levee was dry",
			"Them good ole boys were drinking whiskey and rye",
			"Singin' this'll be the day that I die "

	};
}
