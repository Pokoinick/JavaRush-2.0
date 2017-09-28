package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int r, int c, Color desiredColor) {
        if (c < 0 || image.length <= c || image[0].length <= r || r < 0)
            return false;
        if (image[c][r].equals(desiredColor))
            return false;

        image[c][r] = desiredColor;
        return true;
    }
}
