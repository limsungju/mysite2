package kr.co.itcen.web;

public class PaginationUtil {
	private int currentPage;		// 현재 페이지
	private int totalCnt;			// 전체 게시물 수 1
	private int totalPageCnt;		// 전체 페이지 수
	private int pageSize;			// 페이징 블럭 사이즈 ex) < 1 2 3 4 5 > 에 보여질 개수
	private int listSize;			// 한 페이지에 보여질 게시글의 수
	private int startPage;			// 시작 페이지
	private int endPage;			// 마지막 페이지
	private boolean prev;			// 이전 버튼
	private boolean next;			// 다음 버튼

	public PaginationUtil(int currentPage, int totalCnt, int listSize, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		this.listSize = listSize;
		this.pageSize = pageSize;

		this.totalPageCnt = (int) Math.ceil(this.totalCnt / (double)listSize);

		if (this.totalPageCnt < this.currentPage) {
			this.currentPage = this.totalPageCnt;
		}

		this.startPage = ((this.currentPage - 1) / this.pageSize) * this.pageSize + 1;
		this.endPage = this.startPage + this.pageSize - 1;

		if (this.endPage > this.totalPageCnt) {
			this.endPage = this.totalPageCnt;
		}

		this.prev = (this.startPage > this.pageSize) ? true : false;
		this.next = (this.totalPageCnt > this.pageSize && this.endPage < this.totalPageCnt) ? true : false;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
}