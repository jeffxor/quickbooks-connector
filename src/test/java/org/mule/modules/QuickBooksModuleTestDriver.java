
/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.quickbooks.AccountDetail;
import org.mule.modules.quickbooks.QuickBooksModule;

/**
 * TODO: Description of the class, Comments in english by default
 * 
 * @author Gaston Ponti
 * @since Sep 9, 2011
 */

public class QuickBooksModuleTestDriver
{

    private QuickBooksModule module;
    private String accessToken, accessTokenSecret;

    /**
     * 
     */
    @Before
    public void setup()
    {
        module = new QuickBooksModule();
        module.setConsumerKey("qyprdjjAVqbMjp3iQHH2SutYSm2min");
        module.setConsumerSecret("6I5mTDGjthIeIWJm6iKx7Q4jAihKFd1yvFvkMi3B");
        module.setRealmId("212879793");
        module.init();
        
        accessToken = "lvprdUOzPD8jlLdCSgKGYubbNAwFh03PUHM34gWvXPYoPdgJ";
        accessTokenSecret = "B5zGyujNpe3dTwL4hHY5Cr0x1CRXqgukiAex9Aab";

    }

    @Test
    public void createAccountAnswersNonNullAccountWithId() throws Exception
    {
        module.createAccount(accessToken, accessTokenSecret, "TestAccount", null, AccountDetail.ACCOUNTS_PAYABLE, null, null,
            null, null);
    }
}
