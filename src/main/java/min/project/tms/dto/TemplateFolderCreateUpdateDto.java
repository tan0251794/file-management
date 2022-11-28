package min.project.tms.dto;

public class TemplateFolderCreateUpdateDto {
    private Long id;
    private String name;
    private String code;
    private Long templateGroupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTemplateGroupId() {
        return templateGroupId;
    }

    public void setTemplateGroupId(Long templateGroupId) {
        this.templateGroupId = templateGroupId;
    }

    public TemplateFolderCreateUpdateDto(String name, String code, Long templateGroupId) {
        this.name = name;
        this.code = code;
        this.templateGroupId = templateGroupId;
    }

    public TemplateFolderCreateUpdateDto() {
    }
}
