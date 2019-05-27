package cn.itsource.meijia.controller;

import cn.itsource.meijia.util.AjaxResult;
import cn.itsource.meijia.util.FastDfsApiOpr;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/file/upload")
    public AjaxResult fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            //获取到文件后缀
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            //调用上传的工具类
            String fileId = FastDfsApiOpr.upload(file.getBytes(), extension);
            //将远端的传来的值保存到数据中，用来保存到数据库
            return AjaxResult.me().setData(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败！");
        }
    }

    @GetMapping("/file/delete")
    public AjaxResult deleteFile(String fileId){
        try {
            //拆分组名和其他
            String tempFile = fileId.substring(1);
            String group = tempFile.substring(0, tempFile.indexOf("/"));
            String name = tempFile.substring(tempFile.indexOf("/")+1);
            FastDfsApiOpr.delete(group,name);
            return AjaxResult.me().setData(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败!");
        }
    }


}
