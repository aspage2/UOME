package com.team21.cs465.uome;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * AsyncTask subclass for processing bitmaps
 */
public class BitmapLoader extends AsyncTask<Integer, Void, Bitmap>
{
    /**
     * Calculate the nearest lower power of 2 for the given width and height
     *
     * @param options The BitmapFactory.Options struct for calculations
     * @param rHeight the requested height
     * @param rWidth the requested width
     */
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

    /**
     * Get a decoded bitmap from the given resources & id, scaled to the requested width/height
     * @param res Resources helper
     * @param resId ID of resource image to use
     * @param reqwidth  Requested width
     * @param reqheight Requested height
     * @return a scaled bitmap
     */
    private static Bitmap decodeSampledBitmapFromResource (Resources res, int resId, int reqwidth, int reqheight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqwidth, reqheight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private final WeakReference<ImageView> imageViewReference;
    private Resources resources;

    public BitmapLoader (ImageView view, Resources resources)
    {
        imageViewReference = new WeakReference<>(view);
        this.resources = resources;
    }

    @Override
    protected Bitmap doInBackground (Integer... params)
    {
        int rw, rh;
        if (params.length == 3) {
            rw = params[1];
            rh = params[2];
        } else {
            rw = 100;
            rh = 100;
        }
        return decodeSampledBitmapFromResource(resources, params[0], rw, rh);
    }

    @Override
    protected void onPostExecute (Bitmap bitmap)
    {
        if (bitmap != null)
        {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null)
                imageView.setImageBitmap (bitmap);
        }
    }
}
