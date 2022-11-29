package min.project.fms.dao;

import min.project.fms.model.Item;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemMapperImpl implements ItemMapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Item findByUuid(String itemUuid) {
        Query query = em.createQuery(
                        "SELECT item FROM Item item WHERE item.uuid LIKE :itemUuid");
        query.setParameter("itemUuid", itemUuid);
        return (Item) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void saveItem(Item item) {
        em.persist(item);
    }

    @Override
    public void updateById(Long id) {
        em.createQuery("UPDATE Item SET deleted_flag = true WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Item> findItemsByUuids(List<String> uuidList) {
        Query query = em.createQuery(
                "SELECT item FROM Item item WHERE item.uuid IN :uuidList");
        query.setParameter("uuidList", uuidList);
        return (List<Item>) query.getResultList();
    }
}
