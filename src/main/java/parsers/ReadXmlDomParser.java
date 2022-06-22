package parsers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ReadXmlDomParser {
    private static final String userDir = System.getProperty("user.dir");
    private static final String FILENAME = userDir + "/results/cucumber.xml1";
    private static String result = "ignore";

    public static void main(String[] args) {
        try {
			System.out.println(FILENAME);
            if (new File(FILENAME).exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new File(FILENAME));
                doc.getDocumentElement().normalize();
                int failuresCount = Integer.parseInt(doc.getDocumentElement().getAttribute("failures"));
                System.out.println("Total failures :" + failuresCount);
                System.out.println("------");
                if (failuresCount == 0) {
                    result = "pass";
                } else {
                    result = "fail";
                }
            }
			System.out.println("result => "+result);
            String path = userDir + "/target/functionalResult.txt";
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
