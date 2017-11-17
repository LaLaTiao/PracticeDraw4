package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * camera是变换完才开始投影,所以canvas移动bitmap在变换完移动即可,然后在投影结束后再移动回来
         * 因为canvas是反着来,和Matrix的 preXX一样,是反着的,所以2个canvas的顺序换一下
         * 涉及到canvas的translate,所以还是需要sava/restore,且在canvas的translate前面
         */
        camera.save();
        canvas.save();
        camera.rotateX(30);
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        camera.applyToCanvas(canvas);
        canvas.translate(-(point1.x + bitmap.getWidth() / 2), -(point1.y + bitmap.getHeight() / 2));
        camera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        camera.save();
        canvas.save();
        camera.rotateY(30);
        canvas.translate(point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        camera.applyToCanvas(canvas);
        canvas.translate(-(point2.x + bitmap.getWidth() / 2), -(point2.y + bitmap.getHeight() / 2));
        camera.restore();
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
