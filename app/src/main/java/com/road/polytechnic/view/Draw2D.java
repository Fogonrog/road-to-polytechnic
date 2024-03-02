package com.road.polytechnic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.road.polytechnic.R;

public final class Draw2D extends View {
    private final Paint paint = new Paint();

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Draw2D(Context context) {
        super(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.semi_black));
        canvas.drawPaint(paint);
    }
}
