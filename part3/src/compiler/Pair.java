package compiler;

/**
 * A generic Pair class that holds two objects.
 *
 * @param <A> the type of the first object
 * @param <B> the type of the second object
 */
public class Pair<A, B> {
	private final A first;
	private final B second;

	/**
	 * Constructs a Pair with the specified values.
	 *
	 * @param first  the first value
	 * @param second the second value
	 */
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns the first value of the pair.
	 *
	 * @return the first value
	 */
	public A getFirst() {
		return first;
	}

	/**
	 * Returns the second value of the pair.
	 *
	 * @return the second value
	 */
	public B getSecond() {
		return second;
	}

	/**
	 * Compares this pair to the specified object.
	 *
	 * @param o the object to compare with
	 * @return true if the specified object is equal to this pair, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair<?, ?> pair = (Pair<?, ?>) o;
		return first.equals(pair.first) && second.equals(pair.second);
	}

	/**
	 * Returns the hash code value for this pair.
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		return 31 * first.hashCode() + second.hashCode();
	}

	/**
	 * Returns a string representation of the pair.
	 *
	 * @return a string representation of the pair
	 */
	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}