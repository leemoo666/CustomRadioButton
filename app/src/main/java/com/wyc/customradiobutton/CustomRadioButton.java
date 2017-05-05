package com.wyc.customradiobutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

/**
 * Created by 李小明 on 17/5/4.
 * 邮箱:287907160@qq.com
 */

public class CustomRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    Paint paint = new Paint(); //
    Paint circlePaint = new Paint(); //文字画笔

    private int redius = 10;

    private Bitmap bitmap;
    private int bitmapWidth;
    private int bitmapHeight;

    private int textWidth; //文字宽度
    private int textHeight; //文字高度

    public CustomRadioButton(Context context) {
        super(context);
        init(context, null);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(getTextSize());

        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.FILL);

//        setGravity(Gravity.CENTER_HORIZONTAL);
        try {
            // Popup信息
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header_man);
            if (bitmap != null) {
                bitmapWidth = bitmap.getWidth();
                bitmapHeight = bitmap.getHeight();
            }
        } catch (Throwable e) {
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        textWidth = (int) paint.measureText(getText().toString());
        textHeight = (int) (Math.ceil(paint.getFontMetrics().descent - paint.getFontMetrics().ascent) + 2);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize + 2 * redius;
        } else {
            width = Math.max(bitmapWidth, textWidth) + 2 * redius;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize + 2 * redius;
        } else {
            height = bitmapHeight + textHeight + 2 * redius;

        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int left = (getWidth() - bitmapWidth) / 2;
        int top = redius;
        int right = (getWidth() + bitmapWidth) / 2;
        int bottom = redius + bitmapHeight;
        if (left > 0 && top > 0 && bitmap != null) {
            canvas.drawBitmap(bitmap, left, top, null);
        }

        //绘制文字

        canvas.drawText(getText().toString(), getWidth() / 2 - textWidth / 2, bottom + textHeight, paint);

        canvas.drawCircle(right, top, redius, circlePaint);
    }
}
