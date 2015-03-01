package pl.narowski.picassobitmapcache;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by MNarowski on 2015-03-01.
 */
public class ImageLoader {

    public static Picasso getPicasso() {
        return Picasso.with(App.getsContext());
    }

    public static void loadImage(String url,ImageView target){
        Bitmap bmp= null;

        try {
            bmp = BitmapCache.getInstance().get(url);
            if(bmp == null){
                bmp = getPicasso().load(url).get();
                BitmapCache.getInstance().set(url,bmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        target.setImageBitmap(bmp);
    }
}
