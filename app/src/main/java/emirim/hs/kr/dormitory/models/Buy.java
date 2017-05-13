package emirim.hs.kr.dormitory.models;

/**
 * Created by Eun bee on 2016-delete_things-19.
 */
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Buy {

    public String uid;
    public String author;
    public String title;
    public String body;
    public Map<String, Boolean> stars = new HashMap<>();

    public Buy() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Buy(String uid, String author, String title, String body) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]