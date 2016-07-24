package com.henry.displaydata;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
import com.henry.displaydata.background.DownloadPersonTask;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class Download_Test
{

    @Test
    public void test1()
    {
        //  create mock
        DownloadPersonTask test = mock(DownloadPersonTask.class);
        test.DownloadPersons();
    }
}
