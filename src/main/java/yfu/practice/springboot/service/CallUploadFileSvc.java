package yfu.practice.springboot.service;

/**
 * Call UploadFile API
 * @author yfu
 */
public interface CallUploadFileSvc {

	String uploadFile(String... fileNames);
}