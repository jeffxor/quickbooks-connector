/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;

import org.mule.modules.quickbooks.EntityType;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 19, 2011
 */

public interface QuickBooksClient
{
    <T> T create(T obj);
    
    <T> T getObject(EntityType type, String objectId);
    
    <T> T update(T obj);
    
    void deleteObject(EntityType type, String objectId, String syncToken);

    <T> List<T> findObjects();
}

