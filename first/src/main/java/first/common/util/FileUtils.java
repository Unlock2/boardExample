package first.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


// Component 어노테이션을 이용하여 이 객체의 관리를 스프링이 담당하도록 할 계획이다.
@Component("fileUtils")
public class FileUtils {
    private static final String filePath = "D:\\dev1\\file\\"; // 여기에 파일이 저장될 위치를 선언해 주었다. 
    				// --> 지금은 로컬에만 저장되게 해놨지만 나중에는 properties를 이용하여 로컬과 서버의 저장위치를 따로 관리할 예정이다.

    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); // 클라이언트에서 전송된 파일정보를 담아서 반환을 해줄 List이다.
        								// 여태까지는 단 하나의 파일만 전송했지만, 다중파일 전송을 하도록 수정할 계획이기 때문에 미리 ArrayList로 구성하였다.
        Map<String, Object> listMap = null;
         
        String boardIdx = (String)map.get("IDX");
         
        File file = new File(filePath);		// 이부분을 파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성하도록 하는 부분이다.
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                 
                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                 
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", boardIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        return list;
    }
}