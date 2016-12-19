package com.dengxiaosheng.candleanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import java.util.Random;

/**
 * Created by dengxiaosheng on 2016/12/19.
 */

public class Flame {
    private  static float CHANGE_FACTOR=20;
    private Paint mPaint;
    private Path mPath;

    //火焰左下角坐标
    private int mCurX;
    private int mCurY;
    //火焰高度
    private int mHeight;
    //火焰宽度
    private int mWidth;
    //火焰初始高度
    private int mPreHeight;
    //火焰初始宽度
    private int mPreWidth;
    //火焰顶部贝塞尔曲线控制点变化参数
    private int mTopXFactor;
    private int mTopYFactor;
    //烟球坐标
    private Point mSmokePoint;
    //眼球半径
    private int mSmokeRadius;
    //用于实现火焰的抖动
    private Random mRandom;
    //光环半径
    private int mHaloRadius;
    //正在燃烧
    private boolean mIsFiring;
    //是否启动停止动画
    private boolean mIsStopAnim=false;
    private boolean mFlagStop=false;
    private LinearGradient mLinearGradient;
    private RadialGradient mRadialGradient;

    private ValueAnimator mFlameAnimator;
    private ValueAnimator mHaloAnimator;

    public int getmCurX() {
        return mCurX;
    }

    public void setmCurX(int mCurX) {
        this.mCurX = mCurX;
    }

    public int getmCurY() {
        return mCurY;
    }

    public void setmCurY(int mCurY) {
        this.mCurY = mCurY;
    }

    public Flame(int x, int y) {
        this.mHeight = x;
        this.mWidth = y;
    }
    public void initConfig(int width,int height){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPath=new Path();
        mRandom=new Random();
        mSmokePoint=new Point();
        mWidth=width;
        mHeight=height;
        mHeight=0;
        mPreHeight=height;
        mHaloRadius=70;
        mLinearGradient=new LinearGradient(mCurX+mWidth/2,
                mCurY+mPreHeight/3,
                mCurX+mPreWidth/2,
                mCurY-mPreHeight/3*4,
                Color.YELLOW,Color.RED, Shader.TileMode.REPEAT);
        mRadialGradient=new RadialGradient(mCurX+mWidth/2,mCurY-mPreHeight/2,mHaloRadius,
                new int[]{Color.WHITE,Color.TRANSPARENT},null,Shader.TileMode.REPEAT);
        mSmokePoint.x=mCurX-20;
        mSmokePoint.y=mCurY-20;
    }
    public void initAnim(){
        mFlameAnimator=ValueAnimator.ofFloat(0,4).setDuration(4000);
        mFlameAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mFlameAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float zeroToOne= (float) animation.getAnimatedValue();
                if (zeroToOne>=1.0f&&zeroToOne<=1.2f){
                    //火焰燃起
                    zeroToOne=1.0f-5*(zeroToOne-1.0f);
                    mHeight= (int) (mPreHeight*(1-zeroToOne));
                    mIsFiring=true;
                }else if (zeroToOne >= 3.5f){
                    if (mFlagStop){
                        mFlameAnimator.cancel();
                        return;
                    }
                    //火焰吹灭
                    zeroToOne=2*(zeroToOne-3.5f);
                    mTopXFactor= (int) (-20*zeroToOne);
                    mTopYFactor= (int) (160*zeroToOne);

                    mIsFiring=false;
                }
            }
        });

        mFlameAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                if(mIsStopAnim){
                    mFlagStop=true;
                }
                mTopXFactor=0;
                mTopYFactor=0;
                mHeight=0;
                mWidth=mPreWidth;
            }
        });
        mHaloAnimator=ValueAnimator.ofFloat(0,1).setDuration(500);
        mHaloAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mHaloAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float zeroToOne= (float) animation.getAnimatedValue();
                if (mIsFiring){
                    mHaloRadius= (int) (70+zeroToOne%1.0f*20);
                }
                mHaloAnimator.start();
                mFlameAnimator.start();
            }
        });
    }
    public void stopFlame(){
        mIsStopAnim=true;
    }
    public void drawFlame(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mLinearGradient);
        mPath.reset();
        mPath.moveTo(mCurX,mCurY);
        mPath.quadTo(mCurX+mWidth/2,mCurY+mHeight/3,mCurX+mWidth,mCurY);
        mPath.quadTo(mCurX+mWidth/2+((1-mRandom.nextFloat())*CHANGE_FACTOR)+mTopXFactor,
                mCurY-mHeight*2+mTopYFactor,mCurX,mCurY);
        canvas.drawPath(mPath,mPaint);

        if (mIsFiring){
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setShader(mRadialGradient);
            canvas.drawCircle(mCurX+mWidth/2,mCurY-mHeight/2,mHaloRadius,mPaint);
            canvas.drawCircle(mCurX+mWidth/2,mCurY-mHeight/2,mHaloRadius+5,mPaint);
            canvas.drawCircle(mCurX+mWidth/2,mCurY-mHeight/2,mHaloRadius-5,mPaint);

        }
    }
}
