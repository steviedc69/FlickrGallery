package com.example.steven.flickrgallery;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Steven on 31/12/14.
 */
public class StringUtitities {

    public static String makeUrlEncoded(String input) throws UnsupportedEncodingException {
        return URLEncoder.encode(input, "UTF-8");
    }
}