package com.oneapm.service.record;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oneapm.dao.info.impl.InfoDaoImpl;
import com.oneapm.dto.Account.Admin;
import com.oneapm.dto.info.Info;
import com.oneapm.dto.tag.Category;
import com.oneapm.dto.tag.Person;
import com.oneapm.dto.tag.Province;
import com.oneapm.dto.tag.Rongzi;
import com.oneapm.dto.tag.Tag;
import com.oneapm.service.account.AccountService;
import com.oneapm.service.info.InfoService;
import com.oneapm.service.info.TagService;
import com.oneapm.util.OneTools;
import com.oneapm.util.TimeTools;

@SuppressWarnings("deprecation")
public class Xiaoshouyi {

        protected static final Logger LOG = LoggerFactory.getLogger(Xiaoshouyi.class);
        
        public static final String TOKEN = "a307630d395747fcc15e208b462984e9";

        
        public static String xiaoshouyi(Admin admin, Long infoId, String xiaoshou){
                try{
                        Info info = InfoService.findByIdSimple(infoId);
                        if(info == null || info.getProject() == null || info.getProject().trim().length() <= 0){
                                return OneTools.getResult(0, "项目信息必须完整");
                        }
                        info.setTag(TagService.findByInfoId(info.getId()));
                        if(info.getTag() != null && info.getTag().getProvince() > 0){
                                info.getTag().setProvinceName(Province.getName(info.getTag().getProvince()));
                        }else{
                                info.setTag(new Tag());
                                info.getTag().setProvinceName("未知");
                        }
                        Long xiaoshouyiId = post(info, xiaoshou);
                        if(xiaoshouyiId == null){
                                return OneTools.getResult(0, "推送销售失败");
                        }
                        info.setXiaoshouyi(xiaoshouyiId);
                        info.setXiaoshouyiAdmin(admin.getId());
                        InfoService.update_xiaoshouyi(info, null);
                        return OneTools.getResult(1, "成功");
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return OneTools.getResult(0, "服务器内部错误");
        }
        
        public static void tuisongxiaoshouyi(){
                String start = "2015-04-01 00:00:00";
                String end = "2015-05-01 00:00:00";
                List<Info> infos = InfoDaoImpl.getInstance().findByCreateTime(start, end);
                for(Info info : infos){
                        if(info.getXiaoshouyi() == null){
                                xiaoshouyi(info, null);
                        }
                }
        }
        
        public static void xiaoshouyi(Info info, String xiaoshou){
                try{
                        if(info == null){
                                return;
                        }
                        if(info.getProject() == null || info.getProject().trim().length() <= 0){
                                if(info.getCompany() == null || info.getCompany().length() <= 0){
                                        return;
                                }
                                info.setProject(info.getCompany());
                        }
                        info.setTag(TagService.findByInfoId(info.getId()));
                        if(info.getTag() != null && info.getTag().getProvince() > 0){
                                info.getTag().setProvinceName(Province.getName(info.getTag().getProvince()));
                        }else{
                                info.setTag(new Tag());
                                info.getTag().setProvinceName("未知");
                        }
                        Long xiaoshouyiId = post(info, xiaoshou);
                        if(xiaoshouyiId == null){
                                LOG.info("推送销售失败:"+info.getUserId());
                        }
                        info.setXiaoshouyi(xiaoshouyiId);
                        info.setXiaoshouyiAdmin(99999999L);
                        InfoService.update_xiaoshouyi(info, "20150707");
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                        LOG.info("推送销售失败:"+info.getUserId());
                }
        }
        
        public static Long post(Info info, String xiaoshou) {
                ByteArrayOutputStream bos = null;
                InputStream bis = null;
                byte[] buf = new byte[10240];
                String content = null;
                DefaultHttpClient client = null;
                try {
                        client = new DefaultHttpClient();
                        client.addRequestInterceptor(new HttpRequestInterceptor() {
                                public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                                        request.addHeader("Accept-Language", "zh-CN");
                                        request.addHeader("Accept-Encoding", "gzip");
                                        request.addHeader("Authorization", TOKEN);
                                }
                        });
                        HttpPost request = new HttpPost("https://api.xiaoshouyi.com/data/v1/objects/lead/create");
                        JSONObject object = new JSONObject();
                        object.put("public", true);
                        JSONObject record = new JSONObject();
                        record.put("name", info.getName());                              //姓名
                        record.put("companyName", info.getProject());              //公司名
                        record.put("ownerId", 329832);                                       //负责人
                        record.put("status", 1);                                        //跟进状态1.未处理，2.已联系，3.关闭，4.已转换
                        if(info.getGender() == 1 || info.getGender() == 2){
                                record.put("gender", info.getGender());                                        //性别1男2女
                        }
                        record.put("mobile", info.getPhone());                    //手机
                        record.put("email", info.getEmail());              //邮件
                        record.put("state", info.getTag().getProvinceName());                                       //省
                        record.put("highSeaId", 58830);                                            //所属公海分组，4735默认分组
                        
                        record.put("comment", "无"); 
                        try{
                                record.put("comment", info.getTag().getDescription());
                        }catch(Exception e){}
                        record.put("dimDepart", "180022");                                                    //所属部门
                        record.put("dbcVarchar1", info.getUserId());
                        record.put("dbcVarchar2", "未知");      //融资
                        try{
                                record.put("dbcVarchar2", Rongzi.getName(info.getTag().getRongzi()));
                        }catch(Exception e){}
                        record.put("dbcVarchar3", "未知");        //分类
                        try{
                                record.put("dbcVarchar3", Category.getName(info.getTag().getCategory()));
                        }catch(Exception e){}
                        record.put("dbcVarchar4", "未知");        //人数
                        try{
                                record.put("dbcVarchar4", Person.getName(info.getTag().getPerson()));
                        }catch(Exception e){}
                        try{
                                if(xiaoshou != null && xiaoshou.trim().length() > 0){
                                        record.put("dbcVarchar5", xiaoshou);
                                }else if(info.getSale() != null&& info.getSale() > 0){
                                        Admin a = AccountService.findById(info.getSale());
                                        if(a != null){
                                                record.put("dbcVarchar5", a.getName());
                                        }
                                }
                        }catch(Exception e){}
                        
                        object.put("record", record);
                        String json = object.toString();
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                        nvps.add(new BasicNameValuePair("json",json)); // 参数  
                  
                        request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post  
                  
                        
                        HttpResponse response = client.execute(request);

                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                bis = response.getEntity().getContent();
                                Header[] gzip = response.getHeaders("Content-Encoding");

                                bos = new ByteArrayOutputStream();
                                int count;
                                while ((count = bis.read(buf)) != -1) {
                                        bos.write(buf, 0, count);
                                }
                                bis.close();
                                content = bos.toString();
                                JSONObject xiaoshouyi = null;
                                try{
                                        xiaoshouyi = new JSONObject(content);
                                }catch(Exception e){
                                        LOG.info(content);
                                        return null;
                                }
                                Long id = null;
                                try{
                                        id = xiaoshouyi.getLong("id");
                                }catch(Exception e){
                                        LOG.info(content);
                                }
                                return id;
                        } else {
                                System.out.println(response.getStatusLine().getStatusCode());
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (bis != null) {
                                try {
                                        client.close();
                                        bis.close();
                                } catch (Exception e) {
                                }
                        }
                }
                return null;
        }
        
        public String tpmApi(String url) {
                ByteArrayOutputStream bos = null;
                InputStream bis = null;
                byte[] buf = new byte[10240];
                String content = null;
                DefaultHttpClient client = null;
                try {
                        client = new DefaultHttpClient();
                        client.addRequestInterceptor(new HttpRequestInterceptor() {
                                public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                                        request.addHeader("Accept-Language", "zh-CN");
                                        request.addHeader("Accept-Encoding", "gzip");
                                }
                        });
                        HttpPost request = new HttpPost(url);
                        
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                        nvps.add(new BasicNameValuePair("app_key","Ij2Qp8UCGDc=")); // 参数  
                        nvps.add(new BasicNameValuePair("username","15810086119 ")); // 参数  
                        nvps.add(new BasicNameValuePair("password","liwei123123PTS69WSk")); // 参数  
                  
                        request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post  
                  
                        
                        HttpResponse response = client.execute(request);

                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                bis = response.getEntity().getContent();
                                Header[] gzip = response.getHeaders("Content-Encoding");

                                bos = new ByteArrayOutputStream();
                                int count;
                                while ((count = bis.read(buf)) != -1) {
                                        bos.write(buf, 0, count);
                                }
                                bis.close();
                                content = bos.toString();
                        } else {
                                System.out.println(response.getStatusLine().getStatusCode());
                        }
                        return content;
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (bis != null) {
                                try {
                                        client.close();
                                        bis.close();
                                } catch (Exception e) {
                                }
                        }
                }
                return null;
        }
        
        public static void main(String[] args){
                ByteArrayOutputStream bos = null;
                InputStream bis = null;
                byte[] buf = new byte[10240];
                String content = null;
                DefaultHttpClient client = null;
                try {
                        client = new DefaultHttpClient();
                        client.addRequestInterceptor(new HttpRequestInterceptor() {
                                public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                                        request.addHeader("Accept-Language", "zh-CN");
                                        request.addHeader("Accept-Encoding", "gzip");
                                        request.addHeader("Authorization", TOKEN);
                                }
                        });
                        HttpPost request = new HttpPost("https://api.xiaoshouyi.com/data/v1/objects/lead/create");
                        JSONObject object = new JSONObject();
                        object.put("public", true);
                        JSONObject record = new JSONObject();
                        record.put("name", "闵早华");                              //姓名
                        record.put("companyName", "汇通天下");              //公司名
                        record.put("ownerId", 329832);                                       //负责人
                        record.put("status", 1);                                        //跟进状态1.未处理，2.已联系，3.关闭，4.已转换
                        record.put("mobile", "18910080812");                    //手机
                        record.put("email", "minzaohua@126.com");              //邮件
                        record.put("state", "北京");                                       //省
                        record.put("highSeaId", 58830);                                            //所属公海分组，4735默认分组
                        
                        record.put("comment", "无"); 
                        try{
                                record.put("comment", "三大件网络散乱的发射的拉进了万恶士大夫散乱的");
                        }catch(Exception e){}
                        record.put("dimDepart", "180022");                                                    //所属部门
                        record.put("dbcVarchar1", "39411");
                        record.put("dbcVarchar2", "未知");      //融资
                        try{
                                record.put("dbcVarchar2", "B轮");
                        }catch(Exception e){}
                        record.put("dbcVarchar3", "未知");        //分类
                        try{
                                record.put("dbcVarchar3", "其他");
                        }catch(Exception e){}
                        record.put("dbcVarchar4", "未知");        //人数
                        try{
                                record.put("dbcVarchar4", "未知");
                        }catch(Exception e){}
                        try{
                                       record.put("dbcVarchar5", "惠大庆");
                        }catch(Exception e){}
                        
                        object.put("record", record);
                        String json = object.toString();
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                        nvps.add(new BasicNameValuePair("json",json)); // 参数  
                  
                        request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); // 设置参数给Post  
                  
                        
                        HttpResponse response = client.execute(request);

                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                                bis = response.getEntity().getContent();
                                Header[] gzip = response.getHeaders("Content-Encoding");

                                bos = new ByteArrayOutputStream();
                                int count;
                                while ((count = bis.read(buf)) != -1) {
                                        bos.write(buf, 0, count);
                                }
                                bis.close();
                                content = bos.toString();
                                System.out.println(content);
                                JSONObject xiaoshouyi = null;
                                try{
                                        xiaoshouyi = new JSONObject(content);
                                }catch(Exception e){
                                }
                                Long id = null;
                                try{
                                        id = xiaoshouyi.getLong("id");
                                }catch(Exception e){
                                        LOG.info(content);
                                }
                        } else {
                                System.out.println(response.getStatusLine().getStatusCode());
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (bis != null) {
                                try {
                                        client.close();
                                        bis.close();
                                } catch (Exception e) {
                                }
                        }
                }
        }
}
