package mitso.v.homework_10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BallFragment extends BaseFragment {

    private FrameLayout mFrameLayout_Layout;
    private ImageView mImageView_Ball;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ball, container, false);

        mFrameLayout_Layout = (FrameLayout) view.findViewById(R.id.fl_Layout_FB);
        mImageView_Ball = (ImageView) view.findViewById(R.id.iv_ball_FB);

        mImageView_Ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(getAnimationSet());
            }
        });
        return view;
    }

    private AnimationSet getAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);

        /** (ball width)*(x) = (layout width)*(1.0f) */
        float scaleTo = (float) mFrameLayout_Layout.getWidth() / (float) mImageView_Ball.getWidth();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, scaleTo, 1.0f, scaleTo,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        /** translate to = layout height - ((ball height)*(increasing factor)) */
        /** don't work correctly, I could not find any other solution */
        float translateTo = (float) mFrameLayout_Layout.getHeight() - ((float) mImageView_Ball.getHeight() * (scaleTo - 1.0f));
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, translateTo);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(2000);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /** i didn't know how to do reverse correctly, i did how i could */
                mImageView_Ball.startAnimation(getAnimationSetReverse());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return animationSet;
    }

    private AnimationSet getAnimationSetReverse() {
        AnimationSet animationSet = new AnimationSet(true);

        float scaleTo = (float) mFrameLayout_Layout.getWidth() / (float) mImageView_Ball.getWidth();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, scaleTo, 1.0f, scaleTo,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        float translateTo = (float) mFrameLayout_Layout.getHeight() - ((float) mImageView_Ball.getHeight() * (scaleTo - 1.0f));
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, translateTo);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(500);

        /** bounce don't work when reverse */
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.setInterpolator(new ReverseInterpolator());

        return animationSet;
    }

    private class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float paramFloat) {
            return Math.abs(paramFloat -1.0f);
        }
    }
}