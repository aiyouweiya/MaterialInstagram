package xyz.aiyouweiya.materialinstagram;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiyouweiya on 2017/2/24.
 */

public class FeedHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_feed_top)
    ImageView iv_feed_top;

    @BindView(R.id.iv_feed_center)
    SquaredImageView iv_feed_center;

    @BindView(R.id.iv_feed_bottom)
    ImageView iv_feed_bottom;

    public FeedHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
