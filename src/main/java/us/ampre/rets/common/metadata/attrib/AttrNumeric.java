/*
 * cart:  CRT's Awesome RETS Tool
 *
 * Author: David Terrell
 * Copyright (c) 2003, The National Association of REALTORS
 * Distributed under a BSD-style license.  See LICENSE.TXT for details.
 */
package us.ampre.rets.common.metadata.attrib;

import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;

public class AttrNumeric implements AttrType<Integer> {
	public Integer parse(String value, boolean strict) throws MetaParseException {
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			if( strict ) 
				throw new MetaParseException(e);
			return 0;
		}
	}

	public String render(Integer value) {
		return value.toString();
	}

	public Class<Integer> getType() {
		return Integer.class;
	}
}
