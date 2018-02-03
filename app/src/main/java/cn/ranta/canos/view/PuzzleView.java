package cn.ranta.canos.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.ranta.canos.model.ImageModel;
import cn.ranta.canos.model.AreaModel;
import cn.ranta.canos.model.TextModel;

public class PuzzleView extends View implements View.OnTouchListener {

    // region Private Members

    /**
     * 画布宽度px
     */
    private int canvasWidth;

    /**
     * 画布高度px
     */
    private int canvasHeight;

    /**
     * 画布背景色
     */
    private int canvasBackgroundColor;

    /**
     * 画布背景图片
     */
    private Bitmap canvasBackgroundImage;

    /**
     * 图像占位符背景色
     */
    private int placeholderBackgroundColor;

    /**
     * 场景模型
     */
    //private List<AreaModel> areaModelList;

    //private List<TextModel> textModelList;
    private List<PuzzleLayer> puzzleLayerList;

    //private List<String> filePathList;

    // endregion

    // region View Members
    public PuzzleView(Context context) {
        super(context);

        init();
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }
    //endregion

    private void init() {

        canvasBackgroundColor = Color.WHITE;

        placeholderBackgroundColor = Color.GRAY;

        puzzleLayerList = new ArrayList<>();
        PuzzleTextLayer puzzleLayer = new PuzzleTextLayer();
        puzzleLayer.TextModel = new TextModel();
        puzzleLayerList.add(puzzleLayer);

        setOnTouchListener(this);
    }

    public void initCanvasSize(int canvasWidthPx, int canvasHeightPx) {
        this.canvasWidth = canvasWidthPx;
        this.canvasHeight = canvasHeightPx;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawBackground(canvas);

        drawScene(canvas);
    }

    private void drawBackground(Canvas canvas) {

        if (canvasBackgroundImage != null) {

            Paint imagePaint = new Paint();
            imagePaint.setAntiAlias(true);

            float cellWidth = canvasBackgroundImage.getWidth();
            float cellHeight = canvasBackgroundImage.getHeight();
            int colCount = (int) Math.ceil(canvas.getWidth() / cellWidth);
            int rowCount = (int) Math.ceil(canvas.getHeight() / cellHeight);
            for (int col = 0; col < colCount; col++) {
                for (int row = 0; row < rowCount; row++) {
                    canvas.drawBitmap(canvasBackgroundImage, col * cellWidth, row * cellHeight, imagePaint);
                }
            }
        } else {

            canvas.drawColor(canvasBackgroundColor);
        }
    }

    public void setCanvasBackgroundColor(int color) {

        this.canvasBackgroundColor = color;

        this.invalidate();
    }

    /**
     * 设置背景图片
     *
     * @param bitmap
     */
    public void setCanvasBackgroundImage(Bitmap bitmap) {

        this.canvasBackgroundImage = bitmap;

        this.invalidate();
    }

    /**
     * 画场景背景图
     */
    private void drawScene(Canvas canvas) {

        if (puzzleLayerList != null && puzzleLayerList.size() > 0) {

            Paint imagePaint = new Paint();
            imagePaint.setAntiAlias(true);

            for (int puzzleLayerIndex = 0, puzzleLayerListSize = puzzleLayerList.size(); puzzleLayerIndex < puzzleLayerListSize; puzzleLayerIndex++) {

                if (puzzleLayerList.get(puzzleLayerIndex) instanceof PuzzleImageLayer) {

                    PuzzleImageLayer puzzleImageLayer = (PuzzleImageLayer) puzzleLayerList.get(puzzleLayerIndex);

                    AreaModel areaModel = puzzleImageLayer.AreaModel;

                    areaModel.CanvasPath = areaModel.OriginPath;

                    canvas.save();

                    canvas.clipPath(areaModel.CanvasPath);

                    //background
                    canvas.drawColor(placeholderBackgroundColor);

                    //image
                    if (areaModel.ImageModel != null) {

                        ImageModel imageModel = areaModel.ImageModel;

                        canvas.drawBitmap(
                                imageModel.ScaledBitmap,
                                areaModel.AreaLeft + imageModel.LocationLeftOffset,
                                areaModel.AreaTop + imageModel.LocationTopOffset,
                                imagePaint);
                    }

                    canvas.restore();

                }

                if (puzzleLayerList.get(puzzleLayerIndex) instanceof PuzzleTextLayer) {

                    PuzzleTextLayer puzzleTextLayer = (PuzzleTextLayer) puzzleLayerList.get(puzzleLayerIndex);

                    TextModel textModel = puzzleTextLayer.TextModel;

                    if (textModel != null) {

                        PointF textModelSize = textModel.getSize();

                        //绘制背景
                        {
                            canvas.drawRect(
                                    textModel.LocationLeftOffset,
                                    textModel.LocationTopOffset,
                                    textModel.LocationLeftOffset + textModelSize.x,
                                    textModel.LocationTopOffset + textModelSize.y,
                                    textModel.BgPaint);
                        }

                        //绘制边框
                        {
                            float halfBorderWidth = textModel.borderWidth / 2;
                            float[] borderPoints = new float[]
                                    {
                                            0, halfBorderWidth, textModelSize.x, halfBorderWidth,
                                            textModelSize.x - halfBorderWidth, 0, textModelSize.x - halfBorderWidth, textModelSize.y,
                                            textModelSize.x - halfBorderWidth, textModelSize.y - halfBorderWidth, 0, textModelSize.y - halfBorderWidth,
                                            halfBorderWidth, 0, halfBorderWidth, textModelSize.y - halfBorderWidth
                                    };
                            //左偏移&上偏移
                            for (int i = 0, borderPointsSize = borderPoints.length; i < borderPointsSize; i++) {
                                borderPoints[i] += (i % 2 == 0 ? textModel.LocationLeftOffset : textModel.LocationTopOffset);
                            }

                            canvas.drawLines(borderPoints, textModel.BorderPaint);
                        }

                        //绘制文字
                        {
                            if (textModel.text != null && textModel.text.length() > 0) {

                                //绘制时控制文本绘制的范围
                                Rect textBound = new Rect();
                                textModel.TextPaint.getTextBounds(textModel.text, 0, textModel.text.length(), textBound);

                                int fontLeft = (int) (textModelSize.x - textBound.width()) / 2;

                                Paint.FontMetricsInt fontMetrics = textModel.TextPaint.getFontMetricsInt();
                                int baseline = (int) (textModelSize.y - fontMetrics.descent + fontMetrics.ascent) / 2 - fontMetrics.ascent;

                                canvas.drawText(
                                        textModel.text,
                                        textModel.LocationLeftOffset + fontLeft,
                                        textModel.LocationTopOffset + baseline,
                                        textModel.TextPaint);
                            }
                        }

                        //绘制控制框
//                        if (true) {
//
//                            float halfBorderWidth = textModel.ControlWidth / 2;
//                            float[] controlPoints = new float[]
//                                    {
//                                            0, halfBorderWidth, getWidth(), halfBorderWidth,
//                                            getWidth() - halfBorderWidth, 0, getWidth() - halfBorderWidth, getHeight(),
//                                            getWidth() - halfBorderWidth, getHeight() - halfBorderWidth, 0, getHeight() - halfBorderWidth,
//                                            halfBorderWidth, 0, halfBorderWidth, getHeight() - halfBorderWidth
//                                    };
//
//                            canvas.drawLines(controlPoints, textModel.ControlPaint);
//                        }
                    }
                }
            }
        }
    }

    // region View.onTourchListener Members

    private float originDownX;
    private float originDownY;
    private float downX;
    private float downY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                downX = event.getX();
                downY = event.getY();
                originDownX = event.getX();
                originDownY = event.getY();

                if (puzzleLayerList != null && puzzleLayerList.size() > 0) {

                    for (int puzzleLayerIndex = puzzleLayerList.size() - 1; puzzleLayerIndex >= 0; puzzleLayerIndex--) {

                        if (puzzleLayerList.get(puzzleLayerIndex) instanceof PuzzleTextLayer) {

                            PuzzleTextLayer puzzleTextLayer = (PuzzleTextLayer) puzzleLayerList.get(puzzleLayerIndex);

                            TextModel textModel = puzzleTextLayer.TextModel;

                            if (textModel != null) {
                                PointF textModelSize = textModel.getSize();

                                Region region = new Region(
                                        (int) textModel.LocationLeftOffset,
                                        (int) textModel.LocationTopOffset,
                                        (int) (textModel.LocationLeftOffset + textModelSize.x),
                                        (int) (textModel.LocationTopOffset + textModelSize.y));
                                boolean contains = region.contains((int) downX, (int) downY);
                                if (contains) {
                                    textModel.NeedsCheckLocation = true;
                                    break;
                                }
                            }
                        }

                        if (puzzleLayerList.get(puzzleLayerIndex) instanceof PuzzleImageLayer) {

                            PuzzleImageLayer puzzleImageLayer = (PuzzleImageLayer) puzzleLayerList.get(puzzleLayerIndex);

                            AreaModel areaModel = puzzleImageLayer.AreaModel;

                            Path canvasPath = areaModel.CanvasPath;

                            RectF rectF = new RectF();
                            canvasPath.computeBounds(rectF, true);
                            Region localRegion = new Region();
                            localRegion.setPath(canvasPath, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                            boolean contains = localRegion.contains((int) downX, (int) downY);
                            if (contains) {
                                areaModel.ImageModel.NeedsCheckLocation = true;
                                break;
                            }
                        }
                    }
                }
            }

            break;
            case MotionEvent.ACTION_UP: {

                if (originDownX == downX && originDownY == downY) {//将点击到的图层放到最上层来

                    PuzzleLayer selectedPuzzleLayer = null;

                    for (int puzzleLayerIndex = puzzleLayerList.size() - 1; puzzleLayerIndex >= 0; puzzleLayerIndex--) {

                        PuzzleLayer puzzleLayer = puzzleLayerList.get(puzzleLayerIndex);

                        if (puzzleLayer instanceof PuzzleImageLayer) {
                            PuzzleImageLayer puzzleImageLayer = (PuzzleImageLayer) puzzleLayer;

                            AreaModel areaModel = puzzleImageLayer.AreaModel;

                            Path canvasPath = areaModel.CanvasPath;

                            RectF rectF = new RectF();
                            canvasPath.computeBounds(rectF, true);
                            Region localRegion = new Region();
                            localRegion.setPath(canvasPath, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
                            boolean contains = localRegion.contains((int) downX, (int) downY);
                            if (contains) {
                                selectedPuzzleLayer = puzzleLayer;

                                if (puzzleImageLayer.AreaModel != null && puzzleImageLayer.AreaModel.ImageModel != null) {
                                    puzzleImageLayer.AreaModel.ImageModel.NeedsCheckLocation = false;
                                }
                                break;
                            }
                        }

                        if (puzzleLayer instanceof PuzzleTextLayer) {
                            PuzzleTextLayer puzzleTextLayer = (PuzzleTextLayer) puzzleLayer;

                            TextModel textModel = puzzleTextLayer.TextModel;

                            if (textModel != null) {
                                PointF textModelSize = textModel.getSize();

                                Region region = new Region(
                                        (int) textModel.LocationLeftOffset,
                                        (int) textModel.LocationTopOffset,
                                        (int) (textModel.LocationLeftOffset + textModelSize.x),
                                        (int) (textModel.LocationTopOffset + textModelSize.y));
                                boolean contains = region.contains((int) downX, (int) downY);
                                if (contains) {
                                    selectedPuzzleLayer = puzzleLayer;

                                    textModel.NeedsCheckLocation = false;
                                    break;
                                }
                            }
                        }
                    }

                    if (selectedPuzzleLayer != null) {

                        puzzleLayerList.remove(selectedPuzzleLayer);
                        puzzleLayerList.add(selectedPuzzleLayer);
                    }

                } else {

                    for (int puzzleLayerIndex = 0, puzzleLayerListSize = puzzleLayerList.size(); puzzleLayerIndex < puzzleLayerListSize; puzzleLayerIndex++) {

                        PuzzleLayer puzzleLayer = puzzleLayerList.get(puzzleLayerIndex);

                        if (puzzleLayer instanceof PuzzleImageLayer) {

                            PuzzleImageLayer puzzleImageLayer = (PuzzleImageLayer) puzzleLayer;

                            AreaModel areaModel = puzzleImageLayer.AreaModel;

                            if (areaModel.ImageModel != null && areaModel.ImageModel.NeedsCheckLocation) {

                                ImageModel imageModel = areaModel.ImageModel;

                                if (imageModel.LocationLeftOffset > 0) {
                                    imageModel.LocationLeftOffset = 0;
                                }
                                if (imageModel.LocationTopOffset > 0) {
                                    imageModel.LocationTopOffset = 0;
                                }

                                if (imageModel.LocationLeftOffset + imageModel.ImageWidth < areaModel.AreaWidth) {
                                    imageModel.LocationLeftOffset = areaModel.AreaWidth - imageModel.ImageWidth;
                                }
                                if (imageModel.LocationTopOffset + imageModel.ImageHeight < areaModel.AreaHeight) {
                                    imageModel.LocationTopOffset = areaModel.AreaHeight - imageModel.ImageHeight;
                                }

                                areaModel.ImageModel.NeedsCheckLocation = false;
                            }
                        }

                        if (puzzleLayer instanceof PuzzleTextLayer) {
                            PuzzleTextLayer puzzleTextLayer = (PuzzleTextLayer) puzzleLayer;

                            TextModel textModel = puzzleTextLayer.TextModel;
                            if (textModel != null && textModel.NeedsCheckLocation) {

                                if (textModel.LocationLeftOffset < 0) {
                                    textModel.LocationLeftOffset = 0;
                                }
                                if (textModel.LocationTopOffset < 0) {
                                    textModel.LocationTopOffset = 0;
                                }
                                PointF textModelSize = textModel.getSize();
                                if (textModel.LocationLeftOffset + textModelSize.x > canvasWidth) {
                                    textModel.LocationLeftOffset = canvasWidth - textModelSize.x;
                                }
                                if (textModel.LocationTopOffset + textModelSize.y > canvasHeight) {
                                    textModel.LocationTopOffset = canvasHeight - textModelSize.y;
                                }

                                textModel.NeedsCheckLocation = false;
                            }
                        }
                    }
                }

                this.invalidate();
            }

            break;
            case MotionEvent.ACTION_MOVE: {

                float deltaX = event.getX() - downX;
                float deltaY = event.getY() - downY;

                for (int puzzleLayerIndex = 0, puzzleLayerListSize = puzzleLayerList.size(); puzzleLayerIndex < puzzleLayerListSize; puzzleLayerIndex++) {

                    PuzzleLayer puzzleLayer = puzzleLayerList.get(puzzleLayerIndex);

                    if (puzzleLayer instanceof PuzzleImageLayer) {

                        PuzzleImageLayer puzzleImageLayer = (PuzzleImageLayer) puzzleLayer;

                        AreaModel areaModel = puzzleImageLayer.AreaModel;

                        if (areaModel.ImageModel != null && areaModel.ImageModel.NeedsCheckLocation) {

                            ImageModel imageModel = areaModel.ImageModel;

                            imageModel.LocationLeftOffset = imageModel.LocationLeftOffset + deltaX;
                            imageModel.LocationTopOffset = imageModel.LocationTopOffset + deltaY;
                        }
                    }

                    if (puzzleLayer instanceof PuzzleTextLayer) {

                        PuzzleTextLayer puzzleTextLayer = (PuzzleTextLayer) puzzleLayer;

                        TextModel textModel = puzzleTextLayer.TextModel;
                        if (textModel != null && textModel.NeedsCheckLocation) {

                            textModel.LocationLeftOffset = textModel.LocationLeftOffset + deltaX;
                            textModel.LocationTopOffset = textModel.LocationTopOffset + deltaY;
                        }
                    }
                }

                downX = event.getX();
                downY = event.getY();

                this.postInvalidate();
            }

            default:
                break;
        }

        return true;//consumed = true
    }

    // endregion

    // region Getter and Setter
    public void setAreaModelList(List<AreaModel> areaModelList) {

        if (areaModelList != null && areaModelList.size() > 0) {

            for (int areaModelIndex = 0, areaModelListSize = areaModelList.size(); areaModelIndex < areaModelListSize; areaModelIndex++) {

                PuzzleImageLayer puzzleImageLayer = new PuzzleImageLayer();

                puzzleImageLayer.AreaModel = areaModelList.get(areaModelIndex);

                puzzleLayerList.add(puzzleImageLayer);
            }
        }

        invalidate();
    }

    public void SaveAs(File outputFile) {

        try {

            Bitmap outputBitmap = Bitmap.createBitmap(this.canvasWidth, this.canvasHeight, Bitmap.Config.ARGB_8888);

            Canvas outputCanvas = new Canvas(outputBitmap);

            this.draw(outputCanvas);

            FileOutputStream outputStream = new FileOutputStream(outputFile);

            outputBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();

            outputStream.close();

        } catch (FileNotFoundException x) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // endregion

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(this.canvasWidth, this.canvasHeight);
    }
}
