package tasks;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDateTime taskDateTime;
    private Repeatable repeatability;
    private final int id;
    private static int idCount = 0;

    public Task(String title, String description, TaskType taskType,
                LocalDateTime taskDateTime, Repeatable repeatability)
            throws IllegalArgumentException{
        setTitle(title);
        setDescription(description);
        setTaskType(taskType);
        setTaskDateTime(taskDateTime);
        setRepeatability(repeatability);
        this.id = ++idCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException{
        if (StringUtils.isValid(title)) {
            throw new IllegalArgumentException("Заголовок задачи введен некорректно");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException{
        if (StringUtils.isValid(description)) {
            throw new IllegalArgumentException("Описание задачи введено некорректно");
        }
        this.description = description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) throws IllegalArgumentException {
        if (taskType == null) {
            throw new IllegalArgumentException("Тип задачи введен некорректно");
        }
        this.taskType = taskType;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public void setTaskDateTime(LocalDateTime taskDateTime) throws IllegalArgumentException {
        if (taskDateTime == null) {
            throw new IllegalArgumentException("Дата и время введены некорректно");
        }
        this.taskDateTime = taskDateTime;
    }

    public Repeatable getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(Repeatable repeatability) {
        if (repeatability == null) {
            repeatability = new OnceTask();
        }
        this.repeatability = repeatability;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Задача \"" + title + "\",\nid: " + id + ",\nописание: " + description +
                ",\nтип задачи: " + taskType + ",\nвремя выполнения задачи: " +
                taskDateTime.format(Constant.FORMATTER) + ",\nповторяемость: " + repeatability;
    }
}
