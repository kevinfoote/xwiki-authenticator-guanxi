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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuanxiShibConfig {

    /**
     * properties file 
     */
    private String propertiesFile;

    /**
     * create accounts for new users or not
     */
    private boolean createUsers;

    /**
     * update accounts info on each login 
     */
    private boolean updateInfo;

    /**
     * roles newly created users get assigned
     */
    private List defaultGroups;

    /**
     * default space for users 
     */
    private String defaultUserSpace;

    /**
     * header to use instead of REMOTE_USER 
     */
    private String headerUserid;

    /**
     * header to use for email
     */
    private String headerMail;

    /**
     * header to use for fullname 
     */
    private String headerFullname;

    /**
     * header to use for fullname 
     */
    private String headerFullname;

    /**
     * replacement char to use when defining eppn users 
     */
    private String replacementChar;

    /** METHODS TO FOLLOW **/
   public boolean isCreateUsers() {
        return createUsers;
    }

    public void setCreateUsers(boolean createUsers) {
        this.createUsers = createUsers;


    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }
    public String getPropertiesFile() {
        return propertiesFile;
    }
    
    public void setCreateUsers(boolean createUsers) {
        this.createUsers = createUsers;
    }
    public boolean isCreateUsers() {
        return createUsers;
    }
   
    public void setUpdateInfo(boolean updateInfo) {
        this.updateInfo = updateInfo;
    }
    public boolean isUpdateInfo() {
        return updateInfo;
    }
 
    public void setDefaultGroups(List defaultGroups) {
        this.defaultGroups = defaultGroups;
    }
    public List getDefaultGroups( ) {
        return defaultGroups;
    }
  
    public void setDefaultUserSpace(String defaultUserSpace) {
        this.defaultUserSpace = defaultUserSpace;
    }
    public String getDefaultUserSpace( ) {
        return defaultUserSpace;
    }
 
    public void setHeaderUserid(String headerUserid) {
        this.headerUserid = headerUserid;
    }
    public String getHeaderUserid( ) {
        return headerUserid;
    }

    public void setHeaderMail(String headerMail) {
        this.headerMail = headerMail;
    }
    public String getHeaderMail( ) {
        return headerMail;
    }

    public void setHeaderFullname(String headerFullname) {
        this.headerFullname = headerFullname;
    }
    public String getHeaderFullname( ) {
        return headerFullname;
    }
    
    public void setReplacementChar(String replacementChar) {
        this.replacementChar = replacementChar;
    }
    public String getReplacementChar( ) {
        return replacementChar;
    }

}
