/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.hannotify.tesseract.tess4j.quickstart;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

public class Library {
    public static void main(String[] args) throws TesseractException, IOException {
        doOCR("eng", new File("src/main/resources/skewed.jpg"), false);
        doOCR("eng", new File("src/main/resources/skewed.jpg"), true);
    }

    private static void doOCR(String language, File image, boolean optimizeImage) throws TesseractException, IOException {
        var tesseract = new Tesseract();
        tesseract.setDatapath("/usr/local/Cellar/tesseract/4.1.1/share/tessdata");
        tesseract.setLanguage(language);

        System.out.println("Processing image, please wait...");

        if (optimizeImage) {
            image = optimizeImage(image);
        }

        var results = tesseract.createDocumentsWithResults(
                image.getAbsolutePath(),
                "src/main/resources/ocr",
                List.of(ITesseract.RenderedFormat.TEXT),
                ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE
        );

        System.out.println("Done. Result:");
        System.out.println(results);
    }

    private static File optimizeImage(File image) throws IOException {
        var bufferedImage = ImageIO.read(image);
        var optimizedImage = new File(image.getName() + "_optimized.png");

        System.out.print("Optimizing image... ");
        bufferedImage = ImageHelper.rotateImage(bufferedImage, -4);

        System.out.println(ImageIO.write(bufferedImage, "png", optimizedImage) ? "succeeded." : "failed.");

        return optimizedImage;
    }
}
