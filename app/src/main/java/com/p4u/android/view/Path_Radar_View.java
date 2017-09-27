package com.p4u.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ShaoZhen-PC on 2017/9/22.
 */

public class Path_Radar_View extends View {

    // 画笔
    private Paint mPaint;   // 六边形
    private Paint mP1;      // 连线
    private Paint mP2;     // 填充
    private Paint mP3;     // 填充边框
    private Paint mP4;     // 数据顶点圆点
    private Paint textPaint;//文本

    // 1.创建Picture
    private Picture mPicture = new Picture();

    // 路径
    private Path mPath;     // 六边形
    private Path p1;        // 连线
    private Path p2;        // 填充
    private Path p3;        // 填充边框
    private Path p4;        // 数据顶点圆点

    // View 宽高
    private int mViewWidth;
    private int mViewHeight;

    private int[] fillPath = new int[]{400, 300, 150, 290, 190, 300, 240, 160};
    private String[] titles = {"休", "生", "伤", "杜", "景", "死", "惊", "开"};

    private int count = 8;                //数据个数

    public Path_Radar_View(Context context) {
        this(context, null);
    }

    public Path_Radar_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAll();
    }

    public void initAll() {
        // 创建画笔
//        mPaint = new Paint();
        // 画笔颜色
//        mPaint.setColor(Color.BLACK);
        // 填充模式 - 描边
//        mPaint.setStyle(Paint.Style.STROKE);
        // 填充
//        mPaint.setStyle(Paint.Style.FILL);
        // 光影
//        mPaint.setShadowLayer(15, 0, 0, Color.WHITE);
        // 边框宽度 - 10
//        mPaint.setStrokeWidth(3);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        mP1 = new Paint();
        mP1.setColor(Color.BLACK);
        mP1.setStyle(Paint.Style.STROKE);
        mP1.setStrokeWidth(3);

        mP2 = new Paint();
        mP2.setColor(Color.argb(33, 3, 3, 0xff));
        mP2.setStyle(Paint.Style.FILL);
        mP2.setStrokeWidth(2);

        mP3 = new Paint();
        mP3.setColor(Color.argb(0xff, 3, 3, 0xff));
        mP3.setStyle(Paint.Style.STROKE);
        mP3.setStrokeWidth(2);

        mP4 = new Paint();
        mP4.setColor(Color.argb(0xff, 3, 3, 0xff));
        mP4.setStyle(Paint.Style.FILL);
        mP4.setStrokeWidth(1);

        textPaint = new Paint();                // 创建画笔
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小

        // 创建Path
//        mPath = new Path();
//        // lineTo
//        mPath.lineTo(100, 100);
//        //移动下一次操作的起点位置
//        mPath.moveTo(200,200);
//        mPath.lineTo(300, 300);
//        // 设置之前操作的最后一个点位置
//        mPath.setLastPoint(250,250);
//        mPath.lineTo(200, 0);
        // 矩形
//        mPath.addRect(-200,-200,200,200, Path.Direction.CW);
//        mPath.addRect(-200,-200,200,200, Path.Direction.CW);
//        Path src = new Path();
//        // 圆
//        src.addCircle(0,0,300, Path.Direction.CW);
//        mPath.addPath(src);
//        圆点坐标：(x0,y0)
//        半径：r
//        角度：a0
//        则圆上任一点为：（x1,y1）
//        x1   =   x0   +   r   *   cos(ao   *   3.14   /180   )
        // 0 + 500 * Math.cos(30*3.14/180)
//        y1   =   y0   +   r   *   sin(ao   *   3.14   /180   )
        // Math.cos(32)
        // close 闭合起点和终点
//        mPath.close();

        mPath = new Path();
        p1 = new Path();
        p2 = new Path();
        p3 = new Path();
        p4 = new Path();

        setFillPath();
        setLigature();
        setHexagonPath();

    }

    /**
     * 画连线
     */
    private void setLigature() {
        int radius = 400;
        int angle = 0;
        for (int i = 0; i < count / 2; i++) {
            p1.moveTo(getX(radius, angle), getY(radius, angle));
            angle += 360 / 2;
            p1.lineTo(getX(radius, angle), getY(radius, angle));
            angle += 360 / count;
        }
    }

    /**
     * 画填充区域
     */
    private void setFillPath() {
        int angle = 0;
        p2.moveTo(getX(fillPath[0], angle), getY(fillPath[0], angle));
        p3.moveTo(getX(fillPath[0], angle), getY(fillPath[0], angle));
        p4.addCircle(getX(fillPath[0], angle), getY(fillPath[0], angle), 10, Path.Direction.CW);
        for (int i = 1; i < count; i++) {
            angle += 360 / count;
            p2.lineTo(getX(fillPath[i], angle), getY(fillPath[i], angle));
            p3.lineTo(getX(fillPath[i], angle), getY(fillPath[i], angle));
            p4.addCircle(getX(fillPath[i], angle), getY(fillPath[i], angle), 10, Path.Direction.CW);
        }
        p2.close();
        p3.close();
    }

    /**
     * 画蛛网格
     */
    private void setHexagonPath() {
        int radius = 400;
        int angle;
        for (int h = 0; h < count - 1; h++) {
            angle = 0;
            mPath.moveTo(getX(radius, angle), getY(radius, angle));
            angle += 360 / count;
            for (int i = 0; i < count - 1; i++) {
                mPath.lineTo(getX(radius, angle), getY(radius, angle));
                angle += 360 / count;
            }
            mPath.close();
            radius -= 80;
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {

        Rect rect = new Rect();
        textPaint.getTextBounds(titles[0], 0, titles[0].length(), rect);
        int width = rect.width();//文本的宽度
        int height = rect.height();//文本的高度
        int angle = 0;
        for (int i = 0; i < count; i++) {
            canvas.drawText(titles[i], getX(440, angle) - width / 2, getY(440, angle) + height / 2, textPaint);
            angle += 360 / count;
        }
    }

    private int getX(int radius, int angle) {
        return (int) (0 + radius * Math.cos(angle * 3.14 / 180));
    }

    private int getY(int radius, int angle) {
        return (int) (0 + radius * Math.sin(angle * 3.14 / 180));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        // ↓ 注意 翻转y坐标轴 -1,-1 对角
//        canvas.scale(1, -1);

        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(p1, mP1);
        canvas.drawPath(p2, mP2);
        canvas.drawPath(p3, mP3);
        canvas.drawPath(p4, mP4);
        drawText(canvas);
    }

}
