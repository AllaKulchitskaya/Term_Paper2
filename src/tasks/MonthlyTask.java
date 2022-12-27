package tasks;

import java.time.LocalDateTime;

public class MonthlyTask implements Repeatable{
    @Override
    public LocalDateTime repeatTask(LocalDateTime initialDate) {
        return initialDate.plusMonths(1);
    }

    @Override
    public String toString() {
        return "ежемесячная";
    }
}
