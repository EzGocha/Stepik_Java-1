package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SingInServlet extends HttpServlet
{
    private static final String CONTENT_TYPE_TEXT = "text/html";

    private final AccountService profileService;

    public SingInServlet(AccountService profileService)
    {
        this.profileService = profileService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null)
        {
            response.setContentType(CONTENT_TYPE_TEXT);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = profileService.getUserByLogin(login);
        if (profile == null || !profile.getPassword().equals(password))
        {
            response.setContentType(CONTENT_TYPE_TEXT);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        profileService.addSession(request.getSession().getId(), profile);
        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}