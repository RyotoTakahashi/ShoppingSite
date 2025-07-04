package jp.co.aforce.servlet.cart;

import java.util.UUID;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartUtils {
    public static String getOrCreateGuestId(HttpServletRequest request, HttpServletResponse response) {
        String guestId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("guest_id".equals(c.getName())) {
                    guestId = c.getValue();
                    break;
                }
            }
        }
        if (guestId == null) {
            guestId = UUID.randomUUID().toString();
            Cookie newCookie = new Cookie("guest_id", guestId);
            newCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            newCookie.setPath(request.getContextPath());
            response.addCookie(newCookie);
        }
        return guestId;
    }

	
}


