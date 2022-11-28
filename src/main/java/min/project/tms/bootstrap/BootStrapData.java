package min.project.tms.bootstrap;

import min.project.tms.group.TemplateGroupEntity;
import min.project.tms.group.TemplateGroupRepository;
import org.springframework.boot.CommandLineRunner;

//@Component
//public class BootStrapData implements CommandLineRunner {
//    private final TemplateGroupRepository templateGroupRepository;
//
//    public BootStrapData(TemplateGroupRepository templateGroupRepository) {
//        this.templateGroupRepository = templateGroupRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        TemplateGroupEntity new_template_group = new TemplateGroupEntity("abc", "123");
//        templateGroupRepository.save(new_template_group);
//
//        TemplateGroupEntity new_template_group_2 = new TemplateGroupEntity("ert", "156");
//        templateGroupRepository.save(new_template_group_2);
//
//        System.out.println("group count: " + templateGroupRepository.count());
//    }
//}
