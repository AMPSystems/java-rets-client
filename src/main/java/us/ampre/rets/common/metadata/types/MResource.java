package us.ampre.rets.common.metadata.types;

//import java.util.Date;
import java.util.Map;

import us.ampre.rets.common.metadata.MetaObject;
import us.ampre.rets.common.metadata.MetadataType;

public class MResource extends MetaObject {
	private static final MetadataType[] CHILDREN = { 
		MetadataType.VALIDATION_EXPRESSION, 
		MetadataType.LOOKUP,
		MetadataType.CLASS, 
		MetadataType.OBJECT, 
		MetadataType.VALIDATION_EXTERNAL, 
		MetadataType.VALIDATION_LOOKUP,
		MetadataType.EDITMASK, 
		MetadataType.UPDATE_HELP, 
		MetadataType.SEARCH_HELP
	};

	public static final String RESOURCEID = "ResourceID";
	public static final String STANDARDNAME = "StandardName";
	public static final String VISIBLENAME = "VisibleName";
	public static final String DESCRIPTION = "Description";
	public static final String KEYFIELD = "KeyField";
	public static final String CLASSCOUNT = "ClassCount";
	public static final String CLASSVERSION = "ClassVersion";
	public static final String CLASSDATE = "ClassDate";
	public static final String OBJECTVERSION = "ObjectVersion";
	public static final String OBJECTDATE = "ObjectDate";
	public static final String SEARCHHELPVERSION = "SearchHelpVersion";
	public static final String SEARCHHELPDATE = "SearchHelpDate";
	public static final String EDITMASKVERSION = "EditMaskVersion";
	public static final String EDITMASKDATE = "EditMaskDate";
	public static final String LOOKUPVERSION = "LookupVersion";
	public static final String LOOKUPDATE = "LookupDate";
	public static final String UPDATEHELPVERSION = "UpdateHelpVersion";
	public static final String UPDATEHELPDATE = "UpdateHelpDate";
	public static final String VALIDATIONEXPRESSIONVERSION = "ValidationExpressionVersion";
	public static final String VALIDATIONEXPRESSIONDATE = "ValidationExpressionDate";
	public static final String VALIDATIONLOOKUPVERSION = "ValidationLookupVersion";
	public static final String VALIDATIONLOOKUPDATE = "ValidationLookupDate";
	public static final String VALIDATIONEXTERNALVERSION = "ValidationExternalVersion";
	public static final String VALIDATIONEXTERNALDATE = "ValidationExternalDate";

	public MResource() {
		this(DEFAULT_PARSING);
	}

	public MResource(boolean strictParsing) {
		super(strictParsing);
	}

	public String getResourceID() {
		return getStringAttribute(RESOURCEID);
	}

	public String getStandardName() {
		return getStringAttribute(STANDARDNAME);
	}

	public String getVisibleName() {
		return getStringAttribute(VISIBLENAME);
	}

	public String getDescription() {
		return getStringAttribute(DESCRIPTION);
	}

	public String getKeyField() {
		return getStringAttribute(KEYFIELD);
	}

	public int getClassCount() {
		return getIntAttribute(CLASSCOUNT);
	}

	public String getClassVersion() {
		return getStringAttribute(CLASSVERSION);
	}

	public String getClassDate() {
		return getDateAttribute(CLASSDATE);
	}

	public String getObjectVersion() {
		return getStringAttribute(OBJECTVERSION);
	}

	public String getObjectDate() {
		return getDateAttribute(OBJECTDATE);
	}

	public String getSearchHelpVersion() {
		return getStringAttribute(SEARCHHELPVERSION);
	}

	public String getSearchHelpDate() {
		return getDateAttribute(SEARCHHELPDATE);
	}

	public String getEditMaskVersion() {
		return getStringAttribute(EDITMASKVERSION);
	}

	public String getEditMaskDate() {
		return getDateAttribute(EDITMASKDATE);
	}

	public String getLookupVersion() {
		return getStringAttribute(LOOKUPVERSION);
	}

	public String getLookupDate() {
		return getDateAttribute(LOOKUPDATE);
	}

	public String getUpdateHelpVersion() {
		return getStringAttribute(UPDATEHELPVERSION);
	}

	public String getUpdateHelpDate() {
		return getDateAttribute(UPDATEHELPDATE);
	}

	public String getValidationExpressionVersion() {
		return getStringAttribute(VALIDATIONEXPRESSIONVERSION);
	}

	public String getValidationExpressionDate() {
		return getDateAttribute(VALIDATIONEXPRESSIONDATE);
	}

	public String getValidationLookupVersion() {
		return getStringAttribute(VALIDATIONLOOKUPVERSION);
	}

	public String getValidationLookupDate() {
		return getDateAttribute(VALIDATIONLOOKUPDATE);
	}

	public String getValidationExternalVersion() {
		return getStringAttribute(VALIDATIONEXTERNALVERSION);
	}

	public String getValidationExternalDate() {
		return getDateAttribute(VALIDATIONEXTERNALDATE);
	}

	public MValidationExpression getMValidationExpression(String validationExpressionID) {
		return (MValidationExpression) getChild(MetadataType.VALIDATION_EXPRESSION, validationExpressionID);
	}

	public MValidationExpression[] getMValidationExpressions() {
		MValidationExpression[] tmpl = new MValidationExpression[0];
		return (MValidationExpression[]) getChildren(MetadataType.VALIDATION_EXPRESSION).toArray(tmpl);
	}

	public MLookup getMLookup(String lookupName) {
		return (MLookup) getChild(MetadataType.LOOKUP, lookupName);
	}

	public MLookup[] getMLookups() {
		MLookup[] tmpl = new MLookup[0];
		return (MLookup[]) getChildren(MetadataType.LOOKUP).toArray(tmpl);
	}

	public MClass getMClass(String className) {
		return (MClass) getChild(MetadataType.CLASS, className);
	}

	public MClass[] getMClasses() {
		MClass[] tmpl = new MClass[0];
		return (MClass[]) getChildren(MetadataType.CLASS).toArray(tmpl);
	}

	public MObject getMObject(String objectType) {
		return (MObject) getChild(MetadataType.OBJECT, objectType);
	}

	public MObject[] getMObjects() {
		MObject[] tmpl = new MObject[0];
		return (MObject[]) getChildren(MetadataType.OBJECT).toArray(tmpl);
	}

	public MValidationExternal getMValidationExternal(String validationExternalName) {
		return (MValidationExternal) getChild(MetadataType.VALIDATION_EXTERNAL, validationExternalName);
	}

	public MValidationExternal[] getMValidationExternal() {
		MValidationExternal[] tmpl = new MValidationExternal[0];
		return (MValidationExternal[]) getChildren(MetadataType.VALIDATION_EXTERNAL).toArray(tmpl);
	}

	public MValidationLookup getMValidationLookup(String validationLookupName) {
		return (MValidationLookup) getChild(MetadataType.VALIDATION_LOOKUP, validationLookupName);
	}

	public MValidationLookup[] getMValidationLookups() {
		MValidationLookup[] tmpl = new MValidationLookup[0];
		return (MValidationLookup[]) getChildren(MetadataType.VALIDATION_LOOKUP).toArray(tmpl);
	}

	public MEditMask getMEditMask(String editMaskID) {
		return (MEditMask) getChild(MetadataType.EDITMASK, editMaskID);
	}

	public MEditMask[] getMEditMasks() {
		MEditMask[] tmpl = new MEditMask[0];
		return (MEditMask[]) getChildren(MetadataType.EDITMASK).toArray(tmpl);
	}

	public MUpdateHelp getMUpdateHelp(String updateHelpID) {
		return (MUpdateHelp) getChild(MetadataType.UPDATE_HELP, updateHelpID);
	}

	public MUpdateHelp[] getMUpdateHelps() {
		MUpdateHelp[] tmpl = new MUpdateHelp[0];
		return (MUpdateHelp[]) getChildren(MetadataType.UPDATE_HELP).toArray(tmpl);
	}

	public MSearchHelp getMSearchHelp(String searchHelpID) {
		return (MSearchHelp) getChild(MetadataType.SEARCH_HELP, searchHelpID);
	}

	public MSearchHelp[] getMSearchHelps() {
		MSearchHelp[] tmpl = new MSearchHelp[0];
		return (MSearchHelp[]) getChildren(MetadataType.SEARCH_HELP).toArray(tmpl);
	}

	@Override
	public MetadataType[] getChildTypes() {
		return CHILDREN;
	}

	@Override
	protected String getIdAttr() {
		return RESOURCEID;
	}

	@Override
	protected void addAttributesToMap(Map attributeMap) {
		attributeMap.put(RESOURCEID, sAlphanum32);
		attributeMap.put(STANDARDNAME, sAlphanum32);
		attributeMap.put(VISIBLENAME, sPlaintext32);
		attributeMap.put(DESCRIPTION, sPlaintext64);
		attributeMap.put(KEYFIELD, sAlphanum32);
		attributeMap.put(CLASSCOUNT, sAttrNumeric);
		attributeMap.put(CLASSVERSION, sAlphanum64);
		attributeMap.put(CLASSDATE, sAttrDate);
		attributeMap.put(OBJECTVERSION, sAlphanum64);
		attributeMap.put(OBJECTDATE, sAttrDate);
		attributeMap.put(SEARCHHELPVERSION, sAlphanum64);
		attributeMap.put(SEARCHHELPDATE, sAttrDate);
		attributeMap.put(EDITMASKVERSION, sAlphanum64);
		attributeMap.put(EDITMASKDATE, sAttrDate);
		attributeMap.put(LOOKUPVERSION, sAlphanum64);
		attributeMap.put(LOOKUPDATE, sAttrDate);
		attributeMap.put(UPDATEHELPVERSION, sAlphanum64);
		attributeMap.put(UPDATEHELPDATE, sAttrDate);
		attributeMap.put(VALIDATIONEXPRESSIONVERSION, sAlphanum64);
		attributeMap.put(VALIDATIONEXPRESSIONDATE, sAttrDate);
		attributeMap.put(VALIDATIONLOOKUPVERSION, sAlphanum64);
		attributeMap.put(VALIDATIONLOOKUPDATE, sAttrDate);
		attributeMap.put(VALIDATIONEXTERNALVERSION, sAlphanum64);
		attributeMap.put(VALIDATIONEXTERNALDATE, sAttrDate);
	}

}
