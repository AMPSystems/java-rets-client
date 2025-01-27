package us.ampre.rets.common.metadata.types;

//import java.util.Date;
import java.util.Map;

import us.ampre.rets.common.metadata.MetaObject;
import us.ampre.rets.common.metadata.MetadataType;

public class MLookup extends MetaObject {
	private static final MetadataType[] CHILDREN = { MetadataType.LOOKUP_TYPE };
	private static final MLookupType[] EMPTYLOOKUPTYPES = {};
	public static final String METADATAENTRYID = "MetadataEntryID";
	public static final String LOOKUPNAME = "LookupName";
	public static final String VISIBLENAME = "VisibleName";
	public static final String VERSION = "Version";
	public static final String DATE = "Date";
	public static final String LOOKUPTYPEVERSION = "LookupTypeVersion";
	public static final String LOOKUPTYPEDATE = "LookupTypeDate";

	public MLookup() {
		this(DEFAULT_PARSING);
	}

	public MLookup(boolean strictParsing) {
		super(strictParsing);
	}

	public String getMetadataEntryID() {
		return getStringAttribute(METADATAENTRYID);
	}

	public String getLookupName() {
		return getStringAttribute(LOOKUPNAME);
	}

	public String getVisibleName() {
		return getStringAttribute(VISIBLENAME);
	}

	public String getVersion() {
		
		String ver = getStringAttribute(VERSION);
		if (ver == null){
			ver = getStringAttribute(LOOKUPTYPEVERSION);
		}
		return ver;
	}

	public String getDate() {
		String date = getDateAttribute(DATE);
		if (date == null) {
			date = getDateAttribute(LOOKUPTYPEDATE);
		}
		return date;
	}

	public MLookupType getMLookupType(String value) {
		return (MLookupType) getChild(MetadataType.LOOKUP_TYPE, value);
	}

	public MLookupType[] getMLookupTypes() {
		return (MLookupType[]) getChildren(MetadataType.LOOKUP_TYPE).toArray(EMPTYLOOKUPTYPES);
	}

	@Override
	public MetadataType[] getChildTypes() {
		return CHILDREN;
	}

	@Override
	protected String getIdAttr() {
		return LOOKUPNAME;
	}

	@Override
	protected void addAttributesToMap(Map attributeMap) {
		attributeMap.put(METADATAENTRYID, sAttrMetadataEntryId);
		attributeMap.put(LOOKUPNAME, sAlphanum32);
		attributeMap.put(VISIBLENAME, sPlaintext32);
		attributeMap.put(VERSION, sAlphanum64);
		attributeMap.put(DATE, sAttrDate);
		attributeMap.put(LOOKUPTYPEVERSION, sAlphanum64);
		attributeMap.put(LOOKUPTYPEDATE, sAttrDate);
	}

}
