package task;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(), deadline);
    }
}
