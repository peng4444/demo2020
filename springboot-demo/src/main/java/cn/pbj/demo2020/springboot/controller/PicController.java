package cn.pbj.demo2020.springboot.controller;

import cn.pbj.demo2020.springboot.common.ResponseEntity;
import cn.pbj.demo2020.springboot.exception.NullOrEmptyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * @pClassName: PicController
 * @author: pengbingjiang
 * @create: 2020/9/8 15:25
 * @description: TODO
 */
@RestController
//@RequestMapping(value = "/pic")
public class PicController {
    @RequestMapping("/pic")
    @ResponseBody
    public ResponseEntity<String> pic(MultipartFile[] pictures) throws Exception {
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        long count = Arrays.asList(pictures).stream().
                map(MultipartFile::getOriginalFilename).
                filter(String::isEmpty).count();
        if (count == pictures.length){
            responseEntity.setCode(ResponseEntity.ERROR);
            throw new NullOrEmptyException("图片不能同时为空");
        }
        responseEntity.setCode(ResponseEntity.OK);
        responseEntity.setMessage("上传成功");
        return responseEntity;
    }
}
