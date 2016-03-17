package mitso.v.homework_10;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

public class HeartFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {

    private ImageView mImageView_Heart;

    /** I didn't find your custom seek bar and that is why i didn't use icons */
    private SeekBar mSeekBar_Alpha;
    private SeekBar mSeekBar_Rotate;
//    private SeekBar mSeekBar_Scale;

    private AnimatorSet animatorSet;

    private float alphaTemp;
    private float rotateTemp;
//    private float scaleTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heart, container, false);

        mImageView_Heart = (ImageView) view.findViewById(R.id.iv_Heart_FH);

        animatorSet = getAnimatorSet(1.0f);
        animatorSet.start();

        mSeekBar_Alpha = (SeekBar) view.findViewById(R.id.sb_Alpha_FH);
        mSeekBar_Rotate = (SeekBar) view.findViewById(R.id.sb_Rotate_FH);
//        mSeekBar_Scale = (SeekBar) view.findViewById(R.id.sb_Scale_FH);

        mSeekBar_Alpha.setOnSeekBarChangeListener(this);
        mSeekBar_Rotate.setOnSeekBarChangeListener(this);
//        mSeekBar_Scale.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        /** I didn't know how to change already started animator set.
         *  Adding new object animators to started animator set - don't work correctly.
         *  End animator set, change parameters and then start again - don't work correctly.
         *  Scale seek bar don't work. */

        float alpha = (float) mSeekBar_Alpha.getProgress() / (float) 100;
        if (alpha != alphaTemp)
            ObjectAnimator.ofFloat(mImageView_Heart, "alpha", alpha, alpha).start();
        alphaTemp = alpha;

        float rotate = (float) mSeekBar_Rotate.getProgress();
        if (rotate != rotateTemp)
            ObjectAnimator.ofFloat(mImageView_Heart , "rotation", rotate, rotate).start();
        rotateTemp = rotate;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /** I wanted to change this float x variable */
    private AnimatorSet getAnimatorSet(float x) {

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(mImageView_Heart, "scaleX", x * 1.25f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(mImageView_Heart, "scaleY", x * 1.25f);
        scaleUpX.setDuration(500);
        scaleUpY.setDuration(500);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(mImageView_Heart, "scaleX", x * 1.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(mImageView_Heart, "scaleY", x * 1.0f);
        scaleDownX.setStartDelay(500);
        scaleDownY.setStartDelay(500);
        scaleDownX.setDuration(500);
        scaleDownY.setDuration(500);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);

        animatorSet.playTogether(scaleUpX);
        animatorSet.playTogether(scaleUpY);
        animatorSet.playTogether(scaleDownX);
        animatorSet.playTogether(scaleDownY);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
        return animatorSet;
    }
}