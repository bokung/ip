package uwuke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

import uwuke.output.Printer;

public class Deadline extends Task {

    private String deadlineString;
    private Optional<LocalDateTime> deadlineTime = Optional.empty();

    public Deadline(String task, String deadline) {
        super(task);
        this.deadlineString = deadline;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadlineTime = Optional.of(LocalDateTime.parse(deadline, formatter));
        } catch (DateTimeParseException e) {
            Printer.printError("Could not parse date");
        }
    }


    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", 
                    "[D]", 
                            super.toString(),
                            deadlineTime.map(Object::toString).orElse(deadlineString));
    }
}
