/*6b. Build a servlet program to create a cookie to get your name through text box and press submit
button( through HTML) to display the message by greeting Welcome back your name ! , you have
visited this page n times ( n = number of your visit ) and demonstrate the expiry of cookie also. */

package com.cookieservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {

    // Handle GET requests
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check for a submitted username
        String userName = request.getParameter("userName");
        if (userName != null && !userName.isEmpty()) {
            // Create and set the user cookie
            Cookie userCookie = new Cookie("user", userName);
            userCookie.setMaxAge(60); // 1 minute expiry
            userCookie.setPath("/");
            response.addCookie(userCookie);

            // Also create a visit count cookie
            Cookie countCookie = new Cookie("visitCount", "1");
            countCookie.setMaxAge(60);
            countCookie.setPath("/");
            response.addCookie(countCookie);
        }

        // Retrieve existing cookies
        Cookie[] cookies = request.getCookies();
        String existingUser = null;
        int visitCount = 1;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    existingUser = cookie.getValue();
                }
                if (cookie.getName().equals("visitCount")) {
                    try {
                        visitCount = Integer.parseInt(cookie.getValue()) + 1;
                        // Update the cookie
                        cookie.setValue(String.valueOf(visitCount));
                        cookie.setMaxAge(60);
                        response.addCookie(cookie);
                    } catch (NumberFormatException e) {
                        visitCount = 1;
                    }
                }
            }
        }

        // Build the HTML response
        out.println("<html>");
        out.println("<head><title>Cookie Example</title></head>");
        out.println("<body>");

        if (existingUser != null) {
            out.println("<font color='blue'><h2>Welcome back, " + existingUser + "!</h2></font>");
            out.println("<font color='magenta'><h2>You have visited this page " + visitCount + " times!</h2></font>");
            out.println("<form action='CookieServlet' method='post'>");
            out.println("<input type='submit' value='Logout'>");
            out.println("</form>");
        } else {
            out.println("<h2>Welcome Guest!</h2>");
            out.println("<form action='CookieServlet' method='get'>");
            out.println("Enter your name: <input type='text' name='userName'>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
        }

        out.println("</body></html>");
    }

    // Handle POST requests for logout
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidate both cookies
        Cookie userCookie = new Cookie("user", "");
        userCookie.setMaxAge(0);
        userCookie.setPath("/");

        Cookie countCookie = new Cookie("visitCount", "");
        countCookie.setMaxAge(0);
        countCookie.setPath("/");

        response.addCookie(userCookie);
        response.addCookie(countCookie);

        response.sendRedirect("CookieServlet");
    }
}
