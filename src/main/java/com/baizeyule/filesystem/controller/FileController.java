package com.baizeyule.filesystem.controller;

import cn.hutool.core.codec.Base62;
import com.baizeyule.filesystem.common.R;
import com.baizeyule.filesystem.entity.FileEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RequestMapping("/file")
@RestController
public class FileController {

    @Value("${file.rootDirectory}")
    private String rootDirectory;

    @GetMapping("/getList")
    public R getList(String path){
        File file = new File(path == null? rootDirectory : Base62.decodeStr(path));
        if(file.isDirectory()){
            List<FileEntity> pathList = new ArrayList<>();
            for (String fileName : file.list()) {
                File f = new File(file.getPath() + "/" + fileName);
                FileEntity e = new FileEntity();
                e.setFineName(fileName);
                e.setPath(Base62.encode(f.getPath()));
                e.setIsDirectory(f.isDirectory());
                e.setLastModified(new Date(f.lastModified()));
                if(fileName.lastIndexOf(".") != -1){
                    e.setFileSuffix(fileName.substring(fileName.lastIndexOf(".") + 1));
                }
                pathList.add(e);
            }
            //时间倒序
            pathList.sort(Comparator.comparing(FileEntity::getLastModified).reversed());
            return R.ok(pathList);
        }else{
            return R.failed("不是目录");
        }
    }

    @GetMapping("/download")
    public String fileDownLoad(HttpServletResponse response, String path) throws UnsupportedEncodingException {
        path = Base62.decodeStr(path);
        File file = new File(path);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "下载失败";
        }
        return "下载成功";
    }

    @PostMapping("/upload")
    public R httpUpload(MultipartFile file,String path) throws IOException {
        if(path.equals("undefined")){
            path = rootDirectory;
        }else{
            path = Base62.decodeStr(path);
        }

        String fileName = file.getOriginalFilename();  // 文件名
        File dest = new File(path +'/'+ fileName);
        file.transferTo(dest);
        return R.ok();
    }


    @PostMapping("/delete")
    public R delete(String path) throws IOException {
        path = Base62.decodeStr(path);
        boolean b = Files.deleteIfExists(Paths.get(path));
        if(!b){
            return R.ok("文件不存在");
        }
        return R.ok();
    }
}
