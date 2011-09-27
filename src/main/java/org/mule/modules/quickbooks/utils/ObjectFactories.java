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

package org.mule.modules.quickbooks.utils;

import javax.xml.bind.JAXBElement;

import org.mule.modules.quickbooks.schema.ObjectFactory;


/**
 *   
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 9, 2011
 */

public final class ObjectFactories
{
    /**
     * Creates the ObjectFactories.
     *
     */

    private ObjectFactories()
    {
    }
        /**
         * @param <T>
         * @param obj
         * @param objectFactory
         * @return
         */
    public static <T> JAXBElement<T> createJaxbElement(T obj, ObjectFactory objectFactory)
    {
        try
        {
            return (JAXBElement<T>) objectFactory.getClass()
                .getMethod("create" + obj.getClass().getSimpleName(), obj.getClass())
                .invoke(objectFactory, obj);
        }
        catch (Exception e)
        {
            throw new AssertionError(e);
        }
    }
}

