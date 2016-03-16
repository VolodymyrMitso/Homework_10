package mitso.v.homework_10;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        /** get screen width for correct animation, because layout width = 0 and recycler view width = 0 */
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        float screenWidth = windowManager.getDefaultDisplay().getWidth();

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_RecyclerView_FL);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        CustomAdapter mAdapter = new CustomAdapter(getItems());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /** recycler view start animation - all visible items move */
        mRecyclerView.setAnimation(getAnimation(screenWidth));

        return view;
    }

    private ArrayList<String> getItems() {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++)
            items.add("Item #" + i);
        return items;
    }

    private AnimationSet getAnimation(float width) {
        AnimationSet setAnimation = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(-width,0,0,0);
        setAnimation.addAnimation(translateAnimation);
        setAnimation.setDuration(500);
        return setAnimation;
    }
}
