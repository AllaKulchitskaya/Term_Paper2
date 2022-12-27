import taskService.TaskService;
import tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern PATTERN = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{2}:\\d{2}");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useDelimiter("\n");
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
                            removeTask(scanner);
                            break;
                        case 3:
                            getTaskListForDay(scanner);
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


    private static void inputTask(Scanner scanner) {
        try {
            System.out.print("Введите название задачи: ");
            String taskTitle = scanner.next();
            System.out.print("Введите описание задачи: ");
            String taskDescription = scanner.next();
            TaskType taskType = inputTaskType(scanner);
            LocalDateTime taskDateTime = inputDateTime(scanner);
            Repeatable repeatability = inputRepeatability(scanner);
            Task task = new Task(taskTitle, taskDescription, taskType, taskDateTime, repeatability);
            TaskService.addTask(task);
            System.out.println(task);
            System.out.println("Задача успешно добавлена");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректно введены параметры задачи");
        }
    }

    private static void removeTask(Scanner scanner) {
        try {
            do {
                System.out.print("Введите id задачи: ");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt();
                    TaskService.removeTask(id);
                    System.out.println("Задача успешно удалена");
                    break;
                } else {
                    scanner.next();
                }
            }
            while (true);
        } catch (NoSuchElementException e) {
            System.out.println("Задача не найдена");
        }
    }

    private static void getTaskListForDay(Scanner scanner) {
        try {
            do {
                System.out.print("Введите дату задачи в формате чч.мм.гггг: ");
                if (scanner.hasNext(DATE_PATTERN)) {
                    LocalDate taskDate = parseDate(scanner.next(DATE_PATTERN));
                    if (taskDate == null) {
                        System.out.println("Дата задачи введена некорректно");
                        continue;
                    }
                    Collection<Task> taskList = TaskService.getToDoListForDay(taskDate);
                    if (taskList.isEmpty()) {
                        System.out.println("Задач на эту дату не запланировано");
                    } else {
                        System.out.println("Список задач на " + taskDate.format(Constant.DATE_FORMATTER) + ":");
                        for (Task task : taskList) {
                            System.out.println(task.getTitle());
                        }
                    }
                    break;
                } else {
                    scanner.next();
                }
            }
            while (true);
        } catch (NoSuchElementException e) {
            System.out.println("Задача не найдена");
        }
    }

    private static TaskType inputTaskType (Scanner scanner) {
        TaskType taskType;
        do {
            System.out.print("Введите тип задачи (1. Личная. 2. Рабочая) : ");
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();
                if (userInput != 1 && userInput != 2) {
                    System.out.println("Неправильно выбран тип задачи");
                    continue;
                }
                if (userInput == 1) {
                    taskType = TaskType.PERSONAL;
                } else {
                    taskType = TaskType.WORK;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return taskType;
    }

    private static LocalDateTime inputDateTime(Scanner scanner) {
        LocalDateTime taskDateTime;
        do {
            System.out.print("Введите дату и время задачи в формате дд.мм.гггг чч:мм: ");
            if (scanner.hasNext(PATTERN)) {
                taskDateTime = parseDateTime(scanner.next(PATTERN));
                if (taskDateTime == null) {
                    System.out.println("Дата и время задачи введены некорректно");
                    continue;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return taskDateTime;
    }


    private static LocalDateTime parseDateTime(String userInput) {
        try {
            return LocalDateTime.parse(userInput, Constant.FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static LocalDate parseDate(String userInput) {
        try {
            return LocalDate.parse(userInput, Constant.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static Repeatable inputRepeatability(Scanner scanner) {
        Repeatable repeatability;
        do {
            System.out.print("Введите тип повторяемости задачи (1. Однократная. 2. Ежедневная. " +
                    "3. Еженедельная. 4. Ежемесячная. 5. Ежегодная): ");
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();
                if (userInput < 1 || userInput > 5) {
                    System.out.println("Неправильно выбран тип повторяемости задачи");
                    continue;
                }
                switch (userInput) {
                    default:
                    case 1:
                        repeatability = new OnceTask();
                        break;
                    case 2:
                        repeatability = new DailyTask();
                        break;
                    case 3:
                        repeatability = new WeeklyTask();
                        break;
                    case 4:
                        repeatability = new MonthlyTask();
                        break;
                    case 5:
                        repeatability = new YearlyTask();
                        break;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return repeatability;
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }
}