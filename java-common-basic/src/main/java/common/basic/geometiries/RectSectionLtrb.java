package common.basic.geometiries;

import common.basic.logs.Logger;

public enum RectSectionLtrb {
    None{ },
    Left { },
    Top { },
    Right{ },
    Bottom { },
    ;

    public static RectSectionLtrb getTouchArea(SizeF size, PointF point) {

        RectF rect = new RectF(new PointF(0, 0), size);
//        RectF rectDeflate = rect.deflate(10);
        if(!rect.contains(point))
            return None;

        if(size.width > size.height) {
            float blockWidth = size.width / 4;
            float blockHeight = size.height / 2;
            int blockX = (int) (point.x / blockWidth);
            int blockY = (int) (point.y / blockHeight);
            int blockIndex = blockY * 4 + blockX;
            switch (blockIndex) {
                case 0: return Left;
                case 1: return Top;
                case 2: return Top;
                case 3: return Right;
                case 4: return Left;
                case 5: return Bottom;
                case 6: return Bottom;
                case 7: return Right;
                default:
                    Logger.e(blockIndex, size, point, blockX, blockY);
                    return None;
            }
        } else {
            float blockWidth = size.width / 2;
            float blockHeight = size.height / 4;
            int blockX = (int) (point.x / blockWidth);
            int blockY = (int) (point.y / blockHeight);
            int blockIndex = blockY * 2 + blockX;
            switch (blockIndex) {
                case 0: return Top;
                case 1: return Top;
                case 2: return Left;
                case 3: return Right;
                case 4: return Left;
                case 5: return Right;
                case 6: return Bottom;
                case 7: return Bottom;


                default:
                    Logger.e(blockIndex, size, point, blockX, blockY);
                    return None;
            }
        }

    }

    public boolean isHorizontal() {
        switch (this) {
            case None: return false;
            case Left: return true;
            case Top: return false;
            case Right: return true;
            case Bottom: return false;
        }

        Logger.e(this);
        return false;
    }

    public boolean isBefore() {
        switch (this) {
            case None: return false;
            case Left: return true;
            case Top: return true;
            case Right: return false;
            case Bottom: return false;
        }

        Logger.e(this);
        return false;
    }

    public boolean isAfter() {
        return !isBefore();
    }

    public int getViewIndex(int viewIndex) {
        if(this == None)
        {
            Logger.e();
            return 0;
        }
        return isBefore() ? viewIndex : viewIndex + 1;
    }
}
