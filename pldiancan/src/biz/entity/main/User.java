package biz.entity.main;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements java.io.Serializable {

	// Fields

	private Long userId;
	private String uname;
	private String userName;
	private String userPassword;
	private Integer userGender;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	private String userBirth;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String uname, String userName, String userPassword, Integer userGender) {
		this.uname = uname;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
	}

	 
	@Id
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "uname", nullable = false, length = 20, unique = true)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userPassword", nullable = false, length = 150)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "userGender", nullable = false)
	public Integer getUserGender() {
		return this.userGender;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	@Column(name = "userPhone")
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "userEmail", length = 50)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "userAddress", length = 100)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "userBirth", length = 10)
	public String getUserBirth() {
		return this.userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

}