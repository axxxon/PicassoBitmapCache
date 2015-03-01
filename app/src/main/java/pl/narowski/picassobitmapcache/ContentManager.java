package pl.narowski.picassobitmapcache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MNarowski on 2015-03-01.
 */
public class ContentManager {
    private static final String NAME = "cached_bmp";
    private static ContentManager sInstance;
    private final File sDir;

    private ContentManager() {
        sDir = getDiskCacheDir(App.getsContext(), NAME);
    }

    public static ContentManager getInstance() {
        if (sInstance == null) {
            sInstance = new ContentManager();
        }

        return sInstance;
    }


    public void deleteCacheDir() {
        try {
            FileUtils.cleanDirectory(sDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                        context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    public String getBitmapPath(String key) {
        String absoulte = sDir.getAbsolutePath();
        return absoulte + File.separator + DigestUtils.md5(key);
    }

    public void saveBitmap(String key, Bitmap bmp) {
        if (bmp == null) {
            return;
        }
        String path = getBitmapPath(key);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
