package min.project.fms.dao;

import min.project.fms.model.Item;
import org.springframework.transaction.annotation.Transactional;

public interface ItemMapper {

    Item findByUuid(String itemUuid);

    @Transactional
    void saveItem(Item item);

    @Transactional
    void deleteById(Long id);
}
