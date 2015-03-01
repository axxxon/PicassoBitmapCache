package pl.narowski.picassobitmapcache;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            getUrls(recyclerView);
            return rootView;
        }

        private void getUrls(final RecyclerView recyclerView) {
            new AsyncTask<Void, Void, List<String>>() {
                @Override
                protected List<String> doInBackground(Void... params) {
                    List<String> list = Arrays.asList(new String[]{
                        "http://tinypic.com/view.php?pic=2yo5pxv&s=8",
                                "http://tinypic.com/view.php?pic=2yo5q2c&s=8",
                                "http://tinypic.com/view.php?pic=2yo5q35&s=8",
                                "http://tinypic.com/view.php?pic=2yo5q84&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qfk&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qgp&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qh3&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qih&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qmo&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qo7&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qog&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qts&s=8",
                                "http://tinypic.com/view.php?pic=2yo5qw3&s=8",
                                "http://tinypic.com/view.php?pic=2yo5r0j&s=8",
                                "http://tinypic.com/view.php?pic=2yo5r13&s=8",
                                "http://tinypic.com/view.php?pic=2yo5r88&s=8",
                                "http://tinypic.com/view.php?pic=2yo5r9l&s=8",
                                "http://tinypic.com/view.php?pic=2yo5sms&s=8",
                                "http://tinypic.com/view.php?pic=2yo5soy&s=8",
                                "http://tinypic.com/view.php?pic=2yo5svt&s=8",
                                "http://tinypic.com/view.php?pic=2yo5swm&s=8",
                                "http://tinypic.com/view.php?pic=2yo5t2p&s=8",
                                "http://tinypic.com/view.php?pic=2yo5t75&s=8",
                                "http://tinypic.com/view.php?pic=2yo5t7p&s=8",
                                "http://tinypic.com/view.php?pic=2yo5t92&s=8",
                                "http://tinypic.com/view.php?pic=2yo5ted&s=8"
                    });
                    return list;
                }

                @Override
                protected void onPostExecute(final List<String> aVoid) {
                    super.onPostExecute(aVoid);
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(new CustomAdapter(aVoid));
                        }
                    });
                }
            }.execute();
        }
    }
}
