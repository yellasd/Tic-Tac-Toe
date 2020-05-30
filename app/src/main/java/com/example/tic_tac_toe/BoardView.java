package com.example.tic_tac_toe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BoardView extends View {

    private final int STROKE_WIDTH = 5, GAP = 12;

    private RectF[][] boxes;
    private int currentPlayer = 1;

    private int width = 0;
    private float efficientBoxWidth = 0;
    private Paint xpaint, opaint, boxPaint;
    Board board;
    private MainActivity mainActivity;
    private Player player;


    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        board = new Board();
        player = new Player(board);
        boxes = new RectF[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                boxes[i][j] = new RectF();

        xpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        opaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        boxPaint = new Paint();
        boxPaint.setStyle(Paint.Style.FILL);
        boxPaint.setColor(Color.parseColor("#FFCDD2"));

        xpaint.setColor(Color.parseColor("#09AF00"));
        xpaint.setStyle(Paint.Style.STROKE);
        xpaint.setStrokeWidth(getResources().getDisplayMetrics().density * STROKE_WIDTH);

        opaint.setColor(Color.parseColor("#1E88E5"));
        opaint.setStyle(Paint.Style.STROKE);
        opaint.setStrokeWidth(getResources().getDisplayMetrics().density * STROKE_WIDTH);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        setMeasuredDimension(width, width);

        efficientBoxWidth = (float) (width - 2 * GAP) / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left, right, top, bottom;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                boxes[i][j].left = i * (GAP + efficientBoxWidth);
                boxes[i][j].right = i * (GAP + efficientBoxWidth) + efficientBoxWidth;
                boxes[i][j].top = j * (GAP + efficientBoxWidth);
                boxes[i][j].bottom = j * (GAP + efficientBoxWidth) + efficientBoxWidth;
                canvas.drawRect(boxes[i][j], boxPaint);
                if (board.boxes[i][j] == 1) {
                    canvas.drawLine(boxes[i][j].left + efficientBoxWidth / 3, boxes[i][j].top + efficientBoxWidth / 3, boxes[i][j].right - efficientBoxWidth / 3, boxes[i][j].bottom - efficientBoxWidth / 3, xpaint);
                    canvas.drawLine(boxes[i][j].left + efficientBoxWidth / 3, boxes[i][j].bottom - efficientBoxWidth / 3, boxes[i][j].right - efficientBoxWidth / 3, boxes[i][j].top + efficientBoxWidth / 3, xpaint);
                } else if (board.boxes[i][j] == 2) {
                    canvas.drawCircle(boxes[i][j].left + efficientBoxWidth / 2, boxes[i][j].top + efficientBoxWidth / 2, efficientBoxWidth / 6, opaint);
                }
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);

        if (mainActivity.players == 1 && currentPlayer == 2)
            return true;

        else {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if (boxes[i][j].contains(current.x, current.y) && board.isGameCompleted() == -1 && board.boxes[i][j] == 0) {
                        if (mainActivity.players == 2) {
                            board.boxes[i][j] = currentPlayer;
                            if (currentPlayer == 1) currentPlayer = 2;
                            else if (currentPlayer == 2) currentPlayer = 1;
                            int t = board.isGameCompleted();
                            if (t >= 0) {
                                mainActivity.displayWinner(t);
                            }
                            invalidate();
                        }
                        if (mainActivity.players == 1) {
                            setEnabled(false);
                            if (currentPlayer == 1) {
                                board.boxes[i][j] = currentPlayer;
                                currentPlayer = 2;
                                int t = board.isGameCompleted();
                                if (t >= 0) {
                                    mainActivity.displayWinner(t);
                                }
                                invalidate();
                                if (board.isGameCompleted() == -1) {
                                    Point point = player.getMove();
                                    board.boxes[point.x][point.y] = currentPlayer;
                                    currentPlayer = 1;
                                    if (board.isGameCompleted() >= 0) {
                                        mainActivity.displayWinner(board.isGameCompleted());
                                    }
                                    invalidate();
                                    setEnabled(true);
                                }
                            }
                        }
                        break;
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
