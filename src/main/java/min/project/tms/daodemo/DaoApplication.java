package min.project.tms.daodemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import min.project.tms.group.TemplateGroupEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;


public class DaoApplication {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoConfiguration.class);
        AccountDAO theDAO = context.getBean("accountDAO", AccountDAO.class);
//        theDAO.addAccount(123);
//        boolean bo = theDAO.createAccount();

        ObjectMapper mapper = new ObjectMapper();

        TemplateGroupEntity myGroup = mapper.readValue(ResourceUtils.getFile("classpath:sample.json"), TemplateGroupEntity.class);
        System.out.println(myGroup.getId());

        context.close();
    }
}
