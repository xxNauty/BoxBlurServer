package boxBlurAlghoritm.entity;

public class PixelDensity {
    public int r, g, b;

    public PixelDensity(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public PixelDensity add(PixelDensity rhd) {
        r += rhd.r;
        g += rhd.g;
        b += rhd.b;
        return this;
    }

    public PixelDensity divide(int size) {
        this.r = r / size;
        this.g = g / size;
        this.b = b / size;
        return this;
    }
}