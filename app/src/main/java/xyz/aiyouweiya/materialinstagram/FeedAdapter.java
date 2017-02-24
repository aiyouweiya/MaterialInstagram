package xyz.aiyouweiya.materialinstagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by aiyouweiya on 2017/2/24.
 */

public class FeedAdapter extends RecyclerView.Adapter{

    private static final int ANIMATED_ITEMS_COUNT = 2;

    private Context context;

    private int itemCount = 0;
    private int lastAnimationItem = -1;//控制向上滑动时不会出现item上移

    public FeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new FeedHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        FeedHolder feedHolder = (FeedHolder) holder;
        if(position % 2 == 0){
            feedHolder.iv_feed_bottom.setImageResource(R.drawable.img_feed_bottom_1);
            feedHolder.iv_feed_center.setImageResource(R.drawable.img_feed_center_1);
        }else{
            feedHolder.iv_feed_bottom.setImageResource(R.drawable.img_feed_bottom_2);
            feedHolder.iv_feed_center.setImageResource(R.drawable.img_feed_center_2);
        }
    }

    public void runEnterAnimation(View view,int position){
//        if(position > ANIMATED_ITEMS_COUNT ){
//            return;
//        }

        if(position > lastAnimationItem){
            lastAnimationItem = position;
            view.setTranslationY(Util.getScreenHeight(context));
            view.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3.0f)).setDuration(700).start();
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void updateItems(){
        itemCount = 10;
        notifyDataSetChanged();
    }
}
