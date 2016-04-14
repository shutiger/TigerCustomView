package android.app.tigercustomview.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/4/13.
 */
public class RaysCustomView extends View {
    private Paint mPaint;
    private int mCenterWidth; //view宽度的中心
    private int mCenterHeight; //view
    private int mInnerRadius = 100; //内圈半径
    private int mOuterRadius = 400; //外圈半径
    private int mRayCount = 32; //射线的数量
    private double mDegree = 360 / mRayCount; //每条射线的角度

    public RaysCustomView(Context context) {
        this(context, null);
    }

    public RaysCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaysCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(attrs);
    }

    private void initPaint(AttributeSet attrs) {
        mPaint = new Paint();
//        mPaint.setColor(Color.GREEN);
        mPaint.setColor(Color.parseColor("#ecc35c"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCenterWidth = canvas.getWidth() / 2;
        mCenterHeight = canvas.getHeight() / 2;

        Path path = new Path();
        path.moveTo(mCenterWidth, mCenterHeight - mInnerRadius);
        path.lineTo(mCenterWidth, mCenterHeight - mOuterRadius);
        path.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mOuterRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mOuterRadius)));
        path.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mInnerRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mInnerRadius)));
        path.lineTo(mCenterWidth, mCenterHeight - mInnerRadius);
        path.close();

        for (int i=0; i<16; i++) {
            canvas.save();
            canvas.rotate((float)(2 * i * mDegree), mCenterWidth, mCenterHeight);
            canvas.drawPath(path, mPaint);
            canvas.restore();
        }
    }
}
