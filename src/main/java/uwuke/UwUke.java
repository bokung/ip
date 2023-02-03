package uwuke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import uwuke.input.Advisor;
import uwuke.input.Command;

import uwuke.output.DukeException;
import uwuke.output.Printer;
import uwuke.output.Storage;

import uwuke.task.TaskList;

public class UwUke extends Application {

    private final static int CAPACITY = 100;
    private static TaskList tasks;
    private static Scanner sc = new Scanner(System.in);

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);
        setStage(stage);
        setScrollPane();
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        setAnchorPane();
        stage.setScene(scene);
        stage.show();
    }

    private static void setStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }



    private static void inititialise() {
        Printer.uwu();
        tasks = new TaskList(CAPACITY);
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file");
            tasks = new TaskList();
        }
        sc = new Scanner(System.in);
    }

    private static void run() {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.contains(",")) { // Can potentially cause fatal errors when trying to read files if commas were allowed.
                    throw new DukeException("Please do not use reserved character \',\'.");
                }
                switch (Command.matchCommand(input)) {
                case LIST:
                    Printer.printTasks(tasks.getList());
                    break;
                case DEADLINE:
                    tasks.addDeadline(input);
                    break;
                case EVENT:
                    tasks.addEvent(input);
                    break;
                case TODO:
                    tasks.addTodo(input);
                    break;
                case MARK:
                    tasks.markTask(input);
                    break;
                case UNMARK:
                    tasks.unmarkTask(input);
                    break;
                case DELETE:
                    tasks.deleteTask(input);
                    break;
                case FIND:
                    tasks.findTask(input);
                    break;
                default:
                    Printer.printWithDecorations(Advisor.advise(input));
                }
            } catch (Exception e) {
                Printer.printError(e.getMessage());
            } 

            try {
                input = sc.nextLine();
            } catch (Exception e) {
                Printer.printError("Error occurred when trying to read next line, try again.");
                input = "";
            }
        }

        try {
            Storage.saveTasks(tasks.getList());
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }
    }

    public static void main(String[] args) {
        inititialise();
        run();
        sc.close();
        Printer.printBye();
    }

}