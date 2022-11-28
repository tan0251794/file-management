package min.project.tms.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class TemplateGroupCreateUpdateDto {
    @NotNull
    @Length(min=6, max=25)
    public String name;
    @NotNull
    @Length(min=6, max=25)
    public String code;

    public Long parentId;

    public TemplateGroupCreateUpdateDto() {
    }
}
