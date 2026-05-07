package bg.duosoft.nacid.backoffice.abdocs.util;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ContentDisposition {
    private final String type;
    private final String name;
    private final String filename;
    private final Charset charset;
    private final Long size;
    private final ZonedDateTime creationDate;
    private final ZonedDateTime modificationDate;
    private final ZonedDateTime readDate;

    private ContentDisposition(String type, String name, String filename, Charset charset, Long size, ZonedDateTime creationDate, ZonedDateTime modificationDate, ZonedDateTime readDate) {
        this.type = type;
        this.name = name;
        this.filename = filename;
        this.charset = charset;
        this.size = size;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.readDate = readDate;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getFilename() {
        return this.filename;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public Long getSize() {
        return this.size;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public ZonedDateTime getModificationDate() {
        return this.modificationDate;
    }

    public ZonedDateTime getReadDate() {
        return this.readDate;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof ContentDisposition)) {
            return false;
        } else {
            ContentDisposition otherCd = (ContentDisposition) other;
            return ObjectUtils.nullSafeEquals(this.type, otherCd.type) && ObjectUtils.nullSafeEquals(this.name, otherCd.name) && ObjectUtils.nullSafeEquals(this.filename, otherCd.filename) && ObjectUtils.nullSafeEquals(this.charset, otherCd.charset) && ObjectUtils.nullSafeEquals(this.size, otherCd.size) && ObjectUtils.nullSafeEquals(this.creationDate, otherCd.creationDate) && ObjectUtils.nullSafeEquals(this.modificationDate, otherCd.modificationDate) && ObjectUtils.nullSafeEquals(this.readDate, otherCd.readDate);
        }
    }

    public int hashCode() {
        int result = ObjectUtils.nullSafeHashCode(this.type);
        result = 31 * result + ObjectUtils.nullSafeHashCode(this.name);
        result = 31 * result + ObjectUtils.nullSafeHashCode(this.filename);
        result = 31 * result + ObjectUtils.nullSafeHashCode(this.charset);
        result = 31 * result + ObjectUtils.nullSafeHashCode(this.size);
        result = 31 * result + (this.creationDate != null ? this.creationDate.hashCode() : 0);
        result = 31 * result + (this.modificationDate != null ? this.modificationDate.hashCode() : 0);
        result = 31 * result + (this.readDate != null ? this.readDate.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(this.type);
        }

        if (this.name != null) {
            sb.append("; name=\"");
            sb.append(this.name).append('"');
        }

        if (this.filename != null) {
            if (this.charset != null && !StandardCharsets.US_ASCII.equals(this.charset)) {
                sb.append("; filename*=");
                sb.append(encodeHeaderFieldParam(this.filename, this.charset));
            } else {
                sb.append("; filename=\"");
                sb.append(this.filename).append('"');
            }
        }

        if (this.size != null) {
            sb.append("; size=");
            sb.append(this.size);
        }

        if (this.creationDate != null) {
            sb.append("; creation-date=\"");
            sb.append(DateTimeFormatter.RFC_1123_DATE_TIME.format(this.creationDate));
            sb.append('"');
        }

        if (this.modificationDate != null) {
            sb.append("; modification-date=\"");
            sb.append(DateTimeFormatter.RFC_1123_DATE_TIME.format(this.modificationDate));
            sb.append('"');
        }

        if (this.readDate != null) {
            sb.append("; read-date=\"");
            sb.append(DateTimeFormatter.RFC_1123_DATE_TIME.format(this.readDate));
            sb.append('"');
        }

        return sb.toString();
    }

    public static Builder builder(String type) {
        return new BuilderImpl(type);
    }

    public static ContentDisposition empty() {
        return new ContentDisposition("", (String) null, (String) null, (Charset) null, (Long) null, (ZonedDateTime) null, (ZonedDateTime) null, (ZonedDateTime) null);
    }

    public static ContentDisposition parse(String contentDisposition) {
        List<String> parts = tokenize(contentDisposition);
        String type = (String) parts.get(0);
        String name = null;
        String filename = null;
        Charset charset = null;
        Long size = null;
        ZonedDateTime creationDate = null;
        ZonedDateTime modificationDate = null;
        ZonedDateTime readDate = null;

        for (int i = 1; i < parts.size(); ++i) {
            String part = (String) parts.get(i);
            int eqIndex = part.indexOf(61);
            if (eqIndex == -1) {
                throw new IllegalArgumentException("Invalid content disposition format");
            }

            String attribute = part.substring(0, eqIndex);
            String value = part.startsWith("\"", eqIndex + 1) && part.endsWith("\"") ? part.substring(eqIndex + 2, part.length() - 1) : part.substring(eqIndex + 1, part.length());
            if (attribute.equals("name")) {
                name = value;
            } else if (!attribute.equals("filename*")) {
                if (attribute.equals("filename") && filename == null) {
                    filename = value;
                } else if (attribute.equals("size")) {
                    size = Long.parseLong(value);
                } else if (attribute.equals("creation-date")) {
                    try {
                        creationDate = ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME);
                    } catch (DateTimeParseException var18) {
                    }
                } else if (attribute.equals("modification-date")) {
                    try {
                        modificationDate = ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME);
                    } catch (DateTimeParseException var17) {
                    }
                } else if (attribute.equals("read-date")) {
                    try {
                        readDate = ZonedDateTime.parse(value, DateTimeFormatter.RFC_1123_DATE_TIME);
                    } catch (DateTimeParseException var16) {
                    }
                }
            } else {
                filename = decodeHeaderFieldParam(value);
                charset = Charset.forName(value.substring(0, value.indexOf(39)));
                Assert.isTrue(StandardCharsets.UTF_8.equals(charset) || StandardCharsets.ISO_8859_1.equals(charset), "Charset should be UTF-8 or ISO-8859-1");
            }
        }

        return new ContentDisposition(type, name, filename, charset, size, creationDate, modificationDate, readDate);
    }

    private static List<String> tokenize(String headerValue) {
        int index = headerValue.indexOf(59);
        String type = (index >= 0 ? headerValue.substring(0, index) : headerValue).trim();
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Content-Disposition header must not be empty");
        } else {
            List<String> parts = new ArrayList();
            parts.add(type);
            int nextIndex;
            if (index >= 0) {
                do {
                    nextIndex = index + 1;

                    for (boolean quoted = false; nextIndex < headerValue.length(); ++nextIndex) {
                        char ch = headerValue.charAt(nextIndex);
                        if (ch == ';') {
                            if (!quoted) {
                                break;
                            }
                        } else if (ch == '"') {
                            quoted = !quoted;
                        }
                    }

                    String part = headerValue.substring(index + 1, nextIndex).trim();
                    if (!part.isEmpty()) {
                        parts.add(part);
                    }

                    index = nextIndex;
                } while (nextIndex < headerValue.length());
            }

            return parts;
        }
    }

    private static String decodeHeaderFieldParam(String input) {
        Assert.notNull(input, "Input String should not be null");
        int firstQuoteIndex = input.indexOf(39);
        int secondQuoteIndex = input.indexOf(39, firstQuoteIndex + 1);
        if (firstQuoteIndex != -1 && secondQuoteIndex != -1) {
            Charset charset = Charset.forName(input.substring(0, firstQuoteIndex));
            Assert.isTrue(StandardCharsets.UTF_8.equals(charset) || StandardCharsets.ISO_8859_1.equals(charset), "Charset should be UTF-8 or ISO-8859-1");
            byte[] value = input.substring(secondQuoteIndex + 1, input.length()).getBytes(charset);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int index = 0;

            while (index < value.length) {
                byte b = value[index];
                if (isRFC5987AttrChar(b)) {
                    bos.write((char) b);
                    ++index;
                } else {
                    if (b != 37) {
                        throw new IllegalArgumentException("Invalid header field parameter format (as defined in RFC 5987)");
                    }

                    char[] array = new char[]{(char) value[index + 1], (char) value[index + 2]};
                    bos.write(Integer.parseInt(String.valueOf(array), 16));
                    index += 3;
                }
            }

            return new String(bos.toByteArray(), charset);
        } else {
            return input;
        }
    }

    private static boolean isRFC5987AttrChar(byte c) {
        return c >= 48 && c <= 57 || c >= 97 && c <= 122 || c >= 65 && c <= 90 || c == 33 || c == 35 || c == 36 || c == 38 || c == 43 || c == 45 || c == 46 || c == 94 || c == 95 || c == 96 || c == 124 || c == 126;
    }

    private static String encodeHeaderFieldParam(String input, Charset charset) {
        Assert.notNull(input, "Input String should not be null");
        Assert.notNull(charset, "Charset should not be null");
        if (StandardCharsets.US_ASCII.equals(charset)) {
            return input;
        } else {
            Assert.isTrue(StandardCharsets.UTF_8.equals(charset) || StandardCharsets.ISO_8859_1.equals(charset), "Charset should be UTF-8 or ISO-8859-1");
            byte[] source = input.getBytes(charset);
            int len = source.length;
            StringBuilder sb = new StringBuilder(len << 1);
            sb.append(charset.name());
            sb.append("''");
            byte[] var5 = source;
            int var6 = source.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                byte b = var5[var7];
                if (isRFC5987AttrChar(b)) {
                    sb.append((char) b);
                } else {
                    sb.append('%');
                    char hex1 = Character.toUpperCase(Character.forDigit(b >> 4 & 15, 16));
                    char hex2 = Character.toUpperCase(Character.forDigit(b & 15, 16));
                    sb.append(hex1);
                    sb.append(hex2);
                }
            }

            return sb.toString();
        }
    }

    private static class BuilderImpl implements Builder {
        private String type;
        private String name;
        private String filename;
        private Charset charset;
        private Long size;
        private ZonedDateTime creationDate;
        private ZonedDateTime modificationDate;
        private ZonedDateTime readDate;

        public BuilderImpl(String type) {
            Assert.hasText(type, "'type' must not be not empty");
            this.type = type;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder filename(String filename, Charset charset) {
            this.filename = filename;
            this.charset = charset;
            return this;
        }

        public Builder size(Long size) {
            this.size = size;
            return this;
        }

        public Builder creationDate(ZonedDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder modificationDate(ZonedDateTime modificationDate) {
            this.modificationDate = modificationDate;
            return this;
        }

        public Builder readDate(ZonedDateTime readDate) {
            this.readDate = readDate;
            return this;
        }

        public ContentDisposition build() {
            return new ContentDisposition(this.type, this.name, this.filename, this.charset, this.size, this.creationDate, this.modificationDate, this.readDate);
        }
    }

    public interface Builder {
        Builder name(String var1);

        Builder filename(String var1);

        Builder filename(String var1, Charset var2);

        Builder size(Long var1);

        Builder creationDate(ZonedDateTime var1);

        Builder modificationDate(ZonedDateTime var1);

        Builder readDate(ZonedDateTime var1);

        ContentDisposition build();
    }

}