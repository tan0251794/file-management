package min.project.tms.group;

import min.project.tms.dto.CreateSuccessResponse;
import min.project.tms.dto.IdCodeNameResponse;
import min.project.tms.dto.TemplateGroupCreateUpdateDto;
import min.project.tms.dto.UpdateSuccessResponse;
import min.project.tms.exception.ApiErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/template_group/")
public class TemplateGroupController {
    private final TemplateGroupService templateGroupService;

    public TemplateGroupController(TemplateGroupService templateGroupService) {
        this.templateGroupService = templateGroupService;
    }

    @GetMapping
    public List<IdCodeNameResponse> getTemplateGroups() {
        List<TemplateGroupEntity> groups = templateGroupService.findAllTemplateGroups();

        List<IdCodeNameResponse> listResponse = new ArrayList<>();
        for (TemplateGroupEntity group: groups) {
            listResponse.add(new IdCodeNameResponse(group));
        }
        return listResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public CreateSuccessResponse createTemplateGroup(@Valid @RequestBody TemplateGroupCreateUpdateDto bodyParams){
        TemplateGroupEntity group = new TemplateGroupEntity(
                bodyParams.name,
                bodyParams.code
        );
        if (bodyParams.parentId != null) {
            TemplateGroupEntity parentGroup = (TemplateGroupEntity) templateGroupService.findById(bodyParams.parentId).orElse(null);
            if (parentGroup == null) {
                throw new ApiErrorException("Parent Template Group Not Found");
            }
            group.setParent(parentGroup);
        }
        group.setCreatedBy("Admin");
        templateGroupService.saveTemplateGroup(group);

        return new CreateSuccessResponse(group);
    }

    @PutMapping(value = "{template_group_id}/")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdateSuccessResponse updateTemplateGroup(
            @PathVariable("template_group_id") Long template_group_id,
            @Valid @RequestBody TemplateGroupCreateUpdateDto bodyParams
    ){
        TemplateGroupEntity group = (TemplateGroupEntity) templateGroupService.findById(template_group_id).orElse(null);
        if (group == null) throw new ApiErrorException("Template Group Not Found");
        group.setName(bodyParams.name);
        group.setCode(bodyParams.code);
        group.setUpdatedBy("Admin");
        templateGroupService.saveTemplateGroup(group);
        return new UpdateSuccessResponse(group);
    }

    @DeleteMapping(value = "{template_group_id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplateGroup(@PathVariable("template_group_id") Long template_group_id){
        TemplateGroupEntity group = (TemplateGroupEntity) templateGroupService.findById(template_group_id).orElse(null);
        if (group == null) throw new ApiErrorException("Template Group Not Found");
        templateGroupService.deleteById(template_group_id);
    }

}