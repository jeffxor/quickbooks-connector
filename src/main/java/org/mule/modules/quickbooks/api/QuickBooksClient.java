/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import org.mule.modules.quickbooks.EntityType;
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
    <T extends CdmBase> T create(T obj);
    
    <T extends CdmBase> T getObject(EntityType type, IdType id);
    
    <T extends CdmBase> T update(T obj);
    
    <T extends CdmBase> void deleteObject(EntityType type, IdType id, String syncToken);

    <T extends CdmBase> Iterable<T> findObjects(final EntityType type, final String queryFilter, final String querySort);
    
}

