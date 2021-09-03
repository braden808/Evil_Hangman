//Name: Braden Amorozo
//Assignment: Lab 10
//Title: Evil HangMan
//Course: CS 270
//Lab Section: 2
//Instructor: Dr. Blaha
//Date: 5/17/19
//Sources consulted: Online Forums, Class Lectures, and Youtube
//Known Bugs: none
//Creativity: none

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EvilHangman {
	private static int wordLength;
	private static int tries;
	private static int triesLeft;
	private static boolean play;
	private static boolean showR;
	private static int runningTot;
	private static String word = "";
	private static char guess;
	private static String used = "";
	private static boolean solved = false;
	
	
	

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		
		String status = "";
		play = true;
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome To HANGMAN! The easiest, simplest, and funnest game of all time.  Do you want to play?  Type No if you do not want to play and type any key if you do want to play: ");
		status = scan.nextLine();
		if (status.equalsIgnoreCase("no")) {
			play = false;
		}
		while(play) {
			d.loadDictionary("dictionary.txt");
			System.out.print("What word length would you like? Please enter a number: ");
			wordLength = scan.nextInt();
			while (wordLength > 29 || wordLength < 2 || wordLength == 26 || wordLength == 27) {
				System.out.print("Invalid. Enter new value: ");
				wordLength = scan.nextInt();
				
			}
			for(int i = 0; i < wordLength;i++) {
				word += "-";
			}
			d.setCw(word);
			System.out.println(d.chosenL(wordLength)+"\n");
			d.setL(wordLength);
			System.out.print("How many guesses would you like? (Greater than 0 and less than 26): " );
			tries = scan.nextInt();
			while (tries >= 26 || tries <= 0) {
				System.out.print("Invalid. Enter new value: ");
				tries = scan.nextInt();
			}
			System.out.print("Would you like a running total of the amount of words?  Type No if you do not want a running total and type any key if you do want a running total: ");
			scan.nextLine();
			if(scan.nextLine().equalsIgnoreCase("no")) {
				showR = false;
			} else {
				showR = true;
			}
			
			//First Run
			d.wordsLeft = d.ShowR(d.chosenL(wordLength));
			System.out.println("Let the games begin!");
			
			System.out.println("Tries Remaining: " + tries);
			
			if(showR == true) {
				System.out.println("Current Set: " + d.chosenL(wordLength));
				System.out.println("Remaining Words: " + d.ShowR(d.chosenL(wordLength)));
			}
			triesLeft = tries;
			
			
			System.out.println("Current word: " + word);
			System.out.println("Used: " + used);
			System.out.println("Guess: ");
			guess = scan.nextLine().charAt(0);
			used += guess;
			
			d.addUsed(guess);
		
 			triesLeft --;
			d.currentFamily(guess);
			
			//Looped Run
			while(triesLeft <= tries && triesLeft > 0 && solved == false) {// need to add if the word is solved
				d.wordsLeft = d.ShowR(d.chosenL(wordLength));
				
				
				System.out.println("Tries Remaining: " + triesLeft);
				
				if(showR == true) {
					System.out.println("Current Set: " + d.chooseSet());
					System.out.println("Remaining Words: " + d.ShowR(d.chooseSet()));
				}
				d.setWL(d.ShowR(d.chooseSet()));
				word = d.getCw();
				System.out.println("Current word: " + word);
				System.out.println("Used: " + used);
				System.out.print("Guess:");
				guess = scan.nextLine().charAt(0);
				used += " "+guess;
				d.addUsed(guess);
				d.setCw(word);
				triesLeft --;
				
				
				
				d.currentFamily(guess);
				
				if(d.isSolved()) {
					word = d.getCw();
					solved = true;
					System.out.println("You Win and The Answer is:" + word + "!");
					
				}
				
				
				
			}
			if(solved == false) {
				System.out.println("You Lose!");
			}
			System.out.print("Would you like to play again? Type no if you are done: ");
			
			if(scan.nextLine().equalsIgnoreCase("no")) {
				play = false;
			}
			else {
				tries = 0;
				play = true;
				word = "";
				used = "";
				d.reset();
			}
			
		}
		
		System.out.println("Thanks for Playing!");
		
		
	}

}

	
