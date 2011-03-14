/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xwiki.authentication.guanxi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StringUtils {

    private static Log log = LogFactory.getLog(StringUtils.class);

    /** 
     * Define the delimiter to split on 
    */
    private static String DELIMETER = "[,]";

    /**
     *  Turn a long string into a list split by our delimeter
     *
     *  @param String comma separated list
     *  @return List a list of strings
     */
    public static List toListDelimitedByComma(String s) {
        if(s == null) return Collections.EMPTY_LIST;

        List results = new ArrayList();

        String[] terms = s.split(DELIMETER);

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].trim();
            if (term.length() > 0) {
                results.add(term);
            }
        }
        return results;
    }
    
    /**
     * Clean the incomming string to XWiki name standards
     *
     * @param String to clean
     * @param String to use as replacement
     * @return String valid string
     */
    public static String makeValid(String s, String r) {
        s.replace(".",r);
        //s.replace("@",r);
        return s;
    }
}
