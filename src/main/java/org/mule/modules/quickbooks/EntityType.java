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

/**
 * The supported objects for Data Services for QuickBooks Online.
 * 
 * @author Gaston Ponti
 * @since Oct 5, 2011
 */

public enum EntityType
{
    /**
     * The Account object represents the accounts that you keep to track your business.
     * Account is a component of a chart of accounts, and is part of a ledger. 
     * You can use Account to record the total monetary amount that is allocated 
     * for a specific use.
     */
    ACCOUNT(Account.class),
    
    /**
     * The Bill object represents an expense to the business.
     */
    BILL(Bill.class),
    
    /**
     * BillPayment represents the financial transaction of payment of bills that the 
     * business owner receives from a vendor for goods or services purchased from the vendor. 
     * QBO supports bill payments through a credit card or a bank account.
     */
    BILLPAYMENT(BillPayment.class),
    
    /**
     * CashPurchase represents an expense to the business as a cash transaction.
     */
    CASHPURCHASE(CashPurchase.class),
    
    /**
     * The Check object represents an expense to the business paid as a check transaction.
     */
    CHECK(Check.class),
    
    /**
     * The CreditCardCharge object represents an expense to the business as a credit
     * card charge transaction.
     */
    CREDITCARDCHARGE(CreditCardCharge.class),
    
    /**
     * The Customer object represents the consumer of the service or the product that 
     * your business offers. 
     * QBO allows categorizing the customers in a way that is meaningful to the business. 
     */
    CUSTOMER(Customer.class),
    
    /**
     * The Estimate object represents a proposal for a financial transaction from a 
     * business to a customer for goods or services proposed to be sold, including 
     * proposed pricing. It is also known as quote.
     */
    ESTIMATE(Estimate.class),
    
    /**
     * The Invoice object represents an invoice to a customer. 
     * Invoice could be based on salesterm with invoice and due dates for payment. 
     * Invoice supports tax, but as of now shipping charges are not supported.
     */
    INVOICE(Invoice.class),
    
    /**
     * The Item object represents any product or service that is sold or purchased.
     */
    ITEM(Item.class),
    
    /**
     * The Payment object  represents the financial transaction that signifies a 
     * payment from a customer for one or more sales transactions.
     */
    PAYMENT(Payment.class),
    
    /**
     * PaymentMethod represents the method of payment for a transaction. It can 
     * be a credit card payment type or a non-credit card payment type.
     */
    PAYMENTMETHOD(PaymentMethod.class),
    
    /**
     * SalesReceipt represents the sales receipt that is given to a customer.  
     * A sales receipt is similar to an invoice. However, for a sales receipt, payment 
     * is received as part of the sale of goods and services. The sales receipt 
     * specifies a deposit account where the customer deposits the payment. 
     * If the deposit account is not specified, the payment type is classified 
     * as Undeposited Account.
     */
    SALESRECEIPT(SalesReceipt.class),
    
    /**
     * The SalesTerm object  represents the terms under which a sale is made. 
     * SalesTerm is typically expressed in the form of days due after the goods are 
     * received. There is an optional discount part of the sales term, where a discount 
     * of total amount can automatically be applied if payment is made within a few 
     * days of the stipulated time.
     */
    SALESTERM(SalesTerm.class),
    
    /**
     * The Vendor object represents the buyer from whom you purchase any service or 
     * product for your organization.
     */
    VENDOR(Vendor.class);
    
    private final Class<?> type;
    
    private EntityType(Class<?> type)
    {
        this.type = type;
    }
    
    @SuppressWarnings("unchecked")
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
