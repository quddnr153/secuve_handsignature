package com.handsignature.secuve.secuvehandsignature;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Woori on 2016-08-12.
 */

public class Backup_DrawActivity extends AppCompatActivity{

    /*


    class Point {
        float x;
        float y;
        boolean isDraw;
        public Point(float x, float y, boolean isDraw) {
            this.x = x;
            this.y = y;
            this.isDraw = isDraw;
        }
    }
    class Paper extends View {
        public Paper(Context context) {
            super(context);
            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent e) {
                    switch(e.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            points.add(new Point(e.getX(), e.getY(), true));
                            invalidate();
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_DOWN:
                            points.add(new Point(e.getX(), e.getY(), false));
                    }
                    return true;
                }
            });
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            p.setStrokeWidth(5);
            for(int i=1; i<points.size(); i++) {
                if(!points.get(i).isDraw) continue;
                canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, p);
            }
        }
    }
    ArrayList<Point> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper paper = new Paper(this);
        setContentView(paper);
    }

    */
}
