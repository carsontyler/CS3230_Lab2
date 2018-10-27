import java.util.*;
import java.util.Scanner;

public class FractionCalc {

	static String regex =
			"-?[0-9]+[ \t]*/[ \t]*-?[0-9]+[ \t]*[+-/\\*][ \t]*-?[0-9]+[ \t]*/[ \t]*-?[0-9]+";
	
	public static void main (String[] args) {
		int num1, num2, den1, den2;
		Fraction result = null;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a fraction expression: ");
		String exp = sc.nextLine();
		
		if(!(exp.matches(regex))) {
			System.out.println("ERROR: Invalid input");
			return;
		}
		
		String[] strList1 = exp.split("/");
		for(int i = 0; i < strList1.length; i++)
			strList1[i] = strList1[i].trim();

		// Subtraction 
		if (strList1[1].matches(("(-?\\w+)-(-?\\w+)"))) {
			num1 = Integer.valueOf(strList1[0]); 
			den2 = Integer.valueOf(strList1[2]);
			ArrayList<String> temp = new ArrayList<String>(Arrays.asList(strList1[1].split("((?<=-)|(?=-))")));
			for(int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals("-") && i == 0) {
					temp.set(i+1, "-"+temp.get(i+1));
					temp.remove(i);
				}
				else if (temp.get(i).equals("-") && temp.get(i+1).equals("-")) {
					temp.set(i+2, "-"+temp.get(i+2));
					temp.remove(i);
					temp.remove(i);
				}
				else if (temp.get(i).equals("-"))
					temp.remove(i);
			}
			den1 = Integer.valueOf(temp.get(0));
			num2 = Integer.valueOf(temp.get(1));
			
			Fraction frac1 = new Fraction(num1,den1);
			Fraction frac2 = new Fraction(num2,den2); 
			result = frac1.sub(frac2);
		}
		else {
			try {
				String[] strList2 = strList1[1].split("[+*/]");
				for(int i = 0; i < strList2.length; i++)
					strList2[i] = strList2[i].trim();
				// Addition and Multiplication
				num1 = Integer.valueOf(strList1[0]); 
				den2 = Integer.valueOf(strList1[2]);
				den1 = Integer.valueOf(strList2[0]);
				num2 = Integer.valueOf(strList2[1]);
			}
			catch (Exception e) {
				// Division
				num1 = Integer.valueOf(strList1[0]);
				den1 = Integer.valueOf(strList1[1]);
				num2 = Integer.valueOf(strList1[2]); 
				den2 = Integer.valueOf(strList1[3]);
			}
			
			Fraction frac1 = new Fraction(num1,den1);
			Fraction frac2 = new Fraction(num2,den2); 
			
			if(exp.contains("*"))
				result = frac1.mult(frac2);
			else if(exp.matches("-?[0-9]+[ \\t]*/[ \\t]*-?[0-9]+[ \\t]*[/][ \\t]*-?[0-9]+[ \\t]*/[ \\t]*-?[0-9]+"))
				result = frac1.div(frac2);
			else if(exp.contains("+"))
				result = frac1.add(frac2);
		}
		// demonstrates the matches method of the String class
		System.out.println(result.toString());
	}
}