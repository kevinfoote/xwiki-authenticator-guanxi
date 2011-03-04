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
//import org.securityfilter.realm.SimplePrincipal;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xpn.xwiki.objects.classes.BaseClass;
import com.xpn.xwiki.user.api.XWikiUser;
import com.xpn.xwiki.user.impl.xwiki.XWikiAuthServiceImpl;

public class GuanxiShibAuthenticator extends XWikiAuthServiceImpl {

    private static final Log log = LogFactory.getLog(GuanxiShibAuthenticator.class);
    
/**
    @Override
    public XWikiUser checkAuth(XWikiContext context) throws XWikiException {
        XWikiUser user = null;
        return user;
    }

    @Override
    public Principal authenticate(String username, String password, XWikiContext context) throws XWikiException {
        Principal principal = null;
        return principal;
    }

    @Override
    public void showLogin(XWikiContext context) throws XWikiException {
    }
**/

    public void sayHello() {
        return;
    }
}
