package min.project.tms.dto;

import min.project.tms.folder.TemplateFolderEntity;
import min.project.tms.group.TemplateGroupEntity;

import static min.project.tms.methods.CurrentDateTime.datetimeToString;

public class UpdateSuccessResponse {

    private Long id;
    private String updatedBy;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public UpdateSuccessResponse(TemplateGroupEntity group) {
        this.id = group.getId();
        this.updatedBy = group.getUpdatedBy();
        this.updatedAt = datetimeToString(group.getUpdatedAt());
    }

    public UpdateSuccessResponse(TemplateFolderEntity folder) {
        this.id = folder.getId();
        this.updatedBy = folder.getUpdatedBy();
        this.updatedAt = datetimeToString(folder.getUpdatedAt());
    }
}
