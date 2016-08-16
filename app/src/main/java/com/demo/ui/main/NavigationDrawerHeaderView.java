package com.demo.ui.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.noname.baseapp.R;

public class NavigationDrawerHeaderView extends FrameLayout {

    @BindView(R.id.textViewEmail) TextView mTextViewEmail;
    @BindView(R.id.imageViewProfilePhoto) ImageView mImageViewProfilePhoto;

    {
        inflate(getContext(), R.layout.layout_drawer_header, this);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
    }

    public NavigationDrawerHeaderView(Context context) {
        super(context);
    }

    public NavigationDrawerHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationDrawerHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationDrawerHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setProfileImage(Bitmap bitmap) {
        mImageViewProfilePhoto.setImageBitmap(bitmap);
    }

    public void setProfileImage(Drawable placeHolderDrawable) {
        mImageViewProfilePhoto.setImageDrawable(placeHolderDrawable);
    }

    public void setUserLogin(String login) {
        mTextViewEmail.setText(login);
    }
}
