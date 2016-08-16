package com.handsignature.secuve.secuvehandsignature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Woori on 2016-08-14.
 */
public class DrawActivity extends Activity {
    Button ok, retry, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        retry = (Button) findViewById(R.id.button_retry);

        cancel = (Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    class Paper extends View {
        Paint paint = new Paint();
        Path path = new Path();
        float x, y;

        public Paper(Context context) {
            super(context);
        }

        public Paper(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected void onDraw(Canvas canvas) {
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = event.getX();
            y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    x = event.getX();
                    y = event.getY();
                    path.lineTo(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }

            invalidate();
            return true;
        }
    }
}
