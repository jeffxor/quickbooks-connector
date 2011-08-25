/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 19, 2011
 */

public interface QuickBooksClient
{
    void create(Object obj);
    
    Object getObject(String type, String objectId);
    
    void update(String username);
    
    void deleteObject(String type, String objectId);

    List<Object> findObjects();
}

