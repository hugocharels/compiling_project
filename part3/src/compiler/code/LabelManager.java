package compiler.code;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages the generation and allocation of unique labels for while loops and if statements.
 * This class follows the Singleton design pattern.
 */
public class LabelManager {
	// Singleton instance
	private static LabelManager instance;

	// Set to store allocated labels
	private final Set<String> allocatedLabels;

	// Counters for generating unique identifiers
	private int whileCounter;
	private int ifCounter;
	private int tempCounter;

	// Private constructor to prevent instantiation
	private LabelManager() {
		this.allocatedLabels = new HashSet<>();
		this.whileCounter = 0;
		this.ifCounter = 0;
		this.tempCounter = 0;
	}

	/**
	 * Returns the singleton instance of LabelManager.
	 *
	 * @return the singleton instance
	 */
	public static LabelManager getInstance() {
		if (instance == null) {
			instance = new LabelManager();
		}
		return instance;
	}

	/**
	 * Generates a set of labels for a while loop.
	 *
	 * @return a WhileLabels object containing the labels for the while loop
	 */
	public WhileLabels generateWhileLabels() {
		int currentId = whileCounter++;
		String condLabel = "While_" + currentId;
		String bodyLabel = "Repeat_" + currentId;
		String endLabel = "While_end_" + currentId;

		// Allocate all labels
		allocateLabel(condLabel);
		allocateLabel(bodyLabel);
		allocateLabel(endLabel);

		return new WhileLabels(condLabel, bodyLabel, endLabel);
	}

	/**
	 * Generates a set of labels for an if statement.
	 *
	 * @return an IfLabels object containing the labels for the if statement
	 */
	public IfLabels generateIfLabels() {
		int currentId = ifCounter++;
		String bodyLabel = "Then_" + currentId;
		String elseLabel = "Else_" + currentId;
		String endLabel = "End_if_" + currentId;

		// Allocate all labels
		allocateLabel(bodyLabel);
		allocateLabel(elseLabel);
		allocateLabel(endLabel);

		return new IfLabels(bodyLabel, elseLabel, endLabel);
	}

	/**
	 * Allocates a label by adding it to the set of allocated labels.
	 *
	 * @param label the label to allocate
	 */
	private void allocateLabel(String label) {
		allocatedLabels.add(label);
	}

	/**
	 * Creates a temporary condition label.
	 *
	 * @return a unique temporary condition label
	 */
	public String createTempCond() {
		return "Bool_" + (tempCounter++);
	}

	/**
	 * Holds the labels for a single while loop.
	 */
	public static class WhileLabels {
		private final String condLabel;
		private final String bodyLabel;
		private final String endLabel;

		/**
		 * Constructs a WhileLabels object.
		 *
		 * @param condLabel the condition label
		 * @param bodyLabel the body label
		 * @param endLabel the end label
		 */
		public WhileLabels(String condLabel, String bodyLabel, String endLabel) {
			this.condLabel = condLabel;
			this.bodyLabel = bodyLabel;
			this.endLabel = endLabel;
		}

		/**
		 * Returns the condition label.
		 *
		 * @return the condition label
		 */
		public String getCondLabel() {
			return condLabel;
		}

		/**
		 * Returns the body label.
		 *
		 * @return the body label
		 */
		public String getBodyLabel() {
			return bodyLabel;
		}

		/**
		 * Returns the end label.
		 *
		 * @return the end label
		 */
		public String getEndLabel() {
			return endLabel;
		}
	}

	/**
	 * Holds the labels for a single if statement.
	 */
	public static class IfLabels {
		private final String bodyLabel;
		private final String elseLabel;
		private final String endLabel;

		/**
		 * Constructs an IfLabels object.
		 *
		 * @param bodyLabel the body label
		 * @param elseLabel the else label
		 * @param endLabel the end label
		 */
		public IfLabels(String bodyLabel, String elseLabel, String endLabel) {
			this.bodyLabel = bodyLabel;
			this.elseLabel = elseLabel;
			this.endLabel = endLabel;
		}

		/**
		 * Returns the body label.
		 *
		 * @return the body label
		 */
		public String getBodyLabel() {
			return bodyLabel;
		}

		/**
		 * Returns the else label.
		 *
		 * @return the else label
		 */
		public String getElseLabel() {
			return elseLabel;
		}

		/**
		 * Returns the end label.
		 *
		 * @return the end label
		 */
		public String getEndLabel() {
			return endLabel;
		}
	}
}
