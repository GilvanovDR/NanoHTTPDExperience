/*
 * Copyright (c) GilvanovDR 2019.
 */

package ru.GilvanovDR;


import fi.iki.elonen.NanoHTTPD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;




public class NanoServer extends NanoHTTPD {
    NanoServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            String uri = "/".equals(session.getUri())?"/index.html":session.getUri();
            return newChunkedResponse(Response.Status.OK,
                    NanoHTTPD.getMimeTypeForFile(uri),
                    new FileInputStream(new File("resources" + uri)));
        } catch (FileNotFoundException e) {
            return newFixedLengthResponse(Response.Status.NO_CONTENT, NanoHTTPD.MIME_HTML, "no content");
        }
    }
}