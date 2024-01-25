package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class AppTest {

    @Test
    public void testMainMethod() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[]{});

        System.setOut(System.out);

        String expectedOutput = "ArrayListe: [Apple, Banana, Cherry]" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
    

