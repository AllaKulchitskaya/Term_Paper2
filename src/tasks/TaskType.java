package tasks;

public enum TaskType {
    PERSONAL("личная"),
    WORK("рабочая");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
