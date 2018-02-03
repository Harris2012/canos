package cn.ranta.canos.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import cn.ranta.canos.R;

public class TextModel extends DragItem {

    //控制框宽度
    public int ControlWidth;
    //控制框颜色
    public int ControlColor;

    //当前是否是编辑模式
    //public boolean isEditMode;

    public int borderWidth;//边框粗细
    public int paddingSize;//内间距大小


    public String text;    //文本
    public int FontSize;  //文字大小

    //逆时针旋转的角度 0°~360°
    //public int angle;

    //颜色
    public int BorderColor;//边框颜色
    public int TextColor;//文字颜色
    public int BgColor;    //背景色

    //画笔
    public Paint TextPaint;//文字画笔
    public Paint BorderPaint;//边框画笔
    public Paint BgPaint;//背景
    public Paint ControlPaint;//控制框画笔

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextModel() {

        borderWidth = 10;
        paddingSize = 10;
        ControlWidth = 4;

        FontSize = 100;

        text = "zang张三deuio";

        BorderColor = Color.BLUE;
        TextColor = Color.RED;
        BgColor = Color.YELLOW;
        ControlColor = Color.WHITE;

        // region Paint
        TextPaint = new Paint();
        TextPaint.setTextSize(FontSize);
        TextPaint.setColor(TextColor);
        TextPaint.setAntiAlias(true);

        BorderPaint = new Paint();
        BorderPaint.setStrokeWidth(borderWidth);
        BorderPaint.setColor(BorderColor);

        BgPaint = new Paint();
        BgPaint.setColor(BgColor);

        ControlPaint = new Paint();
        ControlPaint.setStrokeWidth(ControlWidth);
        ControlPaint.setColor(ControlColor);
        // endregion
    }

    public PointF getSize() {

        Paint measurePaint = new Paint();
        Rect textRect = new Rect();

        measurePaint.setTextSize(this.FontSize);
        measurePaint.getTextBounds(this.text, 0, this.text.length(), textRect);
        Paint.FontMetricsInt fontMetricsInt = measurePaint.getFontMetricsInt();

        int textOriginWidth = textRect.width();
        int textOriginHeight = fontMetricsInt.descent - fontMetricsInt.ascent;

        //边框 & 内间距
        int totalWidth = textOriginWidth + borderWidth * 2 + paddingSize * 2;
        int totalHeight = textOriginHeight + borderWidth * 2 + paddingSize * 2;

        return new PointF(totalWidth, totalHeight);
    }
}
