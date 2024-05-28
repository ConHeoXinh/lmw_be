package longND.fpt.home.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	public String upload(MultipartFile file);

	public byte[] download(String publicId);

	public String delete(String publicId);

	public String getNameImage(String publicId);
}
