package common.basic.utils;

import java.io.File;

public enum MimeType {
    Unknown("application/octet-stream") {},

    Epub("application/epub+zip") {},
    Ncx("application/x-dtbncx+xml") {},
    Opf("application/oebps-package+xml"){},


    Txt("text/plain") {},
    Json("application/json") {},
    Xhtml("application/xhtml+xml"){},



    Html("text/html") {
        @Override
        public boolean isMatchExt(String ext) {
            return super.isMatchExt(ext) || StringUtil.equalsIgnoreCase("htm", ext);
        }
    },



    Png("image/png") {
        @Override
        public boolean isImage() {
            return true;
        }
    },
    Jpg("image/jpg") {
        @Override
        public boolean isImage() {
            return true;
        }
    },

    Jpeg("image/jpeg") {
        @Override
        public boolean isImage() {
            return true;
        }
    },

    ;


    private final String value;

    MimeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MimeType detect(File file) {
        return detect(file.getName());
    }

    public static MimeType detect(String fileName) {
        final String extensionNameWithoutDot = FileUtil.getExtensionNameWithoutDot(fileName);
        final String extensionNameWithoutDotLower = extensionNameWithoutDot.toLowerCase();

        for (MimeType mimeType : MimeType.values())
        {
            if (mimeType.isMatchExt(extensionNameWithoutDotLower))
            {
                return mimeType;
            }
        }

        return Unknown;
    }

    public boolean isMatchExt(String ext) {
        return StringUtil.equalsIgnoreCase(this.toString(), ext);
    }

    public boolean isImage() {
        return false;
    }

    public static MimeType parseValue(String value) {
        for (MimeType mimeType : MimeType.values())
        {
            if (mimeType.value.equals(value))
            {
                return mimeType;
            }
        }

        return Unknown;
    }
}
