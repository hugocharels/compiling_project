import java.util.*;


public class GlsGrammar {

	private final Map<Pair<GlsVariable, GlsTerminal>, ProductionRule> actionTable = new HashMap<>();
	private final List<ProductionRule> productionRules = new ArrayList<>(35);

	public GlsGrammar() {
		this.buildProductionRules();
		this.buildActionTable();
	}

	private void buildProductionRules() {
		this.productionRules.add(new ProductionRule(1, GlsVariable.PROGRAM, List.of(GlsTerminal.LET, GlsTerminal.PROGNAME, GlsTerminal.BE, GlsVariable.CODE, GlsTerminal.END)));
		this.productionRules.add(new ProductionRule(2, GlsVariable.CODE, List.of(GlsVariable.INSTRUCTION, GlsTerminal.COLUMN, GlsVariable.CODE)));
		this.productionRules.add(new ProductionRule(3, GlsVariable.CODE, List.of(GlsTerminal.EPSILON)));
		this.productionRules.add(new ProductionRule(4, GlsVariable.INSTRUCTION, List.of(GlsVariable.ASSIGN)));
		this.productionRules.add(new ProductionRule(5, GlsVariable.INSTRUCTION, List.of(GlsVariable.IF)));
		this.productionRules.add(new ProductionRule(6, GlsVariable.INSTRUCTION, List.of(GlsVariable.WHILE)));
		this.productionRules.add(new ProductionRule(7, GlsVariable.INSTRUCTION, List.of(GlsVariable.OUTPUT)));
		this.productionRules.add(new ProductionRule(8, GlsVariable.INSTRUCTION, List.of(GlsVariable.INPUT)));
		this.productionRules.add(new ProductionRule(9, GlsVariable.ASSIGN, List.of(GlsTerminal.VARNAME, GlsTerminal.ASSIGN, GlsVariable.EXPR_ARITH)));
		this.productionRules.add(new ProductionRule(10, GlsVariable.EXPR_ARITH, List.of(GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(11, GlsVariable.EXPR_ARITH_PRIME, List.of(GlsTerminal.PLUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(12, GlsVariable.EXPR_ARITH_PRIME, List.of(GlsTerminal.MINUS, GlsVariable.PROD_ARITH, GlsVariable.EXPR_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(13, GlsVariable.EXPR_ARITH_PRIME, List.of(GlsTerminal.EPSILON)));
		this.productionRules.add(new ProductionRule(14, GlsVariable.PROD_ARITH, List.of(GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(15, GlsVariable.PROD_ARITH_PRIME, List.of(GlsTerminal.TIMES, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(16, GlsVariable.PROD_ARITH_PRIME, List.of(GlsTerminal.DIVIDE, GlsVariable.ATOM, GlsVariable.PROD_ARITH_PRIME)));
		this.productionRules.add(new ProductionRule(17, GlsVariable.PROD_ARITH_PRIME, List.of(GlsTerminal.EPSILON)));
		this.productionRules.add(new ProductionRule(18, GlsVariable.ATOM, List.of(GlsTerminal.NUMBER)));
		this.productionRules.add(new ProductionRule(19, GlsVariable.ATOM, List.of(GlsTerminal.VARNAME)));
		this.productionRules.add(new ProductionRule(20, GlsVariable.ATOM, List.of(GlsTerminal.LPAREN, GlsVariable.EXPR_ARITH, GlsTerminal.RPAREN)));
		this.productionRules.add(new ProductionRule(21, GlsVariable.ATOM, List.of(GlsTerminal.MINUS, GlsVariable.ATOM)));
		this.productionRules.add(new ProductionRule(22, GlsVariable.IF, List.of(GlsTerminal.IF, GlsTerminal.LBRACK, GlsVariable.COND, GlsTerminal.RBRACK, GlsTerminal.THEN, GlsVariable.CODE, GlsVariable.IFSEQ)));
		this.productionRules.add(new ProductionRule(23, GlsVariable.IFSEQ, List.of(GlsTerminal.END)));
		this.productionRules.add(new ProductionRule(24, GlsVariable.IFSEQ, List.of(GlsTerminal.ELSE, GlsVariable.CODE, GlsTerminal.END)));
		this.productionRules.add(new ProductionRule(25, GlsVariable.COND, List.of(GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		this.productionRules.add(new ProductionRule(26, GlsVariable.NEXT_COND, List.of(GlsTerminal.IMPLIES, GlsVariable.COND_SIMPLE, GlsVariable.NEXT_COND)));
		this.productionRules.add(new ProductionRule(27, GlsVariable.NEXT_COND, List.of(GlsTerminal.EPSILON)));
		this.productionRules.add(new ProductionRule(28, GlsVariable.COND_SIMPLE, List.of(GlsTerminal.PIPE, GlsVariable.COND_SIMPLE, GlsTerminal.PIPE)));
		this.productionRules.add(new ProductionRule(29, GlsVariable.COND_SIMPLE, List.of(GlsVariable.EXPR_ARITH, GlsVariable.COMP, GlsVariable.EXPR_ARITH)));
		this.productionRules.add(new ProductionRule(30, GlsVariable.COMP, List.of(GlsTerminal.EQUAL)));
		this.productionRules.add(new ProductionRule(31, GlsVariable.COMP, List.of(GlsTerminal.SMALEQ)));
		this.productionRules.add(new ProductionRule(32, GlsVariable.COMP, List.of(GlsTerminal.SMALLER)));
		this.productionRules.add(new ProductionRule(33, GlsVariable.WHILE, List.of(GlsTerminal.WHILE, GlsTerminal.LBRACK, GlsVariable.COND, GlsTerminal.RBRACK, GlsTerminal.REPEAT, GlsVariable.CODE, GlsTerminal.END)));
		this.productionRules.add(new ProductionRule(34, GlsVariable.OUTPUT, List.of(GlsTerminal.OUTPUT, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
		this.productionRules.add(new ProductionRule(35, GlsVariable.INPUT, List.of(GlsTerminal.INPUT, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
	}

	private void buildActionTable() {
		// For each rule variable -> production
		for (GlsVariable variable : this.getVariables()) {
			for (ProductionRule productionRule : this.getProductionRules(variable)) {
				// Case 1: Production is epsilon
				if (productionRule.getProduction().getFirst() == GlsTerminal.EPSILON) {
					for (GlsTerminal terminal : this.getFollow(variable)) {
						actionTable.put(new Pair<>(variable, terminal), productionRule);
					}
				} // Case 2: Production starts with a terminal
				else if (productionRule.getProduction().getFirst() instanceof GlsTerminal) {
					actionTable.put(new Pair<>(variable, (GlsTerminal) productionRule.getProduction().getFirst()), productionRule);
				} // Case 3: Production starts with a variable
				else {
					GlsVariable otherVariable = (GlsVariable) productionRule.getProduction().getFirst();
					Set<GlsTerminal> firstOther = getFirst(otherVariable);
					for (GlsTerminal terminal : firstOther) {
						actionTable.put(new Pair<>(variable, terminal), productionRule);
					}
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

	public ProductionRule getProduction(GlsVariable variable, GlsTerminal terminal) {
		return this.actionTable.get(new Pair<>(variable, terminal));
	}

	/**
	 * Returns the first set of a variable.
	 *
	 * @param variable the variable whose first set is to be retrieved
	 * @return the first set of the variable
	 */
	public Set<GlsTerminal> getFirst(GlsVariable variable) {
		Set<GlsTerminal> first = new HashSet<>();
		for (ProductionRule productionRule : this.getProductionRules(variable)) {
			Symbol firstSymbol = productionRule.getProduction().getFirst();
			if (firstSymbol instanceof GlsTerminal) {
				first.add((GlsTerminal) firstSymbol);
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
	public Set<GlsTerminal> getFollow(GlsVariable variable) {
		Set<GlsTerminal> follow = new HashSet<>();

		if (variable == this.getStartSymbol()) {
			follow.add(GlsTerminal.EOS);
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
						}
						// Case 2: Variable is followed by other symbols
						else {
							Symbol nextSymbol = productionRule.getProduction().get(i + 1);
							if (nextSymbol instanceof GlsTerminal) {
								follow.add((GlsTerminal) nextSymbol);
							} else {
								// Add FIRST(nextSymbol)
								follow.addAll(this.getFirst((GlsVariable) nextSymbol));
								// If epsilon is in FIRST(nextSymbol), include FOLLOW(v)
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
			if (productionRule.getProduction().getFirst() == GlsTerminal.EPSILON) {
				return true;
			}
		}
		return false;
	}

	public String toLatex() {
		List<GlsTerminal> sortedTerminals = List.of(GlsTerminal.LET, GlsTerminal.PROGNAME, GlsTerminal.BE, GlsTerminal.END, GlsTerminal.ELSE, GlsTerminal.IF, GlsTerminal.THEN, GlsTerminal.WHILE, GlsTerminal.REPEAT, GlsTerminal.OUTPUT, GlsTerminal.INPUT, GlsTerminal.VARNAME, GlsTerminal.NUMBER, GlsTerminal.LPAREN, GlsTerminal.RPAREN, GlsTerminal.COLUMN, GlsTerminal.LBRACK, GlsTerminal.RBRACK, GlsTerminal.ASSIGN, GlsTerminal.PLUS, GlsTerminal.MINUS, GlsTerminal.TIMES, GlsTerminal.DIVIDE, GlsTerminal.EQUAL, GlsTerminal.SMALEQ, GlsTerminal.SMALLER, GlsTerminal.IMPLIES, GlsTerminal.PIPE, GlsTerminal.EOS, GlsTerminal.EPSILON);
		List<GlsVariable> sortedVariables = List.of(GlsVariable.PROGRAM, GlsVariable.CODE, GlsVariable.INSTRUCTION, GlsVariable.ASSIGN, GlsVariable.EXPR_ARITH, GlsVariable.EXPR_ARITH_PRIME, GlsVariable.PROD_ARITH, GlsVariable.PROD_ARITH_PRIME, GlsVariable.ATOM, GlsVariable.IF, GlsVariable.IFSEQ, GlsVariable.COND, GlsVariable.NEXT_COND, GlsVariable.COND_SIMPLE, GlsVariable.COMP, GlsVariable.WHILE, GlsVariable.OUTPUT, GlsVariable.INPUT);
		// Check if sortedTerminals and sortedVariables size is equal to the size of the terminals and variables
		if (sortedTerminals.size() != this.getTerminals().size() || sortedVariables.size() != this.getVariables().size()) {
			throw new IllegalStateException("The sorted terminals and variables lists are not complete.");
		}
		StringBuilder latexCode = new StringBuilder();

		// --- Start building First and Follow sets ---
		latexCode.append("\\begin{align*}\n");
		for (GlsVariable variable : sortedVariables) {
			latexCode.append("\t\\\\texttt{FIRST}(" + variable.toLatex() + ") &= \\{");
			Set<GlsTerminal> first = this.getFirst(variable);
			for (GlsTerminal terminal : sortedTerminals) {
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
			Set<GlsTerminal> follow = this.getFollow(variable);
			for (GlsTerminal terminal : sortedTerminals) {
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
		for (GlsTerminal terminal : sortedTerminals) {
			if (terminal == GlsTerminal.EPSILON) { continue; }
			latexCode.append("&").append("$" + terminal.toLatex() + "$");
		}
		latexCode.append(" \\\\\n\\hline\\hline\n");
		for (GlsVariable variable : sortedVariables) {
			latexCode.append("$" + variable.toLatex() + "$");
			for (GlsTerminal terminal : sortedTerminals) {
				if (terminal == GlsTerminal.EPSILON) { continue; }
				ProductionRule rule = actionTable.getOrDefault(new Pair<>(variable, terminal), null);
				latexCode.append("&").append(rule != null ? rule.getId() : "");
			}
			latexCode.append(" \\\\\n\\hline\n");
		}
		latexCode.append("\\end{tabular}");

		return latexCode.toString();
	}

}
