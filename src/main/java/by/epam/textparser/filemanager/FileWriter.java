package by.epam.textparser.filemanager;

import java.io.BufferedReader;
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
            if (dataText == null || dataText.isEmpty()) {
                dataText = "";
            }
            bufferedWriter.write(dataText);
            return true;
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
