package com.ank.fencis.service;

import com.ank.fencis.util.Constants;
import com.ank.fencis.util.Response;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class CustomFileLoader {

    CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
    private MappedByteBuffer buffer;


    public BufferedReader loadFile(final String filePath) {
        File dataFile = new File(filePath);
        verifyFile(dataFile);
        return readFileIntoBuffer(dataFile);
    }

    protected final void verifyFile(final File file) {
        if (!file.exists()) {
            Response.getResponse(
                    file.getName() + Constants.FILE_NOT_FOUND, 1);
        }
        if (file.length() == 0) {
            Response.getResponse(
                    file.getName() + Constants.EMPTY_FILE, 1);
        }
    }

    protected final BufferedReader readFileIntoBuffer(File dataFile) {
        FileInputStream fInputStream = null;
        MappedByteBuffer mByteBuffer = null;
        FileChannel fChannel = null;
        try {
            fInputStream = new FileInputStream(dataFile);
            fChannel = fInputStream.getChannel();
            mByteBuffer = fChannel.map(FileChannel.MapMode.READ_ONLY, 0, fChannel.size());
            byte[] buffer = new byte[mByteBuffer.limit()];
            mByteBuffer.get(buffer);
            ByteArrayInputStream isr = new ByteArrayInputStream(buffer);
            InputStreamReader ip = new InputStreamReader(isr);
            return new BufferedReader(ip);
        } catch (FileNotFoundException ex) {
            Response.getResponse(ex.getMessage(), 1);
        } catch (IOException e) {
            Response.getResponse(e.getMessage(), 1);
        } finally {
            closeResource(fInputStream, fChannel);
        }
        return null;
    }


    protected final void closeResource(final FileInputStream fis,
                                       final FileChannel fChannel) {
        try {
            fis.close();
            fChannel.close();
        } catch (IOException ex) {
            Response.getResponse(ex.getMessage(), 1);
        }

    }
}
