package com.handsignature.secuve.secuvehandsignature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Woori on 2016-08-16.
 * 서명 View
 */

public class SignView extends View {
    public Paint mPaint;
    public Bitmap mBitmap;
    public Canvas mCanvas;
    private Path path;
    private Paint paint;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    //public ArrayList<Path> paths = new ArrayList<>();
    //public ArrayList<Path> undonePaths = new ArrayList<>();

    public SignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = mBitmap.createBitmap(1400, 1000, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        path = new Path();
        paint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, paint);
/*
        for (Path p: paths) {
            canvas.drawPath(p, mPaint);
        }
*/
        canvas.drawPath(path, mPaint);
    }

    private void touch_start(float x, float y){
        //undonePaths.clear();

        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x+mX) / 2, (y+mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        path.lineTo(mX, mY);
        mCanvas.drawPath(path, mPaint);
       // paths.add(path);
        path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }
/*
    public void undo() {
        if (paths.size() > 0) {
            undonePaths.add(paths.remove(paths.size()-1));
            invalidate();
        } else {
            // toast the user
        }
    }

    public void redo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size()-1));
            invalidate();
        } else {
            // toast the user
        }
    } */
}