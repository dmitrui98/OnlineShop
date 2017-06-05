package by.dmitrui98.initialization;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import by.dmitrui98.config.SecurityConfig;
import org.springframework.security.web.debug.DebugFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;


// автоматически добавляет the springSecurityFilterChain Filter for every URL
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {


}
