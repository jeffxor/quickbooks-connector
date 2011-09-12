
/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;
import static org.apache.commons.lang.StringUtils.*;

/**
 * TODO: Description of the class, Comments in english by default
 * 
 * @author Gaston Ponti
 * @since Sep 12, 2011
 */

public class QuickBooksConventions
{
    public static String toQuickBooksPathVariable(String entityName)
    {
        return join(splitByCharacterTypeCamelCase(entityName), "-").toLowerCase();
    }

}
