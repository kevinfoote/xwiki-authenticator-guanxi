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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GuanxiShibConfigurator {

    private static final Log log = LogFactory.getLog(GuanxiShibConfigurator.class);
    
    /**
     *   
     *   Load the config from file 
     */
    public GuanxiShibConfig getGuanxiShibConfig( ) {
       
       if (log.isDebugEnabled()) {
           log.debug("Loading GuanxiShibConfig using " + GuanxiShibConstants.PROPERTIES_FILE);
       }

       GuanxiShibConfig config = new GuanxiShibConfig();

       try { 
           //LOAD the config from file
           InputStream propertiesIn = null; 
           propertiesIn = GuanxiShibAuthenticator.class.getResourceAsStream(
                    GuanxiShibConstants.PROPERTIES_FILE);

           Properties configProperties = new Properties();
           configProperties.load(propertiesIn);

           if( ) {
           }
           
       } catch (IOException e) {
            log.warn("Unable to read properties from file, defaulting", e);
       }

       return config;

    } 
}
