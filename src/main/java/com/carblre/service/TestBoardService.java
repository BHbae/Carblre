package com.carblre.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.carblre.repository.TestBoardRepository;
import com.carblre.repository.Post;
import com.carblre.utils.Define;

@Service
public class TestBoardService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private TestBoardRepository boardRepository;

	
	
	
	
	public List<Post> findAllBoards() {
		List<Post> boards = boardRepository.findAllBoard();
		return boards;
	}

	@Transactional
	public void savePost(int status, String category, String tile, String content, MultipartFile vidio) {

		String[] fileName = uploadFile(vidio);
		Post post = Post.builder().userId(1).status(status).category(content).title(tile).content(content)
				.originFileName(fileName[0]).uploardFileName(fileName[1]).build();

		boardRepository.savePost(post);

	}

	// 업로드 파일 내일으로 바꾸는 메서드

	private String[] uploadFile(MultipartFile mFile) {

		// 크기
		if (mFile.getSize() > Define.MAX_FILE_SIZE) {

		}
		String saveDirectory = uploadDir;

		String uploadFileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();

		String uploadPath = saveDirectory + File.separator + uploadFileName;

		File destination = new File(uploadPath);

		Path uploadPath1 = Paths.get(uploadDir);
		if (!Files.exists(uploadPath1)) {
			try {
				Files.createDirectories(uploadPath1);
			} catch (IOException e) {
			}
		}

		try {
			mFile.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return new String[] { mFile.getOriginalFilename(), uploadFileName };
	}

}
