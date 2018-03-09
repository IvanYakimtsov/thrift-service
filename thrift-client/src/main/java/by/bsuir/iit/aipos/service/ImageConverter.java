package by.bsuir.iit.aipos.service;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageConverter {

    public byte[] toByteArray(Image image, String format) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, format, os);
            return os.toByteArray();
        } finally {
            os.close();
        }
    }

    public Image toImage(byte[] imageBytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(is);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }


}
