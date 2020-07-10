package io.shivamvk.bareilly.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

import io.shivamvk.bareilly.R;

public class OnBoardingAdapter extends PagerAdapter {

    private Context context;
    private String[] heading;
    private String[] text;
    private SparseArray<LottieAnimationView> viewSparseArray = new SparseArray<>(4);
    private LayoutInflater layoutInflater;
    int[] lottieAnimationViews =
            {R.raw.onboarding_1_final, R.raw.onboarding_2_final, R.raw.onboarding_3_final, R.raw.onboarding_4_final};


    public OnBoardingAdapter(Context context) {
        this.context = context;
        heading = new String[]{
            "heading 1","heading 2","heading 3","heading 4"
        };
        text = new String[]{
            "sub heading 1","sub heading 2","sub heading 3","sub heading 4"
        };
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_onboarding_item, null);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.lottie_view);
        TextView text1 = view.findViewById(R.id.heading);
        TextView text2 = view.findViewById(R.id.sub_heading);
        text1.setText(heading[position]);
        text2.setText(text[position]);

        lottieAnimationView.setAnimation(lottieAnimationViews[position]);
        viewSparseArray.put(position, lottieAnimationView);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }

    public SparseArray<LottieAnimationView> getSparseArray() {
        return viewSparseArray;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
