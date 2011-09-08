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
    <T extends CdmBase> T create(T obj, String accesskey, String accessSecret);
    
    <T extends CdmBase> T getObject(EntityType type, IdType id, String accesskey, String accessSecret);
    
    <T extends CdmBase> T update(T obj, String accesskey, String accessSecret);
    
    <T extends CdmBase> void deleteObject(EntityType type, IdType id, String syncToken, String accesskey, String accessSecret);

    <T extends CdmBase> Iterable<T> findObjects(final EntityType type, final String queryFilter, final String querySort, String accesskey, String accessSecret);
    
}

