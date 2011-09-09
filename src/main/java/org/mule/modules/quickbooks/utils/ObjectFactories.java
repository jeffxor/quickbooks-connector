

/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.utils;

import javax.xml.bind.JAXBElement;

import org.mule.modules.quickbooks.schema.ObjectFactory;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 9, 2011
 */

public class ObjectFactories
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

