package saml2webssotest.common.standardNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the names of the cipher suites for some of the SSL protocols
 * (retrieved from https://www.openssl.org/docs/apps/ciphers.html)
 *
 * @author RiaasM
 *
 */
public class CipherSuiteNames {
	
	public static final ArrayList<String> sslv3 = (ArrayList<String>) Arrays.asList(
			"SSL_RSA_WITH_NULL_MD5", 
			"SSL_RSA_WITH_NULL_SHA",
			"SSL_RSA_EXPORT_WITH_RC4_40_MD5",
			"SSL_RSA_WITH_RC4_128_MD5",
			"SSL_RSA_WITH_RC4_128_SHA",
			"SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5",
			"SSL_RSA_WITH_IDEA_CBC_SHA",
			"SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_RSA_WITH_DES_CBC_SHA",
			"SSL_RSA_WITH_3DES_EDE_CBC_SHA",
			"SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_DH_DSS_WITH_DES_CBC_SHA",
			"SSL_DH_DSS_WITH_3DES_EDE_CBC_SHA",
			"SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_DH_RSA_WITH_DES_CBC_SHA",
			"SSL_DH_RSA_WITH_3DES_EDE_CBC_SHA",
			"SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_DHE_DSS_WITH_DES_CBC_SHA",
			"SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
			"SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_DHE_RSA_WITH_DES_CBC_SHA",
			"SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
			"SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",
			"SSL_DH_anon_WITH_RC4_128_MD5",
			"SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA",
			"SSL_DH_anon_WITH_DES_CBC_SHA",
			"SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",
			"SSL_FORTEZZA_KEA_WITH_NULL_SHA",
			"SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA",
			"SSL_FORTEZZA_KEA_WITH_RC4_128_SHA"); 
}
