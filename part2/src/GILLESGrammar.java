import java.util.List;
import java.util.Map;
import java.util.Set;


public class GILLESGrammar implements CFGrammar<GILLESToken, GILLESVariable, GILLESTerminal> {

	static final Map<GILLESVariable, List<List<GILLESToken>>> productionRules = Map.of(
			GILLESVariable.PROGRAM, List.of(
					List.of(GILLESTerminal.LET, GILLESTerminal.PROGNAME, GILLESTerminal.BE, GILLESVariable.CODE, GILLESTerminal.END)),
			GILLESVariable.CODE, List.of(
					List.of(GILLESVariable.INSTRUCTION, GILLESTerminal.COLUMN, GILLESVariable.CODE),
					List.of()),
			GILLESVariable.INSTRUCTION, List.of(
					List.of(GILLESVariable.ASSIGN),
					List.of(GILLESVariable.IF),
					List.of(GILLESVariable.WHILE),
					List.of(GILLESVariable.OUTPUT),
					List.of(GILLESVariable.INPUT)),
			GILLESVariable.ASSIGN, List.of(
					List.of(GILLESTerminal.VARNAME, GILLESTerminal.ASSIGN, GILLESVariable.EXPR_ARITH)),
			GILLESVariable.EXPR_ARITH, List.of(
					List.of(GILLESVariable.PROD_ARITH, GILLESVariable.EXPR_ARITH_PRIME)),
			GILLESVariable.EXPR_ARITH_PRIME, List.of(
					List.of(GILLESTerminal.PLUS, GILLESVariable.PROD_ARITH, GILLESVariable.EXPR_ARITH_PRIME),
					List.of(GILLESTerminal.MINUS, GILLESVariable.PROD_ARITH, GILLESVariable.EXPR_ARITH_PRIME),
					List.of()),
			GILLESVariable.PROD_ARITH, List.of(
					List.of(GILLESVariable.ATOM, GILLESVariable.PROD_ARITH_PRIME)),
			GILLESVariable.PROD_ARITH_PRIME, List.of(
					List.of(GILLESTerminal.MULT, GILLESVariable.ATOM, GILLESVariable.PROD_ARITH_PRIME),
					List.of(GILLESTerminal.DIV, GILLESVariable.ATOM, GILLESVariable.PROD_ARITH_PRIME),
					List.of()),
			GILLESVariable.ATOM, List.of(
					List.of(GILLESTerminal.NUMBER), List.of(GILLESTerminal.VARNAME),
					List.of(GILLESTerminal.LPAREN, GILLESVariable.EXPR_ARITH, GILLESTerminal.RPAREN),
					List.of(GILLESTerminal.MINUS, GILLESVariable.EXPR_ARITH)),
			GILLESVariable.IF, List.of(
					List.of(GILLESTerminal.IF, GILLESVariable.COND, GILLESTerminal.THEN, GILLESVariable.CODE, GILLESVariable.IFSEQ)),
			GILLESVariable.IFSEQ, List.of(
					List.of(GILLESTerminal.END),
					List.of(GILLESTerminal.ELSE, GILLESVariable.CODE, GILLESTerminal.END)),
			GILLESVariable.COND, List.of(
					List.of(GILLESVariable.COND_SIMPLE, GILLESVariable.NEXT_COND)),
			GILLESVariable.NEXT_COND, List.of(
					List.of(GILLESTerminal.IMPLIES, GILLESVariable.COND),
					List.of()),
			GILLESVariable.COND_SIMPLE, List.of(
					List.of(GILLESTerminal.PIPE, GILLESVariable.EXPR_ARITH, GILLESTerminal.PIPE),
					List.of(GILLESVariable.EXPR_ARITH, GILLESVariable.COMP, GILLESVariable.EXPR_ARITH)),
			GILLESVariable.COMP, List.of(
					List.of(GILLESTerminal.EQUAL),
					List.of(GILLESTerminal.GEQUAL),
					List.of(GILLESTerminal.GREATER)),
			GILLESVariable.WHILE, List.of(
					List.of(GILLESTerminal.WHILE, GILLESVariable.COND, GILLESTerminal.REPEAT, GILLESVariable.CODE, GILLESTerminal.END)),
			GILLESVariable.OUTPUT, List.of(
					List.of(GILLESTerminal.OUT, GILLESTerminal.LPAREN, GILLESTerminal.VARNAME, GILLESTerminal.RPAREN),
					List.of(GILLESTerminal.IN, GILLESTerminal.LPAREN, GILLESTerminal.NUMBER, GILLESTerminal.RPAREN)),
			);


	@Override
	public Set<GILLESVariable> getVariables() {
		return Set.of(GILLESVariable.values());
	}

	@Override
	public Set<GILLESTerminal> getTerminals() {
		return Set.of(GILLESTerminal.values());
	}

	@Override
	public Map<GILLESVariable, List<List<GILLESToken>>> getProductionRules() {
		return GILLESGrammar.productionRules;
	}

	@Override
	public GILLESVariable getStartSymbol() {
		return null;
	}
}
