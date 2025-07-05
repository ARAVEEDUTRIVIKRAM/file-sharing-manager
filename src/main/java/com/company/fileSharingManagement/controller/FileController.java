package com.company.fileSharingManagement.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.fileSharingManagement.model.FileModel;
import com.company.fileSharingManagement.service.FileService;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
 @RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

 

    @GetMapping()
    public String login() {
        return "home";
    }


        @GetMapping("/home")
    public String listFiles( Model model) {
        	System.out.println("Files being sent to view: " + fileService.getAllFiles());
            model.addAttribute("files", fileService.getAllFiles());
            return "list-files";
    }

    


     @PostMapping("/upload")
     public String uploadFile(@RequestParam("file") MultipartFile file,
             @RequestParam("uploadedBy") String uploadedBy) throws IOException {
         fileService.uploadFile(file, uploadedBy);
         return "redirect:/files/home";
     }



       @GetMapping("/share/{id}")
    public String shareFile(@PathVariable("id") Long id, Model model) {
        ResponseEntity<?> fileModel = fileService.shareFile(id);
        if (fileModel.hasBody() && fileModel.getBody() instanceof FileModel) {
            FileModel file = (FileModel) fileModel.getBody();
            model.addAttribute("file", file);
            model.addAttribute("shareUrl", ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return "share-file";
        } else {
            return "redirect:/files/home";
        }

    }


    @GetMapping("/download/{id}")
     public ResponseEntity<?> downloadFile(@PathVariable("id") Long id) {
        return fileService.getFile(id);
    }



    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        ResponseEntity<?> file = fileService.deleteFile(id);
        if(file.hasBody()){
            return "redirect:/files/home";
        }
        else{
            return "redirect:/files";
        }
       
    }
}