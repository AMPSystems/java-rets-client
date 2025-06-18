package us.ampre.rets.client.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtil {
    public static ByteArrayInputStream copyStream(InputStream originalStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[8192];
        int bytesRead;
        while ((bytesRead = originalStream.read(temp)) != -1) {
            buffer.write(temp, 0, bytesRead);
        }
        return new ByteArrayInputStream(buffer.toByteArray());
    }
}