/**
 * Mule QuickBooks Connector
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
	
package org.mule.modules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mule.modules.quickbooks.api.QuickBooksConventions;

	
/**
 *   
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 12, 2011
 */

public class QuickBooksConventionsTest
{
    @Test
    public void test()
    {
        assertEquals("credit-card-charge", QuickBooksConventions.toQuickBooksPathVariable("CreditCardCharge"));
        assertEquals("payment-method", QuickBooksConventions.toQuickBooksPathVariable("PaymentMethod"));
        assertEquals("sales-receipt", QuickBooksConventions.toQuickBooksPathVariable("SalesReceipt"));
    }
}

