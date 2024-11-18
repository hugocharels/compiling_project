import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CFGrammar<Token, Variable, Terminal> {

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


	Set<Terminal> getFirst(Variable variable);

	/**
	 * Returns the first set of a variable.
	 *
	 * @param variable the variable whose first set is to be retrieved
	 * @return the first set of the variable
	 */
	Set<Terminal> getFollow(Variable variable);

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
}
