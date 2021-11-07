package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverters implements ru.netology.graphics.image.TextGraphicsConverter {
    public int MaxWidth;
    private int MaxHeight;
    private double MaxRatio;
    private TextColorSchema Schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int newWidth = 0;
        int newHeight = 0;
        if (MaxRatio != 0) {
            if (img.getWidth() / img.getHeight() < MaxRatio) {
                if (MaxWidth != 0 && MaxWidth < img.getWidth()) {
                    double proportion = img.getWidth() / (double) MaxWidth;
                    newWidth = (int) (Math.ceil(img.getWidth() / proportion));
                    newHeight = (int) (Math.ceil(img.getHeight() / proportion));
                }
                if (MaxHeight != 0 && MaxHeight < img.getHeight()) {
                    double proportion = img.getHeight() / (double) MaxHeight;
                    newWidth = (int) (Math.ceil(img.getWidth() / proportion));
                    newHeight = (int) (Math.ceil(img.getHeight() / proportion));
                }
            } else {
                throw new BadImageSizeException(MaxRatio, img.getWidth() / img.getHeight());
            }
        } else {
            if (MaxWidth != 0 && MaxWidth < img.getWidth()) {
                double proportion = img.getWidth() / (double) MaxWidth;
                newWidth = (int) (img.getWidth() / proportion);
                newHeight = (int) ((img.getHeight() / proportion));
            }
            if (MaxHeight != 0 && MaxHeight < img.getHeight()) {
                double proportion = img.getHeight() / (double) MaxHeight;
                newWidth = (int) (Math.ceil(img.getWidth() / proportion));
                newHeight = (int) (Math.ceil(img.getHeight() / proportion));
            }
        }

        if (newWidth == 0 && newHeight == 0) {
            newWidth = img.getWidth();
            newHeight = img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();

        Character[][] str = new Character[newWidth][newHeight];

        for (int w = 0; w < newWidth; w++) {
            for (int h = 0; h < newHeight; h++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c;
                if (Schema==null){
                    TextColorSchemas schema = new TextColorSchemas();
                    c=schema.convert(color);
                }else {
                    c=Schema.convert(color);
                }
                str[w][h] = c;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                stringBuilder.append(str[w][h]);
                stringBuilder.append(str[w][h]);
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        if (0 < width && width < 5398) {
            this.MaxWidth = width;
        } else {
            System.out.println("нельзя присвоить такое значение");
        }
    }

    @Override
    public void setMaxHeight(int height) {
        if (0 < height && height < 3602) {
            this.MaxHeight = height;
        } else {
            System.out.println("Нельзя присвоить такое значение");
        }
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.MaxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.Schema = schema;
    }
}
