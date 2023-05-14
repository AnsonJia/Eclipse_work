package indi.anson.from.parent;
import java.util.ArrayList;
import java.util.Scanner; 

public class string_bracket_checker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner strInput = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String sentence = strInput.nextLine();
		strInput.close();
		String opBrackets = "{([<";
		String closeBrackets = "})]>";
		int bracketCheck = 0;
		boolean trigger = false;
		int prevBrac = 0;

		ArrayList<Character> newBrac = new ArrayList<Character>();
		
		for (int i=0; i<sentence.length(); i++) {
			String character = String.valueOf(sentence.charAt(i));
			if (closeBrackets.contains(character)) {
				bracketCheck --;

				
				if (newBrac.size() == 0 || sentence.charAt(i) != newBrac.get(newBrac.size()-1)) {
					trigger = true;
					
					break;
				}
				newBrac.remove(newBrac.size()-1);

			} 
			else if (opBrackets.contains(character)) {
				bracketCheck ++;
				prevBrac = opBrackets.indexOf(character);
				
				newBrac.add(closeBrackets.charAt(prevBrac));
				
				System.out.println(prevBrac);

			}

		}
		System.out.println(bracketCheck);
		if (trigger == false && bracketCheck == 0) {
			System.out.println("Valid");
		}else {
			System.out.println("Invalid");
		}
	}

}
