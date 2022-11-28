package min.project.tms.folder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TemplateFolderRepository extends CrudRepository<TemplateFolderEntity, Long> {
    @Query("SELECT f FROM TemplateFolderEntity f WHERE f.templateGroup.id = :templateGroupId")
    List<TemplateFolderEntity> findFoldersByTemplateGroupId(@Param("templateGroupId") Long templateGroupId);

    @Query("SELECT f FROM TemplateFolderEntity f WHERE f.templateGroup.id = :templateGroupId AND f.id = :templateFolderId")
    Optional<TemplateFolderEntity> findFoldersByTemplateGroupIdAndFolderId(
            @Param("templateGroupId") Long templateGroupId,
            @Param("templateFolderId") Long templateFolderId
    );
}
