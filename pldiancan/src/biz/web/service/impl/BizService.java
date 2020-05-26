package biz.web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import util.Apriori;
import util.DateUtil;
import util.MD5;
import util.Page;
import util.StringUtil;
import biz.entity.Goods;
import biz.entity.Recommend;
import biz.entity.UserOrder;
import biz.entity.UserOrderBook;
import biz.entity.main.SimpleUser;
import biz.entity.main.SysUser;
import biz.entity.main.User;
import biz.web.dao.ISysDao;
import common.service.BaseService;

@Service("bizService")
@Repository
public class BizService extends BaseService {

	@Autowired
	private ISysDao dao;

	public List queryByHQL(String hql, Object... values) {
		return dao.queryByHQL(hql, values);
	}

	public void deleteSimpleUser(Class<SimpleUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SimpleUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void addSimpleUser(SimpleUser obj) {
		User user = obj.getUser();
		user.setUserId(new Date().getTime());
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSimpleUser(SimpleUser obj) {
		SimpleUser temp = (SimpleUser) dao.get(SimpleUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	/**
	 * 添加对象
	 * 
	 * @param obj
	 */
	public void add(Object obj) {
		dao.save(obj);
	}

	/**
	 * 修改对象
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		dao.merge(obj);
	}

	/**
	 * 根据id获取对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object get(Class clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	public void deleteUser(String ids) {
		dao.deleteByIds(User.class, ids);
	}

	public void delete(Class clazz, String ids) {
		dao.deleteByIds(clazz, ids);
	}

	public Object findUser(String type, String userid, String pwd) {
		return dao.queryUser(type, userid, pwd);
	}

	public User findUser(String userid) {
		return dao.queryUser(userid);
	}

	public Page findUser(Page page) {
		return dao.list(page, "User");
	}

	public Page find(Page page, Class clazz) {
		return dao.list(page, clazz.getSimpleName());
	}

	public List findAll(Class clazz) {
		return dao.queryByHQL("from " + clazz.getSimpleName());
	}

	public void addSysUser(SysUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSysUser(SysUser obj) {
		SysUser temp = (SysUser) dao.get(SysUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	public List findAll(Class clazz, Map<String, Object> params) {
		return dao.findAll(clazz, params);
	}

	public <T> T unique(final String hql, final Object... paramlist) {
		return dao.unique(hql, paramlist);
	}

	public List<Goods> findGoods(int i) {
		return dao.queryByHQLLimit("from Goods order by id desc", 0, i);
	}

	public void updateCheck(String name) {
		dao.updateByHQL("update SimpleUser set checked=true where checkmd5=?", name);
	}

	public void addOrder(List<Goods> list, SimpleUser user) {

		UserOrder order = new UserOrder();
		order.setId(new Date().getTime());
		order.setAddDate(DateUtil.getCurrentTime());
		order.setBuyer(user);
		order.setStatus("Shipping");
		String sid = "OB" + StringUtil.format0String(5, order.getId());
		order.setSid(sid);
		dao.save(order);

		double money = 0;
		int index = 1;
		for (Goods temp : list) {
			money += temp.getOffPrice() * temp.getBuyAmount();
			UserOrderBook ob = new UserOrderBook();
			ob.setId(order.getId() + (index++));
			ob.setBook(temp);
			ob.setUserOrder(order);
			ob.setPrice(temp.getOffPrice() * temp.getBuyAmount());
			ob.setAmount(temp.getBuyAmount());
			ob.setStatus("未评价");
			dao.save(ob);
		}
		order.setMoney(money);
		dao.update(order);

	}

	@SuppressWarnings("rawtypes")
	public void updateUserOrderBook(UserOrderBook order) {
		dao.update(order);

		UserOrder temp = (UserOrder) dao.get(UserOrder.class, order.getUserOrder().getId());
		List list = dao.queryByHQL("from UserOrderBook where userOrder.id=? and status='未评价'", temp.getId());
		if (list.size() == 0) {
			temp.setStatus("Received");
			dao.update(temp);
		}

	}

	public void updateRecommend(int support, double confidence) {
		ArrayList<String> dataList = new ArrayList<>();

		List<UserOrder> orderList = dao.queryByHQL("from UserOrder");
		for (UserOrder uo : orderList) {
			List<UserOrderBook> blist = dao.queryByHQL("from UserOrderBook where userOrder.id=?", uo.getId());
			if (blist.size() > 1) {
				String temp = "";
				for (UserOrderBook b : blist) {
					temp += b.getBook().getId() + ";";
				}
				dataList.add(temp);
			}
		}

		//		dataList.add("1;2;5;");
		//		dataList.add("2;4;");
		//		dataList.add("2;3;");
		//		dataList.add("1;2;4;");
		//		dataList.add("1;3;");
		//		dataList.add("2;3;");
		//		dataList.add("1;3;");
		//		dataList.add("1;2;3;5;");
		//		dataList.add("1;2;3;");

		System.out.println("=数据集合==========");
		for (String string : dataList) {
			System.out.println(string);
		}

		Apriori apriori2 = new Apriori();
		apriori2.setCONFIDENCE(confidence);
		apriori2.setSUPPORT(support);

		System.out.println("=频繁项集==========");

		Map<String, Integer> frequentSetMap = apriori2.apriori(dataList);
		Set<String> keySet = frequentSetMap.keySet();
		for (String key : keySet) {
			System.out.println(key + " : " + frequentSetMap.get(key));
		}

		System.out.println("=关联规则==========");
		Map<String, Double> relationRulesMap = apriori2.getRelationRules(frequentSetMap);
		Set<String> rrKeySet = relationRulesMap.keySet();
		for (String rrKey : rrKeySet) {
			System.out.println(rrKey + "  :  " + relationRulesMap.get(rrKey));
		}

		Map<String, HashSet<String>> resultMap = new HashMap<String, HashSet<String>>();
		for (String rrKey : rrKeySet) {
			//System.out.println(rrKey + "  :  " + relationRulesMap.get(rrKey));
			String[] ary1 = rrKey.split("->");
			String[] left = ary1[0].substring(0, ary1[0].length() - 1).split(";");
			String[] right = ary1[1].substring(0, ary1[1].length() - 1).split(";");

			//过滤掉多个商品关联推荐
			if (left.length == 1) {
				System.out.println(Arrays.toString(left) + "  ---  " + Arrays.toString(right));
				HashSet<String> setleft = resultMap.get(left[0]);
				if (setleft == null) {
					setleft = new HashSet<String>();
					resultMap.put(left[0], setleft);
				}
				HashSet<String> setright = null;
				if (right.length == 1) {
					setright = resultMap.get(right[0]);
					if (setright == null) {
						setright = new HashSet<String>();
						resultMap.put(right[0], setright);
					}
					setright.add(left[0]);
				}
				for (String r : right) {
					setleft.add(r);
				}
			}
		}
		dao.updateByHQL("delete Recommend");
		for (String id : resultMap.keySet()) {
			HashSet<String> set = resultMap.get(id);
			String ids = "0,";
			for (String s : set) {
				ids += s + ",";
			}
			ids += "0";
			System.out.println(id + "  >>>>  " + ids);

			Goods goods = (Goods) dao.get(Goods.class, Long.valueOf(id));
			Recommend r = new Recommend();
			r.setGoods(goods);
			r.setGoodsIds(ids);
			dao.save(r);

		}

	}

}
