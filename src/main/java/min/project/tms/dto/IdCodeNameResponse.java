package min.project.tms.dto;

import min.project.tms.folder.TemplateFolderEntity;
import min.project.tms.group.TemplateGroupEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

public class IdCodeNameResponse {

    private Long id;

    private String name;

    private String code;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public IdCodeNameResponse(TemplateGroupEntity group) {
        this.id = group.getId();
        this.name = group.getName();
        this.code = group.getCode();
    }

    public IdCodeNameResponse(TemplateFolderEntity folder) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.code = folder.getCode();
    }
}
