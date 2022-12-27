package tasks;

import java.time.LocalDateTime;

public class WeeklyTask implements Repeatable{
    @Override
    public LocalDateTime repeatTask(LocalDateTime initialDate) {
        return initialDate.plusWeeks(1);
    }

    @Override
    public String toString() {
        return "еженедельная";
    }
}
