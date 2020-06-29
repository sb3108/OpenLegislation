package gov.nysenate.openleg.transcripts;

import gov.nysenate.openleg.client.response.base.ListViewResponse;
import gov.nysenate.openleg.client.view.transcript.TranscriptView;
import gov.nysenate.openleg.dao.base.LimitOffset;

import java.util.List;

public class TranscriptsUi {

    private ListViewResponse<TranscriptView> searchResults;
    private List<String> availableYears;
    private String selectedYear;
    private String term;
    private int limit;
    private int offset;

    public TranscriptsUi(ListViewResponse<TranscriptView> searchResults, List<String> availableYears,
                         String selectedYear, String term, LimitOffset limitOffset) {
        this.searchResults = searchResults;
        this.availableYears = availableYears;
        this.selectedYear = selectedYear;
        this.term = term;
        this.limit = limitOffset.getLimit();
        this.offset = limitOffset.getOffsetStart();
    }

    public List<TranscriptView> getResults() {
        return searchResults.getResult().getItems();
    }

    public PaginationUi getPagination() {
        return new PaginationUi(new LimitOffset(searchResults.getLimit(), searchResults.getOffsetStart()), searchResults.getTotal());
    }

    public ListViewResponse<TranscriptView> getSearchResults() {
        return searchResults;
    }

    public List<String> getAvailableYears() {
        return availableYears;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public String getTerm() {
        return term;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
