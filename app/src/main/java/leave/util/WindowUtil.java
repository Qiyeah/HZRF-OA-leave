package leave.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017-12-29.
 */

public class WindowUtil {

    public static int getWindowWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getWindowHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
