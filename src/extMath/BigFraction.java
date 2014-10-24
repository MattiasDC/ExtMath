package extMath;


import java.math.BigInteger;


/**
 * This class represents a mathematical fraction using BigIntegers
 * 
 * @note Caution when using this class, it has been implemented in a naive way
 *       (e.g division by zero is possible)
 * 
 * @author Mattias De Charleroy
 * @version 1.0
 */
public class BigFraction implements Comparable<BigFraction> {

	/**
	 * Initializes this BigFraction with the given numerator and denominator
	 * 
	 * @param numerator
	 *        The numerator of this BigFraction
	 * @param denominator
	 *        The denominator of this BigFraction
	 */
	public BigFraction(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Simplifies this fraction and returns it.
	 */
	public BigFraction simplify() {
		return new BigFraction(numerator.divide(numerator.gcd(denominator)),
				denominator.divide(numerator.gcd(denominator)));
	}

	/**
	 * Inverses this fraction. If this fraction equals 5/3, and we inverse it,
	 * this function returns a BigFraction which equals 3/5.
	 */
	public BigFraction inverse() {
		return new BigFraction(this.denominator, this.numerator);
	}

	/**
	 * Negates this fraction and returns it.
	 */
	public BigFraction negate() {
		return new BigFraction(this.numerator.negate(), this.denominator);
	}

	/**
	 * Adds the given BigFraction to this fraction and returns it.
	 * 
	 * @param other
	 *        The other BigFraction to add to this fraction.
	 * @return Returns a new BigFraction which is the sum of this fraction and
	 *         the argument.
	 */
	public BigFraction add(BigFraction other) {
		BigInteger newNumerator, newDenominator;
		newDenominator = (this.denominator.multiply(other.denominator)).divide((this.denominator
				.gcd(other.denominator)));
		newNumerator = this.numerator.multiply((newDenominator.divide(this.denominator))).add(
				other.numerator.multiply((newDenominator.divide(other.denominator))));
		return new BigFraction(newNumerator, newDenominator).simplify();
	}

	/**
	 * Substracts the given argument from this fraction
	 * 
	 * @param other
	 *        The other BigFraction which is going to be substracted from this
	 *        fraction
	 * @return Returns a new BigFraction which is the substraction of this
	 *         fraction and the argument.
	 */
	public BigFraction substract(BigFraction other) {
		return add(other.negate());
	}

	/**
	 * Multiplies this fraction with the given argument
	 * 
	 * @param other
	 *        The other fraction which is the multiplier of the product of this
	 *        fraction.
	 * @return Returns a new BigFraction which is the product of this fraction
	 *         and the given argument.
	 */
	public BigFraction multiply(BigFraction other) {
		return new BigFraction(this.numerator.multiply(other.numerator),
				this.denominator.multiply(other.denominator)).simplify();
	}

	/**
	 * Divides this fraction with the given argument.
	 * 
	 * @param other
	 *        The other fraction which is the divider of the division
	 * @return Returns a new BigFraction which is the division of this fraction
	 *         and the given argument.
	 */
	public BigFraction divide(BigFraction other) {
		return multiply(other.inverse());
	}

	/**
	 * Returns a BigFraction equal to this fraction raised to the power of the
	 * given argument.
	 * 
	 * @param exponent
	 *        The exponent of the power
	 * @return Returns a BigFraction equal to this fraction raised to the power
	 *         of the given argument.
	 */
	public BigFraction power(int exponent) {
		return new BigFraction(this.numerator.pow(exponent), this.denominator.pow(exponent))
				.simplify();
	}

	/**
	 * Returns the decimal notation of this fraction.
	 * 
	 * @return If the numerator and denominator are representable by a double
	 *         value, this function returns the double representation of this
	 *         fraction.
	 */
	public double toDecimals() {
		double numerator = this.numerator.doubleValue();
		double denominator = this.denominator.doubleValue();
		if (Double.isInfinite(numerator) || Double.isInfinite(denominator))
			throw new OverflowException(
					"The nominator or denominator are to large to represent it as a double");
		return numerator / denominator;
	}

	public final BigInteger numerator, denominator;

	@Override
	public int compareTo(BigFraction other) {
		if (this.toDecimals() > other.toDecimals())
			return 1;
		else if (this.toDecimals() < other.toDecimals())
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return this.numerator + "\\" + this.denominator;
	}
}
