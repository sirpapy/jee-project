package fr.upem.jee.allodoc.faces.patient;

import com.google.common.base.Preconditions;
import fr.upem.jee.allodoc.entity.SearchItem;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;

/**
 * Created by raptao on 3/23/2017.
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class SearchHistoryService {

    private Map<String, List<SearchItem>> searchHistory;

    public SearchHistoryService() {
        searchHistory = new HashMap<>();
    }

    public void add(String login, SearchItem newSearch) {
        Preconditions.checkNotNull(login);
        Preconditions.checkNotNull(newSearch);
        searchHistory.putIfAbsent(login, new ArrayList<>());
        searchHistory.compute(login, (s, searchItems) -> {
            searchItems.add(newSearch);
            return searchItems;
        });
    }

    public List<SearchItem> searchItemsOf(String login) {
        Preconditions.checkNotNull(login);
        return searchHistory.getOrDefault(login, Collections.emptyList());
    }
}
