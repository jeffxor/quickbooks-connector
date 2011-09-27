/**
 * Mule FWS Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;
import static org.apache.commons.lang.StringUtils.*;

/**
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 12, 2011
 */

public final class QuickBooksConventions
{
    private QuickBooksConventions()
    {    
    }
    public static String toQuickBooksPathVariable(String entityName)
    {
        return join(splitByCharacterTypeCamelCase(entityName), "-").toLowerCase();
    }

}
