package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.model.ShoppingCatItem;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 购物车
@RequestMapping("/shoppingCat")
@RestController
public class ShoppingCatController {

    @RequestMapping("/all")
    public List<ShoppingCatItem> all(HttpSession session) {
        return (List<ShoppingCatItem>) session.getAttribute("shoppingCatItems");
    }

    @RequestMapping("/add")
    public Boolean add(ShoppingCatItem item, HttpSession session) {
        List<ShoppingCatItem> items = (List<ShoppingCatItem>) session.getAttribute("shoppingCatItems");
        if (items == null) {
            items = new LinkedList<>();
            session.setAttribute("shoppingCatItems", items);
        }
        items.add(0, item);
        return true;
    }

    @RequestMapping("/remove")
    public Boolean remove(Integer mid, HttpSession session) {
        if (mid == null) {
            return false;
        }
        List<ShoppingCatItem> items = (List<ShoppingCatItem>) session.getAttribute("shoppingCatItems");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getMid().equals(mid)) {
                items.remove(i);
                i--;
            }
        }
        session.setAttribute("shoppingCatItems", items);
        return true;
    }
}
