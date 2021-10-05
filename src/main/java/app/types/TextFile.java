package app.types;

public class TextFile {
    private String path;
    private String content;

    public TextFile(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return this.path;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return this.path + ";" + this.content;
    }

    public static TextFile parseTextFile(String value) {
        String[] parts = value.split(";");
        if (parts.length != 2) return null;
        return new TextFile(parts[0], parts[1]);
    }
}
