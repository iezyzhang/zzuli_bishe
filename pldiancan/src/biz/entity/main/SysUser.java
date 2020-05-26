package biz.entity.main;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_sysuser")
public class SysUser implements java.io.Serializable {

	// Fields

	private long id;
	private User user;

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/** default constructor */
	public SysUser() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}