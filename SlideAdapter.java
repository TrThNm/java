package com.example.appandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class SlideAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mList;
    public SlideAdapter(Context  mContext, List<Slide> mList){
        this.mContext=mContext;
        this.mList=mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout= inflater.inflate(R.layout.slider_item,null);
        ImageView slideImg= slideLayout.findViewById(R.id.slideImg);
        slideImg.setImageResource(mList.get(position).getImage());
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
