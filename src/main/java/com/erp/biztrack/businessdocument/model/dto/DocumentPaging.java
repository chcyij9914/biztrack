package com.erp.biztrack.businessdocument.model.dto;

public class DocumentPaging implements java.io.Serializable {
    private static final long serialVersionUID = -1212718705347152605L;

    // 기본 페이징 필드
    private int currentPage;     // 현재 페이지
    private int start;           // 시작 행 번호
    private int end;             // 끝 행 번호
    private int limit = 10;      // 한 페이지당 게시물 수
    private int total;           // 전체 게시물 수
    private int totalPage;       // 전체 페이지 수
    private int startPage;       // 시작 페이지 번호
    private int endPage;         // 끝 페이지 번호
    private int pageBlock = 10;  // 페이지 블럭 단위 (ex: 10 페이지씩 보이기)

    // 문서 유형 (출고서 O, 세금계산서 G 등)
    private String documentTypeId;

    // 검색 관련 필드
    private String searchType;      // 검색 유형: title, client, status
    private String keyword;         // 검색 키워드
    private String approveStep;     // 결재 단계 (1차 or 2차)
    private String approveStatus;   // 결재 상태 (임시저장, 대기, 승인 등)

    // 기본 생성자
    public DocumentPaging() {}

    // 생성자: currentPage와 documentTypeId만 초기화하는 생성자
    public DocumentPaging(int currentPage, String documentTypeId) {
        this.currentPage = Math.max(currentPage, 1);
        this.documentTypeId = documentTypeId;
        this.start = (this.currentPage - 1) * limit + 1;
        this.end = this.start + limit - 1;
    }

    // 모든 필드를 포함한 생성자
    public DocumentPaging(int currentPage, int start, int end, int limit, int total,
                          String documentTypeId, String searchType, String keyword,
                          String approveStep, String approveStatus) {
        this.currentPage = currentPage;
        this.start = start;
        this.end = end;
        this.limit = limit;
        this.total = total;
        this.documentTypeId = documentTypeId;
        this.searchType = searchType;
        this.keyword = keyword;
        this.approveStep = approveStep;
        this.approveStatus = approveStatus;
    }

    // ----- Getter & Setter -----

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;

        // 현재 페이지 변경 시 start/end 자동 계산
        this.start = (this.currentPage - 1) * limit + 1;
        this.end = this.start + limit - 1;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    // 핵심 로직: 총 게시물 수를 설정하면 totalPage, startPage, endPage도 자동 계산
    public void setTotal(int total) {
        this.total = total;

        // 총 페이지 수 계산
        this.totalPage = (int) Math.ceil((double) total / limit);

        // 시작 페이지 계산
        this.startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

        // 끝 페이지 계산
        this.endPage = startPage + pageBlock - 1;

        // 끝 페이지가 총 페이지 수를 넘지 않도록 조정
        if (endPage > totalPage) {
            endPage = totalPage;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getApproveStep() {
        return approveStep;
    }

    public void setApproveStep(String approveStep) {
        this.approveStep = approveStep;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // 디버깅용
    @Override
    public String toString() {
        return "DocumentPaging [currentPage=" + currentPage + ", start=" + start + ", end=" + end
                + ", limit=" + limit + ", total=" + total + ", totalPage=" + totalPage
                + ", startPage=" + startPage + ", endPage=" + endPage + ", documentTypeId=" + documentTypeId
                + ", searchType=" + searchType + ", keyword=" + keyword + ", approveStep=" + approveStep
                + ", approveStatus=" + approveStatus + "]";
    }
}
