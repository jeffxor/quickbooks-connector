/**
 * Mule FWS Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.quickbooks.api;

import java.util.HashMap;
import java.util.Map;


/**
 *   
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 26, 2011
 */

public class MapBuilder
{
    private Map<String, Object> map = new HashMap<String, Object>();
    
    /**
     * Creates the QuickBooksModule.EntityBuilder.
     *
     */
               
    public MapBuilder with(String propertyName, Object property)
    {
        map.put(propertyName, property);
        return this;
    }
   
    public Map<String, Object> build()
    {
        return map;
    }
}
