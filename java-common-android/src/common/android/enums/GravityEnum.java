package common.android.enums;

import android.view.Gravity;

public enum GravityEnum {
    NoGravity(Gravity.NO_GRAVITY),
    AxisSpecified(Gravity.AXIS_SPECIFIED),
    AxisPullBefore(Gravity.AXIS_PULL_BEFORE),
    AxisPullAfter(Gravity.AXIS_PULL_AFTER),
    AxisClip(Gravity.AXIS_CLIP),
    AxisXShift(Gravity.AXIS_X_SHIFT),
    AxisYShift(Gravity.AXIS_Y_SHIFT),
    Top(Gravity.TOP),
    Bottom(Gravity.BOTTOM),
    Left(Gravity.LEFT),
    Right(Gravity.RIGHT),
    CenterVertical(Gravity.CENTER_VERTICAL),
    FillVertical(Gravity.FILL_VERTICAL),
    CenterHorizontal(Gravity.CENTER_HORIZONTAL),
    FillHorizontal(Gravity.FILL_HORIZONTAL),
    Center(Gravity.CENTER),
    Fill(Gravity.FILL),
    ClipVertical(Gravity.CLIP_VERTICAL),
    ClipHorizontal(Gravity.CLIP_HORIZONTAL),
    RelativeLayoutDirection(Gravity.RELATIVE_LAYOUT_DIRECTION),
    HorizontalGravityMask(Gravity.HORIZONTAL_GRAVITY_MASK),
    VerticalGravityMask(Gravity.VERTICAL_GRAVITY_MASK),
    DisplayClipVertical(Gravity.DISPLAY_CLIP_VERTICAL),
    DisplayClipHorizontal(Gravity.DISPLAY_CLIP_HORIZONTAL),
    Start(Gravity.START),
    End(Gravity.END),
    RelativeHorizontalGravityMask(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK),
    ;

    public final int gravity;
    GravityEnum(int gravity) {
        this.gravity = gravity;
    }

}
