package mitso.v.homework_10;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_item_FL);
        }

        public TextView getTextView() {
            return mTextView;
        }
    }

    private ArrayList<String> items;

    public CustomAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.getTextView().setText(items.get(position));

        /** recycler view scroll animation - every new item move */
        viewHolder.itemView.setAnimation(getAnimation(viewHolder.itemView.getWidth()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private AnimationSet getAnimation(float width) {
        AnimationSet setAnimation = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(-width,0,0,0);
        setAnimation.addAnimation(translateAnimation);
        setAnimation.setDuration(500);
        return setAnimation;
    }
}
