package compiler;

import compiler.code.CodeBlockNode;

public class LLVMCodeGenerator {
	final ParseTree parseTree;
	final StringBuilder llvmCode = new StringBuilder();

	/**
	 * Constructs an LLVMCodeGenerator with the specified parse tree.
	 *
	 * @param parseTree the parse tree
	 */
	public LLVMCodeGenerator(ParseTree parseTree) {
		this.parseTree = parseTree;
	}


	/**
	 * Adds the LLVM code preamble.
	 */
	private void addPreamble() {
		// Add the readInt function declaration for the IN() function
		this.llvmCode.append(
				"""
						@.strR = private unnamed_addr constant [3 x i8] c"%d\\00", align 1
						
						declare i32 @scanf(i8*, ...)
						
						define i32 @readInt() #0 {
						\t%1 = alloca i32, align 4
						\t%2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %1)
						\t%3 = load i32, i32* %1, align 4
						\tret i32 %3
						}
						"""
		);
		// Add the println function declaration for the OUT() function
		this.llvmCode.append(
				"""
						@.strP = private unnamed_addr constant [4 x i8] c"%d\\0A\\00", align 1
						
						declare i32 @printf(i8*, ...)
						
						define void @println(i32 %x) #0 {
						\t%1 = alloca i32, align 4
						\tstore i32 %x, i32* %1, align 4
						\t%2 = load i32, i32* %1, align 4
						\t%3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %2)
						\tret void
						}
						"""
		);
	}

	/**
	 * Generates the LLVM code from the parse tree.
	 */
	public void generateCode() {
		// reset the LLVM code
		this.llvmCode.setLength(0);

		// Add the LLVM code preamble
		this.addPreamble();

		// Add declarations main function
		this.llvmCode.append(
				"""
						define i32 @main() {
						\tentry:
						"""
		);

		CodeBlockNode.fromParseTree(this.parseTree.getChildren().get(3)).generateLLVM(this.llvmCode);

		/**
		 // Depth-first traversal of the parse tree
		 Stack<ParseTree> stack = new Stack<>();
		 stack.push(this.parseTree);
		 while (!stack.isEmpty()) {
		 // Pop the top of the stack
		 ParseTree node = stack.pop();

		 if (node.getChildren().size() > 0) {
		 // Add all children (in the right order) to the stack
		 for (int i = node.getChildren().size() - 1; i >= 0; i--) {
		 stack.push(node.getChildren().get(i));
		 }
		 } else {
		 // TODO: Implement the code generation for the parse tree nodes
		 System.out.println("label: " + node.getLabel() + " symbol: " + node.getLexicalSymbol().getValue());
		 }
		 }
		 **/

		// Add the return statement
		this.llvmCode.append(
				"""
						\tret i32 0
						}
						"""
		);
	}

	/**
	 * Returns the generated LLVM code.
	 *
	 * @return the LLVM code
	 */
	public String getLLVMCode() {
		return this.llvmCode.toString();
	}
}
