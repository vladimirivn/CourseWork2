import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

import exception.IncorrectArgumentException;
import exception.TaskNotFoundException;
import service.TaskService;
import tasks.*;

public class Main {

    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            getAllTasksByDate(scanner);
                            break;
                        case 4:
                            getAllDeletedTasks();
                            break;
                        case 5:
                            updateTask(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n" +
                "4. Получить все удаленные задачи\n5. Изменить название и описание задачи\n0. Выход");

    }

    private static void inputTask(Scanner scanner) {

        scanner.useDelimiter("\n");
        String title = inputTitleTask(scanner);
        String description = inputDescriptionTask(scanner);

        TaskType type = inputTaskType(scanner);

        LocalDateTime dateTime = inputDayTask(scanner);

        int repeatType = inputRepeatType(scanner);
        createTask(title, type, dateTime, description, repeatType);

    }

    private static String inputTitleTask(Scanner scanner) {


        System.out.print("Введите название задачи: ");
        String title = scanner.next();

        if (title == null || title.isEmpty() || title.isBlank()) {
            System.out.println("Необходимо ввести название задачи !");
//            return inputTitleTask(scanner);
        }

        return title;

    }

    private static String inputDescriptionTask(Scanner scanner) {

        System.out.print("Введите описание задачи: ");
        String description = scanner.next();

        if (description == null || description.isEmpty() || description.isBlank()) {
            System.out.println("Необходимо ввести описание задачи !");
//            return inputDescriptionTask(scanner);
        }
        return description;
    }

    private static TaskType inputTaskType(Scanner scanner) {

        TaskType type = null;
        System.out.print("Укажите тип задачи (1 - Рабочая, 2 - Личная) : ");

        int taskType = scanner.nextInt();

        switch (taskType) {
            case 1:
                type = TaskType.WORK;
                break;
            case 2:
                type = TaskType.PERSONAL;
                break;
            default:
                System.out.println("Тип задачи введен некорректно!");
                break;
        }

        return type;
    }

    private static LocalDateTime inputDayTask(Scanner scanner) {

        System.out.print("Введите дату и время задачи (формат dd.MM.yyyy HH:mm) : ");

        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm ");
            scanner.next();
            System.out.println();
            return null;
        }
    }

    private static int inputRepeatType(Scanner scanner) {
        System.out.print("Введите повторяемость задачи (1 - Однократно, 2 - Ежедневно, " +
                "3 - Еженедельно, 4 - Ежемесячно, 5 - Ежегодно ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Введите число повторяемость задачи");
        }
        return -1;
    }

    private static void createTask(String title, TaskType type, LocalDateTime taskTime, String description, int repeatType) {
        Task task = null;
        try {
            switch (repeatType) {
                case 1:
                    task = new OneTimeTask(title, type, taskTime, description);
                    break;
                case 2:
                    task = new DailyTask(title, type, taskTime, description);
                    break;
                case 3:
                    task = new WeeklyTask(title, type, taskTime, description);
                    break;
                case 4:
                    task = new MonthlyTask(title, type, taskTime, description);
                    break;
                case 5:
                    task = new YearlyTask(title, type, taskTime, description);
                    break;
            }
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (task != null) {
            taskService.add(task);
            System.out.println("Задача успешно добавлена:");
            System.out.println(task);
            System.out.println();
        } else {
            System.out.println("Введены некорректные данные по задаче");
        }
    }

    private static void updateTask(Scanner scanner) {
        System.out.print("Введите № задачи: ");
        int id = scanner.nextInt();

        try {
            Task task = taskService.getTaskId(id);
            String title = inputTitleTask(scanner);
            String description = inputDescriptionTask(scanner);
            task.setTitle(title);
            task.setDescription(description);
            System.out.println("Задача №" + id + " обновлена");
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Введите № задачи: ");
        int id = scanner.nextInt();

        try {
            taskService.remove(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAllDeletedTasks() {

        Task[] tasks = taskService.getRemovedTasks().toArray(new Task[0]);
        if (tasks.length != 0) {
            System.out.println("Удалённые задачи:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("Нет удаленных задач!");
        }
    }

    private static void getAllTasksByDate(Scanner scanner) {

        System.out.print("Введите дату в формате dd.MM.yyyy :");

        if (scanner.hasNext(DATE_PATTERN)) {
            String dateTime = scanner.next(DATE_PATTERN);
            LocalDate inputDate = LocalDate.parse(dateTime, DATE_FORMATTER);

            Collection<Task> tasks = taskService.getAllByDate(inputDate);
            if (tasks.isEmpty()) {
                System.out.println("На дату: " + inputDate.toString() + " задач нет");
            } else {
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } else {
            System.out.print("Неверен формат даты!");
            scanner.next();
            System.out.println();
        }
    }
}