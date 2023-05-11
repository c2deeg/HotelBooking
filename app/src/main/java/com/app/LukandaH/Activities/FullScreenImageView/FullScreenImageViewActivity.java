package com.app.LukandaH.Activities.FullScreenImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.LukandaH.Adapters.VpFullScreenImageAdapter;
import com.app.LukandaH.Models.VpFullScreenImageModelData;
import com.app.LukandaH.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class FullScreenImageViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity;
    private ViewPager vp_imageVp;
    private CircleIndicator circleIndicator;
    private ImageView img_previous;
    private ImageView img_next;
    private ArrayList<VpFullScreenImageModelData> data = new ArrayList<>();
    private VpFullScreenImageAdapter adapter;
    private int currentItem;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);
        activity = this;

        init();
        listeners();
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));
        data.add(new VpFullScreenImageModelData(R.drawable.img20));

        adapter = new VpFullScreenImageAdapter(activity, data);
        vp_imageVp.setCurrentItem(1, true);
        vp_imageVp.setAdapter(adapter);
        viewPagerSwiping();
        circleIndicator.setViewPager(vp_imageVp);
        img_previous.setVisibility(View.INVISIBLE);
    }

    private void init() {
        vp_imageVp = findViewById(R.id.vp_imageVp);
        circleIndicator = findViewById(R.id.circleIndicator);
        img_previous = findViewById(R.id.img_previous);
        img_next = findViewById(R.id.img_next);
        img_back = findViewById(R.id.img_back);
    }

    private void listeners() {
        img_previous.setOnClickListener(this);
        img_next.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view == img_next) {
//            Toast.makeText(activity, String.valueOf(vp_imageVp.getCurrentItem()), Toast.LENGTH_SHORT).show();
            vp_imageVp.setCurrentItem(getItem(+1), true);
            vpButtonValidation(vp_imageVp);
            int curretItem = Integer.parseInt(String.valueOf(vp_imageVp.getCurrentItem()));
            if (curretItem == 0) {
                img_previous.setVisibility(View.INVISIBLE);
                img_next.setVisibility(View.VISIBLE);
            } else if (curretItem == data.size() - 1) {
                img_previous.setVisibility(View.VISIBLE);
                img_next.setVisibility(View.INVISIBLE);
            }
        } else if (view == img_previous) {
            vp_imageVp.setCurrentItem(getItem(-1), true);
            vpButtonValidation(vp_imageVp);
        } else if (view == img_back) {
            finish();
        }
    }

    private void viewPagerSwiping() {
        vp_imageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    img_previous.setVisibility(View.INVISIBLE);
                    img_next.setVisibility(View.VISIBLE);
                } else if (position > 0 && position < data.size() - 1) {
                    img_previous.setVisibility(View.VISIBLE);
                    img_next.setVisibility(View.VISIBLE);
                } else if (position == data.size() - 1) {
                    img_previous.setVisibility(View.VISIBLE);
                    img_next.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private int getItem(int i) {
        return vp_imageVp.getCurrentItem() + i;
    }

    private boolean vpButtonValidation(ViewPager viewPager) {
        currentItem = Integer.parseInt(String.valueOf(viewPager.getCurrentItem()));
        if (currentItem == 0) {
            img_previous.setVisibility(View.INVISIBLE);
            img_next.setVisibility(View.VISIBLE);
        } else if (currentItem > 0 && currentItem < data.size() - 1) {
            img_previous.setVisibility(View.VISIBLE);
            img_next.setVisibility(View.VISIBLE);
        } else if (currentItem == data.size() - 1) {
            img_previous.setVisibility(View.VISIBLE);
            img_next.setVisibility(View.INVISIBLE);
        }
        return true;
    }
}