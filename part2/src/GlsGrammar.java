import java.util.*;


public class GlsGrammar{

	private final Map<GlsVariable, List<List<GlsToken>>> productionRules = new HashMap<>();
	private final Map<Pair<GlsVariable, GlsTerminal>, List<GlsToken>> actionTable = new HashMap<>();

	public GlsGrammar() {
		this.buildProductionRules();
		this.buildActionTable();

		System.out.println("First sets:");
		for (GlsVariable variable : this.getVariables()) {
			System.out.println(variable + ": " + this.getFirst(variable));
		}
		System.out.println("\nFollow sets:");
		for (GlsVariable variable : this.getVariables()) {
			System.out.println(variable + ": " + this.getFollow(variable));
		}

		System.out.println("\nAction Table:");
		System.out.println(actionTable);

	}

	private void buildProductionRules() {
		productionRules.put(GlsVariable.PROGRAM, List.of(
				List.of(GlsTerminal.LET, GlsTerminal.PROGNAME, GlsTerminal.BE, GlsVariable.CODE, GlsTerminal.END)));
		productionRules.put(GlsVariable.CODE, List.of(
				List.of(GlsVariable.INSTRUCTION, GlsTerminal.COLUMN, GlsVariable.CODE),
				List.of()));
		productionRules.put(GlsVariable.INSTRUCTION, List.of(
				List.of(GlsVariable.ASSIGN),
				List.of(GlsVariable.IF),
				List.of(GlsVariable.WHILE),
				List.of(GlsVariable.OUTPUT),
				List.of(GlsVariable.INPUT)));
		productionRules.put(GlsVariable.ASSIGN, List.of(
				List.of(GlsTerminal.VARNAME, GlsTerminal.ASSIGN, GlsVariable.EXPR_ARITH)));
		productionRules.put(GlsVariable.EXPR_ARITH, List.of(
				List.of(GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		productionRules.put(GlsVariable.EXPR_ARITH_PRIME, List.of(
				List.of(GlsTerminal.PLUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME),
				List.of(GlsTerminal.MINUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME),
				List.of()));
		productionRules.put(GlsVariable.PROD_ARITH, List.of(
				List.of(GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		productionRules.put(GlsVariable.PROD_ARITH_PRIME, List.of(
				List.of(GlsTerminal.MULT, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME),
				List.of(GlsTerminal.DIV, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME),
				List.of()));
		productionRules.put(GlsVariable.ATOM, List.of(
				List.of(GlsTerminal.NUMBER),
				List.of(GlsTerminal.VARNAME),
				List.of(GlsTerminal.LPAREN, GlsVariable.EXPR_ARITH, GlsTerminal.RPAREN),
				List.of(GlsTerminal.MINUS, GlsVariable.ATOM)));
		productionRules.put(GlsVariable.IF, List.of(
				List.of(GlsTerminal.IF, GlsTerminal.LBRACK, GlsVariable.COND, GlsTerminal.RBRACK, GlsTerminal.THEN, GlsVariable.CODE, GlsVariable.IFSEQ)));
		productionRules.put(GlsVariable.IFSEQ, List.of(
				List.of(GlsTerminal.END),
				List.of(GlsTerminal.ELSE, GlsVariable.CODE, GlsTerminal.END)));
		productionRules.put(GlsVariable.COND, List.of(
				List.of(GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		productionRules.put(GlsVariable.NEXT_COND, List.of(
				List.of(GlsTerminal.IMPLIES, GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND),
				List.of()));
		productionRules.put(GlsVariable.COND_SIMPLE, List.of(
				List.of(GlsTerminal.PIPE, GlsVariable.COND_SIMPLE, GlsTerminal.PIPE),
				List.of(GlsVariable.EXPR_ARITH, GlsVariable.COMP, GlsVariable.EXPR_ARITH)));
		productionRules.put(GlsVariable.COMP, List.of(
				List.of(GlsTerminal.EQUAL),
				List.of(GlsTerminal.GEQUAL),
				List.of(GlsTerminal.GREATER)));
		productionRules.put(GlsVariable.WHILE, List.of(
				List.of(GlsTerminal.WHILE, GlsTerminal.LBRACK, GlsVariable.COND, GlsTerminal.RBRACK, GlsTerminal.REPEAT, GlsVariable.CODE, GlsTerminal.END)));
		productionRules.put(GlsVariable.OUTPUT, List.of(
				List.of(GlsTerminal.OUT, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
		productionRules.put(GlsVariable.INPUT, List.of(
				List.of(GlsTerminal.IN, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
	}

	private void buildActionTable() {
		// For each rule variable -> production
		for (GlsVariable variable : this.getVariables()) {
			Set<GlsTerminal> first = getFirst(variable);
			for (List<GlsToken> production : this.getProductionRule(variable)) {
				// Case 1: Production is epsilon
				if (production.isEmpty()) {
					for (GlsTerminal terminal : this.getFollow(variable)) {
						actionTable.put(new Pair<>(variable, terminal), production);
					}
				} // Case 2: Production starts with a terminal
				else if (production.getFirst() instanceof GlsTerminal) {
					actionTable.put(new Pair<>(variable, (GlsTerminal) production.getFirst()), production);
				} // Case 3: Production starts with a variable
				else {
					GlsVariable otherVariable = (GlsVariable) production.getFirst();
					// for machin in f(firstToken) -> if something then terminal = this one;
					// actionTable.put(new Pair<>(variable, otherVariable), production);
				}
			}
		}
	}

	public Set<GlsVariable> getVariables() {
		return Set.of(GlsVariable.values());
	}

	public Set<GlsTerminal> getTerminals() {
		return Set.of(GlsTerminal.values());
	}

	public GlsVariable getStartSymbol() {
		return null;
	}

	public Map<GlsVariable, List<List<GlsToken>>> getProductionRules() {
		return productionRules;
	}

	public List<List<GlsToken>> getProductionRule(GlsVariable variable) {
		return this.productionRules.get(variable);
	}


	/**
	 * Returns the first set of a variable.
	 *
	 * @param variable the variable whose first set is to be retrieved
	 * @return the first set of the variable
	 */
	public Set<GlsTerminal> getFirst(GlsVariable variable) {
		Set<GlsTerminal> first = new HashSet<>();
		for (List<GlsToken> production : this.getProductionRule(variable)) {
			if (production.isEmpty()) {
				continue;
			}
			GlsToken firstToken = production.getFirst();
			if (firstToken instanceof GlsTerminal) {
				first.add((GlsTerminal) firstToken);
			} else {
				first.addAll(getFirst((GlsVariable) firstToken));
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
	public Set<GlsTerminal> getFollow(GlsVariable variable) {
		Set<GlsTerminal> follow = new HashSet<>();

		for (GlsVariable v : getVariables()) {
			for (List<GlsToken> production : this.getProductionRule(v)) {
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
							GlsToken nextToken = production.get(i + 1);
							if (nextToken instanceof GlsTerminal) {
								follow.add((GlsTerminal) nextToken);
							} else {
								// Add FIRST(nextToken)
								follow.addAll(getFirst((GlsVariable) nextToken));
								// If epsilon (empty list) is in FIRST(nextToken), include FOLLOW(v)
								if (this.getProductionRule((GlsVariable) nextToken).contains(List.of())) {
									follow.addAll(getFollow((GlsVariable) nextToken));
								}
							}
						}
					}
				}
			}
		}
		return follow;
	}
}
