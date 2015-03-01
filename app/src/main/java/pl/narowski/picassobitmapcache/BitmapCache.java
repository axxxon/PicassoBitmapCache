package pl.narowski.picassobitmapcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;

import com.squareup.picasso.Cache;

import org.apache.commons.io.FileUtils;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by MNarowski on 2015-03-01.
 */
public class BitmapCache {

    private static BitmapCache sInstance;
    private static final Object _lock = new Object();
    public static BitmapCache getInstance() {
        if(sInstance==null){
            sInstance = new BitmapCache();
        }
        return sInstance;
    }


    public Bitmap get(String key) {
        synchronized (_lock){
            try {
                return BitmapFactory.decodeFile(ContentManager.getInstance().getBitmapPath(key));
            //TODO: never change this outofmemory isnt an exception
            }catch (Throwable e){
                e.printStackTrace();
            }
                return null;
        }

    }

    public void set(String key, Bitmap bitmap) {
        synchronized (_lock){
            ContentManager.getInstance().saveBitmap(key,bitmap);
        }
    }



    public void clear() {
        ContentManager.getInstance().deleteCacheDir();
    }

    public Bitmap getCurrent(String keyPrefix,String changed) {
        return null;
    }
}
