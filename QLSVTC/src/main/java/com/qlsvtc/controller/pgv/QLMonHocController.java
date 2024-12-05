package com.qlsvtc.controller.pgv;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qlsvtc.CNTT.repository.MonHocRepositoryCNTT;
import com.qlsvtc.entity.MonHoc;


@Controller
@RequestMapping(value = "quanly/pgv")
public class QLMonHocController {
	@Autowired
	MonHocRepositoryCNTT repo;

	
	
	@GetMapping("monhoc")
	public String getVTCN(ModelMap model) {
		/*
		 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
		 */ model.addAttribute("lst", repo.findAll());
		return "pgv/qlmonhoc";
	}

	@GetMapping("monhoc/add")
	public String addVTCN(ModelMap model) {
		MonHoc item = new MonHoc();
		model.addAttribute("item", item);

		return "pgv/form/fadd-monhoc";
	}

	@PostMapping("monhoc/add")
	public String addVTCN1(ModelMap model, @ModelAttribute("item") MonHoc item) {

		//System.out.print(vt.getMaVT());

		if (repo.findById(item.getMaMH()) == null) {
			MonHoc nvsave = null;

			try {
				nvsave = repo.save(item);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "thêm thất bại");
				System.out.print("thêm vật tư thất bại");
			}
			if (nvsave != null) {
				model.addAttribute("message", "thêm thành công");
				System.out.print("thêm vật tư thành công");
			}
		} else {
			model.addAttribute("message", "thêm thất bại, đã tồn tại");
			System.out.print("thêm thất bại đã tồn tại");
		}

		return "redirect:quanly/pgv/monhoc/add";
	}

	@GetMapping(value = "monhoc/edit")
	public String editVTCN1(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");

		MonHoc item = repo.findById(id).get();
		if (item != null) {
			model.addAttribute("item", item);
			System.out.print("tồn tại item");
		}

		else {
			System.out.print("không tồn tại item");
			return "redirect:quanly/pgv/monhoc";
		}

		return "chinhanh/form/fedit-monhoc";
	}

	@PostMapping("monhoc/edit")
	public String editVTCN1(ModelMap model, @ModelAttribute("item") MonHoc item, HttpServletRequest request) {

		MonHoc vtsave = repo.findById(item.getMaMH()).get();

		//System.out.println(vtsave.getTenVT());

		try {
			vtsave.setMaMH(item.getMaMH());
			vtsave.setSotietLT(item.getSotietLT());
			vtsave.setSotietTH(item.getSotietTH());
			vtsave.setTenMH(item.getTenMH());

			repo.save(vtsave);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (vtsave != null) {

			model.addAttribute("message", "Sửa  thành công");
			System.out.print("Sửa thành công");
		}

		return "redirect:quanly/pgv/monhoc";

	}

	@GetMapping(value = "monhoc/xoa")
	public String xoaNVCN1(ModelMap model, HttpServletRequest request) {

		model.addAttribute("id", request.getParameter("id"));

		return "pgv/form/fxoa-vattu";

	}

	@RequestMapping(value = "monhoc/xoa", method = RequestMethod.POST)
	public String xoaNVCN1P(ModelMap model, HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.print(request.getParameter("xacNhan") + request.getParameter("id"));
		try {
			if (request.getParameter("xacNhan").equals("YES")) {
//				VatTuEntity nvsave = vtrepo.findOne(id);
				repo.deleteById(id);
				
				model.addAttribute("message", "xoá thành công");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "xoá thất bại");
		}
		return "redirect:/quanlyvattu/cn1/chinhanh.htm";

	}

	//===================================CONGTY==================================//
	
	
		@RequestMapping(value = {"congty"}, method = RequestMethod.GET)
		public String getVTCTY(ModelMap model) {
			/*
			 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
			 */ model.addAttribute("vts", repo.findAll());
			return "congty/qlvattu";
		}

		
		//===================================USER==================================//
		
		
		@RequestMapping(value = {"user"}, method = RequestMethod.GET)
		public String getVTU(ModelMap model) {
			/*
			 * Sort sort = new Sort(Sort.Direction.ASC, "maVT");;
			 */ model.addAttribute("vts", repo.findAll());
			return "user/qlvattu";
		}

		
}
