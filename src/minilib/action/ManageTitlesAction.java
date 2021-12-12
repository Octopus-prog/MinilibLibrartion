package minilib.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


import minilib.dao.ManageTitlesDao;
import minilib.vo.Title;

import com.opensymphony.xwork2.ActionSupport;
public class ManageTitlesAction extends ActionSupport {
	private Title book;
	private String context;
	public String addTitle() throws Exception{
		String restadd=INPUT;
		ManageTitlesDao mtadd=new ManageTitlesDao();
		mtadd.saveTitle(book);
		restadd="addbook";
		HttpServletRequest req=ServletActionContext.getRequest();
		req.setAttribute("message", "添加成功请继续添加！");
		System.out.print("=======add======="+book.getTypeid()+book.getTitle());
		
		return restadd;
	}
	public Title getBook() {
		return book;
	}
	public void setBook(Title book) {
		this.book = book;
	}
	public String findBookType() throws Exception{
		String restType=INPUT;
		ManageTitlesDao mttype=new ManageTitlesDao();
		List allTypeList=mttype.findbookType();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("alltypelist",allTypeList);
		restType="bookType";
		System.out.print("=======Query===");
		return restType;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String findAllTitles() throws Exception{
		System.out.println("**HJC**"+context);
		Title title=new Title();
		String rest=INPUT;
		ManageTitlesDao mt = new ManageTitlesDao();
		List allTitlesList = mt.findAll();
		List allTitlesList1=new ArrayList();
		Iterator<Title> it=allTitlesList.iterator();
		while(it.hasNext()) {
			if(it.next().getTitle().contains(context))
				//System.out.println("**hjc**"+it.next().getTitle());
				//System.out.println("**hjc**"+it.next().getAuthors());
				/*title.setAuthors(it.next().getAuthors());
				title.setBookid(it.next().getBookid());
				title.setIsbn(it.next().getIsbn());
				title.setPressid(it.next().getPressid());
				title.setTitle(it.next().getTitle());
				title.setTypeid(it.next().getTypeid());*/
				allTitlesList1.add(it.next());
		}
		/*for(int i=0;i<allTitlesList.size();i++)
			System.out.println(allTitlesList.get(i));*/
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("alltitleslist", allTitlesList1);
		rest="querybook";
		System.out.println("====findAllTitles====");
		return rest;
	}
}
