package com.app.LukandaH.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.LukandaH.Models.VpFullScreenImageModelData;
import com.app.LukandaH.R;

import java.util.ArrayList;

public class VpFullScreenImageAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<VpFullScreenImageModelData> data;

    public VpFullScreenImageAdapter(Context context, ArrayList<VpFullScreenImageModelData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.vp_fullscreenimage_listitems, null);
        ImageView img_vp = (ImageView) view.findViewById(R.id.img_vp);
        img_vp.setImageResource(data.get(position).getImage());
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
