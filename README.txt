xwiki-authenticator-guanxi
===================
Kevin Foote <kpfoote@iup.edu> 


Introduction
----------
An authenticator for Shibboleth protected XWiki sites. This authenticator should work with both the i2 ShibbolethSP and others such as the Guanxi Guard.


Building
----------
This install assumes you have a xwiki profile defined within your maven environment.

git clone http://github.com/kevinfoote/xwiki-authenticator-guanxi.git 
cd xwiki-authenticator-guanxi
mvn -DskipTests -Pxwiki install


More Information
----------
For more information on protecting resources with Shibboleth see http://shibboleth.internet2.edu/


License
----------


Credits
----------
Alistair Young <http://codebrane.com> original xwiki shib work

