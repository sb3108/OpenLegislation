package gov.nysenate.openleg.transcripts;

import gov.nysenate.openleg.dao.base.LimitOffset;

import java.util.ArrayList;
import java.util.List;

public class PaginationUi {

    private static final int MAX_DISPLAYED_PAGES = 9;

    private LimitOffset limitOffset;
    private int total;

    public PaginationUi(LimitOffset limitOffset, int total) {
        this.limitOffset = limitOffset;
        this.total = total;
    }

    public int getTotalPages() {
        return total / limitOffset.getLimit();
    }

    public int getCurrPage() {
        return limitOffset.getOffsetEnd() / limitOffset.getLimit();
    }

    public List<PaginationPageUi> getPages() {
        List<PaginationPageUi> pageViews = new ArrayList<>();

        if (getTotalPages() <= MAX_DISPLAYED_PAGES) {
            for (int i = 1; i <= getTotalPages(); i++) {
                pageViews.add(new PaginationPageUi(i, i == getCurrPage(), pageOffset(i)));
            }
        } else {
            for (int i = getCurrPage() - 3; i <= getCurrPage() + 3; i++) {
                if (i > 0 && i <= getTotalPages()) {
                    pageViews.add(new PaginationPageUi(i, i == getCurrPage(), pageOffset(i)));
                }
            }
        }
        return pageViews;
    }

    public boolean isShowJumpToFirstPage() {
        return getCurrPage() > 4 && getTotalPages() > MAX_DISPLAYED_PAGES;
    }

    public PaginationPageUi getFirstPage() {
        return new PaginationPageUi(1, getCurrPage() == 1, pageOffset(1));
    }

    public boolean isShowJumpToLastPage() {
        return getTotalPages() - getCurrPage() > 4 && getTotalPages() > MAX_DISPLAYED_PAGES;
    }

    public PaginationPageUi getLastPage() {
        return new PaginationPageUi(getTotalPages(), getTotalPages() == getCurrPage(), pageOffset(getTotalPages()));
    }

    // has prev page, get prev page
    public boolean isHasPreviousPage() {
        return getCurrPage() > 1;
    }

    public PaginationPageUi getPreviousPage() {
        // If already on the first page, this will just link to the first page again.
        int prevPageNum = getCurrPage() == 1 ? 1 : getCurrPage() - 1;
        return new PaginationPageUi(prevPageNum, false, pageOffset(prevPageNum));
    }

    public boolean isHasNextPage() {
        return getCurrPage() < getTotalPages();
    }

    public PaginationPageUi getNextPage() {
        int nextPageNum = getCurrPage() == getTotalPages() ? getTotalPages() : getCurrPage() + 1;
        return new PaginationPageUi(nextPageNum, false, pageOffset(nextPageNum));
    }

    private int pageOffset(int pageNum) {
        return limitOffset.getLimit() * pageNum;
    }
}
