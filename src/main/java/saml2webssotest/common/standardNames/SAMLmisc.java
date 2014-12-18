package saml2webssotest.common.standardNames;
/**
 * Class contains common names and common, standard values used in SAML that are not specific to any single SAML namespace
 * 
 * @author RiaasM
 *
 */
public class SAMLmisc {
	
	// names
	public static final String ID = "ID";
	public static final String INRESPONSETO = "InResponseTo";
	public static final String VERSION = "Version";
	public static final String SPNAMEQUALIFIER = "SPNameQualifier";
	public static final String FORMAT = "Format";
	public static final String NOTBEFORE = "NotBefore";
	public static final String NOTONORAFTER = "NotOnOrAfter";
	public static final String ADDRESS = "Address";
	public static final String NAME = "Name";
	
	// values
	public static final String BINDING_SOAP = "urn:oasis:names:tc:SAML:2.0:bindings:SOAP";
	public static final String BINDING_PAOS = "urn:oasis:names:tc:SAML:2.0:bindings:PAOS";
	public static final String BINDING_HTTP_REDIRECT = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect";
	public static final String BINDING_HTTP_POST = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
	public static final String BINDING_HTTP_ARTIFACT = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Artifact";
	public static final String BINDING_URI = "urn:oasis:names:tc:SAML:2.0:bindings:URI";
	public static final String STATUS_SUCCESS = "urn:oasis:names:tc:SAML:2.0:status:Success";
	public static final String STATUS_REQUESTER = "urn:oasis:names:tc:SAML:2.0:status:Requester";
	public static final String STATUS_RESPONDER = "urn:oasis:names:tc:SAML:2.0:status:Responder";
	public static final String STATUS_VERSIONMISMATCH = "urn:oasis:names:tc:SAML:2.0:status:VersionMismatch";
	public static final String DECISION_PERMIT = "Permit";
	public static final String DECISION_DENY = "Deny";
	public static final String DECISION_INDETERMINATE = "Indeterminate";
	public static final String NAMEID_FORMAT_UNSPECIFIED = "urn:oasis:names:tc:SAML:1.0:nameid-format:unspecified";
	public static final String NAMEID_FORMAT_EMAILADDRESS = "urn:oasis:names:tc:SAML:1.0:nameid-format:emailAddress";
	public static final String NAMEID_FORMAT_X509SUBJECTNAME = "urn:oasis:names:tc:SAML:1.0:nameid-format:X509SubjectName";
	public static final String NAMEID_FORMAT_WINDOWSDOMAINQUALIFIEDNAME = "urn:oasis:names:tc:SAML:1.0:nameid-format:WindowsDomainQualifiedName";
	public static final String NAMEID_FORMAT_KERBEROS = "urn:oasis:names:tc:SAML:1.0:nameid-format:kerberos";
	public static final String NAMEID_FORMAT_ENTITY = "urn:oasis:names:tc:SAML:2.0:nameid-format:entity";
	public static final String NAMEID_FORMAT_PERSISTENT = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";
	public static final String NAMEID_FORMAT_TRANSIENT = "urn:oasis:names:tc:SAML:2.0:nameid-format:transient";
	public static final String AUTHNCONTEXT_PASSWORD = "urn:oasis:names:tc:SAML:2.0:ac:classes:Password";
	public static final String CONFIRMATION_METHOD_HOLDER = "urn:oasis:names:tc:SAML:2.0:cm:holder-of-key";
	public static final String CONFIRMATION_METHOD_SENDER = "urn:oasis:names:tc:SAML:2.0:cm:sender-vouches";
	public static final String CONFIRMATION_METHOD_BEARER = "urn:oasis:names:tc:SAML:2.0:cm:bearer";
	public static final String URLPARAM_SAMLREQUEST_REDIRECT = "SAMLRequest";
	public static final String URLPARAM_SAMLREQUEST_POST= "SAMLRequest";
	public static final String URLPARAM_SAMLRESPONSE_REDIRECT= "SAMLResponse";
	public static final String URLPARAM_SAMLRESPONSE_POST= "SAMLResponse";
	public static final String URLPARAM_SAMLARTIFACT = "SAMLArt";
	public static final String LANG_ENGLISH = "en";
	public static final String NAMEFORMAT_UNSPECIFIED= "urn:oasis:names:tc:SAML:2.0:attrname-format:unspecified";
	public static final String NAMEFORMAT_URI= "urn:oasis:names:tc:SAML:2.0:attrname-format:uri";
	public static final String NAMEFORMAT_BASIC= "urn:oasis:names:tc:SAML:2.0:attrname-format:basic";
	public static final String SAML20_PROTOCOL = "urn:oasis:names:tc:SAML:2.0:protocol";
	public static final String NAMESPACE_ATTR_X500 = "urn:oasis:names:tc:SAML:2.0:profiles:attribute:X500";
	public static final String X500_ENCODING = "Encoding";
	public static final String X500_ENCODING_LDAP = "LDAP";
	public static final String NAMESPACE_XML_DSIG = "http://www.w3.org/2000/09/xmldsig#"; 
	public static final String XML_DSIG_SIGNATURE = "Signature";
	public static final String XML_DSIG_SIGNATUREVALUE = "SignatureValue";
}
