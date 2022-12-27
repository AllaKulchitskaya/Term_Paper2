package taskService;

import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {
    private static final Map<Integer, Task> TO_DO_LIST = new HashMap<>();

    private TaskService() {
    }

    public static void addTask(Task task) {
        TO_DO_LIST.put(task.getId(), task);
    }

    public static Collection<Task> getToDoListForDay (LocalDate localDate) {
        Collection<Task> toDoListForDay = new ArrayList<>();
        for (Task task : TO_DO_LIST.values()) {
            LocalDateTime initialDateTime = task.getTaskDateTime();
            if (initialDateTime.toLocalDate().equals(localDate)) {
                toDoListForDay.add(task);
                continue;
            }
            LocalDateTime nextDateTime = initialDateTime;
            do {
                nextDateTime = task.getRepeatability().repeatTask(initialDateTime);
                if (nextDateTime == null) {
                    break;
                }
                if (nextDateTime.toLocalDate().equals(localDate)) {
                    toDoListForDay.add(task);
                    break;
                }
            } while (nextDateTime.toLocalDate().isBefore(localDate));
        }
        return toDoListForDay;
    }

    public static void removeTask(int id) {
        if (!TO_DO_LIST.containsKey(id)) {
            throw new NoSuchElementException("Задача не найдена");
        }
        TO_DO_LIST.remove(id);
    }
}
