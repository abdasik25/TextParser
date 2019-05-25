/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.filemanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readFromFile(String filePath) throws FileNotFoundException {
        if (!Paths.get(filePath).toFile().isFile() || filePath == null || filePath.isEmpty()) {
            throw new FileNotFoundException();
        }
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))) {
            return bufferedReader.lines().reduce("", (first, second) -> first + "\n" + second)
                    .replaceFirst("\n", "");
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
