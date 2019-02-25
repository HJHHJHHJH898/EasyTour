package hjh.Tips;

public class ReplyInfo {
	private String username;    //评论的人 username
    private String userid;             //评论人的id    userid
    private int tipid;   //评论的id tipid
    private String content;     //评论的内容 content
    private String status;      //评论状态 status
    private String time;  //评论时间 time
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getTipid() {
		return tipid;
	}
	public void setTipid(int tipid) {
		this.tipid = tipid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public ReplyInfo(String username, String userid, int tipid, String content, String status, String time) {
		super();
		this.username = username;
		this.userid = userid;
		this.tipid = tipid;
		this.content = content;
		this.status = status;
		this.time = time;
	}
	@Override
	public String toString() {
		return "ReplyInfo [username=" + username + ", userid=" + userid + ", tipid=" + tipid + ", content=" + content
				+ ", status=" + status + ", time=" + time + ", getUsername()=" + getUsername() + ", getUserid()="
				+ getUserid() + ", getTipid()=" + getTipid() + ", getContent()=" + getContent() + ", getStatus()="
				+ getStatus() + ", getTime()=" + getTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public ReplyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
