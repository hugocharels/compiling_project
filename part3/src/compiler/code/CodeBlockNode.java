package compiler.code;

import compiler.ParseTree;
import compiler.GlsTerminal;

import java.util.ArrayList;
import java.util.List;

// Composite node: Represents a block of code
public class CodeBlockNode implements CodeComponent {
	private final List<CodeComponent> components = new ArrayList<>();

	public void addComponent(CodeComponent component) {
		components.add(component);
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		for (CodeComponent component : components) {
			if (component == null) {
				continue;
			}
			component.generateLLVM(llvmCode);
		}
	}

	public static CodeBlockNode fromParseTree(ParseTree parseTree) {
		CodeBlockNode block = new CodeBlockNode();
		for (ParseTree child : parseTree.getChildren()) {
			if (child.getLabel().equals(GlsTerminal.COLUMN)) {
				continue;
			}
			block.addComponent(CodeComponent.fromParseTree(child));
		}
		return block;
	}
}
