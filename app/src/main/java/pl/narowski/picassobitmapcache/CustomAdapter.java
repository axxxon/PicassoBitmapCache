package pl.narowski.picassobitmapcache;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MNarowski on 2015-03-01.
 */
public class CustomAdapter extends RecyclerView.Adapter {
    private final List<String> mUrls;

    public CustomAdapter(String[] urls){
        mUrls = Arrays.asList(urls);
    }

    public CustomAdapter(List<String> urls){
        mUrls = urls;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item,viewGroup,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        CustomViewHolder holder = (CustomViewHolder) viewHolder;
        ImageLoader.loadImage(mUrls.get(i), holder.image);
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;

        public CustomViewHolder(View viewGroup) {
            super(viewGroup);
            image = (ImageView) viewGroup.findViewById(R.id.adapter_image);
        }
    }
}
