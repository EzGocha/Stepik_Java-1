package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService
{
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIDToProfile;

    public AccountService()
    {
        loginToProfile = new HashMap<>();
        sessionIDToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile)
    {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login)
    {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionID(String sessionID)
    {
        return sessionIDToProfile.get(sessionID);
    }

    public void addSession(String sessionID, UserProfile userProfile)
    {
        sessionIDToProfile.put(sessionID, userProfile);
    }

    public void deleteSession(String sessionID)
    {
        sessionIDToProfile.remove(sessionID);
    }
}
