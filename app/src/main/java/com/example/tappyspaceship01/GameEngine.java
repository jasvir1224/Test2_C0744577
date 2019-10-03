package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;
    
    Item item1, item2, item3;
    Player player;
    Rect hitbox;


int lives= 3;
int score = 0;
    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC

    // ----------------------------
    // ## GAME STATS
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;




        player=new Player(this.getContext(),1400,100);
     item1=new Item(this.getContext(),800,100);
    item2=new Item(this.getContext(),400, 400);
    item3 = new Item(this.getContext(), 200, 200);



        this.printScreenInfo();
    }



    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions() {
    }


    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);

            this.paintbrush.setColor(Color.BLACK);
            this.canvas.drawRect(100,
                    150,
                    100 + 1300,
                    150 + 30,
                    paintbrush);

            this.canvas.drawRect(100,
                    350 ,
                    100 + 1300,
                    350 + 30,
                    paintbrush);
            this.canvas.drawRect(100,
                    550 ,
                    100 + 1300,
                    550 + 30,
                    paintbrush);
            this.canvas.drawRect(100,
               750 ,
                    100+ 1300,
                    750 + 30,
                    paintbrush);

            this.canvas.drawBitmap(this.player.getImage(),this.player.getxPosition(),this.player.getyPosition(),paintbrush);
           this.canvas.drawBitmap(this.item1.getImage(),this.item1.getxPosition(),this.item1.getyPosition(),paintbrush);
           this.canvas.drawBitmap(this.item2.getImage(),this.item2.getxPosition(),this.item2.getyPosition(),paintbrush);
            this.canvas.drawBitmap(this.item3.getImage(),this.item3.getxPosition(),this.item3.getyPosition(),paintbrush);

   this.paintbrush.setColor(Color.BLACK);
            this.paintbrush.setTextSize(50);
            this.canvas.drawText("Lives "+lives,800,800,paintbrush);
            this.canvas.drawText("Score " + score, 1000, 800,paintbrush);



            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

        }
        else if (userAction == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
