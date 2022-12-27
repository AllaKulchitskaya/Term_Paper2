package tasks;

import java.time.LocalDateTime;

public class OnceTask implements Repeatable {
    @Override
    public LocalDateTime repeatTask(LocalDateTime initialDate) {
        return null;
    }

    @Override
    public String toString() {
        return "однократная";
    }
}
