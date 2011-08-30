/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;

import org.mule.modules.quickbooks.EntityType;
import org.mule.modules.quickbooks.api.Exception.QuickBooksException;
import org.mule.modules.quickbooks.schema.CdmBase;
import org.mule.modules.quickbooks.schema.IdType;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 19, 2011
 */

public interface QuickBooksClient
{
    <T> T create(T obj) throws QuickBooksException;
    
    <T  extends CdmBase> T getObject(EntityType type, IdType id) throws QuickBooksException;
    
    <T> T update(T obj) throws QuickBooksException;
    
    <T extends CdmBase> void deleteObject(EntityType type, IdType id, String syncToken) throws QuickBooksException;

    <T> List<T> findObjects();
}

