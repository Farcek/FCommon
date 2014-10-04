/*
 * Copyright (C) 2014 Farcek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mn.le.farcek.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.activation.MimetypesFileTypeMap;

public class FFileUtils {

    public static String FileNameGenerator(File dir, String ext) {

        if (dir.isFile()) {
            dir = dir.getParentFile();
        }

        String n = UUID.randomUUID().toString();
        String nf = dir.getAbsoluteFile() + File.separator + n;
        if (ext instanceof String && ext.length() > 0) {
            nf += ("." + ext);
        }

        File fn = new File(nf);
        if (fn.exists() && !fn.isFile()) {
            return FileNameGenerator(dir, ext);
        }

        return n;
    }

    public static void write(String content, File f, Charset encoding) throws FileNotFoundException, IOException {
        try (FileOutputStream out = new FileOutputStream(f, false)) {
            out.write(content.getBytes(encoding));
        }

    }

    public static void write(InputStream is, File f) throws FileNotFoundException, IOException {
        try (FileOutputStream out = new FileOutputStream(f)) {
            final byte[] bytes = new byte[1024];
            int read;
            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
        }
    }

    public static String read(File f, Charset encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), encoding))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    public static String read(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String getExtension(String fullName) {

        if (fullName == null) {
            return null;
        }
        int i = fullName.lastIndexOf('.');
        if (i == -1) {
            return null;
        }

        int p = Math.max(fullName.lastIndexOf('/'), fullName.lastIndexOf('\\'));

        if (i > p) {
            return fullName.substring(i + 1);
        }
        return null;
    }

    public static boolean isImageFromName(String path) {
        String ext = getExtension(path);
        return ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif") || ext.equals("png");
    }

    public static boolean isVideoFromName(String path) {
        String ext = getExtension(path);
        return ext.equals("mp4");
    }

    public static boolean isAudioFromName(String path) {
        String ext = getExtension(path);
        return ext.equals("mp3");
    }

    public static boolean isFlashFromName(String path) {
        String ext = getExtension(path);
        return ext.equals("swf");
    }

    public static boolean isDocumentFromName(String path) {
        String ext = getExtension(path);
        return ext.equals("docx") || ext.equals("doc") || ext.equals("xlsx")
                || ext.equals("xls") || ext.equals("csv") || ext.equals("pdf")
                || ext.equals("txt") || ext.equals("csv") || ext.equals("ppt") || ext.equals("pptx");
    }
}
