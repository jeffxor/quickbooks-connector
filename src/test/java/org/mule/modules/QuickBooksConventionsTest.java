
	
	/*
	 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
	 */
	
	package org.mule.modules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mule.modules.quickbooks.api.QuickBooksConventions;

	
	/**
 * TODO: Description of the class, Comments in english by default  
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

	