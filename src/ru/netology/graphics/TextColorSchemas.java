package ru.netology.graphics;

public class TextColorSchemas implements ru.netology.graphics.image.TextColorSchema {
    @Override
    public char convert(int color) {
        char c=(0<=color&&color<=5?'▇':5<color&&color<=40?'●':40<color&&color<=75?'◉':
                75<color&&color<=110?'◍':110<color&&color<=145?'◎':145<color&&color<=180?'○':
                180<color&&color<=215?'☉':215<color&&color<=245?'◌':'-');
        return c;
    }
}
