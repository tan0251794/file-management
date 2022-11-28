package min.project.tms.group;

import java.util.List;
import java.util.Optional;

public interface TemplateGroupService {
    Optional<Object> findById(Long parentId);

    void saveTemplateGroup(TemplateGroupEntity group);

    void deleteById(Long template_group_id);

    boolean existsById(Long template_group_id);

    List<TemplateGroupEntity> findAllTemplateGroups();
}
