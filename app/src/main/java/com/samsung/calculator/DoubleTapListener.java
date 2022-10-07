package com.samsung.calculator;

import android.view.View;


public class DoubleTapListener {

    private boolean isRunning = false;
    private final int resetInTime = 400;

    private onDoubleTap listener;

    public void onDoubleClick(onDoubleTap onDoubleTap){
        this.listener = onDoubleTap;
    }

    public DoubleTapListener(View view){

        view.setOnClickListener(v -> {

            if(isRunning) listener.onDoubleClick(view);

            if(!isRunning){
                isRunning=true;

                new Thread(() -> {
                    try {

                        Thread.sleep(resetInTime);
                        isRunning = false;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        });
    }


    public interface onDoubleTap {
        void onDoubleClick(View v);
    }



}