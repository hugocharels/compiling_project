package compiler.code;

import java.util.HashSet;
import java.util.Set;

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

	// Method to get the singleton instance
	public static LabelManager getInstance() {
		if (instance == null) {
			instance = new LabelManager();
		}
		return instance;
	}

	// Method to generate a set of labels for a while loop
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

	// Method to generate a set of labels for an if statement
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

	// Method to allocate a label
	private void allocateLabel(String label) {
		allocatedLabels.add(label);
	}

	public String createTempCond() {
		return "Bool_" + (tempCounter++);
	}

	// Nested class to hold the labels for a single while loop
	public static class WhileLabels {
		private final String condLabel;
		private final String bodyLabel;
		private final String endLabel;

		public WhileLabels(String condLabel, String bodyLabel, String endLabel) {
			this.condLabel = condLabel;
			this.bodyLabel = bodyLabel;
			this.endLabel = endLabel;
		}

		public String getCondLabel() {
			return condLabel;
		}

		public String getBodyLabel() {
			return bodyLabel;
		}

		public String getEndLabel() {
			return endLabel;
		}
	}

	// Nested class to hold the labels for a single if statement
	public static class IfLabels {
		private final String bodyLabel;
		private final String elseLabel;
		private final String endLabel;

		public IfLabels(String bodyLabel, String elseLabel, String endLabel) {
			this.bodyLabel = bodyLabel;
			this.elseLabel = elseLabel;
			this.endLabel = endLabel;
		}

		public String getBodyLabel() {
			return bodyLabel;
		}

		public String getElseLabel() {
			return elseLabel;
		}

		public String getEndLabel() {
			return endLabel;
		}
	}
}
