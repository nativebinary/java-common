package common.basic.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GraphicsUtil {
    public static void drawStringLeftTop(Graphics graphics, Point point, String value)
    {
        FontMetrics fontMetrics = graphics.getFontMetrics();

        int posX = point.x;
        int posY = point.y + fontMetrics.getAscent() + fontMetrics.getLeading();

        graphics.drawString(value, posX, posY);
    }

    public static void drawStringLeftMiddle(Graphics graphics, Rectangle rectangle, String value)
    {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectValue = fontMetrics.getStringBounds(value, graphics);

        if (rectangle.height == 0)
            rectangle.height = fontMetrics.getHeight();

        int posX = rectangle.x;
        int posY = rectangle.y + fontMetrics.getAscent() + fontMetrics.getLeading();

        posY += (rectangle.getHeight() - rectValue.getHeight()) / 2;

        graphics.drawString(value, posX, posY);
    }


    public static void drawStringRightMiddle(Graphics graphics, Rectangle rectangle, String value) {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectValue = fontMetrics.getStringBounds(value, graphics);


        if (rectangle.height == 0)
            rectangle.height = fontMetrics.getHeight();

        int posX = (int)(rectangle.getX() + (rectangle.getWidth() - rectValue.getWidth()));
        int posY = rectangle.y + fontMetrics.getAscent() + fontMetrics.getLeading();

        posY += (rectangle.getHeight() - rectValue.getHeight()) / 2;

        graphics.drawString(value, posX, posY);
    }

    public static Dimension getStringDimension(Graphics graphics, String value)
    {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        return fontMetrics.getStringBounds(value, graphics).getBounds().getSize();

    }
    public static void drawStringCenterMiddle(Graphics graphics, Rectangle rectangle, String value)
    {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectValue = fontMetrics.getStringBounds(value, graphics);


        if (rectangle.height == 0)
            rectangle.height = fontMetrics.getHeight();

        int posX = (int)(rectangle.getX() + (rectangle.getWidth() - rectValue.getWidth()) / 2);
        int posY = rectangle.y + fontMetrics.getAscent() + fontMetrics.getLeading();

        posY += (rectangle.getHeight() - rectValue.getHeight()) / 2;

        graphics.drawString(value, posX, posY);
    }

    public static void drawRectangle(Graphics graphics, Color color, Rectangle rectangle) {
        drawRectangle(graphics, color, rectangle.x, rectangle.y, rectangle.width, rectangle.height);


    }


    public static void drawRectangle(Graphics graphics, Color color, int x, int y, int width, int height) {
        graphics.setColor(color);
        graphics.drawRect(x, y, width - 1, height - 1);
    }


	// http://www.java2s.com/Tutorial/Java/0120__Development/DecodeanHTMLcolorstringlikeF567BAintoaColor.htm
	public static Color parseHtmlColor(String colorString)
	{
        if(StringUtil.isNullOrEmpty(colorString) || "transparent".equals(colorString))
            return null;

		Color color;

		if (colorString.startsWith("#"))
		{
			colorString = colorString.substring(1);
		}
		if (colorString.endsWith(";"))
		{
			colorString = colorString.substring(0, colorString.length() - 1);
		}

		int red, green, blue;
		switch (colorString.length())
		{
			case 6:
				red = Integer.parseInt(colorString.substring(0, 2), 16);
				green = Integer.parseInt(colorString.substring(2, 4), 16);
				blue = Integer.parseInt(colorString.substring(4, 6), 16);
				color = new Color(red, green, blue);
				break;
			case 3:
				red = Integer.parseInt(colorString.substring(0, 1), 16);
				green = Integer.parseInt(colorString.substring(1, 2), 16);
				blue = Integer.parseInt(colorString.substring(2, 3), 16);
				color = new Color(red, green, blue);
				break;
			case 1:
				red = green = blue = Integer.parseInt(colorString.substring(0, 1), 16);
				color = new Color(red, green, blue);
				break;

			default:
				throw new IllegalArgumentException("Invalid color: " + colorString);
		}
		return color;
	}

}
