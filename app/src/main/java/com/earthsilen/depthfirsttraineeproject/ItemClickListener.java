package com.earthsilen.depthfirsttraineeproject;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Computer on 7/3/2561.
 */

public interface ItemClickListener {
    void onClick (View view, int position, boolean isLongClick, MotionEvent motionEvent);
}
