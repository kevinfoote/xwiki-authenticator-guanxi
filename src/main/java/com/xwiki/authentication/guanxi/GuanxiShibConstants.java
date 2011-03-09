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

public class GuanxiShibConstants {

    /**
     *   Location of configuration file on classpath
     */
    public final static String PROPERTIES_FILE = 
        "/GuanxiShibAuthenticator.properties";

    /**
     *   Should this authenticator create users 
     */
    public final static String CREATE_USERS = "create.users";

    /**
     *   Should this authenticator update userinfo upon login
     */
    public final static String UPDATE_INFO = "update.info";

    /**
     *   Default group(s) to place new users into within XWiki 
     */
    public final static String DEFAULT_XWIKI_GROUPS = "default.groups";

    /**
     *   Default XWiki space users are placed [[XWIKI-363]] 
     */
    public final static String DEFAULT_XWIKI_USER_SPACE = "default.userspace";

    /**
     *  Shib header attribute to use for user if no REMOTE_USER 
     */
    public final static String HEADER_USERID = "header.userid";

    /**
     *  Shib header attribute to use for mail 
     */
    public final static String HEADER_MAIL = "header.mail";

    /**
     *  Shib header to use for full name 
     */
    public final static String HEADER_FULLNAME = "header.fullname";

    /**
     *  Default replacemnt char when encountering userid mappings 
     */
    public final static String REPLACEMENT_CHAR = "replacement.char";

}
