package com.zxn.titleview;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zxn on 2018/12/29.
 */
public class TitleView extends RelativeLayout implements View.OnClickListener {
    ImageView ivBack;
    TextView tvTitle;
    private int mBackIconId;
    private int mTitleLeftIconId;
    private CharSequence mTitleText = "";
    private ColorStateList mTitleTextColor;
    private Drawable mTitleLeftIconDrawable;
    private int mSize;
    private View mRightView;
    private boolean mBackEnable = true;

    private boolean isCanBack = true;


    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        onInit(attrs);
    }

    private void onInit(AttributeSet attrs) {
        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_title_view, this, true);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);

        TypedArray typedArray
                = getContext()
                .obtainStyledAttributes(attrs, R.styleable.TitleView);
        if (typedArray != null) {

            mBackIconId = typedArray.getResourceId(R.styleable.TitleView_backIcon, 0);
            mTitleText = typedArray.getText(R.styleable.TitleView_titleText);
            mTitleTextColor = typedArray.getColorStateList(R.styleable.TitleView_titleTextColor);
            //mTitleLeftIconId = typedArray.getResourceId(R.styleable.TitleView_titleLeftIcon, 0);
            mTitleLeftIconDrawable = typedArray.getDrawable(R.styleable.TitleView_titleLeftIcon);
            mSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_24);
            if (null != mTitleLeftIconDrawable) {
                mTitleLeftIconDrawable.setBounds(0, 0, mSize, mSize);
            }

            if (mBackIconId != 0) {
                setBackEnabled(true);
                ivBack.setImageResource(mBackIconId);
            }
            tvTitle.setText(mTitleText);
            if (null != mTitleTextColor) {
                tvTitle.setTextColor(mTitleTextColor);
            }
//            tvTitle.setCompoundDrawablesWithIntrinsicBounds(mTitleLeftIconDrawable, 0, 0, 0);
//            tvTitle.setCompoundDrawablesWithIntrinsicBounds(mTitleLeftIconDrawable, null, null, null);
            if (null != mTitleLeftIconDrawable) {
                tvTitle.setCompoundDrawables(mTitleLeftIconDrawable, null, null, null);
            }
            tvTitle.setCompoundDrawablePadding(getContext().getResources().getDimensionPixelSize(R.dimen.dp_1));
            typedArray.recycle();
        }
    }

    public void setFontTypeBold() {
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    public void setBackEnabled(boolean enabled) {
        ivBack.setVisibility(enabled ? VISIBLE : GONE);
    }

    public void setBackIcon(@DrawableRes int resId) {
        ivBack.setImageResource(resId);
    }

    public void setTitleText(CharSequence mTitleText) {
        this.mTitleText = mTitleText;
        tvTitle.setText(mTitleText);
    }

    public void setTitleTextColor(ColorStateList mTitleTextColor) {
        this.mTitleTextColor = mTitleTextColor;
        tvTitle.setTextColor(mTitleTextColor);
    }

    public void setTitleTextColor(@ColorRes int color) {
        this.mTitleTextColor = ColorStateList.valueOf(getResources().getColor(color));
        tvTitle.setTextColor(mTitleTextColor);
    }

    public final void setTitleText(@StringRes int resid) {
        this.mTitleText = getContext().getResources().getString(resid);
        tvTitle.setText(resid);
    }

    public void setTitleLeftIconId(int mTitleLeftIconId) {
        this.mTitleLeftIconId = mTitleLeftIconId;
        //mTitleLeftIconDrawable.setBounds(0,0, mSize, mSize);
        //tvTitle.setCompoundDrawables(mTitleLeftIconDrawable, null, null, null);
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(mTitleLeftIconId, 0, 0, 0);
        //tvTitle.setCompoundDrawablePadding(getContext().getResources().getDimensionPixelSize(R.dimen.dp_1));
    }

    public void addRightView(View view) {
        this.mRightView = view;
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_15);
        this.addView(view, params);
    }


    public void showRightView(boolean show) {
        mRightView.setVisibility(show ? VISIBLE : GONE);
    }


    private OnBackListener mOnBackListener;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            if (mOnBackListener != null) {
                mOnBackListener.onClick(view);
            } else if (mBackEnable) {
                if (getContext() instanceof Activity)
                    ((Activity) getContext()).onBackPressed();
            }
        }
    }

    public interface OnBackListener {

        void onClick(View view);
    }

    public void setOnBackListener(OnBackListener listener) {
        this.mOnBackListener = mOnBackListener;
    }

    public CharSequence getTitleText() {
        return mTitleText;
    }
}
