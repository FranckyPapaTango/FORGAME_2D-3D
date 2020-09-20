package com.rafaros.game;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.rafaros.adroidcaneva.spriteclass.Sprite;
import com.rafaros.adroidcaneva.ballclass.Ball;



public class SurfaceviewExample extends Activity implements View.OnTouchListener {
    Ball ball_africa;
    Bitmap bitmap_africa;
    Bitmap bitmap_logaid_fixe;
    Bitmap bitmap_sprite;
    public com.rafaros.adroidcaneva.spriteclass.Sprite sprite;
    Surfacview v;


    float x;
    float y;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        v = new Surfacview(this);
        super.onCreate(savedInstanceState);
        this.v.setOnTouchListener(this);
        this.bitmap_logaid_fixe = BitmapFactory.decodeResource(getResources(), R.drawable.smallwave_bitmap);
        this.bitmap_sprite = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet256x256b);
        this.bitmap_africa = BitmapFactory.decodeResource(getResources(), R.drawable.lune);
        this.y = 150.0f;
        this.x = 150.0f;
        setContentView(this.v);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.v.resume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.v.pause();
    }

    public class Surfacview extends SurfaceView implements Runnable {
        Boolean isItOk = false;
        Boolean spriteIsLoaded = false;
        SurfaceHolder surface_holder = getHolder();
        Thread thread = null;

        public Surfacview(Context context) {
            super(context);


        }

        public void pause() {
            this.isItOk = false;
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.thread = null;
        }

        public void resume() {
            this.isItOk = true;
            this.thread = new Thread(this);
            this.thread.start();
        }

        public void run() {
            SurfaceviewExample.this.sprite = new Sprite(this, SurfaceviewExample.this.bitmap_sprite);
            SurfaceviewExample.this.ball_africa = new Ball(this, SurfaceviewExample.this.bitmap_africa);
            while (this.isItOk.booleanValue()) {
                if (this.surface_holder.getSurface().isValid()) {
                    Canvas canvas = this.surface_holder.lockCanvas();
                    SurfaceviewExample.this.onDrawIt(canvas);
                    this.surface_holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDrawIt(Canvas canvas) {
        canvas.drawARGB(255, 150, 150, 10);
        canvas.drawBitmap(this.bitmap_logaid_fixe, this.x - ((float) (this.bitmap_logaid_fixe.getWidth() / 2)), this.y - ((float) (this.bitmap_logaid_fixe.getHeight() / 2)), (Paint) null);
        this.ball_africa.onDrawBall(canvas);
        this.sprite.onDrawSprite(canvas);
    }

    public boolean onTouch(View v2, MotionEvent event) {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (event.getAction()) {
            case 0:
                this.x = event.getX();
                this.y = event.getY();
                return true;
            case 1:
                this.x = event.getX();
                this.y = event.getY();
                return true;
            case 2:
                this.x = event.getX();
                this.y = event.getY();
                return true;
            default:
                return true;
        }
    }
}
