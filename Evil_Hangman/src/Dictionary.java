import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Dictionary {
	public Map<Integer, Set<String>> allWords;
	private Set<String> sBl;
	private Map<String, Set<String>> fams = new HashMap<>();
	private Set<String> cf = new HashSet<>();
	private int length;
	private String cw;
	private Set<String> mySet;
	public int wordsLeft;
	private int count = 0;
	private Map<String, Set<String>> holder = new HashMap<>();
	private String used;
	private boolean done = false;
	private int c = 0;
	
	
	//
	public String checkMap() {
		return allWords.toString();
	}
	
	public void reset() {
		count = 0;
		fams.clear();
		sBl.clear();
	}
	
	
	//Used once to load the words
	public void loadDictionary(String file) {
		allWords = new HashMap<Integer, Set<String>>();
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.out.println("could not find file");
			System.exit(-1);
		}
		
		while(inFile.hasNext()) {
			String word = inFile.next().toLowerCase();
			int l = word.length();
			sBl = allWords.get(l);
			if(sBl == null) {
				sBl = new HashSet<String>();
			}
			sBl.add(word);
			allWords.put(l,sBl);
		}
		inFile.close();
	}
	
	//Gets the words with the chosen Length
	public Collection<String> chosenL(int i){
		return allWords.get(i);
	}
	
	//updates the current family
	
	public Collection<String> currentFamily(char c){
		count++;
		String word;
		if (count < 2) {
			cf = allWords.get(length);
		} else {
			cf = fams.get(chooseKey());
		}
		
		Iterator iter = cf.iterator();
		
		
		while(iter.hasNext()) {
			String w = (String) iter.next();
			word = getPattern(c, w);
			mySet = fams.get(word);
			if(mySet ==  null) {
				mySet = new HashSet<String>();
			}
			
			mySet.add(w);
			fams.put(word, mySet);
		}

		
		deleteRest();
		if (wordsLeft >1){
			cw = chooseKey();
		}
		
		
			fams = keepKey(fams);
		
		
		
		if(wordsLeft == 1) {
			String s = getValue();
			String temp = "";
			
			for(int i = 0; i < s.length(); i++) {
				if(cw.charAt(i)!='-') {
					temp+=(s.charAt(i));
				}else if(s.charAt(i)==c) {
					temp+=c;
				} else {
					temp+='-';
				}
			}
			
			cw = temp;
			if(cw.equals(s)) {
				done = true;
			}
			
		}
		System.out.println(fams);
		
		return fams.get(chooseKey());
		
	}
	
	//need to make it where it updates with the word
	public String getPattern(char c, String word) {
		StringBuilder f = new StringBuilder();
		
			for(int i = 0; i < word.length(); i++) {
				if(cw.charAt(i)!='-') {
					f.append(cw.charAt(i));
				}else if(word.charAt(i)==c) {
					f.append(c);
				} else {
					f.append('-');
				}
			}
		
		return f.toString();
	}
	
	
	
	//setters and getters
	
	public void setL(int i) {
		length = i;
	}
	
	public int ShowR(Collection<String> a) {
		return a.size();
	}
	
	public void setCw(String s) {
		cw = s;
	}
	
	public String getCw() {
		return cw;
	}
	
	public void setWL(int i) {
		wordsLeft = i;
	}
	
	public boolean isSolved() {
		if(wordsLeft == 1 && done == true) {
			return true;
		}
		
		return false;
	}
	
	//Own Helpers
	
	public String chooseKey() {
		Iterator<Entry<String, Set<String>>> iter = fams.entrySet().iterator();
		
		int max = 0;
		Set<String> maxSet = null;
		String maxKey = "";
		
		while(iter.hasNext()) {
			Entry<String, Set<String>> nextOne = iter.next();
			
			if(nextOne.getValue().size() > max) {
				max = nextOne.getValue().size();
				maxSet = nextOne.getValue();
				maxKey = nextOne.getKey();
			}
			if(wordsLeft == 1 && fams.size() <= 2) {
				
			}
		}	
		
		return maxKey;
	}
	
	public void setLast(char c) {
			Iterator<Entry<String, Set<String>>> iter = fams.entrySet().iterator();
			int maxcount = 0;
			String l = "";
			while(iter.hasNext()) {
				
			//get max count and use that as a chooser for choose key
				
				
				Entry<String, Set<String>> nextOne = iter.next();
				Iterator it = fams.get(nextOne.getKey()).iterator();
				for(int j = 0; j < nextOne.getKey().length();j++) {
					if(nextOne.getKey().charAt(j) != '-' ){
						count++;
					}
					
				}
				
//				for(int i = 0; i < nextOne.getKey().length();i++) {
//					
//					while(it.hasNext()) {
//						l = (String) it.next();
//						
//						for(int j = 0; j < l.length();j++) {
//							if(l.charAt(j) == c && count >= length - 1){
//								cw = l;
//								done = true;
//							}
//						}
//					}
//				}
				
				
				
			}
		
			
			
	}
	
	public String getLast() {
		Iterator iter = fams.get(chooseKey()).iterator();
		String l = "";
		while(iter.hasNext()) {
			l = (String) iter.next();
		}
		return l;
		
	}
	
	public Set<String> chooseSet() {
		Iterator<Entry<String, Set<String>>> iter = fams.entrySet().iterator();
		
		int max = 0;
		Set<String> maxSet = null;
		String maxKey = "";
		
		while(iter.hasNext()) {
			Entry<String, Set<String>> nextOne = iter.next();
			
			if(nextOne.getValue().size() > max) {
				max = nextOne.getValue().size();
				maxSet = nextOne.getValue();
				maxKey = nextOne.getKey();
			}
		}	
		
		return maxSet;
	}
	
	public String getValue() {
		Iterator iter = fams.get(chooseKey()).iterator();
		String l = "";
		while(iter.hasNext()) {
			l = (String) iter.next();
		}
		return l;
		
	}
	
	
	public Map<String, Set<String>> keepKey(Map<String, Set<String>> a) {
		Iterator<Entry<String, Set<String>>> iter = a.entrySet().iterator();
		
		while(iter.hasNext()) {
			Entry<String, Set<String>> nextOne = iter.next();
			if(!nextOne.getKey().equals(chooseKey())) {
				iter.remove();
			}
		}
		return a;
		
	}
	
	//Another method for used words
	public void addUsed(char c) {
		used += c;
	}
	
	public void deleteRest() {
		Iterator<Entry<String, Set<String>>> iter = fams.entrySet().iterator();
		
		int max = 0;
		Set<String> maxSet = null;
		String maxKey = "";
		
		while(iter.hasNext()) {
			Entry<String, Set<String>> nextOne = iter.next();
			
			if(nextOne.getValue().size() < chooseSet().size()) {
				fams.get(chooseKey()).removeAll(nextOne.getValue());
			}
		}
		
		
	}
	
	public boolean ifUsed() {// if the letter is used then they get another guess
		return false;
	}
	

}

	
