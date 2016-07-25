package com.henry.displaydata;

/**
 * Created by Henry.Oforeh on 2016/07/23.
 */
import com.henry.displaydata.activity.PersonListActivity;

import com.henry.displaydata.adapter.data.PersonDataAdapter;
import com.henry.displaydata.model.Person;
import com.henry.displaydata.model.PersonDetail;
import com.henry.displaydata.service.RestClient;
import com.henry.displaydata.service.RestServiceInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

import static org.mockito.Mockito.*;

@Config(constants = BuildConfig.class, sdk = 21,
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
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        ActivityController<PersonListActivity> controller = Robolectric.buildActivity(PersonListActivity.class);
        personActivity = controller.get();

        RestClient.setRestClient(mockRetrofitApiImpl);

        controller.create();
    }

    @Test
    public void GetPersonsTest() throws Exception
    {
       Mockito.verify(mockRetrofitApiImpl).GetPersons();
//
        int objectsQuantity = 3;
        List<Person> list = new ArrayList<Person>();
        for(int i = 0; i < objectsQuantity; ++i) {
            list.add(new Person(i,"Test","Test2"));
        }

         callbackArgumentCaptor.getValue().success(list, null);
//
        //PersonDataAdapter testAdapter = personActivity.getPersonAdapter(); // obtain adapter
//        // simple test check if adapter has as many items as put into response
//        assertThat(testAdapter.getItemCount(),  equalTo(objectsQuantity));



        //assertThat(testAdapter.getItemCount(), equalTo(objectsQuantity));
    }
}
