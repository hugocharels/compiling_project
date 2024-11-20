import java.util.List;

public class ProductionRule {
	private final int id;
	private final GlsVariable variable;
	private final List<Symbol> production;

	public ProductionRule(int id, GlsVariable variable, List<Symbol> production) {
		this.id = id;
		this.variable = variable;
		this.production = production;
	}

	public int getId() {
		return id;
	}

	public GlsVariable getVariable() {
		return variable;
	}

	public List<Symbol> getProduction() {
		return production;
	}

	@Override
	public String toString() {
		return variable + " -> " + production;
	}
}

