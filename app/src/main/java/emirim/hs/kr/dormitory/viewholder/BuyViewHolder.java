package emirim.hs.kr.dormitory.viewholder;

/**
 * Created by Eun bee on 2016-delete_things-19.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import emirim.hs.kr.dormitory.R;
import emirim.hs.kr.dormitory.models.Buy;

public class BuyViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public BuyViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.buy_title);
        authorView = (TextView) itemView.findViewById(R.id.buy_author);
        bodyView = (TextView) itemView.findViewById(R.id.buy_body);
    }

    public void bindToBuy(Buy buy, View.OnClickListener starClickListener) {
        titleView.setText(buy.title);
        authorView.setText(buy.author);
        bodyView.setText(buy.body);
    }
}