package compiler.code;

import java.util.HashSet;
import java.util.Set;

public class LabelManager {
	// Singleton instance
	private static LabelManager instance;

	// Set to store allocated labels
	private Set<String> allocatedLabels;

	// Counters for generating unique identifiers
	private int whileCounter;
	private int ifCounter;
	private boolean lastIsWhile;

	// Private constructor to prevent instantiation
	private LabelManager() {
		this.allocatedLabels = new HashSet<>();
		this.whileCounter = 0;
		this.ifCounter = 0;
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
		this.lastIsWhile = true;
		int currentId = whileCounter++;
		String condLabel = "while_" + currentId;
		String cond = "while_cond_" + currentId;
		String bodyLabel = "repeat_" + currentId;
		String endLabel = "while_end_" + currentId;

		// Allocate all labels
		allocateLabel(condLabel);
		allocateLabel(cond);
		allocateLabel(bodyLabel);
		allocateLabel(endLabel);

		return new WhileLabels(condLabel, cond, bodyLabel, endLabel);
	}

	// Method to generate a set of labels for an if statement
	public IfLabels generateIfLabels() {
		this.lastIsWhile = false;
		int currentId = ifCounter++;
		String cond = "if_cond_" + currentId;
		String bodyLabel = "then_" + currentId;
		String elseLabel = "else_" + currentId;
		String endLabel = "end_if_" + currentId;

		// Allocate all labels
		allocateLabel(cond);
		allocateLabel(bodyLabel);
		allocateLabel(elseLabel);
		allocateLabel(endLabel);

		return new IfLabels(cond, bodyLabel, elseLabel, endLabel);
	}

	// Method to allocate a label
	private void allocateLabel(String label) {
		allocatedLabels.add(label);
	}

	// Method to check if a label is allocated
	public boolean isLabelAllocated(String label) {
		return allocatedLabels.contains(label);
	}

	// Method to deallocate a label (optional)
	public void deallocateLabel(String label) {
		allocatedLabels.remove(label);
	}

	public String getLastCond() {
		if (lastIsWhile) {
			return "while_cond_" + (whileCounter-1);
		} else {
			return "if_cond_" + (ifCounter-1);
		}
	}

	// Nested class to hold the labels for a single while loop
	public static class WhileLabels {
		private final String condLabel;
		private final String cond;
		private final String bodyLabel;
		private final String endLabel;

		public WhileLabels(String condLabel, String cond, String bodyLabel, String endLabel) {
			this.condLabel = condLabel;
			this.cond = cond;
			this.bodyLabel = bodyLabel;
			this.endLabel = endLabel;
		}

		public String getCondLabel() {
			return condLabel;
		}

		public String getCond() {
			return cond;
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
		private final String cond;
		private final String bodyLabel;
		private final String elseLabel;
		private final String endLabel;

		public IfLabels(String cond, String bodyLabel, String elseLabel, String endLabel) {
			this.cond = cond;
			this.bodyLabel = bodyLabel;
			this.elseLabel = elseLabel;
			this.endLabel = endLabel;
		}

		public String getCond() {
			return cond;
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
