/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api.Exception;

import org.mule.modules.quickbooks.schema.FaultInfo;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 30, 2011
 */

public class QuickBooksException extends RuntimeException
{
    private final FaultInfo fault;
    
    /**
     * Creates the QuickBooksException.
     *
     * @param fault
     */
    
    public QuickBooksException(final FaultInfo fault)
    {
        this.fault = fault;
    }

    public FaultInfo getFaultInfo()
    {
        return fault;
    }
}

