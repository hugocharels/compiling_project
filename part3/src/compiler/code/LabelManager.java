package compiler.code;

import java.util.HashSet;
import java.util.Set;

public class LabelManager {
	// Singleton instance
	private static LabelManager instance;

	// Set to store allocated labels
	private Set<String> allocatedLabels;

	// Counter for generating unique identifiers for while loops
	private int whileCounter;

	// Private constructor to prevent instantiation
	private LabelManager() {
		this.allocatedLabels = new HashSet<>();
		this.whileCounter = 0;
	}

	// Method to get the singleton instance
	public static LabelManager getInstance() {
		if (instance == null) {
			synchronized (LabelManager.class) {
				if (instance == null) {
					instance = new LabelManager();
				}
			}
		}
		return instance;
	}

	// Method to generate a set of labels for a while loop
	public WhileLabels generateWhileLabels() {
		int currentId = whileCounter++;
		String condLabel = "while_cond_" + currentId;
		String bodyLabel = "while_body_" + currentId;
		String endLabel = "while_end_" + currentId;

		// Allocate all labels
		allocateLabel(condLabel);
		allocateLabel(bodyLabel);
		allocateLabel(endLabel);

		return new WhileLabels(condLabel, bodyLabel, endLabel);
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

	// Method to get all allocated labels (for debugging or analysis)
	public Set<String> getAllLabels() {
		return new HashSet<>(allocatedLabels); // Return a copy of the set
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
}
