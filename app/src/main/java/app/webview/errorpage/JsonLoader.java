package app.webview.errorpage;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {
    public static List<Item> loadItems(Context context) {
        List<Item> items = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("items.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = is.read(buffer);
            if (bytesRead != size) {
                throw new RuntimeException("Unexpected number of bytes read from JSON file");
            }
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                items.add(new Item(
                        obj.getString("title"),
                        obj.getString("description")
                ));
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return items;
    }
}