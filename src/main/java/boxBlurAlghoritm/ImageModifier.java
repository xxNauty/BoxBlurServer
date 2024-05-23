package boxBlurAlghoritm;

import boxBlurAlghoritm.entity.PixelDensity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

final public class ImageModifier {

    private ImageModifier() {}

    public static BufferedImage addBlurEffect(BufferedImage image, int radius) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y ++) {
                final List<PixelDensity> pixels = new LinkedList<>();

                for (int a = x - radius; a <= x + radius; a++) {
                    for (int b = y - radius; b <= y + radius; b++) {
                        if (a > 0 && a < image.getWidth() && b > 0 && b < image.getHeight()) {
                            final Color color = new Color(image.getRGB(a, b));

                            final PixelDensity density = new PixelDensity(
                                    color.getRed(),
                                    color.getGreen(),
                                    color.getBlue()
                            );

                            pixels.add(density);
                        }
                    }
                }

                PixelDensity result = pixels
                        .stream()
                        .reduce(PixelDensity::add)
                        .map(pixel -> pixel.divide(pixels.size()))
                        .get();

                Color color = new Color(result.r, result.g, result.b);

                image.setRGB(x, y, color.getRGB());
            }
        }
        return image;
    }
}
