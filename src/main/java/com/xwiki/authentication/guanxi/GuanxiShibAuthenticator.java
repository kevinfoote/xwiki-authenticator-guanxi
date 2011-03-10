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

import java.security.Principal;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.ecs.xhtml.map;
import org.securityfilter.realm.SimplePrincipal;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xpn.xwiki.objects.classes.BaseClass;
import com.xpn.xwiki.user.api.XWikiUser;
import com.xpn.xwiki.user.impl.xwiki.XWikiAuthServiceImpl;
import com.xpn.xwiki.web.XWikiRequest;

public class GuanxiShibAuthenticator extends XWikiAuthServiceImpl {

    /**
     * Logging tool
     */
    private static final Log log = LogFactory.getLog(GuanxiShibAuthenticator.class);

    /** 
     * Establish our config vars
     */
    private static GuanxiShibConfig gxConfig;

    static {
        gxConfig = GuanxiShibConfigurator.getGuanxiShibConfig( );
    }


    /**
     *   
     *
     */
    @Override
    public Principal authenticate(String username, String password, XWikiContext context) throws XWikiException {
        // establish the only valid login is through SSO now. 
        // does not take into account login/password.
        // could perhaps model something after sakai's xlogin later 
        XWikiUser user = checkAuth(context);
        return user == null ? null : new SimplePrincipal(checkAuth(context).getUser());
    }

    /**
     *   
     *
     */
    @Override
    public XWikiUser checkAuth(XWikiContext context) throws XWikiException {
        XWikiUser user = null;
        // this is where real work is to be done .. 
        // pull header / REMOTE_USER info should call createUser 
        //   if create_user is true and user does not exist
        XWikiRequest req = context.getRequest();
        String userid = createSafeUserid(req.getRemoteUser());

        if (userExists(userid,context)) {
            user = getUser
        }

        try {
            //Principal principal = this.getUserPrincipal(userid,context);
            //createUserFromAttributes(userid,req);
             
        } catch (XWikiException e) // looking for user exists
            Principal principal = this.createUserFromAttributes(context); 
        }

        principal

        return user;
    }

    /**
     *   
     *
     */
    @Override
    public void showLogin(XWikiContext context) throws XWikiException {
    }

    /**
     *   
     *
     */
    //private void createUser() { return; }
    private Principal createUserFromAttributes(XWikiContext context) throws XWikiException {

        try {
            BaseClass baseclass = context.getWiki().getUserClass(context);
            String[] parts = context.getRequest().getHttpServletRequest().getHeader(
                "HTTP_cn").split(";");

            String userIDAttribute = parts[0];
            String fullwikiname = "XWiki." + userIDAttribute;

            XWikiDocument doc = context.getWiki().getDocument(fullwikiname, context);
            //if (!doc.isNew()) {
            //return getUserPrincipal(fullwikiname, context);
            //}

            Map map = new HashMap();
            map.put("active", "1");
            BaseObject newobject = (BaseObject)baseclass.fromMap(map, context);
            newobject.setName(fullwikiname);
            doc.addObject(baseclass.getName(), newobject);
            doc.setParent("");
            doc.setContent("#includeForm(\"XWiki.XWikiUserTemplate\")");

            context.getWiki().ProtectUserPage(context, fullwikiname, "edit", doc);

            context.getWiki().saveDocument(doc, null, context);

            context.getWiki().SetUserDefaultGroup(context, fullwikiname);

            return getUserPrincipal(fullwikiname, context);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Update user info based on incoming header values.
     *
     * @param context XWiki context
     * @throws XWikiException error 
     */
    public void updateUserInfo() {
        return;
    }

    /**
     *  Get a safe string to use as a userid within XWiki
     *
     *  @param String string to check
     *  @param String returns a valid string
     */
    private String createSafeUserid(String userid) {
        String u = StringUtils.makeValid(userid,
           gxConfig.getReplacementChar());
        return u;
    }

    /**
     * 
     * 
     */
    private boolean userExists() {
        
        return;
    }
    
}
