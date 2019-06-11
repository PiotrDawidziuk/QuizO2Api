package pl.piotrdawidziuk.quizo2api.service;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

import pl.piotrdawidziuk.quizo2api.activities.MainActivity;

public class ResizeImage {

    public static int getHeight() {
        return getDisplaySize(MainActivity.windowManager).get(1);
    }

    public static int getWidth() {
        return getDisplaySize(MainActivity.windowManager).get(0);
    }

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
