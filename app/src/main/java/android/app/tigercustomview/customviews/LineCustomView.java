package android.app.tigercustomview.customviews;

import android.app.tigercustomview.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tiger on 2016/4/9.
 */
public class LineCustomView extends View {
    private Paint mPaint;
    private int mPaintColor = Color.RED;
    private int mPaintWidth = 10;

    public LineCustomView(Context context) {
        //在代码中生成自定义view时调用该构造方法
        this(context, null);
    }

    public LineCustomView(Context context, AttributeSet attrs) {
        //在布局文件中使用自定义view时调用该构造方法
        this(context, attrs, 0);
    }

    public LineCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        //该构造方法可以为自定义view设置默认样式
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs, defStyleAttr);
    }

    private void initPaint(Context context, AttributeSet attrs, int defStyleAttr) {
        // 获取布局文件中使用的LineCustomView的自定义属性（返回一个TypedArray）
        TypedArray mStyleAttrs = context.obtainStyledAttributes(attrs, R.styleable.LineCustomView, defStyleAttr, 0);
        // 获取这个属性数组的大小
        int n = mStyleAttrs.getIndexCount();
        // 遍历这个属性数组，如果发现使用了自定义属性，将其赋值给成员变量
        for (int i=0; i<n; i++) {
            int attr = mStyleAttrs.getIndex(i);
            switch (attr) {
                case R.styleable.LineCustomView_Line_paint_color:
                    mPaintColor = mStyleAttrs.getColor(attr, Color.RED);
                    break;
                case R.styleable.LineCustomView_Line_paint_width:
                    mPaintWidth = mStyleAttrs.getDimensionPixelSize(attr, 10);
                    break;
            }
        }
        // 记的回收
        mStyleAttrs.recycle();

        mPaint = new Paint(); //初始化一支画笔
        mPaint.setColor(mPaintColor); //设置画笔颜色
        mPaint.setStrokeWidth(mPaintWidth); //设置画笔笔尖宽度
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //canvas为画布，onDraw方法即为在画布上画画
        super.onDraw(canvas);
        //用mPaint画一条线从坐标（100，100）到（300，300）
        canvas.drawLine(100, 100, 300, 300, mPaint);
    }
}
