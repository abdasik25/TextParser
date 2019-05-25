/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.filemanager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {

    public boolean writeToFile(String filePath, String dataText) throws FileNotFoundException {
        if (!Paths.get(filePath).toFile().isFile() || filePath == null || filePath.isEmpty()) {
            throw new FileNotFoundException();
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(filePath))) {
            bufferedWriter.write(dataText);
            return true;
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
