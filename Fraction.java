
public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction(int numerator, int denominator) {
		int divisor = gcd(numerator, denominator);
		this.numerator = numerator / divisor;
		this.denominator = denominator / divisor;
	}
	
	public Fraction(int numerator) {
		this.numerator = numerator;
		denominator = 1;
	}
	
	public Fraction add(Fraction right) {
		int newNum = 0;
		int newDen = 0;
		int gcd = 0;
		
		if (this.denominator == right.denominator)
			return new Fraction(this.numerator + right.numerator, this.denominator);
		
		newNum = (this.numerator * right.denominator) + (this.denominator * right.numerator);
		newDen = (this.denominator * right.denominator);
		
		gcd = gcd(newNum, newDen);
		newNum /= gcd;
		newDen /= gcd;
		
		return new Fraction(newNum, newDen);
	}
	
	public Fraction sub(Fraction right) {
		int newNum = 0;
		int newDen = 0;
		int gcd = 0;
		
		if (this.denominator == right.denominator)
			return new Fraction(this.numerator - right.numerator, this.denominator);
		
		newNum = (this.numerator * right.denominator) - (this.denominator * right.numerator);
		newDen = (this.denominator * right.denominator);
		
		gcd = gcd(newNum, newDen);
		newNum /= gcd;
		newDen /= gcd;
		
		return new Fraction(newNum, newDen);
	}
	
	public Fraction mult(Fraction right) {
		return new Fraction(this.numerator*right.numerator,this.denominator*right.denominator);
	}
	
	public Fraction div(Fraction right) {
		return new Fraction(this.numerator*right.denominator,this.denominator*right.numerator);
	}
	
	public String toString() {
		return numerator + " / " + denominator;
	}
	
	public boolean equals(Object other) {
		if (this == other) return true;
		
		if (other == null) return false;
		
		if (getClass() != other.getClass()) return false;
		
		if (getClass() != other.getClass()) return false;
		
		Fraction otherObj = (Fraction)other;
		
		return numerator == otherObj.numerator 
				&& denominator == otherObj.denominator;
	}
	
	private int gcd(int u, int v) {
		u = (u < 0) ? -u : u; 	// make u non-negative
		v = (v < 0) ? -v : v; 	// make v non-negative
		while (u > 0) {
			if (u < v) {
				int t = u; 		// swap u and v
				u = v;
				v = t;
			}
			u -= v;
		}
		return v; 				// the GCD of u and v
	}
}
