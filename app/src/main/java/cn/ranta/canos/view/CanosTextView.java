package cn.ranta.canos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import cn.ranta.canos.R;

public class CanosTextView extends View implements View.OnClickListener, View.OnTouchListener {

    //控制框宽度
    private int controlWidth;
    //控制框颜色
    private int controlColor;

    //当前是否是编辑模式
    private boolean isEditMode;

    //边框粗细
    private int borderWidth;
    //内间距大小
    private int paddingSize;

    //文本
    private String text;

    //文字大小
    private int fontSize;

    //逆时针旋转的角度 0°~360°
    private int angle;

    private int borderColor;//边框颜色
    private int textColor;//文字颜色
    private int bgColor;    //背景色

    //画笔
    private Paint textPaint;//文字画笔
    private Paint borderPaint;//边框画笔
    private Paint bgPaint;//背景
    private Paint controlPaint;//控制框画笔

    public CanosTextView(Context context) {
        super(context);

        init(context, null, 0);
    }

    public CanosTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);
    }

    public CanosTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        borderWidth = 10;
        paddingSize = 10;
        controlWidth = 4;

        fontSize = 100;

        text = getResources().getString(R.string.preview_text_cn);

        //textPaint.setFilterBitmap(Color);

        borderColor = Color.BLUE;
        textColor = Color.RED;
        bgColor = Color.YELLOW;
        controlColor = Color.WHITE;

        textPaint = new Paint();
        borderPaint = new Paint();
        bgPaint = new Paint();
        controlPaint = new Paint();

        setOnClickListener(this);

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.rotate(30, getWidth() / 2, getHeight() / 2);

        //设置画笔
        textPaint.setTextSize(fontSize);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);

        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setColor(borderColor);

        bgPaint.setColor(bgColor);

        controlPaint.setStrokeWidth(controlWidth);
        controlPaint.setColor(controlColor);

        //绘制背景
        {
            canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), bgPaint);
        }

        //绘制边框
        {
            float halfBorderWidth = borderWidth / 2;
            float[] borderPoints = new float[]
                    {
                            0, halfBorderWidth, getWidth(), halfBorderWidth,
                            getWidth() - halfBorderWidth, 0, getWidth() - halfBorderWidth, getHeight(),
                            getWidth() - halfBorderWidth, getHeight() - halfBorderWidth, 0, getHeight() - halfBorderWidth,
                            halfBorderWidth, 0, halfBorderWidth, getHeight() - halfBorderWidth
                    };
            canvas.drawLines(borderPoints, borderPaint);
        }

        //绘制文字
        {
            if (text != null && text.length() > 0) {

                //canvas.save();
                //canvas.rotate(30, getWidth() / 2, getHeight() / 2);

                //绘制时控制文本绘制的范围
                Rect textBound = new Rect();

                textPaint.getTextBounds(text, 0, text.length(), textBound);

                int fontLeft = (getWidth() - textBound.width()) / 2;
                Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                int baseline = (getMeasuredHeight() - fontMetrics.descent + fontMetrics.ascent) / 2 - fontMetrics.ascent;

                canvas.drawText(text, fontLeft, baseline, textPaint);

                //canvas.restore();
            }
        }

        //绘制控制框
        if (true) {

            float halfBorderWidth = controlWidth / 2;
            float[] controlPoints = new float[]
                    {
                            0, halfBorderWidth, getWidth(), halfBorderWidth,
                            getWidth() - halfBorderWidth, 0, getWidth() - halfBorderWidth, getHeight(),
                            getWidth() - halfBorderWidth, getHeight() - halfBorderWidth, 0, getHeight() - halfBorderWidth,
                            halfBorderWidth, 0, halfBorderWidth, getHeight() - halfBorderWidth
                    };

            canvas.drawLines(controlPoints, controlPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        Paint measurePaint = new Paint();
        Rect textRect = new Rect();

        measurePaint.setTextSize(fontSize);
        measurePaint.getTextBounds(text, 0, text.length(), textRect);
        Paint.FontMetricsInt fontMetricsInt = measurePaint.getFontMetricsInt();

        int textOriginWidth = textRect.width();
        int textOriginHeight = fontMetricsInt.descent - fontMetricsInt.ascent;

        //边框 & 内间距
        int totalWidth = textOriginWidth + borderWidth * 2 + paddingSize * 2;
        int totalHeight = textOriginHeight + borderWidth * 2 + paddingSize * 2;

        //Point newSize = getNewSize(width, height, 30);
        //width = newSize.x;
        //height = newSize.y;

        setMeasuredDimension(totalWidth, totalHeight);
    }

    //旋转矩形，获得旋转后的最大宽度和最大高度
    private Point getNewSize(float oldWidth, float oldHeight, float angle) {
        Point point = new Point();

        //对角线长度
        double diagonal = Math.sqrt(oldWidth * oldWidth + oldHeight * oldHeight);

        //α角
        double originAngle = Math.acos(oldWidth / diagonal);

        double finalAngleForHeight = originAngle + angle / 180 * Math.PI;

        double newHeight = Math.abs(Math.sin(finalAngleForHeight) * diagonal);

        double finalAngleForWidth = originAngle - angle / 180 * Math.PI;

        double newWidth = Math.abs(Math.cos(finalAngleForWidth) * diagonal);

        point.x = (int) Math.ceil(newWidth);
        point.y = (int) Math.ceil(newHeight);

        return point;
    }

    @Override
    public void onClick(View view) {

        //点击进入编辑模式
        Log.d("onConfirmButtonClicked", "clicked...");

        if (onClicked != null) {
            onClicked.onClicked(this);
        }
    }

    public interface OnClicked {
        void onClicked(CanosTextView canosTextView);
    }

    public CanosTextView.OnClicked onClicked;

    private int appViewWidth;
    private int appViewHeight;

    private float originDownX;
    private float originDownY;
    private float downX;
    private float downY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (appViewWidth == 0 || appViewHeight == 0) {

            FrameLayout frameLayout = (FrameLayout) view.getParent();
            appViewWidth = frameLayout.getMeasuredWidth();
            appViewHeight = frameLayout.getMeasuredHeight();
        }

        boolean stopProcess = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                downX = event.getRawX();
                downY = event.getRawY();
                originDownX = event.getRawX();
                originDownY = event.getRawY();
            }
            break;
            case MotionEvent.ACTION_UP: {

                if (originDownX == downX && originDownY == downY) {
                    stopProcess = false;
                } else {
                    stopProcess = true;
                }
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                float dx = event.getRawX() - downX;
                float dy = event.getRawY() - downY;
                float left = view.getLeft() + dx;
                float bottom = view.getBottom() + dy;
                float right = view.getRight() + dx;
                float top = view.getTop() + dy;

                if (left < 0) {
                    left = 0;
                    right = left + view.getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + view.getHeight();
                }
                if (right > appViewWidth) {
                    right = appViewWidth;
                    left = right - view.getWidth();
                }
                if (bottom > appViewHeight) {
                    bottom = appViewHeight;
                    top = bottom - view.getHeight();
                }
                view.layout((int) left, (int) top, (int) right, (int) bottom);
                downX = event.getRawX();
                downY = event.getRawY();
                view.postInvalidate();
            }
            break;
        }

        return stopProcess;
    }
}
