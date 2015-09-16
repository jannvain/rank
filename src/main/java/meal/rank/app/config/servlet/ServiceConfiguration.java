package meal.rank.app.config.servlet;

// import org.apache.commons.io.IOUtils;
import meal.rank.app.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.io.File;

@ComponentScan
@Configuration
//@EnableJpaRepositories
public class ServiceConfiguration {

    public static final String CRM_NAME = "crm";
    /**
     * The root directory to which all uploads for the application are uploaded.
     */
    public static final File CRM_STORAGE_DIRECTORY = new File(
            System.getProperty("user.home"), CRM_NAME);
    /**
     * Things are first uploaded by the application server to this directory. it's a sort
     * of staging directory
     */
    public static final File CRM_STORAGE_UPLOADS_DIRECTORY = new File(CRM_STORAGE_DIRECTORY, "uploads");

    @PostConstruct
    protected void setupStorage() throws Throwable {
        File[] files = {CRM_STORAGE_DIRECTORY, CRM_STORAGE_UPLOADS_DIRECTORY};
        for (File f : files) {
            if (!f.exists() && !f.mkdirs()) {
                String msg = String.format("you must create the profile photos directory ('%s') " +
                        "and make it accessible to this process. Unable to do so from this process.", f.getAbsolutePath());
                throw new RuntimeException(msg);
            }
        }
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(  JpaVendorAdapter adapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan(User.class.getPackage().getName());
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(adapter);
        return emf;
    }

    @Bean
    PlatformTransactionManager transactionManager( EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Configuration
    @Profile({"default", "test"})
    static class DefaultDataSourceConfiguration {



    }
}
