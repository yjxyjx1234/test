package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /*
     * 分页查询
     *
     * */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        /*类别，也没有o的cid啊  难怪可以传入null 不传也没事*/
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        /*当前页码*/
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        /*每页显示条数*/
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            currentPage = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }


        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        System.out.println("我进了后台");

        /*区别就是要不要返回json 返回有什么用*/
        writeValue(pb, response);

    }

    /*rid查询旅游详细信息*/
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        Route route = routeService.findOne(rid);
        writeValue(route,response);
    }


    /*收藏*/
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user!=null){
            uid=user.getUid();

        }else {
            uid = 0;
        }

        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            return;
        }else {
            uid=user.getUid();
        }

        favoriteService.add(rid,uid);
    }




}
