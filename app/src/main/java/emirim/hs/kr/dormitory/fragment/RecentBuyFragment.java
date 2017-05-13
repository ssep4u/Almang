package emirim.hs.kr.dormitory.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import emirim.hs.kr.dormitory.S;

public class RecentBuyFragment extends BuyListFragment {

    public RecentBuyFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentBuyQuery = databaseReference.child("buy/"+ S.roomNameP+"/")
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentBuyQuery;
    }
}