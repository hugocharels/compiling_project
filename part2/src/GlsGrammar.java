import java.util.*;


public class GlsGrammar{

	private final Map<GlsVariable, List<List<Symbol>>> productionRules = new HashMap<>();
	private final Map<Pair<GlsVariable, LexicalUnit>, List<Symbol>> actionTable = new HashMap<>();

	public GlsGrammar() {
		this.buildProductionRules();
		this.buildActionTable();

//		System.out.println("First sets:");
//		for (GlsVariable variable : this.getVariables()) {
//			System.out.println(variable + ": " + this.getFirst(variable));
//		}
//		System.out.println("\nFollow sets:");
//		for (GlsVariable variable : this.getVariables()) {
//			System.out.println(variable + ": " + this.getFollow(variable));
//		}
//
//		System.out.println("\nAction Table:");
//		System.out.println(actionTable);

	}

	private void buildProductionRules() {
		productionRules.put(GlsVariable.PROGRAM, List.of(
				List.of(LexicalUnit.LET, LexicalUnit.PROGNAME, LexicalUnit.BE, GlsVariable.CODE, LexicalUnit.END)));
		productionRules.put(GlsVariable.CODE, List.of(
				List.of(GlsVariable.INSTRUCTION, LexicalUnit.COLUMN, GlsVariable.CODE),
				List.of()));
		productionRules.put(GlsVariable.INSTRUCTION, List.of(
				List.of(GlsVariable.ASSIGN),
				List.of(GlsVariable.IF),
				List.of(GlsVariable.WHILE),
				List.of(GlsVariable.OUTPUT),
				List.of(GlsVariable.INPUT)));
		productionRules.put(GlsVariable.ASSIGN, List.of(
				List.of(LexicalUnit.VARNAME, LexicalUnit.ASSIGN, GlsVariable.EXPR_ARITH)));
		productionRules.put(GlsVariable.EXPR_ARITH, List.of(
				List.of(GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		productionRules.put(GlsVariable.EXPR_ARITH_PRIME, List.of(
				List.of(LexicalUnit.PLUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME),
				List.of(LexicalUnit.MINUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME),
				List.of()));
		productionRules.put(GlsVariable.PROD_ARITH, List.of(
				List.of(GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		productionRules.put(GlsVariable.PROD_ARITH_PRIME, List.of(
				List.of(LexicalUnit.TIMES, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME),
				List.of(LexicalUnit.DIVIDE, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME),
				List.of()));
		productionRules.put(GlsVariable.ATOM, List.of(
				List.of(LexicalUnit.NUMBER),
				List.of(LexicalUnit.VARNAME),
				List.of(LexicalUnit.LPAREN, GlsVariable.EXPR_ARITH, LexicalUnit.RPAREN),
				List.of(LexicalUnit.MINUS, GlsVariable.ATOM)));
		productionRules.put(GlsVariable.IF, List.of(
				List.of(LexicalUnit.IF, LexicalUnit.LBRACK, GlsVariable.COND, LexicalUnit.RBRACK, LexicalUnit.THEN, GlsVariable.CODE, GlsVariable.IFSEQ)));
		productionRules.put(GlsVariable.IFSEQ, List.of(
				List.of(LexicalUnit.END),
				List.of(LexicalUnit.ELSE, GlsVariable.CODE, LexicalUnit.END)));
		productionRules.put(GlsVariable.COND, List.of(
				List.of(GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		productionRules.put(GlsVariable.NEXT_COND, List.of(
				List.of(LexicalUnit.IMPLIES, GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND),
				List.of()));
		productionRules.put(GlsVariable.COND_SIMPLE, List.of(
				List.of(LexicalUnit.PIPE, GlsVariable.COND_SIMPLE, LexicalUnit.PIPE),
				List.of(GlsVariable.EXPR_ARITH, GlsVariable.COMP, GlsVariable.EXPR_ARITH)));
		productionRules.put(GlsVariable.COMP, List.of(
				List.of(LexicalUnit.EQUAL),
				List.of(LexicalUnit.SMALEQ),
				List.of(LexicalUnit.SMALLER)));
		productionRules.put(GlsVariable.WHILE, List.of(
				List.of(LexicalUnit.WHILE, LexicalUnit.LBRACK, GlsVariable.COND, LexicalUnit.RBRACK, LexicalUnit.REPEAT, GlsVariable.CODE, LexicalUnit.END)));
		productionRules.put(GlsVariable.OUTPUT, List.of(
				List.of(LexicalUnit.OUTPUT, LexicalUnit.LPAREN, LexicalUnit.VARNAME, LexicalUnit.RPAREN)));
		productionRules.put(GlsVariable.INPUT, List.of(
				List.of(LexicalUnit.INPUT, LexicalUnit.LPAREN, LexicalUnit.VARNAME, LexicalUnit.RPAREN)));
	}

	private void buildActionTable() {
		// For each rule variable -> production
		for (GlsVariable variable : this.getVariables()) {
			Set<LexicalUnit> first = getFirst(variable);
			for (List<Symbol> production : this.getProductionRule(variable)) {
				// Case 1: Production is epsilon
				if (production.isEmpty()) {
					for (LexicalUnit terminal : this.getFollow(variable)) {
						actionTable.put(new Pair<>(variable, terminal), production);
					}
				} // Case 2: Production starts with a terminal
				else if (production.getFirst() instanceof LexicalUnit) {
					actionTable.put(new Pair<>(variable, (LexicalUnit) production.getFirst()), production);
				} // Case 3: Production starts with a variable
				else {
					GlsVariable otherVariable = (GlsVariable) production.getFirst();
					Set<LexicalUnit> firstOther = getFirst(otherVariable);
					for (LexicalUnit terminal : firstOther) {
						actionTable.put(new Pair<>(variable, terminal), production);
					}
				}
			}
		}
	}

	public Set<GlsVariable> getVariables() {
		return Set.of(GlsVariable.values());
	}

	public Set<LexicalUnit> getTerminals() {
		return Set.of(LexicalUnit.values());
	}

	public GlsVariable getStartSymbol() {
		return GlsVariable.PROGRAM;
	}

	public Map<GlsVariable, List<List<Symbol>>> getProductionRules() {
		return productionRules;
	}

	public List<List<Symbol>> getProductionRule(GlsVariable variable) {
		return this.productionRules.get(variable);
	}

	public List<Symbol> getProduction(GlsVariable variable, LexicalUnit terminal) {
		return this.actionTable.get(new Pair<>(variable, terminal));
	}


	/**
	 * Returns the first set of a variable.
	 *
	 * @param variable the variable whose first set is to be retrieved
	 * @return the first set of the variable
	 */
	public Set<LexicalUnit> getFirst(GlsVariable variable) {
		Set<LexicalUnit> first = new HashSet<>();
		for (List<Symbol> production : this.getProductionRule(variable)) {
			if (production.isEmpty()) {
				continue;
			}
			Symbol firstToken = production.getFirst();
			if (firstToken instanceof LexicalUnit) {
				first.add((LexicalUnit) firstToken);
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
	public Set<LexicalUnit> getFollow(GlsVariable variable) {
		Set<LexicalUnit> follow = new HashSet<>();

		if (variable == this.getStartSymbol()) {
			follow.add(LexicalUnit.EOS);
		}

		for (GlsVariable v : getVariables()) {
			for (List<Symbol> production : this.getProductionRule(v)) {
				for (int i = 0; i < production.size(); i++) {
					if (production.get(i) == variable) {
						// Case 1: Variable is at the end of the production
						if (i == production.size() - 1) {
							if (v != variable) {
								follow.addAll(this.getFollow(v));
							}
							// TODO: Check if thing just under is correct
							//	else {
							//	    follow.addAll(this.getFirst(v));
							//	}
						}
						// Case 2: Variable is followed by other symbols
						else {
							Symbol nextToken = production.get(i + 1);
							if (nextToken instanceof LexicalUnit) {
								follow.add((LexicalUnit) nextToken);
							} else {
								// Add FIRST(nextToken)
								follow.addAll(this.getFirst((GlsVariable) nextToken));
								// If epsilon (empty list) is in FIRST(nextToken), include FOLLOW(v)
								if (this.getProductionRule((GlsVariable) nextToken).contains(List.of())) {
									follow.addAll(this.getFollow((GlsVariable) nextToken));
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
