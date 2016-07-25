package com.henry.displaydata;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
import com.henry.displaydata.activity.PersonListActivity;
import com.henry.displaydata.background.DownloadPersonTask;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.service.RestClient;
import com.henry.displaydata.service.RestServiceInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.List;

import retrofit.Callback;

import static org.mockito.Mockito.*;

@Config(constants = BuildConfig.class, sdk = 23,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class RetrofitCallTest
{
    private PersonListActivity personActivity;

    @Mock
    private RestServiceInterface mockRetrofitApiImpl;

    @Captor
    private ArgumentCaptor<Callback<List<Person>>> callbackArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<PersonListActivity> controller = Robolectric.buildActivity(PersonListActivity.class);
        personActivity = controller.get();

       //RestClient.setApi(mockRetrofitApiImpl);

        controller.create();
    }

    @Test
    public void test1()
    {
        //  create mock
        DownloadPersonTask test = mock(DownloadPersonTask.class);
        test.DownloadPersons();
    }
}
