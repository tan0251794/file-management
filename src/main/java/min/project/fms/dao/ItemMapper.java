package min.project.fms.dao;

import min.project.fms.model.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemMapper {

    Item findByUuid(String itemUuid);

    @Transactional
    void saveItem(Item item);

    @Transactional
    void updateById(Long id);

    List<Item> findItemsByUuids(List<String> uuidList);
}
