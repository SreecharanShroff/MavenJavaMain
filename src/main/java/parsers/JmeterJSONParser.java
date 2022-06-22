package parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JmeterJSONParser {
    private static final String userDir = System.getProperty("user.dir");
    private static final String FILENAME = userDir + "/results/statistics.json";
    private static String result = "ignore";

    public static void main(String args[]) {
        try {
            if (new File(FILENAME).exists()) {
                String contents = new String(Files.readAllBytes(Paths.get(FILENAME)));
                if (contents.contains("\"errorCount\" : 0")) {
                    result = "pass";
                } else {
                    result = "fail";
                }
            }
            String path = userDir + "\\target\\jmeterResult.txt";
            if (new File(path).exists()) {
                Files.delete(Paths.get(path));
            }
            Files.createFile(Paths.get(path));
            Files.write(Paths.get(path), result.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
