package min.project.tms.folder;

import min.project.tms.base.BaseEntity;
import min.project.tms.group.TemplateGroupEntity;

import javax.persistence.*;

@Entity
@Table(name = "template_folder")
public class TemplateFolderEntity extends BaseEntity {
    @Id
    @SequenceGenerator(
            name = "folder_sequence",
            sequenceName = "folder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "folder_sequence"
    )
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    private TemplateGroupEntity templateGroup;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private TemplateFolderEntity parentId;

    public TemplateFolderEntity(String name, String code, TemplateGroupEntity templateGroup) {
        this.name = name;
        this.code = code;
        this.templateGroup = templateGroup;
    }

    public TemplateFolderEntity() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
