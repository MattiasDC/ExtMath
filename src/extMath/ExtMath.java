package extMath;
import java.util.ArrayList;

/**
 *This class serves as an extension of the Java Math library.
 *While Math offers a lot of functions, it doesn't include some other helpful mathematical functions.
 *
 *@author Mattias De Charleroy
 *@version 1.0
 */
public final class ExtMath {

	/**
	 * This method returns the greatest common divisor of the a and b.
	 * This means that there is no larger integer which is divisible by a and b
	 * than the result of gcd(a,b).
	 * @param 	a
	 * 			The first argument whose gcd must be found with the second argument
	 * @param 	b
	 * 			The second argument whose gcd must be found with the first argument		
	 * @return	Returns the greatest common divisor of the two arguments
	 */
	public static int gcd(int a,int b){
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

	/**
	 * This method returns the greatest common divisor of the a and b.
	 * This means that there is no larger long value which is divisible by a and b
	 * than the result of gcd(a,b).
	 * @param 	a
	 * 			The first argument whose gcd must be found with the second argument
	 * @param 	b
	 * 			The second argument whose gcd must be found with the first argument		
	 * @return	Returns the greatest common divisor of the two arguments
	 */
	public static long gcd(long a,long b){
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
	
	/**
	 * This method returns the least common multiple of the a and b.
	 * This means that there is no smaller integer which is a multiple of a and b
	 * than the result of lcm(a,b).
	 * @param 	a
	 * 			The first argument whose lcm must be found with the second argument
	 * @param 	b
	 * 			The second argument whose lcm must be found with the first argument		
	 * @return	Returns the least common multiple of the two arguments
	 */
	public static int lcm(int a,int b){
		return (a*b)/gcd(a,b);
	}
	
	/**
	 * This method returns the least common multiple of the a and b.
	 * This means that there is no smaller long which is a multiple of a and b
	 * than the result of lcm(a,b).
	 * @param 	a
	 * 			The first argument whose lcm must be found with the second argument
	 * @param 	b
	 * 			The second argument whose lcm must be found with the first argument		
	 * @return	Returns the least common multiple of the two arguments
	 */
	public static long lcm(long a,long b){
		return (a*b)/gcd(a,b);
	}
	
	/**
	 * This method determines if the given argument is prime or not in an efficient way.
	 * @param 	number
	 * 			The number to determine whether it is prime or not.
	 * @return	Returns true if and only if the argument is prime.
	 */
	public static boolean isPrime(int number){	
		if (number == 2 || number == 3) return true;
		if (number % 2 == 0 || number % 3 == 0 || number <= 1) return false;
		for (int i = 6; (i-1)*(i-1) <= number;i+=6){
			if (number % (i-1) == 0 || number % (i+1) == 0) return false;
		}	
		return true;
	}

	/**
	 * This method determines if the given argument is prime or not in an efficient way.
	 * @param 	number
	 * 			The number to determine whether it is prime or not.
	 * @return	Returns true if and only if the argument is prime.
	 */
	public static boolean isPrime(long number){	
		if (number == 2 || number == 3) return true;
		if (number % 2 == 0 || number % 3 == 0 || number <= 1) return false;
		for (int i = 6; (i-1)*(i-1) <= number;i+=6){
			if (number % (i-1) == 0 || number % (i+1) == 0) return false;
		}	
		return true;
	}

	/**
	 * This function creates and returns the sieve of Eratosthenes up to the given argument.
	 * More precisely if the number x is prime, sieve[x] == true, if x is not prime
	 * sieve[x] == false
	 * @param 	n
	 * 			The argument up to which number the sieve must be created
	 * @return	Returns the sieve of Eratosthenes where sieve[x] is true if and only if x is prime
	 */
	public static boolean[] sieve(int n){
		boolean[] sieve = new boolean[n+1];
		for (int i = 2; i < n;i++)
			sieve[i] = true;
		int i = 2;
		while (i*i <= n){
			if (!sieve[i]){
				i++;
				continue;
			}
			for (int j = i; j*i < n;j++)
				sieve[j*i] = false;
			i++;
		}
		return sieve;
	}

	/**
	 * This function returns all the prime factors and it's exponents of the given number
	 * @param 	number
	 * 			The argument of which the prime factorization must be found
	 * @return	Returns a list of Longs where the ith place in the list represents the i/2 th
	 * 			smallest prime factor if i is even, if i is uneven it represents the exponent of
	 * 			the (i-1)/2 th prime factor.
	 * 			
	 * 			E.g.
	 * 			list.get(5) is the exponent belonging to the prime factor list.get(4) and this
	 * 			prime factor is the 4/2 = 2th smallest prime factor of the number
	 * 			
	 */
	public static ArrayList<Long> getPrimeFactors(long number){
		ArrayList<Long> primeFactors = new ArrayList<Long>();
		long exp = 0;

		if (number % 2 == 0){
			number/=2;
			exp++;
			while (number %2 == 0){
				number /=2;
				exp++;
			}
			primeFactors.add(2L);
			primeFactors.add(exp);
		}
		for (long j = 3; number != 1 && j <= number/j;j+= 2){
			exp = 0;
			if (number % j == 0){
				number /=j;
				exp++;
				while (number % j == 0){
					number /=j;
					exp++;
				}
				primeFactors.add(j);
				primeFactors.add(exp);
			}
		}

		if (number > 1){
			primeFactors.add(number);
			primeFactors.add(1L);
		}

		return primeFactors;
	}

	/**
	 * This method returns all the divisors of the given number.
	 * The divisors are calculated using the prime factorization of the number
	 * @param 	number
	 * 			The number whose divisors are to be found
	 * @return	Returns a list of all the divisors of the argument
	 */
	public static ArrayList<Long> getDivisors(long number){
		ArrayList<Long> primes = getPrimeFactors(number);
		return getDivisorsLong(primes);
	}

	private static ArrayList<Long> getDivisorsLong(ArrayList<Long> primes){
		ArrayList<Long> divisors = new ArrayList<Long>();	
		if (primes.size() == 0){
			divisors.add(1L);
			return divisors;
		}

		long workingPrime = primes.remove(0);
		long expPrime = primes.remove(0);
		ArrayList<Long> otherDivisors = getDivisorsLong(primes);
		for (long i = expPrime; i >= 0;i--){
			for (long divisor : otherDivisors){
				divisors.add((long) (divisor*Math.pow(workingPrime,i)));
			}
		}

		return divisors;
	}

	/**
	 * This function returns all the prime factors and it's exponents of the given number
	 * @param 	number
	 * 			The argument of which the prime factorization must be found
	 * @return	Returns a list of Longs where the ith place in the list represents the i/2 th
	 * 			smallest prime factor if i is even, if i is uneven it represents the exponent of
	 * 			the (i-1)/2 th prime factor.
	 * 			
	 * 			E.g.
	 * 			list.get(5) is the exponent belonging to the prime factor list.get(4) and this
	 * 			prime factor is the 4/2 = 2th smallest prime factor of the number
	 * 			
	 */
	public static ArrayList<Integer> getPrimeFactors(int number){
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		int exp = 0;

		if (number % 2 == 0){
			number/=2;
			exp++;
			while (number %2 == 0){
				number /=2;
				exp++;
			}
			primeFactors.add(2);
			primeFactors.add(exp);
		}
		for (int j = 3; number != 1 && j <= number/j;j+= 2){
			exp = 0;
			if (number % j == 0){
				number /=j;
				exp++;
				while (number % j == 0){
					number /=j;
					exp++;
				}
				primeFactors.add(j);
				primeFactors.add(exp);
			}
		}

		if (number > 1){
			primeFactors.add(number);
			primeFactors.add(1);
		}

		return primeFactors;
	}

	/**
	 * This method returns all the divisors of the given number.
	 * The divisors are calculated using the prime factorization of the number
	 * @param 	number
	 * 			The number whose divisors are to be found
	 * @return	Returns a list of all the divisors of the argument
	 */
	public static ArrayList<Integer> getDivisors(int number){
		ArrayList<Integer> primes = getPrimeFactors(number);
		return getDivisorsInt(primes);
	}

	private static ArrayList<Integer> getDivisorsInt(ArrayList<Integer> primes){
		ArrayList<Integer> divisors = new ArrayList<Integer>();	
		if (primes.size() == 0){
			divisors.add(1);
			return divisors;
		}

		int workingPrime = primes.remove(0);
		int expPrime = primes.remove(0);
		ArrayList<Integer> otherDivisors = getDivisorsInt(primes);
		for (int i = expPrime; i >= 0;i--){
			for (int divisor : otherDivisors){
				divisors.add((int) (divisor*Math.pow(workingPrime,i)));
			}
		}

		return divisors;
	}

	/**
	 * This method returns the number of divisors of the given number
	 * @param 	number
	 * 			The number for which the number of divisors has to be calculated
	 * @return	Returns the number of different divisors of the number, this includes 1 and itself
	 */
	public static int getNumberOfDivisors(int number){
		ArrayList<Integer> primeFactors = getPrimeFactors(number);
		int numberOfDivisors = 1;
		for (int i = 1; i < primeFactors.size();i +=2){
			numberOfDivisors *= (primeFactors.get(i)+1);
		}
		return numberOfDivisors;
	}

	/**
	 * Returns the sum of all the divisors of the given number
	 * @param 	number
	 * 			The number whose sum of all the divisors have to be calculated
	 * @return	Returns the sum of all the divisors, this includes 1 and itself
	 */
	public static long getSumDivisors(int number){
		long sum = 0;
		for (Integer divisor : getDivisors(number))
			sum += divisor;
		return sum;
	}

	/**
	 * This method returns the number of divisors of the given number
	 * @param 	number
	 * 			The number for which the number of divisors has to be calculated
	 * @return	Returns the number of different divisors of the number, this includes 1 and itself
	 */
	public static int getNumberOfDivisors(long number){
		ArrayList<Long> primeFactors = getPrimeFactors(number);
		int numberOfDivisors = 1;
		for (int i = 1; i < primeFactors.size();i +=2){
			numberOfDivisors *= (primeFactors.get(i)+1);
		}
		return numberOfDivisors;
	}

	/**
	 * Returns the sum of all the divisors of the given number
	 * @param 	number
	 * 			The number whose sum of all the divisors have to be calculated
	 * @return	Returns the sum of all the divisors, this includes 1 and itself
	 */
	public static long getSumDivisors(long number){
		long sum = 0;
		for (Long divisor : getDivisors(number))
			sum += divisor;
		return sum;
	}


	/**
	 * This functions returns the faculty of the given number
	 * @param 	number
	 * 			The argument for which the faculty has to be calculated
	 * @return	Returns the faculty of this number
	 */
	public static double getFaculty(int number){
		double faculty = 1;		
		for (int i = number; i > 1;i--)
			faculty *= i;		
		return faculty;
	}

	/**
	 * This function tries to solve a linear system of equations.
	 * This function solves the system of the form A*x = b if and only if there is only one single solution.
	 * It is possible to solve multiple systems at the same time with the same matrix A.
	 * E.g. |if you want to find A*x_1 = b
	 * 							A*x_2 = d
	 * 							A*x_3 = f
	 * You only need to give as parameter A and the matrix which contains each the vectors b,d,f.
	 * These vectors must be column vectors.
	 * 
	 * The solution is given as column vectors.
	 * E.g. |if A = [1 0	and b = [1 3
	 * 		|		 0 1]			 2 4]
	 * 		|then x = [1 3
	 * 		|		   2 4]
	 * Thus the solutions are for b_1 (1,2) are (1,2) and for b_2 (3,4) are (3,4).
	 * @param 	A
	 * 			The matrix which is a representation of the linear equations
	 * @param 	b
	 * 			The argument where b = A*x
	 * @return	Returns the solutions of the linear system for the given argument b. This two
	 * 			dimensional matrix will be a result in column vectors.
	 * @throws 	NoSingleSolutionException
	 * 			Thrown when the matrix A is singular, thus when the system has none, or an infinite
	 * 			amount of solutions.
	 */
	public static double[][] solveSystem(double[][] A, double[][] b) throws NoSingleSolutionException{
		double[][] c = new double[A.length][A[0].length+b[0].length];		
		for (int i = 0; i < A.length;i++){
			for (int j = 0; j < A.length;j++)
				c[i][j] = A[i][j];
			for (int j = 0; j < b[0].length;j++)
				c[i][A.length+j] = b[i][j];
		}	
		return solveSystem(c);
	}


	/**
	 * This method is equivalent with solveSystem(A,b) where c = [A|b].
	 * Thus c is the expanded matrix. See more info about this function at solveSystem(A,b)
	 */
	public static double[][] solveSystem(double c[][]) throws NoSingleSolutionException{
		double[][] x = new double[c.length][c[0].length-c.length];
		int n = c.length-1;
		double m;
		for (int i = 0; i <= n;i++){
			getPivotAndSwitch(c,i);
			for (int j = i+1; j <= n;j++){
				m = c[j][i]/c[i][i];
				for (int l = i;l <= n+c[0].length-c.length;l++)
					c[j][l] = c[j][l]-(m*c[i][l]);	
			}
		}

		backwardSubsitution(c);

		for (int j = 0; j < x[0].length;j++)
			for (int i = 0; i < c.length;i++)
				x[i][j] = c[i][n+1+j];
		return x;
	}

	private static void backwardSubsitution(double[][] c) {
		int n = c.length-1;
		for (int k = n;k >= 0;k--){
			for (int i = k+1;i <=n;i++)
				for (int j = 0; j < c[0].length-c.length;j++)
					c[k][n+1+j] = c[k][n+1+j]-c[k][i]*c[i][n+1+j];
			for (int j = 0; j < c[0].length-c.length;j++)
				c[k][n+1+j] = c[k][n+1+j]/c[k][k];
		}
	}

	private static void getPivotAndSwitch(double[][] c, int i) throws NoSingleSolutionException{
		double pivot = c[i][i];
		int pivotIndex = i;
		for (int j = i+1; j < c.length;j++)
			if (pivot < c[j][i]){
				pivot = c[j][i];
				pivotIndex = j;
			}

		double[] tmp = c[i];
		c[i] = c[pivotIndex];
		c[pivotIndex] = tmp;

		if (pivot == 0)
			throw new NoSingleSolutionException("The system contains none, or an infinite amount of solutions");
	}
	
	/**
	 * Reverses the number and returns it
	 * @param 	number
	 * 			The number to reverse
	 * @return	Returns the reverse of the given number
	 */
	public static int reverseNumber(int number){
		int reverseNumber = 0;
		while (number > 0){
			reverseNumber = reverseNumber*10 + number%10;
			number /= 10;
		}
		return reverseNumber;
	}
	
	/**
	 * Reverses the number and returns it
	 * @param 	number
	 * 			The number to reverse
	 * @return	Returns the reverse of the given number
	 */
	public static long reverseNumber(long number){
		long reverseNumber = 0;
		while (number > 0){
			reverseNumber = reverseNumber*10 + number%10;
			number /= 10;
		}
		return reverseNumber;
	}
}
