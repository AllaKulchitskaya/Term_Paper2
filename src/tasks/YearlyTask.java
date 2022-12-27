package tasks;

import java.time.LocalDateTime;

public class YearlyTask implements Repeatable{
    @Override
    public LocalDateTime repeatTask(LocalDateTime initialDate) {
        return initialDate.plusYears(1);
    }

    @Override
    public String toString() {
        return "ежегодная";
    }
}
