package us.ampre.rets.client.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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

    /**
     * Copies the given input stream to a temporary file and returns the Path to the file.
     * The caller may open a FileInputStream on the returned Path. The temporary file is
     * created via Files.createTempFile and must be deleted by the caller when no longer needed.
     */
    public static Path copyStreamToTempFile(InputStream originalStream, String prefix) throws IOException {
        Path temp = Files.createTempFile(prefix, null);
        try (OutputStream out = Files.newOutputStream(temp)) {
            byte[] buffer = new byte[8192];
            int r;
            while ((r = originalStream.read(buffer)) != -1) {
                out.write(buffer, 0, r);
            }
        }
        return temp;
    }
}
