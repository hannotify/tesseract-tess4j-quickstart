/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tesseract.tess4j.quickstart;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Library {
    public static void main(String[] args) throws TesseractException {
        String textCleanCode = doOCR("eng", new File("src/main/resources/clean-code.png"));
        System.out.println("Done. Result:");
        System.out.println(textCleanCode);

        String textNewTestament = doOCR("grc", new File("src/main/resources/new-testament.png"));
        System.out.println("Done. Result:");
        System.out.println(textNewTestament);
    }

    private static String doOCR(String language, File image) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/usr/local/Cellar/tesseract/4.0.0/share/tessdata/");
        tesseract.setLanguage(language);

        System.out.println(String.format("Processing image '%s', please wait...", image.getName()));
        return tesseract.doOCR(image);
    }
}
