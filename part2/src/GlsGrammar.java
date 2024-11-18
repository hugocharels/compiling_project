import java.util.*;


public class GlsGrammar implements CFGrammar<GlsToken, GlsVariable, GlsTerminal> {

	private static final Map<GlsVariable, List<List<GlsToken>>> productionRules = new HashMap<>();
	private static final Map<Pair<GlsVariable, GlsTerminal>, List<GlsToken>> actionTable = new HashMap<>();

	static {
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
				List.of(GlsTerminal.MINUS, GlsVariable.EXPR_ARITH)));
		productionRules.put(GlsVariable.IF, List.of(
				List.of(GlsTerminal.IF, GlsVariable.COND, GlsTerminal.THEN, GlsVariable.CODE, GlsVariable.IFSEQ)));
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
				List.of(GlsTerminal.WHILE, GlsVariable.COND, GlsTerminal.REPEAT, GlsVariable.CODE, GlsTerminal.END)));
		productionRules.put(GlsVariable.OUTPUT, List.of(
				List.of(GlsTerminal.OUT, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
		productionRules.put(GlsVariable.INPUT, List.of(
				List.of(GlsTerminal.IN, GlsTerminal.LPAREN, GlsTerminal.VARNAME, GlsTerminal.RPAREN)));
	}

	@Override
	public Set<GlsVariable> getVariables() {
		return Set.of(GlsVariable.values());
	}

	@Override
	public Set<GlsTerminal> getTerminals() {
		return Set.of(GlsTerminal.values());
	}

	@Override
	public Map<GlsVariable, List<List<GlsToken>>> getProductionRules() {
		return GlsGrammar.productionRules;
	}

	@Override
	public GlsVariable getStartSymbol() {
		return null;
	}


	@Override
	public Set<GlsTerminal> getFirst(GlsVariable variable) {
		Set<GlsTerminal> first = new HashSet<>();
		for (List<GlsToken> production : GlsGrammar.productionRules.get(variable)) {
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

	@Override
	public Set<GlsTerminal> getFollow(GlsVariable variable) {
		Set<GlsTerminal> follow = new HashSet<>();
		for (GlsVariable v : getVariables()) {
			if (v == variable) {
				continue;
			}
			for (List<GlsToken> production : this.getProductionRule(v)) {
				for (int i = 0; i < production.size(); i++) {
					if (production.get(i) == variable) {
						if (i == production.size() - 1) {
							follow.addAll(getFollow(v));
						} else {
							if (production.get(i + 1) instanceof GlsTerminal) {
								follow.add((GlsTerminal) production.get(i + 1));
							} else {
								follow.addAll(getFirst((GlsVariable) production.get(i + 1)));
							}
						}
					}
				}
			}
		}
		return follow;
	}

	@Override
	public Map<Pair<GlsVariable, GlsTerminal>, List<GlsToken>> getActionTable() {
		if (GlsGrammar.actionTable == null) {
			// TODO: Implement the action table
		}
		return GlsGrammar.actionTable;
	}
}
