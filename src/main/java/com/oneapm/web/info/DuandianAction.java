package com.oneapm.web.info;

import java.io.IOException;
import java.util.List;

import com.oneapm.dto.group.Group;
import com.oneapm.dto.info.Info;
import com.oneapm.service.group.GroupService;
import com.oneapm.service.info.DuandianService;
import com.oneapm.web.SupportAction;

public class DuandianAction extends SupportAction {

        private static final long serialVersionUID = 7112393993649033943L;

        private List<Info> infos;
        private int agent;
        private int data;
        private int login;
        private int paixu;
        private String banben;
        private int fuze;
        private Long groupId1;
        private Long groupId2;
        private Long fatherId;
        private String dataStart;
        private String dataEnd;
        private int nodata;
        private String nodataStart;
        private String nodataEnd;
        private int duli;
        private String loginStart;
        private String loginEnd;
        private List<Group> groups;
        private int caozuo;
        private int caozuoTime;
        private String caozuoStart;
        private String caozuoEnd;
        public String index() {
                if (!isLogin()) {
                        return "login";
                }
                try{
                        groups = GroupService.findChild(0L);
                }catch(Exception e){
                        LOG.error(e.getMessage(),  e);
                }
                return "index";
        }

        public void chaxun() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        if(fatherId != null && fatherId > 0){
                                String result = DuandianService.chaxun(fatherId);
                                getServletResponse().getWriter().print(result);
                                return;
                        }
                        String result = DuandianService.chaxun(agent, data, paixu, banben, fuze, groupId1, groupId2, dataStart, dataEnd, nodataStart, nodataEnd, 
                                        nodata, duli,login,loginStart,loginEnd,caozuo, caozuoTime, caozuoStart, caozuoEnd, getAdmin());
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }
        
        private int yuan;
        private String yuanStart;
        private String yuanEnd;
        private int leixing;
        private int zuobiao;
        private int zuobiaoZidingyi;
        private String zuobiaoStart;
        private String zuobiaoEnd;
        private int yuanType;
        public void chaxun_baobiao() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        String result = DuandianService.chaxun(yuan, yuanStart, yuanEnd, agent, leixing, zuobiao, zuobiaoZidingyi, zuobiaoStart, zuobiaoEnd, yuanType);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }
        
        public void chaxun_baobiao_view() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        String result = DuandianService.chaxun_chazhi(yuanStart, yuanEnd);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }
        

        public void versions() throws IOException {
                if (!isLogin()) {
                        getServletResponse().sendRedirect("/login.action");
                }
                try {
                        String result = DuandianService.findVersions(agent);
                        getServletResponse().getWriter().print(result);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
        }

        public List<Info> getInfos() {
                return infos;
        }

        public void setInfos(List<Info> infos) {
                this.infos = infos;
        }

        public int getAgent() {
                return agent;
        }

        public void setAgent(int agent) {
                this.agent = agent;
        }

        public int getData() {
                return data;
        }

        public void setData(int data) {
                this.data = data;
        }

        public int getLogin() {
                return login;
        }

        public void setLogin(int login) {
                this.login = login;
        }

        public int getPaixu() {
                return paixu;
        }

        public void setPaixu(int paixu) {
                this.paixu = paixu;
        }


        public String getBanben() {
                return banben;
        }

        public void setBanben(String banben) {
                this.banben = banben;
        }

        public int getFuze() {
                return fuze;
        }

        public void setFuze(int fuze) {
                this.fuze = fuze;
        }

        public List<Group> getGroups() {
                return groups;
        }

        public void setGroups(List<Group> groups) {
                this.groups = groups;
        }

        public Long getGroupId1() {
                return groupId1;
        }

        public void setGroupId1(Long groupId1) {
                this.groupId1 = groupId1;
        }

        public Long getGroupId2() {
                return groupId2;
        }

        public void setGroupId2(Long groupId2) {
                this.groupId2 = groupId2;
        }

        public Long getFatherId() {
                return fatherId;
        }

        public void setFatherId(Long fatherId) {
                this.fatherId = fatherId;
        }

        public String getDataStart() {
                return dataStart;
        }

        public void setDataStart(String dataStart) {
                this.dataStart = dataStart;
        }

        public String getDataEnd() {
                return dataEnd;
        }

        public void setDataEnd(String dataEnd) {
                this.dataEnd = dataEnd;
        }

        public int getNodata() {
                return nodata;
        }

        public void setNodata(int nodata) {
                this.nodata = nodata;
        }

        public String getNodataStart() {
                return nodataStart;
        }

        public void setNodataStart(String nodataStart) {
                this.nodataStart = nodataStart;
        }

        public String getNodataEnd() {
                return nodataEnd;
        }

        public void setNodataEnd(String nodataEnd) {
                this.nodataEnd = nodataEnd;
        }

        public int getDuli() {
                return duli;
        }

        public void setDuli(int duli) {
                this.duli = duli;
        }

        public String getLoginStart() {
                return loginStart;
        }

        public void setLoginStart(String loginStart) {
                this.loginStart = loginStart;
        }

        public String getLoginEnd() {
                return loginEnd;
        }

        public void setLoginEnd(String loginEnd) {
                this.loginEnd = loginEnd;
        }

        public int getCaozuo() {
                return caozuo;
        }

        public void setCaozuo(int caozuo) {
                this.caozuo = caozuo;
        }

        public int getCaozuoTime() {
                return caozuoTime;
        }

        public void setCaozuoTime(int caozuoTime) {
                this.caozuoTime = caozuoTime;
        }

        public String getCaozuoStart() {
                return caozuoStart;
        }

        public void setCaozuoStart(String caozuoStart) {
                this.caozuoStart = caozuoStart;
        }

        public String getCaozuoEnd() {
                return caozuoEnd;
        }

        public void setCaozuoEnd(String caozuoEnd) {
                this.caozuoEnd = caozuoEnd;
        }

        public int getYuan() {
                return yuan;
        }

        public void setYuan(int yuan) {
                this.yuan = yuan;
        }

        public String getYuanEnd() {
                return yuanEnd;
        }

        public void setYuanEnd(String yuanEnd) {
                this.yuanEnd = yuanEnd;
        }

        public int getLeixing() {
                return leixing;
        }

        public void setLeixing(int leixing) {
                this.leixing = leixing;
        }

        public String getYuanStart() {
                return yuanStart;
        }

        public void setYuanStart(String yuanStart) {
                this.yuanStart = yuanStart;
        }

        public int getZuobiao() {
                return zuobiao;
        }

        public void setZuobiao(int zuobiao) {
                this.zuobiao = zuobiao;
        }

        public String getZuobiaoStart() {
                return zuobiaoStart;
        }

        public void setZuobiaoStart(String zuobiaoStart) {
                this.zuobiaoStart = zuobiaoStart;
        }

        public int getZuobiaoZidingyi() {
                return zuobiaoZidingyi;
        }

        public void setZuobiaoZidingyi(int zuobiaoZidingyi) {
                this.zuobiaoZidingyi = zuobiaoZidingyi;
        }

        public String getZuobiaoEnd() {
                return zuobiaoEnd;
        }

        public void setZuobiaoEnd(String zuobiaoEnd) {
                this.zuobiaoEnd = zuobiaoEnd;
        }

        public int getYuanType() {
                return yuanType;
        }

        public void setYuanType(int yuanType) {
                this.yuanType = yuanType;
        }
}
