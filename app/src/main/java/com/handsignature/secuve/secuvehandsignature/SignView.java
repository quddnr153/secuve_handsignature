package com.handsignature.secuve.secuvehandsignature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    private int start = 0;
    private int end = 0;

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
        //Log.d(">>>>>>>>>>", "Cheers love~ the cavalry's here!");

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

            Log.d(">>>", "("+(int)x+","+(int)y+")");
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
                countStart(x, y);
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

    public ArrayList<String> countStart(final float x, final float y) {
        // txt file에 1000분의 1초마다 (int)x랑 (int)y를 저장하면 됨.

        if (start==0) start = 1;
        else return null; // 아니면 그냥 바로 끝내버리기

        final ArrayList<String> log = null;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.add((int)x+","+(int)y);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 1);

        return log;
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
