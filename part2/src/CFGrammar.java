import java.util.*;

public interface CFGrammar<Token, Variable extends Token, Terminal extends Token> {
	/**
	 * Returns an unmodifiable set of variables (non-terminal symbols) in the grammar.
	 *
	 * @return an unmodifiable set of variables (V)
	 */
	Set<Variable> getVariables();

	/**
	 * Returns an unmodifiable set of terminal symbols in the grammar.
	 *
	 * @return an unmodifiable set of terminal symbols (T)
	 */
	Set<Terminal> getTerminals();

	/**
	 * Returns an unmodifiable map of production rules of the grammar.
	 * The map uses variables as keys and a list of right-hand side productions as values.
	 *
	 * @return an unmodifiable map of production rules (P)
	 */
	Map<Variable, List<List<Token>>> getProductionRules();

	/**
	 * Returns the start symbol of the grammar.
	 *
	 * @return the start symbol (S)
	 */
	Variable getStartSymbol();

	/**
	 * Returns the action table of the grammar.
	 *
	 * @return the action table
	 */
	Map<Pair<Variable, Terminal>, List<Token>> getActionTable();

	/**
	 * Returns the list of right-hand side productions for a given variable.
	 *
	 * @param variable the variable whose production rules are to be retrieved
	 * @return a list of right-hand side productions, or null if the variable is not in the grammar
	 */
	default List<List<Token>> getProductionRule(Variable variable) {
		return getProductionRules().get(variable);
	}

	/**
	 * Returns the first set of a variable.
	 *
	 * @param variable the variable whose first set is to be retrieved
	 * @return the first set of the variable
	 */
	default Set<Terminal> getFirst(Variable variable) {
		Set<Terminal> first = new HashSet<>();
		for (List<Token> production : this.getProductionRule(variable)) {
			if (production.isEmpty()) {
				continue;
			}
			Token firstToken = production.getFirst();
			if (firstToken != null) {
				first.add((Terminal) firstToken);
			} else {
				first.addAll(getFirst((Variable) firstToken));
			}
		}
		return first;
	}

	/**
	 * Returns the follow set of a variable.
	 *
	 * @param variable the variable whose follow set is to be retrieved
	 * @return the follow set of the variable
	 */
	default Set<Terminal> getFollow(Variable variable) {
		Set<Terminal> follow = new HashSet<>();

		for (Variable v : getVariables()) {
			for (List<Token> production : this.getProductionRule(v)) {
				for (int i = 0; i < production.size(); i++) {
					if (production.get(i) == variable) {
						// Case 1: Variable is at the end of the production
						if (i == production.size() - 1) {
							if (v != variable) { // Avoid self-referential recursion
								follow.addAll(getFollow(v));
							}
						}
						// Case 2: Variable is followed by other symbols
						else {
							Token nextToken = production.get(i + 1);
							if (nextToken != null) {
								follow.add((Terminal) nextToken);
							} else {
								// Add FIRST(nextToken)
								follow.addAll(getFirst((Variable) nextToken));
								// If epsilon (empty list) is in FIRST(nextToken), include FOLLOW(v)
								if (this.getProductionRule((Variable) nextToken).contains(List.of())) {
									follow.addAll(getFollow((Variable) nextToken));
								}
							}
						}
					}
				}
			}
		}
		return follow;
	}

	/**
	 * Builds the action table of the grammar.
	 *
	 * @return the action table
	 */
	default Map<Pair<Variable, Terminal>, List<Token>> buildActionTable() {
		Map<Pair<Variable, Terminal>, List<Token>> actionTable = new HashMap<>();
		for (Variable variable : this.getVariables()) {
			for (List<Token> production : this.getProductionRule(variable)) {
				// TODO: Implement the logic to build the action table
			}

		}
		return actionTable;
	}
}
