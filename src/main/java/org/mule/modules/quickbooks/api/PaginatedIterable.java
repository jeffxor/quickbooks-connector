/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.Iterator;


/**
 * TODO: Description of the class, Comments in english by default  
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

