package hjh.Tips;

public class TipscomInfo {
	int id;	//评论的id
	private String userid;
    private String author;    //author
    private String data;     //内容data
    private int pinglun;     //评论量 pinglun
    private int zan;            //点赞数
    private String where;       //对某地内容
    
    
    
	public TipscomInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipscomInfo(int id,String userid, String author, String data, int pinglun, int zan, String where) {
		super();
		this.id = id;
		this.userid = userid;
		this.author = author;
		this.data = data;
		this.pinglun = pinglun;
		this.zan = zan;
		this.where = where;
	}
	
	@Override
	public String toString() {
		return "TipscomInfo [id=" + id + ", userid=" + userid + ", author=" + author + ", data=" + data + ", pinglun="
				+ pinglun + ", zan=" + zan + ", where=" + where + ", getUserid()=" + getUserid() + ", getAuthor()="
				+ getAuthor() + ", getData()=" + getData() + ", getPinglun()=" + getPinglun() + ", getZan()=" + getZan()
				+ ", getWhere()=" + getWhere() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getPinglun() {
		return pinglun;
	}
	public void setPinglun(int pinglun) {
		this.pinglun = pinglun;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
    
}
