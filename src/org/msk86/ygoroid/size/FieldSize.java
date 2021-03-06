package org.msk86.ygoroid.size;

import org.msk86.ygoroid.newutils.Style;

public class FieldSize extends Size {

    public FieldSize(int width, int height) {
        super(width, height);
    }

    public static Size RECT, SQUARE;
    static {
        RECT = new FieldSize(CardSize.NORMAL.width() + 2 * Style.fieldPadding(), CardSize.NORMAL.height() + 2 * Style.fieldPadding());
        SQUARE = new FieldSize(CardSize.NORMAL.height() + 2 * Style.fieldPadding(), CardSize.NORMAL.height() + 2 * Style.fieldPadding());
    }
}
