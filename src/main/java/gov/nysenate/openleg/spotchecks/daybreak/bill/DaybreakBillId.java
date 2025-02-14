package gov.nysenate.openleg.spotchecks.daybreak.bill;

import com.google.common.base.MoreObjects;
import gov.nysenate.openleg.legislation.bill.BaseBillId;

import java.time.LocalDate;

/** Serves as an identifier for daybreak fragments
 */
public class DaybreakBillId {

    /** The Id of the fragment's bill */
    private BaseBillId baseBillId;

    /** The date of the Daybreak Report that generated the fragment*/
    private LocalDate reportDate;

    /** --- Constructors --- */

    public DaybreakBillId(BaseBillId baseBillId, LocalDate reportDate) {
        this.baseBillId = baseBillId;
        this.reportDate = reportDate;
    }

    /** --- Overrides --- */

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("baseBillId", baseBillId)
                .add("reportDate", reportDate)
                .toString();
    }

    /** --- Getters/Setters --- */

    public BaseBillId getBaseBillId() {
        return baseBillId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
