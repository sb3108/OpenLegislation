package gov.nysenate.openleg.spotchecks.scraping.lrs.agenda;

import gov.nysenate.openleg.spotchecks.model.ActiveListHTMLParser;
import gov.nysenate.openleg.spotchecks.model.ActiveListSpotcheckReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kyle on 12/8/14.
 */
@Service
public class ActiveListScrapingService  {
    ArrayList<ActiveListSpotcheckReference> actList = new ArrayList<>();
    private static final String directoryName = "/data/openleg/scraped/CALENDAR/";
    @Autowired
    SqlActiveListReferenceDAO dao;

    public void main () throws Exception{
        //scrape();
        parse();
        addToDatabase();
    }

    //would just add more files to that directory.set up new directory for each scrape?
    public void parse() throws Exception{
        File dir = new File(directoryName);
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                //just for Active Lists
                if (file.toString().contains("active_list"))
                    actList.add(ActiveListHTMLParser.getSpotcheckReference(file));
            }
        }
    }
    public void addToDatabase(){

        for (ActiveListSpotcheckReference act : actList)
            dao.addCalendarReference(act);
    }

}









