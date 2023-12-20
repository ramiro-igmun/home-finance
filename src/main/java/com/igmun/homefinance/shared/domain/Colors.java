package com.igmun.homefinance.shared.domain;

import java.awt.*;

public final class Colors {
  private Colors() {}

  public static Color brightenColor(Color color, double factor) {
    int red = (int) (color.getRed() * (1 + factor));
    int green = (int) (color.getGreen() * (1 + factor));
    int blue = (int) (color.getBlue() * (1 + factor));

    red = Math.min(255, red);
    green = Math.min(255, green);
    blue = Math.min(255, blue);

    return new Color(red, green, blue);
  }

  public static String colorToHexString(Color color) {
    return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
  }
}
