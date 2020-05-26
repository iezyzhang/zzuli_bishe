package biz.web.action.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.Constant;
import util.DateUtil;
import util.FieldUtil;
import util.MD5;
import util.MathExtend;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;
import biz.entity.Goods;
import biz.entity.GoodsType;
import biz.entity.News;
import biz.entity.Recommend;
import biz.entity.SessionBean;
import biz.entity.UserOrderBook;
import biz.entity.main.SimpleUser;
import biz.web.service.impl.BizService;
import common.action.struts.BaseAction;

@ParentPackage("struts-default")
@Namespace("/com")
@Component
public class WebAction extends BaseAction {
	@Autowired
	private BizService service;

	private long uid;
	private String name;
	private String password;
	private static final String SIMPLEUSERCART = "SIMPLEUSERCART";

	@Action(value = "toOrder", results = { @Result(name = "toCart", type = "redirect", location = "/com/toCart.action") })
	//@RequestMapping(value = "/toOrder.do", method = RequestMethod.GET)
	public String toOrder() {
		SimpleUser user = getUser();
		if (user == null) {
			getHttpSession().setAttribute("orderMessage", "Please login to order");
			return "toCart";
		}

		List<Goods> list = (List<Goods>) getHttpSession().getAttribute(SIMPLEUSERCART);
		if (list == null || list.size() == 0) {
			getHttpSession().setAttribute("orderMessage", "没有商品");
			return "toCart";
		}
		try {
			service.addOrder(list, user);
			getHttpSession().setAttribute("orderMessage", "操作成功");
			list.clear();

		} catch (Exception e) {
			e.printStackTrace();
			getHttpSession().setAttribute("orderMessage", "操作失败");
		}
		return "toCart";
	}

	@Action(value = "toCart", results = { @Result(name = "checkout", location = "/qiantai/checkout.jsp") })
	//@RequestMapping(value = "/toCart.do", method = RequestMethod.GET)
	public String toCart() {
		SimpleUser user = getUser();
		if (user == null) {
			putRequestValue("total", 0);
			putRequestValue("size", 0);
			return "checkout";
		}
		List<Goods> list = (List<Goods>) getHttpSession().getAttribute(SIMPLEUSERCART);
		double total = 0;
		int size = 0;
		if (list != null) {
			for (Goods g : list) {
				total += g.getOffPrice() * g.getBuyAmount();
			}
			size = list.size();
		}
		total = MathExtend.round(total, 2);
		putRequestValue("total", total);
		putRequestValue("size", size);
		return "checkout";
	}

	@Action(value = "del2Cart", results = { @Result(name = "toCart", type = "redirect", location = "/com/toCart.action") })
	@RequestMapping(value = "/del2Cart.do", method = RequestMethod.GET)
	public String del2Cart() {
		Goods goods = (Goods) service.get(Goods.class, uid);
		if (goods != null) {
			List<Goods> list = (List<Goods>) getHttpSession().getAttribute(SIMPLEUSERCART);
			if (list != null) {
				list.remove(goods);
			}

		}
		return "toCart";
	}

	@Action(value = "add2Cart", results = { @Result(name = "checkout", location = "/qiantai/checkout.jsp"),
			@Result(name = "toCart", type = "redirect", location = "/com/toCart.action") })
	public String add2Cart() {
		SimpleUser user = getUser();
		if (user == null) {
			putRequestValue("total", 0);
			return "checkout";
		}
		Goods goods = (Goods) service.get(Goods.class, uid);
		if (goods != null) {
			List<Goods> list = (List<Goods>) getHttpSession().getAttribute(SIMPLEUSERCART);
			if (list == null) {
				list = new ArrayList<Goods>();
				getHttpSession().setAttribute(SIMPLEUSERCART, list);
			}
			if (list.contains(goods)) {
				Goods temp = list.get(list.indexOf(goods));
				temp.setBuyAmount(temp.getBuyAmount() + amount);
			} else {
				goods.setBuyAmount(amount);
				list.add(goods);
			}
		}
		return "toCart";
	}

	@Action(value = "userLogout", results = { @Result(name = "index", type = "redirect", location = "/com/index.action") })
	public String userLogout() {
		getHttpSession().removeAttribute("SimpleUser");
		getHttpSession().removeAttribute(SIMPLEUSERCART);
		getHttpSession().invalidate();
		return "index";
	}

	@Action(value = "toUserManage", results = { @Result(name = "main", location = "/WEB-INF/jsp/main.jsp") })
	public String toUserManage() {
		SessionBean sb = new SessionBean();
		sb.setUser(getUser());

		getHttpSession().setAttribute(Constant.SESSION_BEAN, sb);
		return "main";
	}

	@Action(value = "index", results = { @Result(name = "index", location = "/qiantai/index.jsp") })
	public String index() {
		List<Goods> list = service.findGoods(8);
		putRequestValue("list", list);
		return "index";
	}

	@Action(value = "getGoods", results = { @Result(name = "index", location = "/qiantai/single.jsp") })
	public String getGoods() {

		Goods item = (Goods) service.get(Goods.class, uid);
		putRequestValue("item", item);

		List list1 = service.findAll(GoodsType.class);
		putRequestValue("list1", list1);

		List<UserOrderBook> list = service.queryByHQL("from UserOrderBook where book.id=? order by id desc", item.getId());
		int today = 0;
		int week = 0;
		int month = 0;
		int total = list.size();

		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		String currentday = DateUtil.getCurrentTime();
		Date _weekday = DateUtil.addDays(new Date(), -7);
		String weekday = timeformat.format(_weekday);
		Date _monthday = DateUtil.addDays(new Date(), -30);
		String monthday = timeformat.format(_monthday);

		log.info(currentday);
		log.info(weekday);
		log.info(monthday);

		double totalscore = 0d;
		if (list.size() > 0) {
			int size = 0;
			for (UserOrderBook b : list) {
				String orderDate = b.getUserOrder().getAddDate();
				if (orderDate.equals(currentday)) {
					today += b.getAmount();
				}
				if (orderDate.compareTo(weekday) > 0 && orderDate.compareTo(currentday) <= 0) {
					week += b.getAmount();
				}
				if (orderDate.compareTo(monthday) > 0 && orderDate.compareTo(currentday) <= 0) {
					month += b.getAmount();
				}
				if (b.getScore() > 0) {
					totalscore += b.getScore();
					size++;
				}
			}
			if (size > 0) {
				totalscore = MathExtend.round(totalscore / size, 1);
			}
		}
		if (totalscore == 0) {
			totalscore = 5;
		}
		putRequestValue("goodsScore", totalscore);
		putRequestValue("commentList", list);
		putRequestValue("today", today);
		putRequestValue("week", week);
		putRequestValue("month", month);
		putRequestValue("total", total);

		Recommend r = service.unique("from Recommend where goods.id=?", item.getId());
		if (r != null) {
			List gl = service.queryByHQL("from Goods where id in(" + r.getGoodsIds() + ")");
			r.setRlist(gl);
			putRequestValue("Recommend", r);
		}

		return "index";
	}

	@Action(value = "list", results = { @Result(name = "queryList", location = "/qiantai/product.jsp") })
	public String list() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageNum");
			if (StringUtil.notEmpty(t)) {
				pageNum = Integer.valueOf(t);
			}
			Page p = (Page) getHttpSession().getAttribute(Constant.SESSION_PAGE + "_WEB");
			if (pageNum == 0 || p == null) {
				p = new Page();
				p.setCurrentPageNumber(1);
				p.setTotalNumber(0l);
				p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);

				// 字段名称集合
				LinkedList<String> parmnames = new LinkedList<String>();
				// 字段值集合
				LinkedList<Object> parmvalues = new LinkedList<Object>();
				// 页面参数集合
				Set parm = getHttpServletRequest().getParameterMap().entrySet();
				for (Object o : parm) {
					Entry<String, Object> e = (Entry<String, Object>) o;
					String name = e.getKey();// 页面字段名称
					if (name.startsWith("s_")) {
						String fieldValue = getHttpServletRequest().getParameter(name);// 页面字段值
						if (StringUtil.notEmpty(fieldValue)) {
							name = name.substring(2, name.length());// 实体字段名称
							parmnames.add(name);
							parmvalues.add(FieldUtil.format(Goods.class, name, fieldValue));
						}
					}
				}
				if (uid != 0) {
					parmnames.add("bookType.id");
					parmvalues.add(uid);
				}

				SearchParamBean sbean = new SearchParamBean();
				sbean.setParmnames(parmnames);
				sbean.setParmvalues(parmvalues);

				p.setConditonObject(sbean);
			} else {
				p.setCurrentPageNumber(pageNum);
			}
			Page page = null;
			page = service.find(p, Goods.class);

			List list1 = service.findAll(GoodsType.class);
			putRequestValue("list1", list1);

			getHttpSession().setAttribute(Constant.SESSION_PAGE + "_WEB", page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "userLogin", results = { @Result(name = "index", type = "redirect", location = "/com/index.action"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String userLogin() {

		String msg = "账号或者密码错误";
		try {
			MD5 md = new MD5();
			password = md.getMD5ofStr(password);
			SimpleUser user = (SimpleUser) service.findUser(SimpleUser.class.getSimpleName(), name, password);
			if (user != null) {

				getHttpSession().setAttribute("SimpleUser", user);
				msg = "Success";

			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		getHttpSession().setAttribute("loginErrorMessage", msg);
		return "login";
	}

	@Action(value = "activeAccount", results = { @Result(name = "index", type = "redirect", location = "/qiantai/index.jsp"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String activeAccount() {

		String msg = "激活账号成功";
		try {
			service.updateCheck(name);
		} catch (Exception e) {
			msg = "激活失败";
			e.printStackTrace();
		}
		getHttpSession().setAttribute("loginErrorMessage", msg);
		return "login";
	}

	private SimpleUser bean;

	@Action(value = "regUser", results = { @Result(name = "account", type = "redirect", location = "/qiantai/account.jsp"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String regUser() throws Exception {
		String msg = "";
		try {
			bean.setId(new Date().getTime());
			service.addSimpleUser(bean);

			msg = "注册成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "registration failed";
		}
		getHttpSession().setAttribute("regErrorMessage", msg);
		return "account";
	}

	@Action(value = "listNews", results = { @Result(name = "index", location = "/qiantai/listNews.jsp") })
	public String listNews() {
		List<?> list = service.queryByHQL("from News order by id desc");
		putRequestValue("list", list);
		return "index";
	}

	@Action(value = "getNews", results = { @Result(name = "index", location = "/qiantai/detailNews.jsp") })
	public String getNews() {
		News item = (News) service.get(News.class, iuid);
		putRequestValue("item", item);
		return "index";
	}

	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static String getSimpleusercart() {
		return SIMPLEUSERCART;
	}

	public SimpleUser getBean() {
		return bean;
	}

	public void setBean(SimpleUser bean) {
		this.bean = bean;
	}

	public BizService getService() {
		return service;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private SimpleUser getUser() {
		SimpleUser su = (SimpleUser) getHttpSession().getAttribute("SimpleUser");
		return su;
	}

	private int iuid;

	public int getIuid() {
		return iuid;
	}

	public void setIuid(int iuid) {
		this.iuid = iuid;
	}

}
