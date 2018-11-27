package lab.ride.controller;

import lab.ride.dto.FileInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author cwz
 * @date 2018/11/26
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final String folder = "D:\\01-learn\\11-ssl\\tmp";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        File localFile = new File(folder, new Date().getTime() + ".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try(InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"))){
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }catch (Exception e){

        }
    }
}
