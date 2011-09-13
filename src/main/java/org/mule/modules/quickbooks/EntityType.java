/**
 * Mule FWS Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.quickbooks;

import org.mule.modules.quickbooks.api.QuickBooksConventions;
import org.mule.modules.quickbooks.schema.Account;
import org.mule.modules.quickbooks.schema.Bill;
import org.mule.modules.quickbooks.schema.BillPayment;
import org.mule.modules.quickbooks.schema.CashPurchase;
import org.mule.modules.quickbooks.schema.Check;
import org.mule.modules.quickbooks.schema.CreditCardCharge;
import org.mule.modules.quickbooks.schema.Customer;
import org.mule.modules.quickbooks.schema.Estimate;
import org.mule.modules.quickbooks.schema.Invoice;
import org.mule.modules.quickbooks.schema.Item;
import org.mule.modules.quickbooks.schema.Payment;
import org.mule.modules.quickbooks.schema.PaymentMethod;
import org.mule.modules.quickbooks.schema.SalesReceipt;
import org.mule.modules.quickbooks.schema.SalesTerm;
import org.mule.modules.quickbooks.schema.Vendor;

public enum EntityType
{
    ACCOUNT(Account.class),
    BILL(Bill.class),
    BILLPAYMENT(BillPayment.class),
    CASHPURCHASE(CashPurchase.class),
    CHECK(Check.class),
    CREDITCARDCHARGE(CreditCardCharge.class),
    CUSTOMER(Customer.class),
    ESTIMATE(Estimate.class),
    INVOICE(Invoice.class),
    ITEM(Item.class),
    PAYMENT(Payment.class),
    PAYMENTMETHOD(PaymentMethod.class),
    SALESRECEIPT(SalesReceipt.class),
    SALESTERM(SalesTerm.class),
    VENDOR(Vendor.class);
    
    private final Class<?> type;
    
    private EntityType(Class<?> type)
    {
        this.type = type;
    }
    
    public <A> A newInstance()
    {
        try
        {
            return (A) type.newInstance();
        }
        catch (Exception e)
        {
            throw new AssertionError(e);
        }
    }

    /**
     * Answers the resource name of this entity type as present in the entity uri
     * 
     * @return
     */
    public String getResouceName()
    {
        return  QuickBooksConventions.toQuickBooksPathVariable(getSimpleName());
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public <A> Class<A> getType()
    {
        return (Class<A>) type;
    }

    /**
     * @return the simple name of the associated class for this entity type
     */
    public String getSimpleName()
    {
        return getType().getSimpleName();
    }
}
