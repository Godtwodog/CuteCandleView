package com.dengxiaosheng.candleanimation;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by dengxiaosheng on 2016/12/19.
 */

public class FireCandle extends ICandle {
    private Paint mPaint;
    //中心X坐标
    private int mCenterX;
    //记录初始高
    private int mPreHeight;
    //记录初始宽
    private int mPreWidth;
    //蜡烛芯旋转角度
    private int mCandlewickDegrees=0;

    private Flame mFlame;
    private boolean mIsFire=false;
    private boolean mIsStateOnStart=false;
    private boolean mIsStateOnEnd=false;
    private boolean mFlagStop=false;
    private ValueAnimator mCandlesAnimator;

    public interface FlameStateListener{
        void FlameStart();

        void FlameEnd();

    }
    private FlameStateListener mFlameStateListener;

    public void setmFlameStateListener(FlameStateListener mFlameStateListener) {
        this.mFlameStateListener = mFlameStateListener;
    }

    public FireCandle(int mCurX, int mCurY) {
        super(mCurX, mCurY);
    }

    @Override
    public void initAnim() {
        super.initAnim();
    }

    @Override
    public void initCandle(int width, int height) {
        super.initCandle(width, height);
    }

    @Override
    public void drawSelf(Canvas canvas) {
        super.drawSelf(canvas);
    }

    @Override
    public void stopAnim() {
        super.stopAnim();
        mFlame.stopFlame();
        mIsAnimStoping=true;
    }
}
