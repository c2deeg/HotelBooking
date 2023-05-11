package com.app.LukandaH.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.LukandaH.Models.VpHotelImagesModel;
import com.app.LukandaH.R;

import java.util.ArrayList;

public class VpHotelImagesAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<VpHotelImagesModel> models;

    public VpHotelImagesAdapter(Context context, ArrayList<VpHotelImagesModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.vp_hotel_images_items, null);
        ImageView img_vp = (ImageView) view.findViewById(R.id.img_vp);
        img_vp.setImageResource(models.get(position).getImage());
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
