package com.joker.basemvp.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joker.basemvp.R;
import com.joker.basemvp.utils.StatusBarUtil;


/**
 * Created by Joker
 */

public class CustomTitleBar extends LinearLayout {


    private final TextView left_title;
    private final String left_text;
    private final int right_button_imageId2;
    private final boolean show_right_image2;
    private final boolean show_left_image;
    private final ImageView reght_image2;
    private final View view_line;
    /**
     * 标题栏的根布局
     */
    private RelativeLayout ll;
    /**
     * 标题栏的左边按返回按钮
     */
    private final ImageView left_image;

    private final ImageView reght_image;
    /**
     * 标题栏的中间的文字
     */
    private TextView title;
    /**
     * 标题栏的背景颜色
     */
    private int title_background_color;
    /**
     * 标题栏的显示的标题文字
     */
    private String title_text;
    /**
     * 标题栏的显示的标题文字颜色
     */
    private int title_textColor;
    /**
     * 标题栏的显示的标题文字大小
     */
    private int title_textSize;


    /**
     * 返回按钮的资源图片
     */
    private int left_button_imageId;


    /**
     * 右边保存按钮的资源图片
     */
    private int right_button_imageId;

    /**
     * 是否显示右边保存按钮
     */
    private boolean show_right_image;

    /**
     * 标题的点击事件
     */
    private TitleOnClickListener titleOnClickListener;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**加载布局文件*/
        LayoutInflater.from(context).inflate(R.layout.layout_topbar, this, true);
        ll = (RelativeLayout) findViewById(R.id.ll);
        StatusBarUtil.setPaddingSmart(context,ll);
        left_image = (ImageView) findViewById(R.id.left_image);
        reght_image = (ImageView) findViewById(R.id.right_image);
        reght_image2 = (ImageView) findViewById(R.id.right_image2);
        title = (TextView) findViewById(R.id.title);
        left_title = (TextView) findViewById(R.id.left_title);
        view_line = findViewById(R.id.view_line);

        /**获取属性值*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        /**标题相关*/
        title_background_color = typedArray.getColor(R.styleable.CustomTitleBar_title_background, Color.rgb(64, 113, 247));
        title_text = typedArray.getString(R.styleable.CustomTitleBar_title_text);
        left_text = typedArray.getString(R.styleable.CustomTitleBar_left_text);
        title_textColor = typedArray.getColor(R.styleable.CustomTitleBar_title_textColor, Color.WHITE);
        title_textSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_title_textSize, 20);
        /**返回按钮相关*/
        left_button_imageId = typedArray.getResourceId(R.styleable.CustomTitleBar_left_button_image, R.drawable.topbar_back);
        show_left_image = typedArray.getBoolean(R.styleable.CustomTitleBar_show_left_image, false);
        /**右边按钮1相关*/
        right_button_imageId = typedArray.getResourceId(R.styleable.CustomTitleBar_right_button_image, R.drawable.topbar_drop_down);
        show_right_image = typedArray.getBoolean(R.styleable.CustomTitleBar_show_right_image, false);

        /**右边按钮2相关*/
        right_button_imageId2 = typedArray.getResourceId(R.styleable.CustomTitleBar_right_button_image2, R.drawable.topbar_upload);
        show_right_image2 = typedArray.getBoolean(R.styleable.CustomTitleBar_show_right_image2, false);
        /**设置值*/

        setTitle_background_color(title_background_color);
        setTitle_text(title_text);
        setShow_left_image(show_left_image);
        setShow_right_image(show_right_image);
        setShow_right_image2(show_right_image2);
        setLeft_text(left_text);
        left_image.setOnClickListener(v -> {
            /*if (titleOnClickListener != null) {
                titleOnClickListener.onLeftClick();
            }else {
                ((Activity)context).finish();
            }*/
            ((Activity)context).finish();
        });

        reght_image.setOnClickListener(v -> {
            if (titleOnClickListener != null) {
                titleOnClickListener.onRightClick();
            }
        });

        reght_image2.setOnClickListener(v->{
            if (titleOnClickListener != null) {
                titleOnClickListener.onRight2Click();
            }
        });
    }

    public ImageView getReght_image() {
        return reght_image;
    }

    public void setShow_right_image(boolean show_right_image) {
        reght_image.setVisibility(show_right_image?VISIBLE:GONE);
        if (show_right_image) {
            reght_image.setImageResource(right_button_imageId);
        }
    }

    public void setShow_right_image2(boolean show_right_image2) {
        reght_image2.setVisibility(show_right_image2?VISIBLE:GONE);
        if (show_right_image2) {
            reght_image2.setImageResource(right_button_imageId2);
        }
    }
    public void setShow_left_image(boolean show_left_image) {
        left_image.setVisibility(show_left_image?VISIBLE:GONE);
        if (show_left_image) {
//            left_image.setImageResource(left_button_imageId);
            setLeft_button_imageId(left_button_imageId);
        }
    }

    public void setLeft_text(String left_text) {
        if (!TextUtils.isEmpty(left_text)) {
            left_title.setText(left_text);
            left_title.setVisibility(VISIBLE);
            left_image.setVisibility(VISIBLE);
            setLeft_button_imageId(left_button_imageId);
        }
    }
    public void hideLine() {
        view_line.setVisibility(INVISIBLE);
    }

    /**
     * 获取标题栏的跟布局
     *
     * @return LinearLayout
     */
    public RelativeLayout getLl() {
        return ll;
    }

    /**
     * 获取标题栏标题TextView
     *
     * @return TextView
     */
    public TextView getTitle() {
        return title;
    }



    /**
     * 设置返回按钮的资源图片id
     *
     * @param left_button_imageId 资源图片id
     */
    public void setLeft_button_imageId(int left_button_imageId) {
        left_image.setBackgroundResource(left_button_imageId);
    }


    /**
     * 设置标题背景的颜色
     *
     * @param title_background_color
     */
    public void setTitle_background_color(int title_background_color) {
        ll.setBackgroundColor(title_background_color);
    }

    /**
     * 设置标题的文字
     *
     * @param title_text
     */
    public void setTitle_text(String title_text) {
        if (!TextUtils.isEmpty(title_text)) {
            title.setText(title_text);
            title.setVisibility(VISIBLE);
            setTitle_textSize(title_textSize);
            setTitle_textColor(title_textColor);
        }

    }

    /**
     * 设置标题的文字颜色
     *
     * @param title_textColor
     */
    public void setTitle_textColor(int title_textColor) {
        title.setTextColor(title_textColor);
    }

    /**
     * 设置标题的文字大小
     *
     * @param title_textSize
     */
    public void setTitle_textSize(int title_textSize) {
        title.setTextSize(title_textSize);
    }


    /**
     * 设置标题的点击监听
     *
     * @param titleOnClickListener
     */
    public void setOnTitleClickListener(TitleOnClickListener titleOnClickListener) {
        this.titleOnClickListener = titleOnClickListener;
    }

    /**
     * 监听标题点击接口
     */
    public interface TitleOnClickListener {
//        /**
//         * 返回按钮的点击事件
//         */
//        void onLeftClick();

        /**
         * 保存按钮的点击事件
         */
        void onRightClick();
        /**
         * 保存按钮的点击事件
         */
        void onRight2Click();

    }
}
