package emirim.hs.kr.dormitory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

import emirim.hs.kr.dormitory.BuyDetailActivity;
import emirim.hs.kr.dormitory.PostDetailActivity;
import emirim.hs.kr.dormitory.R;
import emirim.hs.kr.dormitory.models.Buy;
import emirim.hs.kr.dormitory.models.Post;
import emirim.hs.kr.dormitory.viewholder.BuyViewHolder;
import emirim.hs.kr.dormitory.viewholder.PostViewHolder;

public abstract class BuyListFragment extends Fragment {

    private static final String TAG = "PostListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Buy, BuyViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private GridLayoutManager mManager;

    public BuyListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_buy, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.buy_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new GridLayoutManager(getActivity(),2);
        //mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query buyQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Buy, BuyViewHolder>(Buy.class, R.layout.item_buy,
                BuyViewHolder.class, buyQuery) {
            @Override
            protected void populateViewHolder(final BuyViewHolder viewHolder, final Buy model, final int position) {
                final DatabaseReference buyRef = getRef(position);

                // Set click listener for the whole post view
                final String buyKey = buyRef.getKey();
                int color = (int)(Math.random()*12)+1;
                switch (color){
                    case 1: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color1));break;
                    case 2: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color2));break;
                    case 3: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color3));break;
                    case 4: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color4));break;
                    case 5: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color5));break;
                    case 6: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color6));break;
                    case 7: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color7));break;
                    case 8: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color8));break;
                    case 9: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color9));break;
                    case 10: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color10));break;
                    case 11: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color11));break;
                    case 12: viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.color12));break;
                }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), BuyDetailActivity.class);
                        intent.putExtra(BuyDetailActivity.EXTRA_BUY_KEY, buyKey);
                        startActivity(intent);
                    }
                });

                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToBuy(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
                        // Need to write to both places the post is stored
                        DatabaseReference globalBuyRef = mDatabase.child("buy").child(buyRef.getKey());
                        DatabaseReference userBuyRef = mDatabase.child("user-buy").child(model.uid).child(buyRef.getKey());
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}