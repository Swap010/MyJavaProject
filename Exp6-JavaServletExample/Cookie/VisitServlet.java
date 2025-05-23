/*6b. Build a servlet program to create a cookie to get your name through text box and press submit
button( through HTML) to display the message by greeting Welcome back your name ! , you have
visited this page n times ( n = number of your visit ) and demonstrate the expiry of cookie also.  */

package com.cookieapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VisitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        int visitCount = 1;

        // Get cookies from request
        Cookie[] cookies = request.getCookies();
        boolean foundUser = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username") && c.getValue().equals(username)) {
                    foundUser = true;
                }
                if (c.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(c.getValue()) + 1;
                }
            }
        }

        // Set or update cookies
        Cookie nameCookie = new Cookie("username", username);
        nameCookie.setMaxAge(60); // expires in 60 seconds

        Cookie countCookie = new Cookie("visitCount", Integer.toString(visitCount));
        countCookie.setMaxAge(60); // expires in 60 seconds

        response.addCookie(nameCookie);
        response.addCookie(countCookie);

        out.println("<html><body>");
        if (foundUser) {
            out.println("<h2>Welcome back " + username + "!</h2>");
            out.println("<p>You have visited this page " + visitCount + " times.</p>");
        } else {
            out.println("<h2>Welcome " + username + "!</h2>");
            out.println("<p>This is your first visit.</p>");
        }
        out.println("<p><i>(Cookies will expire in 60 seconds)</i></p>");
        out.println("</body></html>");
    }
}
