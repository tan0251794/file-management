package min.project.tms.group;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import min.project.tms.base.BaseEntity;
import min.project.tms.folder.TemplateFolderEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "template_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateGroupEntity extends BaseEntity {
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "template_group_id")
    private Set<TemplateFolderEntity> folders;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private TemplateGroupEntity parent;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TemplateGroupEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public TemplateGroupEntity(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public TemplateGroupEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public TemplateGroupEntity getParent() {
        return parent;
    }

    public void setParent(TemplateGroupEntity parent) {
        this.parent = parent;
    }
}
