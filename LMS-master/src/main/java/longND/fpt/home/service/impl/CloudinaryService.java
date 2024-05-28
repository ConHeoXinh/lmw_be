package longND.fpt.home.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.service.StorageService;

@Slf4j
@Service
public class CloudinaryService implements StorageService {

	private final Cloudinary cloudinary;

	public CloudinaryService(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	@Override
	public String upload(MultipartFile file) {
		if (file.isEmpty()) {
			throw new NotFoundException("File ảnh rỗng");
		}
		File fileObj = convertMultiPartFileToFile(file);

		Long size = fileObj.length() / (1024 * 1024);
		if (size > 25) {
			throw new BadRequestAlertException("Size ảnh quá lớn");
		}

		String type = file.getContentType();

		if (type.contains("image/jpg") || type.equals("image/png") || type.equals("image/jpeg")) {
			String imageUrl = null;

			try {
				Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(),
						ObjectUtils.asMap("public_id", fileObj.getName(), "overwrite", true));
				imageUrl = (String) result.get("url");
				fileObj.delete();
			} catch (Exception e) {
				log.error("Lỗi khi upload file", e);
			}
			return imageUrl;
		} else {
			throw new BadRequestAlertException("Hãy chọn ảnh có đuôi là jpg hoặc png");
		}
	}

	@Override
	public byte[] download(String publicId) {
		String imageUrl = cloudinary.url().generate(publicId);
		try {
			URL url = new URL(imageUrl);
			byte[] content = Files.readAllBytes(Path.of(url.toURI()));

			return content;
		} catch (Exception e) {
			log.error("Lỗi khi tải xuống ", e);
		}
		return null;
	}

	@Override
	public String delete(String publicId) {
		try {
			cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
		} catch (java.io.IOException e) {
			log.error("Lỗi khi xóa file", e);
		}
		return publicId + " removed ...";
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try {
			FileOutputStream fos = new FileOutputStream(convertedFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {
			log.error("Lỗi khi coverting multipartFile to file", e);
		}
		return convertedFile;
	}

	@Override
	public String getNameImage(String publicId) {
		String imageUrl = cloudinary.url().generate(publicId);

		List<String> list = List.of(imageUrl.split("/"));

		String nameImage = list.get(11);
		return nameImage;
	}

}
