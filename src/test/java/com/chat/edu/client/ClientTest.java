package com.chat.edu.client;

import com.chat.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientTest implements SysoutCaptureAndAssertionAbility {

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldNotClientStartWithoutHostAndPort(){
        //region when
        Client.main(new String[]{""});
        //endregion

        //region then
        assertSysoutEquals("Please specify host and port and try again" + System.lineSeparator());
        //endregion
    }

    @Test
    public void shouldStartClientWithHostAndPort(){
        ClientController clientControllerMock = mock(ClientController.class);

        //region when
        Client.main(new String[]{"0.0.0.0","100"});
        //endregion

        //region then
        verify(clientControllerMock).start("0.0.0.0",100);
        //endregion
    }
}
