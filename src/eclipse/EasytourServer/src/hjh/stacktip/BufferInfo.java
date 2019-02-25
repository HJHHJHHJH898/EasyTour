package hjh.stacktip;

public class BufferInfo {
	private int tipid;
	private String myfrom;
	private String myto;
	private String content;
	@Override
	public String toString() {
		return "StackInfo [tipid=" + tipid + ", from=" + myfrom + ", to=" + myto + ", content=" + content + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getTipid() {
		return tipid;
	}
	public void setTipid(int tipid) {
		this.tipid = tipid;
	}
	public String getFrom() {
		return myfrom;
	}
	public void setFrom(String from) {
		this.myfrom = from;
	}
	public String getTo() {
		return myto;
	}
	public void setTo(String to) {
		this.myto = to;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BufferInfo(int tipid, String from, String to, String content) {
		super();
		this.tipid = tipid;
		this.myfrom = from;
		this.myto = to;
		this.content = content;
	}
	public BufferInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
