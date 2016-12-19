package com.dengxiaosheng.candleanimation;


import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by dengxiaosheng on 2016/12/19.
 */

public abstract class ICandle {
    //蜡烛底部坐标
    private int mCurX;
    private int mCurY;
    //蜡烛宽高
    protected int mCandleWidth;
    protected int mCandleHeight;
    //蜡烛左眼坐标
    protected Point mEyeLPoint;
    //蜡烛右眼坐标
    protected Point mEyeRPoint;
    //蜡烛眼睛半径
    protected int mEyeRadius;
    //蜡烛眼睛之间间距
    protected int mEyePitch;
    //蜡烛颜色
    protected int mCandleColor;
    //是否停止动画中
    protected boolean mIsAnimStoping=false;
    //蜡烛芯坐标
    protected Point mCandlewickPoint;

    public ICandle(int mCurX, int mCurY) {
        this.mCurX = mCurX;
        this.mCurY = mCurY;
    }
    public void initCandle(int width,int height){
        mCandleWidth=width;
        mCandleHeight=height;
    }

    public int getX() {
        return mCurX;
    }

    public int getY() {
        return mCurY;
    }

    public int getmCandleWidth() {
        return mCandleWidth;
    }

    public void setmCandleWidth(int mCandleWidth) {
        this.mCandleWidth = mCandleWidth;
    }

    public int getmCandleHeight() {
        return mCandleHeight;
    }

    public void setmCandleHeight(int mCandleHeight) {
        this.mCandleHeight = mCandleHeight;
    }

    public void setmCandleColor(int mCandleColor) {
        this.mCandleColor = mCandleColor;
    }

    public void initAnim(){

    }
    public void stopAnim(){

    }
    public void drawSelf(Canvas canvas){

    }

}
