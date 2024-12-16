package code;

// The common interface for all nodes
interface CodeComponent {
	void generateLLVM(StringBuilder llvmCode);
}