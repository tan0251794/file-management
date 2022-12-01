package min.project.fms.controller;

import io.minio.errors.*;
import min.project.fms.dao.ItemMapper;
import min.project.fms.model.*;
import min.project.fms.util.FileUploader;
import min.project.fms.util.RegexUtil;
import min.project.fms.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static min.project.fms.util.Constant.MAX_UPLOAD_FILE_SIZE;


@RestController
@RequestMapping(path = "api/")
public class ItemController {
    private final ItemMapper itemMapper;

    @Autowired
    FileUploader fileUploader;

    public ItemController(ItemMapper itemMapper, FileUploader fileUploader) {

        this.itemMapper = itemMapper;
        this.fileUploader = fileUploader;
    }

    @GetMapping(value = "{itemUuid}/")
    public Object detail(@PathVariable("itemUuid") String itemUuid) {
        if (!RegexUtil.isUuid(itemUuid)) {
            return ResponseUtil.fail(400, "uuid invalid");
        }
        Item item = itemMapper.findByUuid(itemUuid);
        if (item == null) {
            return ResponseUtil.fail(400, String.format("uuid = %s is not found", itemUuid));
        }
        if (item.getDeletedFlag()) {
            return ResponseUtil.fail(400, "File have been deleted");
        }
        ModelMapper mapper = new ModelMapper();
        ItemRespDto itemRespDto = mapper.map(item, ItemRespDto.class);
        return ResponseUtil.ok(itemRespDto);
    }

    @PostMapping
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String uuid = UUID.randomUUID().toString();
        Item item = new Item();
        item.setName(file.getOriginalFilename());
        item.setContentType(file.getContentType());
        item.setSize((int) file.getSize());
        item.setUuid(uuid);
        item.setCreatedBy("Admin");
        item.setUpdatedBy("Admin");
        item.setStoragePathName(String.format("%s/%s", uuid, file.getOriginalFilename()));

        /*
        * RULE:
        * - Size of file must less than or equal 3 MB.
        * */
        if (file.getSize() > MAX_UPLOAD_FILE_SIZE) {
            return ResponseUtil.fail(400, "File size too large");
        }

        itemMapper.saveItem(item);
        fileUploader.upload(item.getStoragePathName(), file);

        Map<String, Object> response = new HashMap<>();
        response.put("uuid", item.getUuid());
        response.put("createdAt", item.getCreatedAt());
        response.put("createdBy", item.getCreatedBy());

        return ResponseUtil.ok(response);
    }

    @GetMapping(value = "{itemUuid}/download/")
    public Object download(@PathVariable("itemUuid") String itemUuid) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!RegexUtil.isUuid(itemUuid)) {
            return ResponseUtil.fail(400, "uuid invalid");
        }

        Item item = itemMapper.findByUuid(itemUuid);
        if (item == null) {
            return ResponseUtil.fail(400, String.format("uuid = %s is not found", itemUuid));
        }
        if (item.getDeletedFlag()) {
            return ResponseUtil.fail(400, "File have been deleted");
        }

        String url = fileUploader.getUrl(item.getStoragePathName());
        return ResponseUtil.ok(url);
    }

    @GetMapping(value = "download/")
    public Object bulkDownload(@RequestParam("uuidList") List<String> uuidList) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        for (String itemUuid: uuidList) {
            if (!RegexUtil.isUuid(itemUuid)) {
                return ResponseUtil.fail(400, "uuid invalid");
            }
        }

        List<Item> items = itemMapper.findItemsByUuids(uuidList);

        // Cannot get file have marked as deleted.
        for (Item item: items) {
            if (item.getDeletedFlag()) {
                return ResponseUtil.fail(400, "Some of files have been deleted");
            }
        }

        List<String> urls = new ArrayList<>();
        for (Item item: items) {
            String url = fileUploader.getUrl(item.getStoragePathName());
            urls.add(url);
        }

        return ResponseUtil.ok(urls);
    }

    @DeleteMapping(value = "{itemUuid}/")
    public Object delete(@PathVariable("itemUuid") String itemUuid){
        if (!RegexUtil.isUuid(itemUuid)) {
            return ResponseUtil.fail(400, "uuid invalid");
        }

        Item item = itemMapper.findByUuid(itemUuid);
        if (item == null) {
            return ResponseUtil.fail(400, "item not existed");
        }
        
        // @TODO: Delete file on minio server. 

        // Just upload deleteFlag = true.
        itemMapper.updateById(item.getId());
        return ResponseUtil.ok();
    }

}
