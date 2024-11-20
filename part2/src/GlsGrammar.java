import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GlsGrammar implements CFGrammar<GlsToken, GlsVariable, GlsTerminal> {

	private final Map<GlsVariable, List<List<GlsToken>>> productionRules = new HashMap<>();
	private final Map<Pair<GlsVariable, GlsTerminal>, List<GlsToken>> actionTable;

	public GlsGrammar() {
		// Production rules
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

		this.actionTable = this.buildActionTable();
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
		return productionRules;
	}

	@Override
	public GlsVariable getStartSymbol() {
		return null;
	}

	@Override
	public Map<Pair<GlsVariable, GlsTerminal>, List<GlsToken>> getActionTable() {
		return actionTable;
	}
}
