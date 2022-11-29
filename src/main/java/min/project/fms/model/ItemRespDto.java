package min.project.fms.model;

import java.util.Date;

public class ItemRespDto {
    private String name;
    private String contentType;
    private Integer size;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;

    public ItemRespDto(String name, String contentType, Integer size, String createdBy, String updatedBy, Date createdAt, Date updatedAt) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ItemRespDto(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public Integer getSize() {
        return size;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}