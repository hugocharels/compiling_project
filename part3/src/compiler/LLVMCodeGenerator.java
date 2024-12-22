package compiler;

import compiler.code.CodeBlockNode;
import compiler.code.StringBuilderWrapper;
import compiler.exceptions.CompilationException;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LLVMCodeGenerator {
	final ParseTree parseTree;
	final StringBuilderWrapper llvmCode = new StringBuilderWrapper();

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
		this.llvmCode.appendParagraph(
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
		this.llvmCode.appendParagraph(
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
	public void generateCode() throws CompilationException {
		// Verify for each variable if it is declared
		this.verifyVariables();

		// reset the LLVM code
		this.llvmCode.clear();

		// Add the LLVM code preamble
		this.addPreamble();

		// Add declarations main function
		this.llvmCode.appendln("define i32 @main() {");
		this.llvmCode.incrementIndentLevel();
		this.llvmCode.appendln("entry:");

		// ⟨Code⟩ is the third argument (others are useless)
		CodeBlockNode codeBlockNode = CodeBlockNode.fromParseTree(this.parseTree.getChildren().get(3));
		codeBlockNode.generateLLVM(this.llvmCode);

		// Add the return statement
		this.llvmCode.appendln("ret i32 0");
		this.llvmCode.decrementIndentLevel();
		this.llvmCode.appendln("}");
	}

	/**
	 * Returns the generated LLVM code.
	 *
	 * @return the LLVM code
	 */
	public String getLLVMCode() {
		return this.llvmCode.toString();
	}


	/**
	 * Verifies if all variables are declared.
	 *
	 * @throws CompilationException if a variable is not declared
	 */
	private void verifyVariables() throws CompilationException {
		Set<String> declaredVariables = new HashSet<>();

		// Get the variables from the parse tree
		Stack<ParseTree> stack = new Stack<>();
		stack.push(this.parseTree.getChild(3));
		while (!stack.isEmpty()) {
			// Pop the top of the stack
			ParseTree node = stack.pop();
			if (node.getLabel().equals(GlsVariable.ASSIGN)) {
				// Get the variable name
				String variableName = node.getChild(0).getLexicalSymbol().getValue().toString();
				if (!declaredVariables.contains(variableName)) {
					// For each variable in the expression, check if it is declared
					// Do another DFS to check if the variable is declared
					Stack<ParseTree> stack2 = new Stack<>();
					stack2.push(node.getChild(2));
					while (!stack2.isEmpty()) {
						ParseTree node2 = stack2.pop();
						if (node2.getLabel().equals(GlsTerminal.VARNAME)) {
							String variableName2 = node2.getLexicalSymbol().getValue().toString();
							if (!declaredVariables.contains(variableName2)) {
								throw new CompilationException("Variable '" + variableName + "' can not be assigned because variable '" + variableName2 + "' is not declared.");
							}
						} else if (node2.getChildren().size() > 0) {
							// Add all children (in the right order) to the stack
							for (int i = node2.getChildren().size() - 1; i >= 0; i--) {
								stack2.push(node2.getChildren().get(i));
							}
						}
					}
				}
				declaredVariables.add(variableName);
			} else if (node.getLabel().equals(GlsVariable.INPUT)) {
				// Get the variable name
				String variableName = node.getChild(2).getLexicalSymbol().getValue().toString();
				if (declaredVariables.contains(variableName)) {
					System.out.println(";Warning: Variable '" + variableName + "' has been declared multiple times with the 'IN' keyword.");
				}
				declaredVariables.add(variableName);
			} else if (node.getLabel().equals(GlsVariable.FOR)) {
				String variableName = node.getChild(2).getLexicalSymbol().getValue().toString();
				if (declaredVariables.contains(variableName)) {
					throw new CompilationException("Variable '" + variableName + "' has already been declared. Shadowing is not allowed.");
				}
				declaredVariables.add(variableName);
				stack.push(node.getChild(11)); // Add the code block to the stack
			} else if (node.getChildren().size() > 0) {
				// Add all children (in the right order) to the stack
				for (int i = node.getChildren().size() - 1; i >= 0; i--) {
					stack.push(node.getChildren().get(i));
				}
			} else if (node.getLabel().equals(GlsTerminal.VARNAME)) {
				// Get the variable name
				String variableName = node.getLexicalSymbol().getValue().toString();
				if (!declaredVariables.contains(variableName)) {
					throw new CompilationException("Variable '" + variableName + "' is not declared.");
				}
			}
		}
	}
}
