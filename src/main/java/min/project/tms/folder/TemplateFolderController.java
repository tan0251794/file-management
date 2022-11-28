package min.project.tms.folder;

import min.project.tms.dto.CreateSuccessResponse;
import min.project.tms.dto.IdCodeNameResponse;
import min.project.tms.dto.TemplateFolderCreateUpdateDto;
import min.project.tms.dto.UpdateSuccessResponse;
import min.project.tms.exception.ApiErrorException;
import min.project.tms.group.TemplateGroupEntity;
import min.project.tms.group.TemplateGroupRepository;
import min.project.tms.group.TemplateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping(path = "api/template_group/{templateGroupId}/template_folder/")
//public class TemplateFolderController {
//    private final TemplateFolderRepository templateFolderRepository;
//    private final TemplateGroupService templateGroupService;
//
//    @Autowired
//    public TemplateFolderController(TemplateFolderRepository templateFolderRepository, TemplateGroupService templateGroupService) {
//        this.templateFolderRepository = templateFolderRepository;
//        this.templateGroupService = templateGroupService;
//    }
//
//    @GetMapping
//    public List<IdCodeNameResponse> getTemplateFolders(@PathVariable("templateGroupId") String templateGroupId){
//        if (!TemplateGroupService.existsById(Long.valueOf(templateGroupId))) {
//            throw new ApiErrorException("templateGroupId is not exist");
//        }
//        List<TemplateFolderEntity> folders = templateFolderRepository.findFoldersByTemplateGroupId(Long.valueOf(templateGroupId));
//        List<IdCodeNameResponse> listResponse = new ArrayList<>();
//        for (TemplateFolderEntity folder: folders) {
//            listResponse.add(new IdCodeNameResponse(folder));
//        }
//        return listResponse;
//    }
//    @PostMapping
//    public CreateSuccessResponse createTemplateFolders(
//            @PathVariable("templateGroupId") Long templateGroupId,
//            @Valid @RequestBody TemplateFolderCreateUpdateDto bodyParams){
//        TemplateGroupEntity group = (TemplateGroupEntity) TemplateGroupService.findById(templateGroupId).orElse(null);
//        if (group == null) throw new ApiErrorException("Template Group Not Found");
//        TemplateFolderEntity folder = new TemplateFolderEntity(
//                bodyParams.getName(),
//                bodyParams.getCode(),
//                group
//        );
//        templateFolderRepository.save(folder);
//        return new CreateSuccessResponse(folder);
//    }
//    @PutMapping("{templateFolderId}/")
//    public UpdateSuccessResponse updateTemplateFolders(
//            @PathVariable("templateGroupId") Long templateGroupId,
//            @PathVariable("templateFolderId") Long templateFolderId,
//            @Valid @RequestBody TemplateFolderCreateUpdateDto bodyParams
//    ){
//        TemplateFolderEntity folder = getFolderForUpdateDelete(templateGroupId, templateFolderId);
//
//        folder.setName(bodyParams.getName());
//        folder.setCode(bodyParams.getCode());
//
//        templateFolderRepository.save(folder);
//        return new UpdateSuccessResponse(folder);
//    }
//
//    @DeleteMapping("{templateFolderId}/")
//    public void deleteTemplateFolders(
//            @PathVariable("templateGroupId") Long templateGroupId,
//            @PathVariable("templateFolderId") Long templateFolderId
//    ){
//        TemplateFolderEntity folder = getFolderForUpdateDelete(templateGroupId, templateFolderId);
//        templateFolderRepository.delete(folder);
//    }
//
//    private TemplateFolderEntity getFolderForUpdateDelete(
//            @PathVariable("templateGroupId") Long templateGroupId,
//            @PathVariable("templateFolderId") Long templateFolderId
//    ) {
//        TemplateGroupEntity group = (TemplateGroupEntity) templateGroupRepository.findById(templateGroupId).orElse(null);
//        if (group == null) throw new ApiErrorException("Template Group Not Found");
//
//        if (templateFolderId == null) throw new ApiErrorException("Folder Id in body params cannot be null");
//        TemplateFolderEntity folder = templateFolderRepository.findFoldersByTemplateGroupIdAndFolderId(
//                templateGroupId,
//                templateFolderId
//        ).orElse(null);
//        if (folder == null) throw new ApiErrorException("Template Folder Id is Not Found");
//
//        return folder;
//    }
//
//}
