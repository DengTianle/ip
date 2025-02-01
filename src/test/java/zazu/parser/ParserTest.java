package zazu.parser;

import org.junit.jupiter.api.Test;
import zazu.data.exception.EmptyDescriptionException;
import zazu.data.exception.IncompleteCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest{
    @Test
    public void parseIndex_success() throws IncompleteCommandException {
        assertEquals(0, Parser.parseIndex("blah 1"));
    }

    @Test
    public void parseIndex_empty_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseIndex("blah"));
            fail();
        } catch (Exception e) {
            assertEquals("Error: please enter an index. ", e.getMessage());
        }
    }

    @Test
    public void parseIndex_notNumber_exceptionThrown() {
        try {
            assertEquals(0, Parser.parseIndex("blah blah"));
            fail();
        } catch (Exception e) {
            assertEquals(true, e instanceof NumberFormatException);
        }
    }

    @Test
    public void parseDescription_success() throws EmptyDescriptionException {
        assertEquals("test this", Parser.parseDescription("event test this /by".split(" "), 3));
    }

    @Test
    public void parseDescription_exceptionThrown() {
        try {
            assertEquals("test", Parser.parseDescription("event /by".split(" "), 1));
            fail();
        } catch (EmptyDescriptionException e) {
            assertEquals("Error: please enter a nonempty description. ", e.getMessage());
        }
    }
}
