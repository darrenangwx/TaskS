package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();


        System.out.println();
        System.out.println("Printing deadlines before sorting");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Printing deadlines after sorting");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> filteredList = filterTaskListUsingStreams(tasksData, "11");
        System.out.println("\nFiltered List of tasks:");
        printData(filteredList);
        printData(tasksData);
        printDataUsingStreams(tasksData);

        System.out.println("Total number of deadlines using streams: " + countDeadlineUsingStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    private static int countDeadlineUsingStream(ArrayList<Task> tasks){
        int count;
        System.out.println("Printing deadline count using streams");
        count = (int)tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();
        return count;
    }
    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks){
        System.out.println("Printing data using streams");
        tasks.stream()
                .forEach(System.out::println);

    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadline using streams");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a,b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))// u can give any number of parameters here
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<Task> task, String filteredString) {
        ArrayList<Task> filteredList = (ArrayList<Task>)task.stream()
                .filter(t -> t.getDescription().contains(filteredString))
                .collect(toList());

        return filteredList;
    }
}
