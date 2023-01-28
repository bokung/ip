package output;

import java.util.ArrayList;

import task.Task;

public class Printer {
    public static void printWithDecorations(String input) {
        System.out.println("\nOwO OwO OwO OwO OwO OwO");
        System.out.println(input);
        System.out.println("OwO OwO OwO OwO OwO OwO\n");
    }

    public static void printWelcome() {
        String welcomeString = 
                "Hello from\n"
                +" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        printWithDecorations(welcomeString);
    }

    public static void uwu() {
        String uwu =
        "Hewwo from\n"+
        "_\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\_______\\/\\\\\\_       \n"+
        "__/\\\\\\________/\\\\\\___/\\\\\\______________/\\\\\\___/\\\\\\________/\\\\\\_       \n"+ 
        " _\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\_______\\/\\\\\\_      \n"+
        "  _\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\____/\\\\\\____/\\\\\\___\\/\\\\\\_______\\/\\\\\\_     \n"+
        "   _\\/\\\\\\_______\\/\\\\\\___\\//\\\\\\__/\\\\\\\\\\__/\\\\\\____\\/\\\\\\_______\\/\\\\\\_    \n"+
        "    _\\/\\\\\\_______\\/\\\\\\____\\//\\\\\\/\\\\\\/\\\\\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\_   \n"+
        "     _\\//\\\\\\______/\\\\\\______\\//\\\\\\\\\\\\//\\\\\\\\\\______\\//\\\\\\______/\\\\\\__  \n"+
        "      __\\///\\\\\\\\\\\\\\\\\\/________\\//\\\\\\__\\//\\\\\\________\\///\\\\\\\\\\\\\\\\\\/___ \n"+
        "       ____\\/////////___________\\///____\\///___________\\/////////_____\n";
       
        System.out.print(uwu);
    }

    public static void printBye() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDecorations(bye);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        String s;

        if (tasks.size() <= 0) {
            s = "No task found! Please add a task.";
        } else if (tasks.size() == 1) {
            s = "Here is the task in your list:\n";
        } else {
            s = "Here are the tasks in your list:\n";
        }
        
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i+1, tasks.get(i).toString()));
        }
        printWithDecorations(sb.toString());
    }

    public static void printAddedConfirmation(Task task, int numTasks) {
        String grammar = numTasks <= 1 ? "task" : "tasks";
        String s = String.format("Got it. I've added this task:\n   %s\nNow you have %d %s in the list.", task.toString(), numTasks, grammar);
        printWithDecorations(s);
    }

    public static void printNotEnoughSpace() {
        Printer.printWithDecorations("You have added too many tasks!");
    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println("Please try again.");
    }

    public static void printDeleteConfirmation(Task task, int numTasksAfterDelete) {
        String grammar = numTasksAfterDelete == 1 ? "task" : "tasks";
        String s = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.", 
                                 task.toString(), 
                                 numTasksAfterDelete, 
                                 grammar);
        printWithDecorations(s);    
    }
    
}