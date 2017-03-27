package fr.upem.jee.allodoc.faces.patient;

import fr.upem.jee.allodoc.entity.SearchItem;
import org.junit.Test;

/**
 * Created by raptao on 3/24/2017.
 */
public class SearchItemTest {
    @Test
    public void getFormatedTimeStamp() throws Exception {
        SearchItem build = SearchItem.builder().build();
        System.out.println(build.getFormatedTimeStamp());
    }

}