package by.dmitrui98.initialization;


import by.dmitrui98.config.WebConfig;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ApplicationInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";
    private static final Logger logger = Logger.getLogger(ApplicationInitializer.class);

    // TODO перевести время на час вперед
    // TODO изменить формат вывода даты: "yyyy/MM/dd HH:mm:ss"
    // TODO перехватить исключения, выбрасываемые на браузер
    // TODO страница comeIn только для неаутентифицированных пользователей


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        logger.debug("The configuration of the encoding filter...");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
        logger.debug("The configuration of the encoding filter completed.");

        logger.debug("The configuration of the context...");
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        //ctx.register(DatabaseConfig.class);
        //ctx.register(SecurityConfig.class);
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));
        logger.debug("The configuration of the context completed.");

        logger.debug("The configuration of the servlet...");
        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        logger.debug("The configuration of the servlet completed.");
    }

}
