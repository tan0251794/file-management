package min.project.tms.dto;

import min.project.tms.folder.TemplateFolderEntity;
import min.project.tms.group.TemplateGroupEntity;

import static min.project.tms.methods.CurrentDateTime.datetimeToString;

public class CreateSuccessResponse {
    private Long id;
    private String createdBy;
    private String createdAt;

    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public CreateSuccessResponse(TemplateGroupEntity group) {
        this.id = group.getId();
        this.createdBy = group.getCreatedBy();
        this.createdAt = datetimeToString(group.getCreatedAt());
    }

    public CreateSuccessResponse(TemplateFolderEntity folder) {
        this.id = folder.getId();
        this.createdBy = folder.getCreatedBy();
        this.createdAt = datetimeToString(folder.getCreatedAt());
    }
}
