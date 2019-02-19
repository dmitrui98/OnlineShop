package by.dmitrui98.config;


import by.dmitrui98.filter.SessionFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ApplicationInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";

    // TODO изменить формат вывода даты: "yyyy/MM/dd HH:mm:ss"
    // TODO перехватить исключения, выбрасываемые на браузер
    // TODO страница comeIn только для неаутентифицированных пользователей
    // TODO починить regex для email


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setConfigLocation("by.dmitrui98.config");
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));

        FilterRegistration.Dynamic filter = servletContext.addFilter("sessionFilter", new SessionFilter());
        filter.addMappingForUrlPatterns(null, true, "/*");

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
