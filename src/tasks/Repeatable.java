package tasks;

import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime repeatTask(LocalDateTime initialDate);
}
