package tasks;

import java.time.LocalDateTime;

public class DailyTask implements Repeatable{
    @Override
    public LocalDateTime repeatTask(LocalDateTime initialDate) {
        return initialDate.plusDays(1);
    }

    @Override
    public String toString() {
        return "ежедневная";
    }
}
