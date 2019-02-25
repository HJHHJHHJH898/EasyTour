package hjh.Tips;

public class TipsInfo {
	// id
	private int _id;
	// 作者
	private String _author;
	// 地方
	private String _where;
	//时间
	private String _time;
	private String _data;
	
	public TipsInfo(int _id, String _author, String _where, String _time, String _data) {
		super();
		this._id = _id;
		this._author = _author;
		this._where = _where;
		this._time = _time;
		this._data = _data;
	}
	public String get_data() {
		return _data;
	}
	public void set_data(String _data) {
		this._data = _data;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_author() {
		return _author;
	}
	public void set_author(String _author) {
		this._author = _author;
	}
	public String get_where() {
		return _where;
	}
	public void set_where(String _where) {
		this._where = _where;
	}
	public String get_time() {
		return _time;
	}
	public void set_time(String _time) {
		this._time = _time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_author == null) ? 0 : _author.hashCode());
		result = prime * result + ((_data == null) ? 0 : _data.hashCode());
		result = prime * result + _id;
		result = prime * result + ((_time == null) ? 0 : _time.hashCode());
		result = prime * result + ((_where == null) ? 0 : _where.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipsInfo other = (TipsInfo) obj;
		if (_author == null) {
			if (other._author != null)
				return false;
		} else if (!_author.equals(other._author))
			return false;
		if (_data == null) {
			if (other._data != null)
				return false;
		} else if (!_data.equals(other._data))
			return false;
		if (_id != other._id)
			return false;
		if (_time == null) {
			if (other._time != null)
				return false;
		} else if (!_time.equals(other._time))
			return false;
		if (_where == null) {
			if (other._where != null)
				return false;
		} else if (!_where.equals(other._where))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipsInfo [_id=" + _id + ", _author=" + _author + ", _where=" + _where + ", _time=" + _time + ", _data="
				+ _data + ", get_data()=" + get_data() + ", get_id()=" + get_id() + ", get_author()=" + get_author()
				+ ", get_where()=" + get_where() + ", get_time()=" + get_time() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	public TipsInfo(int _id, String _author, String _where, String _time) {
		super();
		this._id = _id;
		this._author = _author;
		this._where = _where;
		this._time = _time;
	}
	public TipsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
