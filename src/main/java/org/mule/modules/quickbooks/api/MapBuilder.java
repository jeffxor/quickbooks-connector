/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.Map;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 26, 2011
 */

public class MapBuilder
{
    private Map<String, Object> map;
        
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
