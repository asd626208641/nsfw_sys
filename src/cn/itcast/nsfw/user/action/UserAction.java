package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

public class UserAction extends ActionSupport {
	@Resource
	private UserService userService;
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	private File headImg; // 这是struts为我们以及封装好了 只要headImg名字与前面一致 后面两个名字+ContentType 这样类型 再get set方法就可以自动获取了
	private String headImgContentType;
	private String headImgFileName;

	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;

	// 列表页面
	public String listUI() {
		userList = userService.findObjects();
		return "listUI";
	}

	// 跳转到增加页面
	public String addUI() {
		return "addUI";
	}

	// 保存增加
	public String add() {
		try {
			if (user != null) {
				if (headImg != null) {
					// 1.保存头像到Upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "")
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 复制文件
					FileUtil.copyFile(headImg, new File(filePath, fileName));
					// 设置用户头像路径
					user.setHeadImg("user/" + fileName);
				}
				userService.save(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (user != null && user.getId() != null) {
			user = userService.findObjectById(user.getId());
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if (user != null) {
				if (headImg != null) {
					// 1.保存头像到Upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "")
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 老图的名字
					String old_fileName = user.getHeadImg().split("/")[1];
					// 删除老图
					new File(filePath + "\\" + old_fileName).delete();

					// 复制文件
					FileUtil.copyFile(headImg, new File(filePath, fileName));
					// 设置用户头像路径
					user.setHeadImg("user/" + fileName);
				}
				// System.out.println(road.split("/")[1].getClass().getName().toString());
				userService.update(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (user != null) {
			userService.delete(user.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		System.out.println(selectedRow);
		return "list";
	}

	public List<User> getUserList() {
		return userList;
	}

	// 导出用户列表
	public void exportExcel() {
		try {
			// 1.查找用戶列表
			userList = userService.findObjects();
			// 2.导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + new String("用户列表.xlsx".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			// 上述三句代码就只是实现下载功能
			userService.exportExcel(userList, outputStream);
			if (outputStream != null) {
				outputStream.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String importExcel() {
		if (userExcel != null) {
			// 是否是Excel
			if (userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				userService.importExcel(userExcel, userExcelFileName);
			}
		}
		return "list";
	}

	//校验用户帐号唯一
	public void verifyAccount(){
		try {
			//1、获取帐号
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				//2、根据帐号到数据库中校验是否存在该帐号对应的用户
				List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
				String strResult = "true";
				if(list != null && list.size() > 0){
					//说明该帐号已经存在
					strResult = "false";
				}	
				//输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public File getUserExcel() {
		return userExcel;
	}

	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}

	public String getUserExcelContentType() {
		return userExcelContentType;
	}

	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}

	public String getUserExcelFileName() {
		return userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

}
