package com.qlsvtc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlsvtc.CNTT.repository.KhoaRepositoryCNTT;
import com.qlsvtc.CNTT.repository.SinhVienRepositoryCNTT;
import com.qlsvtc.VT.repository.KhoaRepositoryVT;
import com.qlsvtc.VT.repository.SinhVienRepositoryVT;
import com.qlsvtc.entity.Khoa;
import com.qlsvtc.entity.SinhVien;
import com.qlsvtc.model.MaKhoaModel;



@RestController
@RequestMapping("/api")
public class APIKhoaController {

	@Autowired
	KhoaRepositoryCNTT svrepocn;
	@Autowired
	KhoaRepositoryVT svrepovt;
	

	@GetMapping("/khoa")
	public <R extends JpaRepository<Khoa, String>> List<Khoa> getsv(MaKhoaModel khoa) {
		 R svrepo;

	        // Determine the appropriate repository based on the department code
	        if ("CNTT".equals(khoa.getMaKhoa())) {
	            svrepo = (R) svrepocn; // Cast to the generic type
	        } else if ("VT".equals(khoa.getMaKhoa())) {
	            svrepo = (R) svrepovt; // Cast to the generic type
	        } else {
	            // Return an empty list if no matching department is found
	            return List.of();
	        }

	        // Use the selected repository to fetch the list of students
	        return svrepo.findAll();
	    }
}
