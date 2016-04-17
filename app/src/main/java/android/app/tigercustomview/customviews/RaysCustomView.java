package android.app.tigercustomview.customviews;

import android.app.tigercustomview.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/4/13.
 */
public class RaysCustomView extends View {
    private Paint mPaint;
    private int mCenterWidth; //view宽度的中心
    private int mCenterHeight; //view高度的中心
    private int mInnerRadius = 100; //内圈半径
    private int mOuterRadius = 400; //外圈半径
    private int mRayCount = 15; //射线的数量
    private double mDegree = 360 / (mRayCount * 2); //每条射线的角度
    private int mRayColor = Color.GREEN; //射线颜色
    private Path mPath;

    public RaysCustomView(Context context) {
        this(context, null);
    }

    public RaysCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaysCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPath = new Path();
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RaysCustomView);
        mRayCount = typedArray.getInteger(R.styleable.RaysCustomView_Rays_count, 15);

        mPaint = new Paint();
        mPaint.setColor(mRayColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCenterWidth = canvas.getWidth() / 2;
        mCenterHeight = canvas.getHeight() / 2;

        mPath.moveTo(mCenterWidth, mCenterHeight - mInnerRadius);
        mPath.lineTo(mCenterWidth, mCenterHeight - mOuterRadius);
        mPath.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mOuterRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mOuterRadius)));
        mPath.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mInnerRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mInnerRadius)));
        mPath.close();

        for (int i = 0; i < mRayCount; i++) {
            canvas.save();
            canvas.rotate((float)(2 * i * mDegree), mCenterWidth, mCenterHeight);
            canvas.drawPath(mPath, mPaint);
            canvas.restore();
        }
    }
}
