package gov.nysenate.openleg.controller.ui;

import gov.nysenate.openleg.client.response.base.ListViewResponse;
import gov.nysenate.openleg.client.view.search.SearchResultView;
import gov.nysenate.openleg.client.view.transcript.TranscriptInfoView;
import gov.nysenate.openleg.client.view.transcript.TranscriptView;
import gov.nysenate.openleg.controller.api.base.BaseCtrl;
import gov.nysenate.openleg.dao.base.LimitOffset;
import gov.nysenate.openleg.model.search.SearchException;
import gov.nysenate.openleg.model.search.SearchResult;
import gov.nysenate.openleg.model.search.SearchResults;
import gov.nysenate.openleg.model.transcript.Transcript;
import gov.nysenate.openleg.model.transcript.TranscriptId;
import gov.nysenate.openleg.service.transcript.data.TranscriptDataService;
import gov.nysenate.openleg.service.transcript.search.TranscriptSearchService;
import gov.nysenate.openleg.transcripts.TranscriptsUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/session_transcripts")
public class SessionTranscriptPageCtrl extends BaseCtrl {

    @Autowired
    private TranscriptDataService transcriptDataService;
    @Autowired
    private TranscriptSearchService transcriptSearchService;

    @RequestMapping("")
    public String viewSessionTranscripts(@RequestParam(required = false) String term,
                                         @RequestParam(required = false) String year,
                                         HttpServletRequest req,
                                         WebRequest webRequest) throws SearchException {
        term = term == null ? "" : term.trim();
        year = year == null ? String.valueOf(LocalDate.now().getYear()) : year;

        LimitOffset limitOffset = getLimitOffset(webRequest, 25);

        String sort = "_score:desc,dateTime:desc";
        String searchTerm = term.isEmpty() ? "*" : term;
        SearchResults<TranscriptId> searchResults;
        if (year.equals("Any")) {
            searchResults = transcriptSearchService.searchTranscripts(searchTerm, sort, limitOffset);
        }
        else {
            searchResults = transcriptSearchService.searchTranscripts(searchTerm, Integer.valueOf(year), sort, limitOffset);
        }

        List<SearchResultView> searchResultViews = new ArrayList<>();
        for (SearchResult<TranscriptId> sr : searchResults.getResults()){
            TranscriptView transcriptView = new TranscriptView(transcriptDataService.getTranscript(sr.getResult()));
            searchResultViews.add(new SearchResultView(transcriptView, sr.getRank(), sr.getHighlights()));
        }

        ListViewResponse<SearchResultView> results = ListViewResponse.of(searchResultViews,
                searchResults.getTotalResults(), searchResults.getLimitOffset());

        List<String> availableYears = new ArrayList<>();
        availableYears.add("Any");
        for (int i = LocalDate.now().getYear(); i >= 1993; i--) {
            availableYears.add(String.valueOf(i));
        }

        TranscriptsUi view = new TranscriptsUi(results, availableYears, String.valueOf(year), term, limitOffset);
        req.setAttribute("view", view);
        return "session_transcripts/view_session_transcripts";
    }

    @RequestMapping("/{dateTime:.*}")
    public String viewSessionTranscript(@PathVariable String dateTime,
                                        HttpServletRequest req) {
        Transcript transcript = transcriptDataService.getTranscript(new TranscriptId(dateTime));
        TranscriptView view = new TranscriptView(transcript);
        req.setAttribute("transcript", view);
        return "session_transcripts/session_transcript";
    }
}
