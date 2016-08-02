package common.basic.htmlFetcher;

import common.basic.geometiries.Size;

public class ImageMetadata {


    public boolean isPhoto = false;
    public Size dimension;

    private static boolean hasExifType(String contentType) {

        if ("image/jpeg".equals(contentType))
            return true;
        if ("image/jpg".equals(contentType))
            return true;

        return false;
    }
}
