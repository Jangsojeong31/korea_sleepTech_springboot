package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.file.PostRequestDto;
import com.example.korea_sleepTech_springboot.dto.file.PostResponseDto;
import com.example.korea_sleepTech_springboot.dto.reponse.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.Post;
import com.example.korea_sleepTech_springboot.entity.UploadFile;
import com.example.korea_sleepTech_springboot.repository.PostDataRepository;
import com.example.korea_sleepTech_springboot.repository.UploadFIleRepository;
import com.example.korea_sleepTech_springboot.service.PostDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostDataServiceImpl implements PostDataService {
    private final PostDataRepository postDataRepository;
    private final UploadFIleRepository uploadFIleRepository;
    private String uploadDir;

    @Value("${file.upload-dir}")
    @Override
    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto, MultipartFile file) throws IOException {
        PostResponseDto data = null;

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        post = postDataRepository.save(post);

        if(file != null && !file.isEmpty()) {
            // 업로드된 파일이 존재하고 비어있지 않다면
            saveFile(file, post.getId(), "POST"); // 파일 저장 메서드 호출 (게시글 ID와 타입 전달)
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 실제 파일을 저장하고 DB에 메타데이터를 저장하는 메서드
    private void saveFile(MultipartFile file, Long targetId, String type) throws IOException {

        File dir = new File(uploadDir); // 업로드 디렉토리 객체 생성
        if(!dir.exists()) dir.mkdirs(); // 디렉토리가 없으면 생성 (mk: make, dirs: directory 폴더)

        String original = file.getOriginalFilename(); // 원본 파일명
        String uuidName = UUID.randomUUID() + "_" + original; // UUID를 활용한 유일한 파일명 생성
        String fullPath = uploadDir + "/" + uuidName; // 전체 경로 구성
        file.transferTo(new File(fullPath));

        // 업로드 파일 정보 DB에 저장
        UploadFile uf = new UploadFile();
        uf.setOriginalName(original);
        uf.setFileName(uuidName);
        uf.setFilePath("/files/" + uuidName); // 실제 접근 가능한 URL로 설정
        uf.setFileSize(file.getSize());
        uf.setFileType(file.getContentType());
        uf.setTargetId(targetId);
        uf.setTargetType(type);



    }
}
