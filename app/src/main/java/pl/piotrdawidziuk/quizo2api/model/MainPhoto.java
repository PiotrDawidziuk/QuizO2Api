package pl.piotrdawidziuk.quizo2api.model;

public class MainPhoto {
    String source;
    String url;

    public MainPhoto(String source, String url) {
        this.source = source;
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }
}
