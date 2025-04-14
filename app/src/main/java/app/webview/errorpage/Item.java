package app.webview.errorpage;
import androidx.annotation.NonNull;

public class Item {
    private final String title;
    private final String description;

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
