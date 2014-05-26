package common.android.enums;

import android.view.DragEvent;

public enum DragEventAction {
    DragStarted(DragEvent.ACTION_DRAG_STARTED),
    DragEntered(DragEvent.ACTION_DRAG_ENTERED),
    DragExited(DragEvent.ACTION_DRAG_EXITED),
    DragLocation(DragEvent.ACTION_DRAG_LOCATION),
    Drop(DragEvent.ACTION_DROP),
    DragEnded(DragEvent.ACTION_DRAG_ENDED),
    ;

    public static DragEventAction valueOf(int action) {
        for (DragEventAction dragEventAction : values()) {
            if(dragEventAction.action == action)
                return dragEventAction;
        }

        return null;
    }

    private final int action;

    DragEventAction(int action) {
        this.action = action;
    }
}
