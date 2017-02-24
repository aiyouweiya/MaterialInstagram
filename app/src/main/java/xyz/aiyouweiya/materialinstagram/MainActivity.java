package xyz.aiyouweiya.materialinstagram;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {

    private boolean pendingIntroAnimation;

    @BindView(R.id.fab_create)
    ImageButton fab_create;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_img)
    ImageView toolbar_img;

    @BindView(R.id.rvFeed)
    RecyclerView rv_feed;

    Unbinder unbinder;

    private FeedAdapter adapter;

    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        if(savedInstanceState == null){
            pendingIntroAnimation = true;
        }

        setSupportActionBar(toolbar); //设置toolbar取代原来的action_bar
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_feed.setLayoutManager(manager);
        adapter = new FeedAdapter(this);
        rv_feed.setAdapter(adapter);


    }

    private void startIntroAnimation() {
        fab_create.setTranslationY(2 * getResources().getDimensionPixelSize(R.dimen.btn_fab_size));
        int actionBarSize = Util.dp2px(56);
        toolbar.setTranslationY(-actionBarSize);
        toolbar_img.setTranslationY(-actionBarSize);
        item.getActionView().setTranslationY(-actionBarSize);

        toolbar.animate().translationY(0).setDuration(300).setStartDelay(300).start();
        toolbar_img.animate().translationY(0).setDuration(300).setStartDelay(400).start();
        item.getActionView().animate().translationY(0).setDuration(300).setStartDelay(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startContentAnimation();
            }
        }).start();
    }

    private void startContentAnimation() {
        adapter.updateItems();
        fab_create.animate().translationY(0).setInterpolator(new OvershootInterpolator(1f)).setDuration(300).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        item = menu.findItem(R.id.action_inbox);
        item.setActionView(R.layout.menu_item_view);
//        Timber.i("first menu itemis is %s", menu.getItem(0).getTitle());

        if(pendingIntroAnimation){
            pendingIntroAnimation = false;
            startIntroAnimation();
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
