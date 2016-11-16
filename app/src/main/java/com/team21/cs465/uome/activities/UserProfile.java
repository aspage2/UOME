package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.Transaction;
import com.team21.cs465.uome.User;

import java.lang.ref.WeakReference;

public class UserProfile extends AppCompatActivity {

    private static int calculateInSampleSize (BitmapFactory.Options options, int rWidth, int rHeight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > rHeight || width > rWidth)
        {
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while (halfHeight/inSampleSize >= rHeight && halfWidth/inSampleSize >= rWidth)
                inSampleSize *= 2;
        }
        return inSampleSize;
    }
    private static Bitmap decodeSampledBitmapFromResource (Resources res, int resId, int reqwidth, int reqheight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqwidth, reqheight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_profile);

        Intent intent = getIntent();
        User u = Data.getUser(intent.getExtras().getString("USER.TAG"));
        boolean isAppUser = intent.getExtras().getBoolean("USER.ISME");
        if (u == null)
            return;

        ((TextView)findViewById(R.id.fullName)).setText(u.getfName()+" "+u.getlName());
        ((TextView)findViewById(R.id.userName)).setText(u.getUserTag());
        ((TextView)findViewById(R.id.levelNum)).setText(Integer.toString(u.getLevel()));
        ((TextView)findViewById(R.id.text_level_desc)).setText(Data.describe (u.getLevel()));
        if (isAppUser)
            ((ProgressBar)findViewById(R.id.progress_user_level)).setProgress (u.getProgress());
        else
            findViewById(R.id.progress_user_level).setVisibility(View.INVISIBLE);
        new BitmapLoader((ImageView)findViewById(R.id.image_user_profile), getResources()).execute(u.getUserProfileResource());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (Transaction t : u.getHistory())
            transaction.add (R.id.layout_user_activity,t.getFragmentRepresentation());
        transaction.commit();
    }

    private static class BitmapLoader extends AsyncTask<Integer, Void, Bitmap>
    {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private Resources resources;

        public BitmapLoader (ImageView view, Resources resources)
        {
            imageViewReference = new WeakReference<ImageView>(view);
            this.resources = resources;
        }

        @Override
        protected Bitmap doInBackground (Integer... params)
        {
            data = params[0];
            return decodeSampledBitmapFromResource(resources, data, 100, 100);
        }

        @Override
        protected void onPostExecute (Bitmap bitmap)
        {
            if (imageViewReference != null && bitmap != null)
            {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null)
                    imageView.setImageBitmap (bitmap);
            }
        }
    }
}
