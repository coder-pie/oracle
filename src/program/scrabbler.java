package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * 
 * @author payal
 * 
 * algoritm:
 * Load the file in memory to avoid frequent file IO.
 * data structure used: Hashset- no duplictes, direct search access O(1).
 *
 */

public class scrabbler {
	static HashSet<String> dictionary;//store words.txt in memory
	static boolean nullset;//flag to check if anything found in dictionary
	static int wordcount;//for junit tests;
	static ArrayList<String> words;//for junit tests, used only in prefix method, since less expected outcomes.
	public scrabbler(){
		dictionary=new HashSet<String>();
		nullset=true;
		wordcount=0;
		try{
			InputStream in=this.getClass().getClassLoader().getResourceAsStream("utils/words.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String word=br.readLine();
			while(word!=null){
				dictionary.add(word.toLowerCase());
				word=br.readLine();
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println("System error. Could not locate dictionary! Exiting Program.");
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("File IO exception! Exiting Program.");
			System.exit(1);
		}
	}
	public static void main(String args[]){
		if (args.length==0 || args.length>2 || (args.length==2 && !args[0].equalsIgnoreCase("prefix"))
					&& (args.length==2 && !args[0].equalsIgnoreCase("suffix"))){
			System.out.println("Please specify valid arguments. Usage:\n"
					+ "1. scrabbler <string> - list all permutations of string in dictionary, no duplicates\n"
					+ "2. scrabbler prefix <string> - list all words with this prefix\n"
					+ "3. scrabbler suffix <string> - list all words with this suffix\n");
			System.exit(0);
		}
		scrabbler s= new scrabbler();
//		System.out.println(dicionary.size());
		if(args.length==1){
			if(args[0].length()<2){
				System.out.println("String length should be more than 1. Exiting program.");
				System.exit(1);
			}
			permute(args[0].toLowerCase());
			
		}
		else if ("prefix".equalsIgnoreCase(args[0])){
			prefix(args[1].toLowerCase());
		}
		else if("suffix".equalsIgnoreCase(args[0])){
			suffix(args[1].toLowerCase());
		}
		if(nullset)
			System.out.println("No matching pattern found for input string.");
		
		System.out.println("Program Completed. Exiting program.");
	}
	/*
	 * Permute method takes the input string and iterate over each subset of the string.
     * jumble method is called by permute to for each of these subsets. 
     * This method shuffles each set using recursion call to itself and matches with words in dictionary.
	 */
	public static void permute(String word){
		char[] set=word.toCharArray();
		String subset;
		for (int i = 0; i < (1<<word.length()); i++){
			subset=new String();
			for (int j = 0; j < word.length(); j++)
				if ((i & (1 << j)) > 0)
					subset+=String.valueOf(set[j]);
			if(!subset.isEmpty() && subset.length()>1)
				jumble(subset,0,subset.length()-1);
//			System.out.println(subset+"\n");
	        }
	}
	
	private static void jumble(String s, int start, int end){
		if(start==end){
			if(dictionary.contains(s)){
				System.out.println(s);
				wordcount++;
				if(nullset)
					nullset=false;
			}
		}
		else{
			for(int i=start;i<=end;i++){
				s=swap(s,start,i);
				jumble(s,start+1,end);
				s=swap(s,start,i);
			}
			return;
		}
	}
	
	private static String swap(String s,int i,int j){
		if (i==j) return s;
		char[] c = s.toCharArray();
		char temp=c[j];
		c[j]=c[i];
		c[i]=temp;
		return String.valueOf(c);
	}
	/*
	 * prefix method converts to dictionary to treeset to get a reduced subset of the words
     * then iterates over this smaller subset to find matches. Uses flag to terminate.
	 */
	public static void prefix(String pref){
		TreeSet<String> ts=new TreeSet<String>(new StrComp());
		boolean terminate=false;
		ts.addAll(dictionary);
		Set<String> last=ts.tailSet(pref);
		Iterator<String> iter=last.iterator();
		words=new ArrayList<String>();
		String elem;
		do{
			elem=iter.next();
			if(elem.startsWith(pref)){
				System.out.println(elem);
				wordcount++;
				words.add(elem);
				if(nullset)
					nullset=false;
			}
			else{
				terminate=true;
				
			}
		}while(!terminate && elem!=null);	
	}
	

	public static ArrayList<String> getWords() {
		return words;
	}
	/*
	 * Suffix method iterates over the dictionary to fix all words ending with give suffix.
	 */
	public static void suffix(String suf){
		TreeSet<String> ts=new TreeSet<String>(new StrComp());
		ts.addAll(dictionary);
		String elem;
		Iterator<String> iter=ts.iterator();
		while(iter.hasNext()){
			elem=iter.next();
			if(elem.endsWith(suf)){
				System.out.println(elem);
				wordcount++;
				if(nullset)
					nullset=false;
			}
			
		}
	
	}
	public static int getWordcount() {
		return wordcount;
	}

}
class StrComp implements Comparator<String> {
	@Override
	public int compare(String o1, String o2){
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
}

