package leave;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2017-12-29.
 */

public class XPopupWindow extends PopupWindow {
    private View mView;
    private int height;
    private int width;
    private DisplayMetrics dm;


    public XPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(true);
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(true);
    }

}
