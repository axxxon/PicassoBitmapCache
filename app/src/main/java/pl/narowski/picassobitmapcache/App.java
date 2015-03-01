package pl.narowski.picassobitmapcache;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by MNarowski on 2015-03-01.
 */
public class App extends Application {
    private static App sContext;

    public App() {
        super();
        sContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }

    public static Context getsContext() {
        return sContext;
    }
}
