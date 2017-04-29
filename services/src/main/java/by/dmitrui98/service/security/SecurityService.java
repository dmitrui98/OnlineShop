package by.dmitrui98.service.security;

/**
 * Created by Администратор on 18.04.2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
