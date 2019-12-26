import javafx.scene.image.Image;

class ImageWithURL extends Image {
    private final String url;

    public ImageWithURL(String url) {
        super(url);
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}