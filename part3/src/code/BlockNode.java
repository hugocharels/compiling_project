package code;

import java.util.ArrayList;
import java.util.List;

// Composite node: Represents a block of code
class BlockNode implements CodeComponent {
	private final List<CodeComponent> components = new ArrayList<>();

	public void addComponent(CodeComponent component) {
		components.add(component);
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		for (CodeComponent component : components) {
			component.generateLLVM(llvmCode);
		}
	}
}
