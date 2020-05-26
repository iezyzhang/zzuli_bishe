package biz.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import biz.entity.main.SimpleUser;

@Entity
@Table(name = "t_user_order")
public class UserOrder {
	private long id;
	private SimpleUser buyer;
	private String sid;
	private String addDate;
	private double money;
	private String status;//Shipping,Shipped,Received

	@Transient
	public String getStatusStr(){
		if("Shipping".equals(status)){
			return "待发货";
		}else if("Shipped".equals(status)){
			return "已发货";
		}else if("Received".equals(status)){
			return "已完成";
		}
		return "";
	}
	 

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_id")
	public SimpleUser getBuyer() {
		return buyer;
	}

	public void setBuyer(SimpleUser buyer) {
		this.buyer = buyer;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
