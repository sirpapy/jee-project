package fr.upem.jee.allodoc.faces;

import com.google.common.base.Preconditions;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raptao on 3/23/2017.
 */
@ManagedBean
@ApplicationScoped
public class SearchHistoryService {

    private Map<String, SearchItem> searchHistory;

    public SearchHistoryService() {
        searchHistory = new HashMap<>();
    }

    public void add(String login, SearchItem newSearch) {
        Preconditions.checkNotNull(login);
        Preconditions.checkNotNull(newSearch)
        searchHistory.put(login, newSearch);
    }
}
