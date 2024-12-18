package compiler;

import java.util.List;

/**
 * Represents a production rule in a grammar.
 */
public class ProductionRule {
	private final int id;
	private final GlsVariable variable;
	private final List<Symbol> production;

	/**
	 * Constructs a ProductionRule with the specified id, variable, and production.
	 *
	 * @param id         the unique identifier of the production rule
	 * @param variable   the variable on the left-hand side of the production rule
	 * @param production the list of symbols on the right-hand side of the production rule
	 */
	public ProductionRule(int id, GlsVariable variable, List<Symbol> production) {
		this.id = id;
		this.variable = variable;
		this.production = production;
	}

	/**
	 * Returns the unique identifier of the production rule.
	 *
	 * @return the id of the production rule
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the variable on the left-hand side of the production rule.
	 *
	 * @return the variable of the production rule
	 */
	public GlsVariable getVariable() {
		return variable;
	}

	/**
	 * Returns the list of symbols on the right-hand side of the production rule.
	 *
	 * @return the production of the production rule
	 */
	public List<Symbol> getProduction() {
		return production;
	}

	/**
	 * Returns a string representation of the production rule.
	 *
	 * @return a string representation of the production rule
	 */
	@Override
	public String toString() {
		return this.getId() + "";
	}
}