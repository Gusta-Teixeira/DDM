package com.ifsc.imc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {
    Path currentPath;
    Paint currentPaint;
    List<Paint> mPaintList;
    List<Path> mPathList;
    ColorDrawable currentColor;
    Boolean bLinha = true, bQuadrado = false, bCirculo = false;

    float x0, y0;
    public SimplePaint(Context context) {
        super(context);
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintList = new ArrayList<Paint>();
        mPathList = new ArrayList<Path>();
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        init();
    }

    public void init(){
        currentPaint = new Paint();
        currentPath  = new Path();
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(10);
        currentPaint.setColor(currentColor.getColor());
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i<mPaintList.size(); i++){
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }
        canvas.drawPath(currentPath,currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (bLinha) {
            x0 = event.getX();
            y0 = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    currentPath.moveTo(x0,y0);
                    invalidate();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    currentPath.lineTo(x0,y0);
                    invalidate();
                    return true;
                case MotionEvent.ACTION_UP:
                    currentPath.lineTo(x0, y0);
                    mPaintList.add(currentPaint);
                    mPathList.add(currentPath);
                    init();
            }
        } else
          if (bQuadrado) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                      x0 = event.getX();
                      y0 = event.getY();
                      invalidate();
                      return true;
                  case MotionEvent.ACTION_MOVE:
                      float pX = event.getX();
                      float pY = event.getY();
                      init();
                      currentPath.addRect(x0, y0, pX, pY, Path.Direction.CW);
                      invalidate();
                      return true;
                  case MotionEvent.ACTION_UP:
                      mPaintList.add(currentPaint);
                      mPathList.add(currentPath);
                      init();
              }
        } else
          if (bCirculo) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                      x0 = event.getX();
                      y0 = event.getY();
                      invalidate();
                      return true;
                  case MotionEvent.ACTION_MOVE:
                      float pX = event.getX() - x0;
                      float pY = event.getY() - y0;
                      float raio = (float) Math.sqrt(pX * pX + pY * pY);
                      init();
                      currentPath.addCircle(x0, y0, raio, Path.Direction.CW);
                      invalidate();
                      return true;
                  case MotionEvent.ACTION_UP:
                      mPaintList.add(currentPaint);
                      mPathList.add(currentPath);
                      init();
              }
        }
        return true;
    }

    public void limpa(){
        currentPath.reset();
        mPathList.clear();
        mPaintList.clear();
        invalidate();
    }

    public void mudaCor(int Cor){
        currentColor.setColor(Cor);
        currentPaint.setColor(Cor);
    }

    public void setModo(String forma) {
        bLinha = forma.equals("linha");
        bQuadrado = forma.equals("quadrado");
        bCirculo = forma.equals("circulo");
    }

}
