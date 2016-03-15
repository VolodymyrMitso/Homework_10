package mitso.v.homework_10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class BallFragment extends BaseFragment {

    private FrameLayout mFrameLayout_FragmentBall;
    private ImageView mImageView_Ball;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ball, container, false);

        mFrameLayout_FragmentBall = (FrameLayout) view.findViewById(R.id.fl_FragmentBall_FB);

        mImageView_Ball = (ImageView) view.findViewById(R.id.iv_ball_FB);
        mImageView_Ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(getAnimation());

            }
        });
        return view;
    }

    private AnimationSet getAnimation() {
        AnimationSet setAnimation = new AnimationSet(true);

        float scaleTo = (float) mFrameLayout_FragmentBall.getWidth() / (float) mImageView_Ball.getWidth();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, scaleTo, 1.0f, scaleTo,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        float TenDpInPx = (float) getResources().getDimensionPixelSize(R.dimen.d_size_10dp); // because margin is 10 dp
        float translateTo = mFrameLayout_FragmentBall.getHeight() - ((mImageView_Ball.getHeight() + TenDpInPx) * (scaleTo - 1));
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, translateTo);

        setAnimation.addAnimation(scaleAnimation);
        setAnimation.addAnimation(translateAnimation);
        setAnimation.setDuration(2000);

        setAnimation.setInterpolator(new BounceInterpolator());

        return setAnimation;
    }
}