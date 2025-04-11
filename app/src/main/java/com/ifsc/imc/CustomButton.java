package com.ifsc.imc;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomButton extends androidx.appcompat.widget.AppCompatButton {

    float iniPosX, buttonInicialX, toqueInicialX;
    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int acao = event.getAction();

        switch (event.getAction()){
            case (MotionEvent.ACTION_DOWN):
                toqueInicialX=event.getRawX();
                buttonInicialX=this.getX();

                return true;
            case (MotionEvent.ACTION_MOVE):
                float delta=event.getRawX()- toqueInicialX;
                this.setX(delta+buttonInicialX);

                float normalized = Math.max(-1f, Math.min(1f, delta/getWidth()));
                int red = (int) (255*(1 - normalized) / 2);
                int green = (int) (255*(1 - normalized) / 2);
                this.setBackgroundColor(Color.rgb(red,green,0));

                return true;
            case (MotionEvent.ACTION_UP):
                this.setBackgroundColor(Color.GRAY);
                ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", this.getX(), buttonInicialX);
                animator.setDuration(500);
                animator.start();
                return true;
        }

        return true;
    }
}
