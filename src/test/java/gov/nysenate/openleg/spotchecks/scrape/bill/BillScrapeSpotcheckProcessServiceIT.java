package gov.nysenate.openleg.spotchecks.scrape.bill;

import gov.nysenate.openleg.BaseTests;
import gov.nysenate.openleg.config.annotation.IntegrationTest;
import gov.nysenate.openleg.legislation.bill.BaseBillId;
import gov.nysenate.openleg.spotchecks.scraping.lrs.bill.BillScrapeSpotcheckProcessService;
import gov.nysenate.openleg.spotchecks.scraping.lrs.bill.ScrapeQueuePriority;
import gov.nysenate.openleg.spotchecks.scraping.lrs.bill.SqlFsBillScrapeReferenceDao;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class BillScrapeSpotcheckProcessServiceIT extends BaseTests {

    @Autowired
    BillScrapeSpotcheckProcessService procService;

    @Autowired
    SqlFsBillScrapeReferenceDao dao;

    @Test
    public void queueThenProcessTest() {
        BaseBillId billId = new BaseBillId("S5513", 2015);
        dao.addBillToScrapeQueue(billId, ScrapeQueuePriority.MANUAL_ENTRY.getPriority());
        assertEquals(1, procService.collate());
        assertEquals(1, procService.ingest());
    }
}


