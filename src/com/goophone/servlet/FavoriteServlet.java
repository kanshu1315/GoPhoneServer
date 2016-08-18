package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.goophone.dao.FavoriteDao;
import com.goophone.dao.impl.FavoriteDaoImpl;
import com.goophone.enity.Favorite;
import com.goophone.enity.ResponseObject;

public class FavoriteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3659007351795577042L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		// 鑾峰彇璇锋眰鍙傛暟
		String flag = request.getParameter("flag");
		String userPhone = request.getParameter("userphone");
		String productId = request.getParameter("productid");
		System.out.println("flag---"+flag);
		//
		FavoriteDao favoriteDao = new FavoriteDaoImpl();

		
		ResponseObject result = null;
		if (flag.equals("chang_favorite")) {// 鍙樻洿鍏虫敞鐘舵�
			List<Favorite> list = favoriteDao.getFavoriteList(userPhone);
			if (list != null && list.size() > 0) {
				for (Iterator it = list.iterator(); it.hasNext();) {// 瀵筶ist杩涜閬嶅巻
					Favorite favorite = (Favorite) it.next();
					if (productId.equals(favorite.getProductId())) {// 濡傛灉宸茬粡瀛樺湪锛岄偅涔堣鏄庣敤鎴疯鍙栨秷鍏虫敞
						favoriteDao.deleteAFavorite(userPhone, productId);// 鍒犻櫎鐩稿簲璁板綍
						result = new ResponseObject(1, "鍙栨秷鎴愬姛锛�");
					}else {
						favoriteDao.addAFavorite(userPhone, productId);
						result = new ResponseObject(0, "鍏虫敞鎴愬姛锛�");
					}
				}
				

			} else {
				favoriteDao.addAFavorite(userPhone, productId);
				result = new ResponseObject(0, "鍏虫敞鎴愬姛锛�");
			}
			
		}else if (flag.equals("get_favorite")) {// 鑾峰彇鎵�湁鍏虫敞
			
			List<Favorite> list = favoriteDao.getFavoriteList(userPhone);
			if (list != null && list.size() > 0) {
				result = new ResponseObject(1, list);
				System.out.println("get_favorite------");
			}else {
				result = new ResponseObject(0, "鎮ㄨ繕娌℃湁鍏虫敞鐨勫晢鍝侊紒");
				
				System.out.println("get_favorite------"+"鎮ㄨ繕娌℃湁鍏虫敞鐨勫晢鍝侊紒");
			}

		} 
		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();
	}

}
