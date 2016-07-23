package com.henry.displaydata.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
public class Utility
{
    public static String[] split(String splitStr, String delimiter, int limit)
    {
        // some input validation / short-circuiting
        if(delimiter == null || delimiter.length() == 0)
        {
            return new String[] {splitStr};
        }
        else if (splitStr == null)
        {
            return new String[0];
        }

        // enabling switches based on the 'limit' parameter
        boolean arrayCanHaveAnyLength = false;
        int maximumSplits = Integer.MAX_VALUE;
        boolean dropTailingDelimiters = true;
        if (limit < 0)
        {
            arrayCanHaveAnyLength = true;
            maximumSplits = Integer.MAX_VALUE;
            dropTailingDelimiters = false;
        }
        else if (limit > 0)
        {
            arrayCanHaveAnyLength = false;
            maximumSplits = limit - 1;
            dropTailingDelimiters = false;
        }

        StringBuffer token = new StringBuffer();
        Vector tokens = new Vector();
        char[] chars = splitStr.toCharArray();
        boolean lastWasDelimiter = false;
        int splitCounter = 0;
        for (int i = 0; i < chars.length; i++)
        {
            // check for a delimiter
            if (i + delimiter.length() <= chars.length && splitCounter < maximumSplits)
            {
                String candidate = new java.lang.String(chars, i, delimiter.length());
                if (candidate.equals(delimiter))
                {
                    tokens.addElement(token.toString());
                    token.setLength(0);

                    lastWasDelimiter = true;
                    splitCounter++;
                    i = i + delimiter.length() - 1;

                    continue; // continue the for-loop
                }
            }

            // this character does not start a delimiter -> append to the token
            token.append(chars[i]);
            lastWasDelimiter = false;
        }
        // don't forget the "tail"...
        if (token.length() > 0 || (lastWasDelimiter && !dropTailingDelimiters))
        {
            tokens.addElement(token.toString());
        }
        // convert the vector into an array
        String[] splitArray = new String[tokens.size()];
        for (int i = 0; i < splitArray.length; i++)
        {
            splitArray[i] = (String) tokens.elementAt(i);
        }
        return splitArray;
    }

    // read the photo file into a byte array...
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        is.close();
        return bytes;
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean containsDigit(final String s)
    {
        boolean containsDigit = false;
        if(s != null)
        {
            for(char c : s.toCharArray())
            {
                if(Character.isDigit(c))
                {
                    containsDigit = true;
                    break;
                }
            }
        }
        return containsDigit;
    }

    public static boolean validateEmail(String email)
    {
        String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(email);
        if(m.find())
        {
            return true;
        }else{
            return false;
        }
    }

}
