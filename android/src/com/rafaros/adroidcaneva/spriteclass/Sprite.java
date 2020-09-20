package com.rafaros.adroidcaneva.spriteclass;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.rafaros.game.SurfaceviewExample;

public class Sprite {
    int currentFrame = 0;
    int direction = 0;
    int height;
    Bitmap sprite_bitmap;
    SurfaceviewExample.Surfacview sprite_surfv;
    int width;
    int x;
    int xSpeed;
    int y;
    int ySpeed;

    public Sprite(SurfaceviewExample.Surfacview surfaceview, Bitmap blob) {
        this.sprite_bitmap = blob;
        this.sprite_surfv = surfaceview;
        this.height = this.sprite_bitmap.getHeight() / 4;
        this.width = this.sprite_bitmap.getWidth() / 4;
        this.y = 0;
        this.x = 0;
        this.xSpeed = 5;
        this.ySpeed = 0;
    }

    public void onDrawSprite(Canvas canvas) {
        update();
        int srcY = this.direction * this.height;
        int srcX = this.currentFrame * this.width;
        canvas.drawBitmap(this.sprite_bitmap, new Rect(srcX, srcY, this.width + srcX, this.height + srcY), new Rect(this.x, this.y, this.x + this.width, this.y + this.width), (Paint) null);
    }

    private void update() {
        if (this.x > (this.sprite_surfv.getWidth() - this.width) - this.xSpeed) {
            this.xSpeed = 0;
            this.ySpeed = 5;
            this.direction = 0;
        }
        if (this.y > (this.sprite_surfv.getHeight() - this.height) - this.ySpeed) {
            this.xSpeed = -5;
            this.ySpeed = 0;
            this.direction = 1;
        }
        if (this.x + this.xSpeed < 0) {
            this.x = 0;
            this.xSpeed = 0;
            this.ySpeed = -5;
            this.direction = 3;
        }
        if (this.y + this.ySpeed < 0) {
            this.y = 0;
            this.xSpeed = 5;
            this.ySpeed = 0;
            this.direction = 2;
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.currentFrame + 1;
        this.currentFrame = i;
        this.currentFrame = i % 4;
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }
}
