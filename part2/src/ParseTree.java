import java.util.ArrayList;
import java.util.List;

/**
 * A skeleton class to represent parse trees. The arity is not fixed: a node can
 * have 0, 1 or more children. Trees are represented in the following way: Tree
 * :== Symbol * List&lt;Tree&gt; In other words, trees are defined recursively: A tree
 * is a root (with a label of type Symbol) and a list of trees children. Thus, a
 * leave is simply a tree with no children (its list of children is empty). This
 * class can also be seen as representing the Node of a tree, in which case a
 * tree is simply represented as its root.
 *
 * @author Léo Exibard, Sarah Winter
 */
public class ParseTree {
	private final Symbol label; // The label of the root of the tree
	private List<ParseTree> children; // Its children, which are trees themselves

	/**
	 * Creates a singleton tree with only a root labeled by lbl.
	 *
	 * @param lbl The label of the root
	 */
	public ParseTree(Symbol lbl) {
		this.label = lbl;
		this.children = new ArrayList<>(); // This tree has no children
	}

	/**
	 * Creates a tree with root labeled by lbl and children chdn.
	 *
	 * @param lbl  The label of the root
	 * @param chdn Its children
	 */
	public ParseTree(Symbol lbl, List<ParseTree> chdn) {
		this.label = lbl;
		this.children = chdn;
	}

	/**
	 * Adds a child to the tree.
	 *
	 * @param child The child to be added
	 */
	public void addChild(ParseTree child) {
		children.add(child);
	}

	/**
	 * Reverses the order of the children.
	 */
	public void reverseChildrenOrder() {
		this.children = this.children.reversed();
	}

	/**
	 * Writes the tree as LaTeX code.
	 *
	 * @return The LaTeX representation of the tree
	 */
	public String toLaTexTree() {
		StringBuilder treeTeX = new StringBuilder();
		treeTeX.append("[");
		treeTeX.append("{$" + label.toLatex() + "$}");
		treeTeX.append(" ");

		for (ParseTree child : children) {
			treeTeX.append(child.toLaTexTree());
		}
		treeTeX.append("]");
		return treeTeX.toString();
	}

	/**
	 * Writes the tree as TikZ code. TikZ is a language to specify drawings in LaTeX
	 * files.
	 *
	 * @return The TikZ representation of the tree
	 */
	public String toTikZ() {
		StringBuilder treeTikZ = new StringBuilder();
		treeTikZ.append("node {$");
		treeTikZ.append(label.toLatex());
		treeTikZ.append("$}\n");
		for (ParseTree child : children) {
			treeTikZ.append("child { ");
			treeTikZ.append(child.toTikZ());
			treeTikZ.append(" }\n");
		}
		return treeTikZ.toString();
	}

	/**
	 * Writes the tree as a TikZ picture. A TikZ picture embeds TikZ code so that
	 * LaTeX understands it.
	 *
	 * @return The TikZ picture representation of the tree
	 */
	public String toTikZPicture() {
		return "\\begin{tikzpicture}[tree layout]\n\\" + toTikZ() + ";\n\\end{tikzpicture}";
	}

	/**
	 * Writes the tree as a forest picture. Returns the tree in forest environment
	 * using the LaTeX code of the tree.
	 *
	 * @return The forest picture representation of the tree
	 */
	public String toForestPicture() {
		return "\\begin{forest}for tree={rectangle, draw, l sep=20pt}" + toLaTexTree() + ";\n\\end{forest}";
	}

	/**
	 * Writes the tree as a LaTeX document which can be compiled using PDFLaTeX.
	 * <br>
	 * <br>
	 * The result can be used with the command:
	 *
	 * <pre>
	 * pdflatex some-file.tex
	 * </pre>
	 *
	 * @return The LaTeX document representation of the tree
	 */
	public String toLaTeX() {
		return "\\documentclass[border=5pt]{standalone}\n\n\\usepackage{tikz}\n\\usepackage{forest}\n\\usepackage{amsmath}\n\n\\begin{document}\n\n"
				+ toForestPicture() + "\n\n\\end{document}\n%% Local Variables:\n%% TeX-engine: pdflatex\n%% End:";
	}
}