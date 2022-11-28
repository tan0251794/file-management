package min.project.tms.group;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Repository
public class TemplateGroupRepositoryImpl implements TemplateGroupRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TemplateGroupEntity findTemplateGroupById(Long templateGroupId) {
        return null;
    }

    @Override
    public List findAllTemplateGroups() {
        return this.em.createQuery(
                "SELECT g FROM TemplateGroupEntity g ORDER BY g.id"
        ).getResultList();
    }

    @Override
    public Optional<TemplateGroupEntity> findById(Long parentId) {
        return Optional.empty();
    }

    @Override
    public void saveTemplateGroup(TemplateGroupEntity group) {
        this.em.persist(group);
    }
}
