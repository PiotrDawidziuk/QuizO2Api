package pl.piotrdawidziuk.quizo2api.service;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

public class ResizeImage {



    public static ArrayList<Integer> getDisplaySize(WindowManager windowManager) {
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;
        ArrayList<Integer> values = new ArrayList<>();
        values.add(width);
        values.add(height);
        return values;
    }
}
