package zazu.data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        assertEquals("[D][ ] d1 (by: 11 Nov 2011)", new Deadline("d1",
                LocalDate.parse("2011-11-11")).toString());
    }
}
