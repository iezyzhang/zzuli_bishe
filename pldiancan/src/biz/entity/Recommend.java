package biz.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_recommend")
public class Recommend {
	private long id;
	private Goods goods;
	private String goodsIds;

	private List<Goods> rlist;

	@Transient
	public List<Goods> getRlist() {
		return rlist;
	}

	public void setRlist(List<Goods> rlist) {
		this.rlist = rlist;
	}

	@Transient
	public String getRecommendNames() {
		if (rlist == null) {
			return "";
		}
		String ret = "";

		for (Goods g : rlist) {
			ret += g.getName() + " | ";
		}

		return ret;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

}
