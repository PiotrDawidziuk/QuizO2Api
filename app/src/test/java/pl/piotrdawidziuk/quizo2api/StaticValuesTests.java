package pl.piotrdawidziuk.quizo2api;

import org.junit.Assert;
import org.junit.Test;

import pl.piotrdawidziuk.quizo2api.activities.MainActivity;
import pl.piotrdawidziuk.quizo2api.service.AnswersAdapter;

public class StaticValuesTests {
    @Test
    public void checkMainStatics() {

        String id = MainActivity.EXTRA_ID;
        String title = MainActivity.EXTRA_TITLE;
        String url = MainActivity.EXTRA_URL;

        Assert.assertEquals("id",id);
        Assert.assertEquals("title",title);
        Assert.assertEquals("url",url);
    }

    @Test
    public void checkAdapterStatic(){
        boolean rightAnswerIsSelected = AnswersAdapter.RIGHT_ANSWER_IS_SELECTED;
        Assert.assertEquals(rightAnswerIsSelected,false);
    }
}

