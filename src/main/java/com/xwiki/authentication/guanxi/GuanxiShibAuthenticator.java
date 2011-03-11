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
    private static GuanxiShibConfig gxconfig;

    static {
        gxconfig = GuanxiShibConfigurator.getGuanxiShibConfig( );
    }

    /**
     * Authenticates the user
     * @param context
     * @return authenticated XWiki user
     * @throws com.xpn.xwiki.XWikiException
     */
    @Override
    public XWikiUser checkAuth(XWikiContext context) throws XWikiException {

        log.debug("GuanxiShibAuthenticator.checkAuth");

        String user = context.getRequest().getRemoteUser();

        if ((user == null) || user.equals("")) {
            log.debug("GuanxiShibAuthenticator: (REMOTE_USER) is null/not present");
            log.debug("GuanxiShibAuthenticator: using header search instead");

            user = context.getRequest().getHttpServletRequest().
                  getHeader(gxconfig.getHeaderUserid());
            user = user.toLowerCase();
            this.createUser(user, context);
            user = gxconfig.getDefaultUserSpace() + "." + user;

        } else {
            user = user.toLowerCase();
            this.createUser(user, context);
            user = gxconfig.getDefaultUserSpace() + "." + user;
        }
                                                                                                         context.setUser(user);
        log.debug("GuanxiShibAuthenticator: authentication successful for user " + user);
        return new XWikiUser(user);
    }

    /**
     * Creates the user if he doesnt exist in the XWiki repository. 
     * User is assigned to the default XWikiAllGroup
     *
     * @param String  
     * @param context
     * @throws com.xpn.xwiki.XWikiException
     */
    @Override
    protected void createUser(String user, XWikiContext context) throws XWikiException {
        String xwikiUser = super.findUser(user, context);
        if (xwikiUser == null) {
            log.debug("GuanxiShibAuthenticator: User " + xwikiUser + " does not exist");
            String wikiname = context.getWiki().clearName(user, true, true, context);
            context.getWiki().createEmptyUser(wikiname, "edit", context);
            log.debug("GuanxiShibAuthenticator: User " + xwikiUser + " has been created");
        } else {
            log.debug("GuanxiShibAuthenticator: User " + xwikiUser + " exists continuing");
        }
    }

    /**
     *   
     *
     */
    private Principal createUserFromAttributes(XWikiContext context) throws XWikiException {

        try {
            if (log.isDebugEnabled()) {
              log.debug("attempting to create a user for you");
            }
            BaseClass baseclass = context.getWiki().getUserClass(context);
            XWikiRequest req = context.getRequest();

            String eid = createSafeUserid(req.getRemoteUser());
            if (eid == null) {
                eid = createSafeUserid(context.getRequest().getHttpServletRequest().
                  getHeader(gxconfig.getHeaderUserid()));
                if (log.isDebugEnabled()) {
                    log.debug("getting EID value from header value");
                }
            }
       
            String email = context.getRequest().getHttpServletRequest().getHeader(
               gxconfig.getHeaderMail());

            String displayname = context.getRequest().getHttpServletRequest().
               getHeader(gxconfig.getHeaderFullname());

            String fullwikiname = gxconfig.getDefaultUserSpace() + "." + eid;

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
    public void updateUserInfo() throws XWikiException {
        return;
    }

    /**
     *  Get a safe string to use as a userid within XWiki
     *
     *  @param String string to check
     *  @param String returns a valid string
     */
    private String createSafeUserid(String userid) {
        userid.toLowerCase();
        String u = StringUtils.makeValid(userid,
           gxconfig.getReplacementChar());

        if (log.isDebugEnabled()) {
            log.debug("Creating xwiki-safe userid " + userid +
              " = " + u);
        }

        return u;
    }

    /**
     * See if user exists in the system already.
     * 
     * @param String eid Enterprise id of user
     * @param XWikiContext context The context
     * @return Boolean t,f
     */
    private Boolean userExists(String eid, XWikiContext context) {
        Boolean b = new Boolean(true);
        String fullwikiname = gxconfig.getDefaultUserSpace() + "." + eid;
        Principal p = getUserPrincipal(fullwikiname,context);

        if (p == null) {
            b = false;
        }

        if (log.isDebugEnabled()) {
            log.debug("looking for user " + fullwikiname + "[" + eid + 
              "] = " + b); 
        }
        

        return b;
    }

    /**
     * 
     * 
     */
    private Principal getUserPrincipal(String susername, XWikiContext context) {
        Principal principal = null;

        // First we check in the local database
        try {
            String user = findUser(susername, context);
            if (user!=null) {
                principal = new SimplePrincipal(user);
            }
        } catch (Exception e) {}

        if (context.isVirtual()) {
         if (principal==null) {
         // Then we check in the main database
            String db = context.getDatabase();
            try {
                context.setDatabase(context.getWiki().getDatabase());
                try {
                    String user = findUser(susername, context);
                    if (user!=null)
                        principal = new SimplePrincipal(context.getDatabase() + ":" + user);
                } catch (Exception e) {}
            } finally {
                context.setDatabase(db);
            }
         }
        }
        return principal;
     }
}
