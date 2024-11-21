import java.util.*;


public class GlsGrammar {

	private final Map<Pair<GlsVariable, LexicalUnit>, ProductionRule> actionTable = new HashMap<>();
	private final List<ProductionRule> productionRules = new ArrayList<>(35);

	public GlsGrammar() {
		this.buildProductionRules();
		this.buildActionTable();
	}

	private void buildProductionRules() {
		this.productionRules.add(new ProductionRule(1, GlsVariable.PROGRAM, List.of(LexicalUnit.LET, LexicalUnit.PROGNAME, LexicalUnit.BE, GlsVariable.CODE, LexicalUnit.END)));
		this.productionRules.add(new ProductionRule(2, GlsVariable.CODE, List.of(GlsVariable.INSTRUCTION, LexicalUnit.COLUMN, GlsVariable.CODE)));
		this.productionRules.add(new ProductionRule(3, GlsVariable.CODE, List.of()));
		this.productionRules.add(new ProductionRule(4, GlsVariable.INSTRUCTION, List.of(GlsVariable.ASSIGN)));
		this.productionRules.add(new ProductionRule(5, GlsVariable.INSTRUCTION, List.of(GlsVariable.IF)));
		this.productionRules.add(new ProductionRule(6, GlsVariable.INSTRUCTION, List.of(GlsVariable.WHILE)));
		this.productionRules.add(new ProductionRule(7, GlsVariable.INSTRUCTION, List.of(GlsVariable.OUTPUT)));
		this.productionRules.add(new ProductionRule(8, GlsVariable.INSTRUCTION, List.of(GlsVariable.INPUT)));
		this.productionRules.add(new ProductionRule(9, GlsVariable.ASSIGN, List.of(LexicalUnit.VARNAME, LexicalUnit.ASSIGN, GlsVariable.EXPR_ARITH)));
		this.productionRules.add(new ProductionRule(10, GlsVariable.EXPR_ARITH, List.of(GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(11, GlsVariable.EXPR_ARITH_PRIME, List.of(LexicalUnit.PLUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(12, GlsVariable.EXPR_ARITH_PRIME, List.of(LexicalUnit.MINUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(13, GlsVariable.EXPR_ARITH_PRIME, List.of()));
		this.productionRules.add(new ProductionRule(14, GlsVariable.PROD_ARITH, List.of(GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(15, GlsVariable.PROD_ARITH_PRIME, List.of(LexicalUnit.TIMES, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(16, GlsVariable.PROD_ARITH_PRIME, List.of(LexicalUnit.DIVIDE, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(17, GlsVariable.PROD_ARITH_PRIME, List.of()));
		this.productionRules.add(new ProductionRule(18, GlsVariable.ATOM, List.of(LexicalUnit.NUMBER)));
		this.productionRules.add(new ProductionRule(19, GlsVariable.ATOM, List.of(LexicalUnit.VARNAME)));
		this.productionRules.add(new ProductionRule(20, GlsVariable.ATOM, List.of(LexicalUnit.LPAREN, GlsVariable.EXPR_ARITH, LexicalUnit.RPAREN)));
		this.productionRules.add(new ProductionRule(21, GlsVariable.ATOM, List.of(LexicalUnit.MINUS, GlsVariable.ATOM)));
		this.productionRules.add(new ProductionRule(22, GlsVariable.IF, List.of(LexicalUnit.IF, LexicalUnit.LBRACK, GlsVariable.COND, LexicalUnit.RBRACK, LexicalUnit.THEN, GlsVariable.CODE, GlsVariable.IFSEQ)));
		this.productionRules.add(new ProductionRule(23, GlsVariable.IFSEQ, List.of(LexicalUnit.END)));
		this.productionRules.add(new ProductionRule(24, GlsVariable.IFSEQ, List.of(LexicalUnit.ELSE, GlsVariable.CODE, LexicalUnit.END)));
		this.productionRules.add(new ProductionRule(25, GlsVariable.COND, List.of(GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		this.productionRules.add(new ProductionRule(26, GlsVariable.NEXT_COND, List.of(LexicalUnit.IMPLIES, GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		this.productionRules.add(new ProductionRule(27, GlsVariable.NEXT_COND, List.of()));
		this.productionRules.add(new ProductionRule(28, GlsVariable.COND_SIMPLE, List.of(LexicalUnit.PIPE, GlsVariable.COND_SIMPLE, LexicalUnit.PIPE)));
		this.productionRules.add(new ProductionRule(29, GlsVariable.COND_SIMPLE, List.of(GlsVariable.EXPR_ARITH, GlsVariable.COMP, GlsVariable.EXPR_ARITH)));
		this.productionRules.add(new ProductionRule(30, GlsVariable.COMP, List.of(LexicalUnit.EQUAL)));
		this.productionRules.add(new ProductionRule(31, GlsVariable.COMP, List.of(LexicalUnit.SMALEQ)));
		this.productionRules.add(new ProductionRule(32, GlsVariable.COMP, List.of(LexicalUnit.SMALLER)));
		this.productionRules.add(new ProductionRule(33, GlsVariable.WHILE, List.of(LexicalUnit.WHILE, LexicalUnit.LBRACK, GlsVariable.COND, LexicalUnit.RBRACK, LexicalUnit.REPEAT, GlsVariable.CODE, LexicalUnit.END)));
		this.productionRules.add(new ProductionRule(34, GlsVariable.OUTPUT, List.of(LexicalUnit.OUTPUT, LexicalUnit.LPAREN, LexicalUnit.VARNAME, LexicalUnit.RPAREN)));
		this.productionRules.add(new ProductionRule(35, GlsVariable.INPUT, List.of(LexicalUnit.INPUT, LexicalUnit.LPAREN, LexicalUnit.VARNAME, LexicalUnit.RPAREN)));
	}

	private void buildActionTable() {
		// For each rule variable -> production
		for (GlsVariable variable : this.getVariables()) {
			for (ProductionRule productionRule : this.getProductionRules(variable)) {
				// Case 1: Production is epsilon
				if (productionRule.getProduction().isEmpty()) {
					for (LexicalUnit terminal : this.getFollow(variable)) {
						actionTable.put(new Pair<>(variable, terminal), productionRule);
					}
				} // Case 2: Production starts with a terminal
				else if (productionRule.getProduction().getFirst() instanceof LexicalUnit) {
					actionTable.put(new Pair<>(variable, (LexicalUnit) productionRule.getProduction().getFirst()), productionRule);
				} // Case 3: Production starts with a variable
				else {
					GlsVariable otherVariable = (GlsVariable) productionRule.getProduction().getFirst();
					Set<LexicalUnit> firstOther = getFirst(otherVariable);
					for (LexicalUnit terminal : firstOther) {
						actionTable.put(new Pair<>(variable, terminal), productionRule);
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

	public List<ProductionRule> getProductionRules(GlsVariable variable) {
		List<ProductionRule> rules = new ArrayList<>();
		for (ProductionRule rule : this.productionRules) {
			if (rule.getVariable() == variable) {
				rules.add(rule);
			}
		}
		return rules;
	}

	public ProductionRule getProduction(GlsVariable variable, LexicalUnit terminal) {
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
		for (ProductionRule productionRule : this.getProductionRules(variable)) {
			if (productionRule.getProduction().isEmpty()) {
				continue;
			}
			Symbol firstSymbol = productionRule.getProduction().getFirst();
			if (firstSymbol instanceof LexicalUnit) {
				first.add((LexicalUnit) firstSymbol);
			} else {
				first.addAll(getFirst((GlsVariable) firstSymbol));
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
			for (ProductionRule productionRule : this.getProductionRules(v)) {
				for (int i = 0; i < productionRule.getProduction().size(); i++) {
					if (productionRule.getProduction().get(i) == variable) {
						// Case 1: Variable is at the end of the production
						if (i == productionRule.getProduction().size() - 1) {
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
							Symbol nextSymbol = productionRule.getProduction().get(i + 1);
							if (nextSymbol instanceof LexicalUnit) {
								follow.add((LexicalUnit) nextSymbol);
							} else {
								// Add FIRST(nextSymbol)
								follow.addAll(this.getFirst((GlsVariable) nextSymbol));
								// If epsilon (empty list in production of productionRUle) is in FIRST(nextSymbol), include FOLLOW(v)
								if (this.hasEmptyProduction((GlsVariable) nextSymbol)) {
									follow.addAll(this.getFollow((GlsVariable) nextSymbol));
								}
							}
						}
					}
				}
			}
		}
		return follow;
	}

	private boolean hasEmptyProduction(GlsVariable nextSymbol) {
		for (ProductionRule productionRule : this.getProductionRules(nextSymbol)) {
			if (productionRule.getProduction().isEmpty()) {
				return true;
			}
		}
		return false;
	}


	public String toLatex() {
		List<LexicalUnit> sortedTerminals = List.of(LexicalUnit.LET, LexicalUnit.PROGNAME, LexicalUnit.BE, LexicalUnit.END, LexicalUnit.ELSE, LexicalUnit.IF, LexicalUnit.THEN, LexicalUnit.WHILE, LexicalUnit.REPEAT, LexicalUnit.OUTPUT, LexicalUnit.INPUT, LexicalUnit.VARNAME, LexicalUnit.NUMBER, LexicalUnit.LPAREN, LexicalUnit.RPAREN, LexicalUnit.COLUMN, LexicalUnit.LBRACK, LexicalUnit.RBRACK,LexicalUnit.ASSIGN,LexicalUnit.PLUS,LexicalUnit.MINUS,LexicalUnit.TIMES,LexicalUnit.DIVIDE,LexicalUnit.EQUAL,LexicalUnit.SMALEQ,LexicalUnit.SMALLER, LexicalUnit.IMPLIES,LexicalUnit.PIPE,LexicalUnit.EOS);
		List<GlsVariable> sortedVariables = List.of(GlsVariable.PROGRAM,GlsVariable.CODE,GlsVariable.INSTRUCTION,GlsVariable.ASSIGN,GlsVariable.EXPR_ARITH,GlsVariable.EXPR_ARITH_PRIME,GlsVariable.PROD_ARITH,GlsVariable.PROD_ARITH_PRIME,GlsVariable.ATOM,GlsVariable.IF,GlsVariable.IFSEQ, GlsVariable.COND, GlsVariable.NEXT_COND, GlsVariable.COND_SIMPLE, GlsVariable.COMP,GlsVariable.WHILE,GlsVariable.OUTPUT,GlsVariable.INPUT);
		// Check if sortedTerminals and sortedVariables size is equal to the size of the terminals and variables
		if (sortedTerminals.size() != this.getTerminals().size() || sortedVariables.size() != this.getVariables().size()) {
			throw new IllegalStateException("The sorted terminals and variables lists are not complete.");
		}
		StringBuilder latexCode = new StringBuilder();

		// --- Start building First and Follow sets ---
		latexCode.append("\\begin{align*}\n");
		for (GlsVariable variable : sortedVariables) {
			latexCode.append("\t\\\\texttt{FIRST}(" + variable.toLatex() + ") &= \\{");
			Set<LexicalUnit> first = this.getFirst(variable);
			for (LexicalUnit terminal : sortedTerminals) {
				if (first.contains(terminal)) {
					latexCode.append("$" + terminal.toLatex() + "$, ");
				}
			}
			// Remove the last comma and space
			if (!first.isEmpty()) {
				latexCode.deleteCharAt(latexCode.length() - 2);
			}
			latexCode.append("\\}\n");
		}
		// Remove the last newline
		latexCode.deleteCharAt(latexCode.length() - 1);
		latexCode.append("\n\\end{align*}\n\n");

		latexCode.append("\\begin{align*}\n");
		for (GlsVariable variable : sortedVariables) {
			latexCode.append("\t\\\\texttt{FOLLOW}(" + variable.toLatex() + ") &= \\{");
			Set<LexicalUnit> follow = this.getFollow(variable);
			for (LexicalUnit terminal : sortedTerminals) {
				if (follow.contains(terminal)) {
					latexCode.append("$" + terminal.toLatex() + "$, ");
				}
			}
			// Remove the last comma and space
			if (!follow.isEmpty()) {
				latexCode.deleteCharAt(latexCode.length() - 2);
			}
			latexCode.append("\\}\n");
		}
		// Remove the last newline
		latexCode.deleteCharAt(latexCode.length() - 1);
		latexCode.append("\n\\end{align*}\n\n");


		// --- Start building Action table ---
		latexCode.append("\\begin{tabular}{|l||")
				.append("c|".repeat(sortedTerminals.size()))
				.append("}\n\\hline\n");
		latexCode.append("Variable");
		for (LexicalUnit terminal : sortedTerminals) {
			latexCode.append("&").append("$" + terminal.toLatex() + "$");
		}
		latexCode.append(" \\\\\n\\hline\\hline\n");
		for (GlsVariable variable : sortedVariables) {
			latexCode.append("$" + variable.toLatex() + "$");
			for (LexicalUnit terminal : sortedTerminals) {
				ProductionRule rule = actionTable.getOrDefault(new Pair<>(variable, terminal), null);
				latexCode.append("&").append(rule != null ? rule.getId() : "");
			}
			latexCode.append(" \\\\\n\\hline\n");
		}
		latexCode.append("\\end{tabular}");

		return latexCode.toString();
	}

}
