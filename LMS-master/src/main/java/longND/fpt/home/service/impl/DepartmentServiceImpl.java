package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.DepartmentDto;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.ViewSearchDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Department;
import longND.fpt.home.data.modal.Publisher;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.DepartmentRepository;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.CreateDPRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.DepartmentService;
import longND.fpt.home.util.MapperObject;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public ResponseEntity<ApiResponse> createDepartment(CreateDPRequest creatRequest) {
		String nameDepartment = creatRequest.getName().trim();
		if (creatRequest.getName().isBlank() || creatRequest.getName().isEmpty()) {
			throw new BadRequestAlertException("Hãy điền tên danh mục");
		}
		if (departmentRepository.existsByName(nameDepartment)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên danh mục đã tồn tại");
		} else {
			Department Department = new Department();
			Department.setName(nameDepartment);
			departmentRepository.save(Department);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm danh mục thành công", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editDepartment(CreateDPRequest creatRequest, Long departmentId) {
		Department department = departmentRepository.getById(departmentId);
		if (creatRequest.getName().isBlank() || creatRequest.getName().isEmpty()) {
			throw new BadRequestAlertException("Hãy điền tên danh mục");
		}
		if (Objects.isNull(department)) {
			throw new NotFoundException("Không tìm thấy danh mục");
		} else {
			String nameDepartment = creatRequest.getName().trim();
			if (departmentRepository.existsByName(nameDepartment)
					&& !department.getName().equals(creatRequest.getName())) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên danh mục đã tồn tại");
			} else {
				department.setName(nameDepartment);
				departmentRepository.save(department);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Sửa danh mục thành công", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchDepartment(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Department> listDepartment = departmentRepository.listDepartment(text, pageable);

		List<DepartmentDto> list = new ArrayList<>();

		for (Department item : listDepartment.getContent()) {
			DepartmentDto DepartmentDto = modelMapper.map(item, DepartmentDto.class);
			list.add(DepartmentDto);
		}

		CustomPage<DepartmentDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listDepartment.getTotalElements(), listDepartment.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("tìm kiếm thành công", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listDepartment(int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Department> listDepartment = departmentRepository.getListDepartment(pageable);
		List<DepartmentDto> list = new ArrayList<>();

		for (Department item : listDepartment.getContent()) {
			DepartmentDto DepartmentDto = modelMapper.map(item, DepartmentDto.class);
			list.add(DepartmentDto);
		}

		CustomPage<DepartmentDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listDepartment.getTotalElements(), listDepartment.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Danh sách danh mục", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getBookOfDepartment(Long departmentId) {

		Department department = departmentRepository.getById(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Không tìm thấy danh mục");
		}
		DepartmentDto dto = DepartmentDto.builder().id(department.getId()).name(department.getName()).build();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ObjectResponse("Danh sách sách của danh mục ", new HashMap<>() {
					{
						put("departmentDetail", dto);
					}
				}));

	}

}
