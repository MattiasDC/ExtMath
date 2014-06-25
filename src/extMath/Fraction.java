package extMath;

/**
 * This class represents a fraction using integers
 * See the class BigFraction for a documentation of the functions.
 * BigFraction is a class equal to this class, but it uses BigIntegers instead of integers.
 * @author Mattias De Charleroy
 * @version 1.0
 */
public class Fraction implements Comparable<Fraction>{
	
	public Fraction(int numerator,int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Fraction simplify(){
		return new Fraction(this.numerator/(ExtMath.gcd(this.numerator,this.denominator)),this.denominator/ExtMath.gcd(this.numerator, this.denominator));
	}
	
	public Fraction inverse(){
		return new Fraction(this.denominator,this.numerator);
	}
	
	public Fraction negate(){
		return new Fraction(this.numerator*-1,this.denominator);
	}
	
	public Fraction sum(Fraction other){
		int newNumerator, newDenominator;
		newDenominator = ExtMath.lcm(this.denominator, other.denominator);
		newNumerator = this.numerator*(newDenominator/this.denominator) + other.numerator*(newDenominator/other.denominator);
		return new Fraction(newNumerator,newDenominator).simplify();
	}
	
	public Fraction substract(Fraction other){
		return sum(other.negate());
	}
	
	public Fraction multiply(Fraction other){
		return new Fraction(this.numerator*other.numerator,this.denominator*other.denominator).simplify();
	}
	
	public Fraction divide(Fraction other){
		return multiply(other.inverse());
	}
	
	public Fraction power(int exponent){
		return new Fraction((int)Math.pow(this.numerator,exponent),(int)Math.pow(this.denominator,exponent)).simplify();
	}
	
	public double toDecimals(){
		return this.numerator/1.0/this.denominator;
	}
	
	public final int numerator, denominator;
	
	@Override
	public int compareTo(Fraction other){
		if (this.toDecimals() > other.toDecimals())
				return 1;
		else if (this.toDecimals() < other.toDecimals())
			return -1;
		return 0;
	}
	
	@Override
	public String toString(){
		return this.numerator +"\\" + this.denominator;
	}
}
