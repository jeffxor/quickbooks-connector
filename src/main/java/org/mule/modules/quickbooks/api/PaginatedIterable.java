/**
 * Mule QuickBooks Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.quickbooks.api;

import java.util.Iterator;


/**
 *   
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 31, 2011
 */

public abstract class PaginatedIterable<T, Page> implements Iterable<T>
{
    public Iterator<T> iterator()
    {
        final Page initialPageInfo = firstPage();
        return new Iterator<T>()
        {
            private Page currentList = initialPageInfo;
            private Iterator<T> currentIter = pageIterator(initialPageInfo);
            public boolean hasNext()
            {
                updateIter();
                return currentIter.hasNext();
            }
            public T next()
            {
                updateIter();
                return currentIter.next();
            }
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
            private void updateIter()
            {
                if (!currentIter.hasNext() && hasNextPage(currentList))
                {
                    currentList = nextPage(currentList);
                    currentIter = pageIterator(currentList);
                }
            }
        };
    }
    /**
     * Answers the first page of the paginated result
     */
    protected abstract Page firstPage();
    /**
     * Given a page, answers the next page
     * 
     * @param currentPage
     * @return the next page
     */
    protected abstract Page nextPage(Page currentPage);
    /**
     * Answers if the given page is not the last one
     * 
     * @param page
     * @return if current page is the last one or not
     */
    protected abstract boolean hasNextPage(Page page);

    /**
     * Answers an iterator for the current page
     * 
     * @param page
     * @return the given page iterator
     */
    protected abstract Iterator<T> pageIterator(Page page);
}

