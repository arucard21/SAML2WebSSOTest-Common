package saml2tester.common;
/**
 * This class contains all the information required to interact with a link (anchor) on an HTML page.
 * 
 * @author RiaasM
 *
 */
public class LinkInteraction {

	/**
	 * Define the possible methods of looking up a link (this refers specifically to the <a> HTML element)
	 */
	public enum LookupType{
		NAME, TEXT, HREF
	}
	private LookupType lookupType;
	private String lookupValue;
	
	/**
	 * Create a new LinkInteraction
	 * 
	 * @param type is the type of lookup that is required for this interaction (value should be name, text or href)
	 * @param value is the string which should be used for the lookup. Its value depends on the type of lookup performed.
	 */
	public LinkInteraction(LookupType type, String value){
		setLookupType(type);
		setLookupValue(value);
	}

	/**
	 * @return the lookupType
	 */
	public LookupType getLookupType() {
		return lookupType;
	}

	/**
	 * @param lookupType the lookupType to set
	 */
	public void setLookupType(LookupType lookupType) {
		this.lookupType = lookupType;
	}

	/**
	 * @return the lookupValue
	 */
	public String getLookupValue() {
		return lookupValue;
	}

	/**
	 * @param lookupValue the lookupValue to set
	 */
	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}
	
}
