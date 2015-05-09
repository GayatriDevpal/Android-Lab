package com.example.gayatrid.myflickr;

import android.app.ListFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Gayatri D on 5/7/2015.
 */
public class FlickerFragment extends Fragment implements AdapterView.OnItemClickListener{
    String[] mTitles;
    ArrayList<FlickrPhoto> photos;
    //private LruCache<String, Bitmap> mMemoryCache;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        MainActivity activity = (MainActivity)this.getActivity();
        photos = activity.getmPhotos();
        mTitles = new String[photos.size()];

        for(int i = 0; i< mTitles.length; i++){
            mTitles[i] = photos.get(i).title;
        }

        ListView lv =(ListView)view.findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, mTitles));
        lv.setOnItemClickListener(this);


//        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//
//        final int cacheSize = maxMemory / 8;
//
//        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getByteCount() / 1024;
//            }
//        };

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(), mTitles[i], Toast.LENGTH_LONG).show();
        String photoURL = photos.get(i).getPhotoURL(true);
        PhotoFragment pf = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString("URL", photoURL);
        pf.setArguments(args);
        Bitmap bitmap=null;
        args.putParcelable("image", bitmap);
        pf.setArguments(args);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, pf);
        ft.addToBackStack("Image");
        ft.commit();
    }

//    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//        if (getBitmapFromMemCache(key) == null) {
//            mMemoryCache.put(key, bitmap);
//        }
//    }
//
//    public Bitmap getBitmapFromMemCache(String key) {
//        return mMemoryCache.get(key);
//    }
//
//
//    private class GetPhoto extends AsyncTask<String, Void, Long> {
//        InputStream is = null;
//        HttpURLConnection connection = null;
//
//        @Override
//        protected void onPreExecute() {
//            progress.setVisibility(View.VISIBLE);
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Long doInBackground(String... strings) {
//
//            try{
//                URL imageUrl = new URL(photoURL);
//
//                final Bitmap bitmap = getBitmapFromMemCache(photoURL);
//                if (bitmap != null) {
//                    mImageView.setImageBitmap(bitmap);
//                } else {
//                    connection = (HttpURLConnection)imageUrl.openConnection();
//                    connection.connect();
//                    is = connection.getInputStream();
//                    mBitmap = BitmapFactory.decodeStream(is);
//
//                    addBitmapToMemoryCache(photoURL, mBitmap);
//
//                }
//
//
//                return(0L);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return(1L);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return(1L);
//            }finally{
//                connection.disconnect();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Long aLong) {
//            mImageView.setImageBitmap(mBitmap);
//            progress.setVisibility(View.GONE);
//            super.onPostExecute(aLong);
//        }
//    }

}