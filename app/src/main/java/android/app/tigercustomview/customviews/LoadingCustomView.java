package android.app.tigercustomview.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by Administrator on 2016/4/21.
 */
public class LoadingCustomView extends View {

    private static final int UPDATE_PROGRESS = 1;
    private Paint mPaint;
    private int mRadius = 50;
    private int mColor = Color.GREEN;
    private float mFastProgress = 0;
    private float mSlowProgress = 0;
    private float mFastSpeed = 20;
    private float mAfterSpeed = 5;
    private final int[] colors = new int[]{Color.GREEN
        , Color.CYAN
        , Color.BLUE
        , Color.RED
};
    private int colorIndex = 0;

    private Handler mHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PROGRESS: //收到更新圆弧进度的消息
                    //更新起点进度 mFastSpeed为起点的速度
                    mFastProgress = (mFastProgress + mFastSpeed);
                    //更新终点进度 mAfterSpeed为终点的速度
                    mSlowProgress = (mSlowProgress + mAfterSpeed);
                    if (mFastProgress <= mSlowProgress) {
                        //如果终点追赶上了起点，放慢终点的速度，重置画笔颜色
                        mColor = colors[++colorIndex % colors.length];
                        mSlowProgress = mFastProgress - 1;
                        mAfterSpeed = 6;
                    } else if (mFastProgress - mSlowProgress > 340) {
                        //如果起点追赶上了终点，加快终点速度
                        mAfterSpeed = 34;
                    }
                    //刷新当前view
                    invalidate();
                    //每50毫秒刷新一次
                    mHandle.sendEmptyMessageDelayed(UPDATE_PROGRESS, 50);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    public LoadingCustomView(Context context) {
        this(context, null);
    }

    public LoadingCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        mHandle.sendEmptyMessage(UPDATE_PROGRESS);

        //内存泄露
        //占用线程资源
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerWidth = canvas.getWidth() / 2;
        int centerHeight = canvas.getHeight() / 2;
        //圆弧所在的长方形边框
        RectF over = new RectF(centerWidth - mRadius, centerHeight - mRadius
                , centerWidth + mRadius, centerHeight + mRadius);
        //设置画笔颜色
        mPaint.setColor(mColor);
        //画圆弧
        canvas.drawArc(over, mSlowProgress % 360, (mFastProgress - mSlowProgress) % 360, false, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandle.removeCallbacksAndMessages(null);
    }
}
