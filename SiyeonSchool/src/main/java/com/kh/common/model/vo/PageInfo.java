package com.kh.common.model.vo;

public class PageInfo {

	private int listCount;	// 현재 총 게시글 개수
	private int cPage;		// 현재 페이지
	private int pageLimit;	// 페이징바 페이지 최대개수
	private int boardLimit; // 한 페이지에서 보여질 게시글 최대 개수
	private int maxPage;	// 가장 마지막 페이지
	private int startPage;	// 페이징바의 시작수
	private int endPage;	// 페이징바의 끝수
	
	public PageInfo() {}

	public PageInfo(int listCount, int cPage, int pageLimit, int boardLimit, int maxPage, int startPage, int endPage) {
		super();
		this.listCount = listCount;
		this.cPage = cPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getcPage() {
		return cPage;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
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

	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", cPage=" + cPage + ", pageLimit=" + pageLimit + ", maxPage="
				+ maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
