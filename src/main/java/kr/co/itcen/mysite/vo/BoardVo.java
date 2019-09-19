package kr.co.itcen.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String contents;
	private Integer hit;
	private String regDate;
	private Integer gNo;
	private Integer oNo;
	private Integer depth;
	private Long uNo;
	private String uName;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getgNo() {
		return gNo;
	}

	public void setgNo(Integer gNo) {
		this.gNo = gNo;
	}

	public Integer getoNo() {
		return oNo;
	}

	public void setoNo(Integer oNo) {
		this.oNo = oNo;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Long getuNo() {
		return uNo;
	}

	public void setuNo(Long uNo) {
		this.uNo = uNo;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", regDate="
				+ regDate + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", uNo=" + uNo + ", uName=" + uName
				+ "]";
	}

}
