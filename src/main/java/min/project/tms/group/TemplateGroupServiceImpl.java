package min.project.tms.group;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateGroupServiceImpl implements TemplateGroupService{

    TemplateGroupRepository templateGroupRepository;

    public TemplateGroupServiceImpl(TemplateGroupRepository templateGroupRepository) {
        this.templateGroupRepository = templateGroupRepository;
    }

    @Override
    public Optional<Object> findById(Long parentId) {
        return null;
    }

    @Override
    public void saveTemplateGroup(TemplateGroupEntity group) {
        templateGroupRepository.saveTemplateGroup(group);
    }

    @Override
    public void deleteById(Long template_group_id) {

    }

    @Override
    public boolean existsById(Long template_group_id) {
        return false;
    }

    @Override
    public List<TemplateGroupEntity> findAllTemplateGroups() {
        return templateGroupRepository.findAllTemplateGroups();
    }
}
