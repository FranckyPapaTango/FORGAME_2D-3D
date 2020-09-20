package com.rafaros.adroidcaneva.ballclass;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.rafaros.game.SurfaceviewExample;

public class Ball {
    private double angle;
    public Bitmap ball_bitmap;
    public SurfaceviewExample.Surfacview ball_surfv;
    private double deplx = 6.0d;
    private double deply = 6.0d;
    int height;

    private int r;
    int width ;
    double x = 2.0d;
    int xSpeed;
    private int xc;
    double y = 7.0d;
    int ySpeed;
    private int yc;

    public Ball(SurfaceviewExample.Surfacview surfaceview, Bitmap blobBall) {
        this.ball_bitmap = blobBall;
        this.ball_surfv = surfaceview;
        try{
            height = this.ball_bitmap.getHeight();
        }
        catch(Exception e){
        }
        try{
            width = this.ball_bitmap.getWidth();
        }
        catch(Exception e){
        }
    }

    public void onDrawBall(Canvas canvas) {
        update(canvas);
        canvas.drawBitmap(this.ball_bitmap, new Rect(0, 0, this.width, this.height), new Rect((int) Math.round(this.x), (int) Math.round(this.y), (int) Math.round(this.x + ((double) this.width)), (int) Math.round(this.y + ((double) this.width))), (Paint) null);
    }

    private void update(Canvas canvas) {
        if (this.x + ((double) this.ball_bitmap.getWidth()) < ((double) this.ball_surfv.getWidth())) {
            this.x += this.deplx;
            this.y += this.deply;
        } else {
            this.deplx = -this.deplx;
            this.x += this.deplx;
            this.y += this.deply;
        }
        if (this.y + ((double) this.height) < ((double) this.ball_surfv.getHeight())) {
            this.x += this.deplx;
            this.y += this.deply;
        } else {
            this.deply = -this.deply;
            this.x += this.deplx;
            this.y += this.deply;
        }
        if (this.x + ((double) this.width) < ((double) this.width)) {
            this.deplx = -this.deplx;
            this.x += this.deplx;
            this.y += this.deply;
        } else {
            this.x += this.deplx;
            this.y += this.deply;
        }
        if (this.y + ((double) this.height) < ((double) this.height)) {
            this.deply = -this.deply;
            this.x += this.deplx;
            this.y += this.deply;
        } else {
            this.x += this.deplx;
            this.y += this.deply;
        }
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
