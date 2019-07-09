package kr.or.ddit.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.file.service.IfileService;

@Controller
public class FileDownloadController {

	@Resource(name="fileService")
	private IfileService fileService;
	
	@RequestMapping("/fileDownload")
	public void fileDownload(Model model,HttpSession session,String file_id,HttpServletRequest request,HttpServletResponse response) {
		int fileId =Integer.parseInt(file_id); 
		FileVo fileVo = fileService.getfile(fileId);
		
		String savePath = fileVo.getFile_path();
		
		String orgfilename = fileVo.getFile_name();
		
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";
		
		try{
			// 파일을 읽어 스트림에 담기
			try{
				file = new File(savePath);
				in = new FileInputStream(file);
			}catch(FileNotFoundException fe){
				skip = true;
			}

			client = request.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");


			if(!skip){
				// IE
				if(client.indexOf("MSIE") != -1){
					response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));

				}else{
					// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				} 

				response.setHeader ("Content-Length", ""+file.length() );

				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;

				while( (leng = in.read(b)) > 0 ){
					os.write(b,0,leng);
				}
			}

			in.close();
			os.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
