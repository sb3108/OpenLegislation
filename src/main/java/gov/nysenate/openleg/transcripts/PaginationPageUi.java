package gov.nysenate.openleg.transcripts;

public class PaginationPageUi {

    private int pageNumber;
    private boolean isCurrent;
    private int pageOffset; // The offset needed to view this page's results

    public PaginationPageUi(int pageNumber, boolean isCurrent, int pageOffset) {
        this.pageNumber = pageNumber;
        this.isCurrent = isCurrent;
        this.pageOffset = pageOffset;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public int getPageOffset() {
        return pageOffset;
    }
}
