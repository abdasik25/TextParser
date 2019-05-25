/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.filemanager;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.testng.Assert.*;

public class FileReaderTest {

    private FileReader reader;
    private static final String FILE_PATH = "data/scource_text";

    public FileReaderTest() {
        reader = new FileReader();
    }

    @Test
    public void testReadFromFile() {
        String actual = null;
        try {
            actual = reader.readFromFile(FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String expected = "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                "It is a established fact that a reader will be of a page when looking at its layout.\n" +
                "Bye.";
        assertEquals(actual, expected);
    }
}