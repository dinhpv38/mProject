package vn.manroid.devchat.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by manro on 13/06/2017.
 */

public class CustomView extends ImageView {
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null)
            return;

        if (getWidth() == 0 || getHeight() == 0)
            return;
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap roundedBitmap = getRoundedCroppedBitmap(bitmap, getHeight());
        canvas.drawBitmap(roundedBitmap, 0, 0, null);
    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {


        if (bitmap.getHeight() != radius || bitmap.getWidth() != radius) {
            bitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        Rect rect = new Rect(0, 0, radius, radius);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
    }
}