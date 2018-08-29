package cn.lau1yach.store.web.servlet;

import cn.lau1yach.store.domain.Category;
import cn.lau1yach.store.domain.PageModel;
import cn.lau1yach.store.domain.Product;
import cn.lau1yach.store.service.CategoryService;
import cn.lau1yach.store.service.ProductService;
import cn.lau1yach.store.service.serviceImp.CategoryServiceImp;
import cn.lau1yach.store.service.serviceImp.ProductServiceImp;
import cn.lau1yach.store.utils.UUIDUtils;
import cn.lau1yach.store.utils.UploadUtils;
import cn.lau1yach.store.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminProductServlet",urlPatterns="/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        获取当前页
        int curNum=Integer.parseInt(req.getParameter("num"));
//        调用业务层全部商品信息返回PageModel
        ProductService productService=new ProductServiceImp();
        PageModel pm=productService.findAllProductsWithPage(curNum);
//        将PageModel放入req
        req.setAttribute("page",pm);
//        转发到/admin/product/list.jsp
        return "/admin/product/list.jsp";
    }
//    addProductUI
    public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ProductService productService =new ProductServiceImp();
        CategoryService categoryService =new CategoryServiceImp();
//        获取全部分类信息
        List<Category> list=categoryService.getAllCats();
//        将全部分类信息放入req
        req.setAttribute("allCats",list);
//        转发到/admin/product/add.jsp
        return "/admin/product/add.jsp";
    }
//    addProduct
    public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        存储表单中的数据
        Map<String,String> map= new HashMap<String, String>();
//        携带表单中的数据发送到service，dao
        Product product=new Product();

        try {
//            利用req.getInputStream();获取到请求体中全部数据，进行拆分和封装
            DiskFileItemFactory fac= new DiskFileItemFactory();
            ServletFileUpload upload =new ServletFileUpload(fac);
            List<FileItem> list= upload.parseRequest(req);
//            遍历集合
            for (FileItem fileItem :list ) {
                if (fileItem.isFormField()){
//                    如果当前的FileItem对象是普通项
//                    将普通项name属性的值作为键，获取到的内容作为值，放入map中
                    map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
                }else {
//                    如果当前的FileItem对象是上传项


//                    获取到原始文件的名称
                    String oldFileName=fileItem.getName();
//                    获取到要保存文件的名称
                    String newFileName=UploadUtils.getUUIDName(oldFileName);

//                    通过FileItem获取到输入流对象，通过输入流可以获取到图片二进制数据
                    InputStream is=fileItem.getInputStream();
//                    获取到当前项目下product/3下的真实路径
                    String realPath =getServletContext().getRealPath("/product/3/");
                    String dir=UploadUtils.getDir(newFileName);
                    String path=realPath+dir;
//                    内存中声明一个目录
                    File newDir =new File(path);
                    if (!newDir.exists()){
                        newDir.mkdirs();
                    }
//                    在服务端创建一个空文件（后缀必须和上传到服务端的文件名后缀一致）
                    File finalFile = new File(newDir,newFileName);
                    if (!finalFile.exists()){
                        finalFile.createNewFile();
                    }
//                    建立和空文件对应的输出流
                    OutputStream os=new FileOutputStream(finalFile);
//                    将输入流中的数据刷到输出流中
                    org.apache.tomcat.util.http.fileupload.IOUtils.copy(is,os);
//                    释放资源
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);

//                    向map存入一个键值对的数据
                    map.put("pimage","/product/3/"+dir+"/"+newFileName);
                }
            }
//            利用BeanUtils将map中的数据填充到Product对象上
            BeanUtils.populate(product,map);
            product.setPid(UUIDUtils.getId());
            product.setPdate(new Date());
            product.setPflag(0);
//            调用service,dao将user上携带的数据存入数据仓库,重定向到查询查询全部商品信息路径
            ProductService productService=new ProductServiceImp();
            productService.saveProduct(product);
            resp.sendRedirect("/Store/AdminProductServlet?method=findAllProductsWithPage&num=1");

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
