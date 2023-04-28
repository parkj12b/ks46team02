package ks46team02.customerservice.dto;

public class PageDto {
	private int min;//최소 페이지 번호
	private int max;//최대 페이지 번호
	private int prevPage;//이전버튼의 페이지 번호
	private int nextPage;//다음버튼의 페이지 번호
	private int totalPageCnt;//전체 페이지 개수
	private int currentPage;//현재 페이지 번호
	
	//전체글 개수, 현재 페이지 번호, 페이지당 글의 개수, 페이지 버튼의 개수
	public PageDto(int totalcnt, int currentPage, int pageListCnt, int paginationCnt) {
		
		//현재 페이지 번호
		this.currentPage = currentPage;
		
		//전체 페이지 개수
		totalPageCnt = totalcnt / pageListCnt;
		if(totalcnt % pageListCnt > 0) {
			totalPageCnt++;
		}
		
		min = ((currentPage - 1) / pageListCnt) * pageListCnt + 1;
		max = min + paginationCnt - 1;
		
		if(max > totalPageCnt) {
			max = totalPageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		
		if(nextPage > totalPageCnt) {
			nextPage = totalPageCnt;
		}
		
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public int getCurrentPage() {
		return currentPage;
	}
}

