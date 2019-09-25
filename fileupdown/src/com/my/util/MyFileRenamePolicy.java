package com.my.util;

import java.io.File;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File file) {
		System.out.println("rename(" + file + ")호출");
		// 1. 파일 이름 얻기
		System.out.println(file.getName() + ", " + file.getAbsolutePath());	
		// getName: 파일명
		// getAbsolutePath: 절대(전체) 경로
		// String filePath = file.getAbsolutePath();
		String parent = file.getParent();
		String oldName = file.getName();
		
		// 2. 파일 이름 바꾸기 -yyMMddHHmmss
		// ex) 파일명-190907104257.txt
		
		// 2-1) 전체 파일 경로에서 확장자를 제외한 파일 이름 얻기
		int extIdx = oldName.lastIndexOf(".");
		String name = oldName.substring(0, extIdx);
		
		// 2-2) 이름에 yyMMddHHmmss 이어 붙이기
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String now = sdf.format(new java.util.Date());
		
		name += now;
		
		// 2-3) 이름과 확장자 이어붙이기
		String newName = name + oldName.substring(extIdx);
		
		// 3. 새 파일 생성 후 반환
		File f = new File(parent, newName);
		return f;
	}

}
