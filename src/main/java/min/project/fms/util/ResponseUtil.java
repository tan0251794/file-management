package min.project.fms.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "success");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "success");
        obj.put("data", data);
        return obj;
    }

// github.pagehelper.Page
//    public static Object okList(List list) {
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("list", list);
//
//        if (list instanceof Page) {
//            Page page = (Page) list;
//            data.put("total", page.getTotal());
//            data.put("page", page.getPageNum());
//            data.put("limit", page.getPageSize());
//            data.put("pages", page.getPages());
//        } else {
//            data.put("total", list.size());
//            data.put("page", 1);
//            data.put("limit", list.size());
//            data.put("pages", 1);
//        }
//
//        return ok(data);
//    }
//
//    public static Object okList(List list, List pagedList) {
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("list", list);
//
//        if (pagedList instanceof Page) {
//            Page page = (Page) pagedList;
//            data.put("total", page.getTotal());
//            data.put("page", page.getPageNum());
//            data.put("limit", page.getPageSize());
//            data.put("pages", page.getPages());
//        } else {
//            data.put("total", pagedList.size());
//            data.put("page", 1);
//            data.put("limit", pagedList.size());
//            data.put("pages", 1);
//        }
//
//        return ok(data);
//    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", -1);
        obj.put("errmsg", "error");
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object fail(int errno, String errmsg, String data) {
        Map<String, Object> obj = new HashMap<String, Object>(3);
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        obj.put("data", data);
        return obj;
    }

    public static Object badArgument() {
        return fail(401, "invalid argument");
    }

    public static Object badArgumentValue() {
        return fail(402, "invalid argument value");
    }

    public static Object unlogin() {
        return fail(501, "please login");
    }

    public static Object serious() {
        return fail(502, "internal server error");
    }

    public static Object unsupport() {
        return fail(503, "unsupport service");
    }

    public static Object updatedDateExpired() {
        return fail(504, "update date expired");
    }

    public static Object updatedDataFailed() {
        return fail(505, "update failed");
    }

    public static Object unauthz() {
        return fail(506, "unauthorized");
    }
}