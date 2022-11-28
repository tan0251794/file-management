package min.project.fms.controller;

import min.project.fms.dao.ItemMapper;
import min.project.fms.model.*;
import min.project.fms.util.FileUploader;
import min.project.fms.util.RegexUtil;
import min.project.fms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        return ResponseUtil.ok(item);
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

//        itemMapper.saveItem(item);
        fileUploader.upload(file);

        Map<String, Object> response = new HashMap<>();
        response.put("uuid", item.getUuid());
        response.put("createdAt", item.getCreatedAt());
        response.put("createdBy", item.getCreatedBy());

        return ResponseUtil.ok(response);
    }

    @GetMapping(value = "download/{itemUuid}/")
    public Object download(@PathVariable("itemUuid") String itemUuid) {
        if (!RegexUtil.isUuid(itemUuid)) {
            return ResponseUtil.fail(400, "uuid invalid");
        }
        Item item = itemMapper.findByUuid(itemUuid);
        return ResponseUtil.ok(item);
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

        itemMapper.deleteById(item.getId());
        return ResponseUtil.ok();
    }

}