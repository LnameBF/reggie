package com.start01.reggie.Controller;

import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/common")
public class CommonController {


    @Value("${reggie.path}")
    private String path;


    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public R<String> ImgUpload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        //做一个文件是否存在的判断
        File file = new File(path);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        //获得原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        multipartFile.transferTo(new File(path + originalFilename));
        return R.success(multipartFile.getOriginalFilename());
    }

    /**
     * 页面数据回显
     *
     * @param filename
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam("name") String filename, HttpServletResponse response) {
        FileInputStream fis = null;
        ServletOutputStream outputStream = null;
        //读取一个数据，这个数据就是上面上传的那个
        try {
            fis = new FileInputStream(new File(path + filename));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
