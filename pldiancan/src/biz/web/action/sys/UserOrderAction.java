package biz.web.action.sys;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;
import biz.entity.UserOrder;
import biz.entity.UserOrderBook;
import biz.entity.main.SimpleUser;
import biz.web.service.impl.BizService;

import com.opensymphony.xwork2.ModelDriven;

import common.action.struts.BaseAction;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class UserOrderAction extends BaseAction implements ModelDriven<UserOrder> {
	@Autowired
	private BizService service;

	private long uid;
	private UserOrder bean = new UserOrder();

	@Action(value = "add2UserOrder", results = { @Result(name = "add2", location = "/WEB-INF/jsp/sys/addUserOrder.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getUserOrder", results = { @Result(name = "getOne", location = "/WEB-INF/jsp/sys/modifyUserOrder.jsp") })
	public String get() {
		try {
			UserOrder temp = (UserOrder) service.get(UserOrder.class, uid);
			putRequestValue("modifybean", temp);

			List<?> list = service.queryByHQL("from UserOrderBook where userOrder.id=?", temp.getId());
			putRequestValue("list", list);

			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "getMyOrder", results = { @Result(name = "getOne", location = "/WEB-INF/jsp/sys/modifyMyOrder.jsp") })
	public String getMyOrder() {
		try {
			UserOrderBook temp = (UserOrderBook) service.get(UserOrderBook.class, uid);
			putRequestValue("modifybean", temp);

			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteUserOrder")
	public String delete() {
		try {
			service.delete(UserOrder.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "操作成功.", "mainquery");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	private UserOrderBook userOrderBook;

	@Action(value = "updateUserOrder")
	public String update() {
		try {
			UserOrder order = (UserOrder) service.get(UserOrder.class, bean.getId());
			order.setStatus("Shipped");
			service.update(order);
			MessageUtil.addMessage(getHttpServletRequest(), "操作成功.");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "updateUserOrderBook")
	public String updateUserOrderBook() {
		try {
			UserOrderBook order = (UserOrderBook) service.get(UserOrderBook.class, userOrderBook.getId());
			order.setScore(userOrderBook.getScore());
			order.setComment(userOrderBook.getComment());
			order.setStatus("已评价");

			service.updateUserOrderBook(order);
			MessageUtil.addMessage(getHttpServletRequest(), "操作成功.");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "addUserOrder")
	public String add() {
		try {
			service.add(bean);
			bean.setId(new Date().getTime());
			MessageUtil.addMessage(getHttpServletRequest(), "操作成功.");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "queryUserOrder", results = { @Result(name = "queryList", location = "/WEB-INF/jsp/sys/listUserOrder.jsp") })
	public String query() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageNum");
			if (StringUtil.notEmpty(t)) {
				pageNum = Integer.valueOf(t);
			}
			Page p = (Page) getHttpSession().getAttribute(Constant.SESSION_PAGE);
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
							parmvalues.add(FieldUtil.format(UserOrder.class, name, fieldValue));
						}
					}
				}

				SearchParamBean sbean = new SearchParamBean();
				sbean.setParmnames(parmnames);
				sbean.setParmvalues(parmvalues);

				p.setConditonObject(sbean);
			} else {
				p.setCurrentPageNumber(pageNum);
			}
			Page page = null;
			page = service.find(p, UserOrder.class);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "queryMyOrder", results = { @Result(name = "queryList", location = "/WEB-INF/jsp/sys/listMyOrder.jsp") })
	public String queryMyOrder() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageNum");
			if (StringUtil.notEmpty(t)) {
				pageNum = Integer.valueOf(t);
			}
			Page p = (Page) getHttpSession().getAttribute(Constant.SESSION_PAGE);
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
							parmvalues.add(FieldUtil.format(UserOrderBook.class, name, fieldValue));
						}
					}
				}
				SimpleUser user = (SimpleUser) getSessionUser();
				parmnames.add("userOrder.buyer.id");
				parmvalues.add(user.getId());

				SearchParamBean sbean = new SearchParamBean();
				sbean.setParmnames(parmnames);
				sbean.setParmvalues(parmvalues);

				p.setConditonObject(sbean);
			} else {
				p.setCurrentPageNumber(pageNum);
			}
			Page page = null;
			page = service.find(p, UserOrderBook.class);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public UserOrder getModel() {
		return bean;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public UserOrderBook getUserOrderBook() {
		return userOrderBook;
	}

	public void setUserOrderBook(UserOrderBook userOrderBook) {
		this.userOrderBook = userOrderBook;
	}

}
