/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.hannotify.tesseract.tess4j.quickstart;

import java.io.File;
import java.util.List;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Library {
    public static void main(String[] args) throws TesseractException {
        doOCR("eng+deu+fra+ita+spa+por", new File("src/main/resources/eurotext.png"));
    }

    private static void doOCR(String language, File image) throws TesseractException {
        var tesseract = new Tesseract();
        tesseract.setDatapath("/usr/local/Cellar/tesseract/4.1.1/share/tessdata");
        tesseract.setLanguage(language);

        System.out.println("Processing image, please wait...");

        var results = tesseract.createDocumentsWithResults(
                image.getAbsolutePath(),
                "src/main/resources/ocr",
                List.of(ITesseract.RenderedFormat.TEXT),
                ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE
        );

        System.out.println("Done. Result:");
        System.out.println(results);
    }
}
