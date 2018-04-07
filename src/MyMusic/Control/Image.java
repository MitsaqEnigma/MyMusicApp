package MyMusic.Control;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Image {

    BufferedImage resizedImage;
    Graphics2D g2;
    float xScale;
    float yScale;
    AffineTransform affine;

    public Image() {

    }

    public BufferedImage linearResize(BufferedImage origin, int width, int height) {
//        resizedImage = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
//        g2 = resizedImage.createGraphics();
//        xScale = (float) width / origin.getWidth();
//        yScale = (float) height / origin.getHeight();
//        affine = AffineTransform.getScaleInstance(xScale, yScale);
//        g2.drawRenderedImage(origin, affine);
//        g2.dispose();
        return origin;
    }
}
