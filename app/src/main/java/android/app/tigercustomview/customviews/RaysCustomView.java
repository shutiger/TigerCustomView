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
    private int mRayCount = 20; //射线的数量
    private double mDegree = 360 / (mRayCount * 2); //每条射线的角度
    private int mRayColor = Color.GREEN; //射线颜色
    private Path mPath;//一条射线（一个小梯形）的边路径

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
        //初始化画笔的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RaysCustomView);
        mRayCount = typedArray.getInteger(R.styleable.RaysCustomView_Rays_count, 20);
        mDegree = 360 / (mRayCount * 2);
        mRayColor = typedArray.getColor(R.styleable.RaysCustomView_Rays_paint_color, Color.GREEN);

        mPaint = new Paint();
        mPaint.setColor(mRayColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取自定义view的中心位置点
        mCenterWidth = canvas.getWidth() / 2;
        mCenterHeight = canvas.getHeight() / 2;

        //一条射线即一个小梯形的路径
        mPath.moveTo(mCenterWidth, mCenterHeight - mInnerRadius); //移动到起始点
        mPath.lineTo(mCenterWidth, mCenterHeight - mOuterRadius); //画线
        mPath.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mOuterRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mOuterRadius))); //画线
        mPath.lineTo((float) (mCenterWidth + (Math.sin(Math.toRadians(mDegree)) * mInnerRadius))
                , (float) (mCenterHeight - (Math.cos(Math.toRadians(mDegree)) * mInnerRadius))); //画线
        mPath.close(); //回到起点闭合

        //循环画出所有梯形射线
        for (int i = 0; i < mRayCount; i++) {
            canvas.save();
            canvas.rotate((float)(2 * i * mDegree), mCenterWidth, mCenterHeight);
            canvas.drawPath(mPath, mPaint);
            canvas.restore();
        }
    }
}
