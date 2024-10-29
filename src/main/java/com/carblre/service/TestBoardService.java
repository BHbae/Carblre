package com.carblre.service;

import java.io.IOException;
import java.io.OutputStream;
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

import com.carblre.dto.DetailDTO;
import com.carblre.repository.interfaces.TestBoardRepository;
import com.carblre.repository.model.Post;
import com.carblre.utils.Define;

@Service
public class TestBoardService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private TestBoardRepository boardRepository;

	public DetailDTO selectByPostId(int postId) {
		DetailDTO dto = boardRepository.selectByPostId(postId);
		return dto;
	}

	public Post findById(int postId) {
		Post post = boardRepository.findById(postId);
		return post;
	}

	public List<Post> findAllBoards() {
		List<Post> boards = boardRepository.findAllBoard();
		return boards;
	}

	@Transactional
	public void savePost(int status, String category, String tile, String content, MultipartFile vidio) {

		// 파일 업로드 로직
		String[] fileName = uploadFile(vidio);

		Post post = Post.builder().userId(1).status(status).category(content).title(tile).content(content)
				.originFileName(fileName[0]).uploadFileName(fileName[1]).build();

		boardRepository.savePost(post);

	}

	// 업로드 파일 이름 바꾸는 메서드

	private String[] uploadFile(MultipartFile mFile) {

		// 크기 20MB확인
		if (mFile.getSize() > Define.MAX_FILE_SIZE) {

		}
		// 확장자가 mp4인지 확인
		String contentType = mFile.getContentType();
		if (!contentType.equals("video/mp4")) {

		}

		String uploadFileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();

		String saveDirectory = uploadDir;

		Path uploadPath1 = Paths.get(uploadDir);
		if (!Files.exists(uploadPath1)) {
			try {
				Files.createDirectories(uploadPath1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Path filePath = Paths.get(saveDirectory, uploadFileName);

		try (OutputStream os = Files.newOutputStream(filePath)) {
			os.write(mFile.getBytes());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return new String[] { mFile.getOriginalFilename(), uploadFileName };
	}

}
