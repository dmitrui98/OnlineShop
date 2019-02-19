package by.dmitrui98.filter;

import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Администратор on 17.11.2018.
 */
@Log4j
public class SessionFilter implements Filter {

    private SessionFactory sessionFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext());
        this.sessionFactory = ctx.getBean(SessionFactory.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        sessionFactory.openSession();
        filterChain.doFilter(servletRequest, servletResponse);
        try {
            sessionFactory.getCurrentSession().close();
        } catch (HibernateException ex) {
        }
    }

    @Override
    public void destroy() {

    }
}
