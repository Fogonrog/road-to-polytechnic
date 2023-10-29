package com.road.polytechnic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.road.polytechnic.R;

public class Draw2D extends View {

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Draw2D(Context context) {
        super(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        // стиль Заливка
        Paint mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(getResources().getColor(R.color.semi_black));
        canvas.drawPaint(mPaint);
    }
}
