package min.project.tms.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TemplateGroupRepository{

    TemplateGroupEntity findTemplateGroupById(Long templateGroupId);

    List<TemplateGroupEntity> findAllTemplateGroups();

    Optional<TemplateGroupEntity> findById(Long parentId);

    void saveTemplateGroup(TemplateGroupEntity group);
}
