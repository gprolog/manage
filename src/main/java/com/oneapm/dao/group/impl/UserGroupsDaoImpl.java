package com.oneapm.dao.group.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.UserGroups;
import com.oneapm.dto.group.Group;
import com.oneapm.dto.info.Info;

public class UserGroupsDaoImpl extends DaoImplBase<Group>{
	protected static final Logger LOG = LoggerFactory.getLogger(UserGroupsDaoImpl.class);
	protected final String TABLE_NAME = "groups";
	
	static {
		Instance = new UserGroupsDaoImpl();
	}
	
	private final static UserGroupsDaoImpl Instance;
	
	public static UserGroupsDaoImpl getInstance(){
		return Instance;
	}
	
	 public boolean update_xiaoshouyi(UserGroups userGroups, String lableId){
         try{
                 DBObject object = new BasicDBObject();
                 DBObject value = new BasicDBObject();
                 value.put("xiaoshouyi", userGroups.getXiaoshouyi());
                 if(lableId != null && lableId.length() > 0){
                         value.put("xiaoshouyi_lable_id", lableId);
                 }
                 object.put("group_id", userGroups.getGroupId());
                 return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set",value)).getN() > -1;
         }catch(Exception e){
                 LOG.error(e.getMessage(), e);
         }
         return false;
 }
	
	public UserGroups findByAdminId(Long admin_id){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("admin_id", admin_id);
	                DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
	                if(cursor.hasNext()){
	                        return findComplicatedGroupsByObject(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	public UserGroups findById(Long groupId){
                try{
                        DBObject object = new BasicDBObject();
                        object.put("group_id", groupId);
                        DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
                        if(cursor.hasNext()){
                                return findComplicatedGroupsByObject(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }
	public List<Long> findByTime(String start, String end){
	        try{
	                DBObject object = new BasicDBObject();
	                BasicDBList list = new BasicDBList();
	                list.add(new BasicDBObject("create_time", new BasicDBObject("$gte", start)));
	                list.add(new BasicDBObject("create_time", new BasicDBObject("$lt", end)));
	                object.put("$and", list);
	                DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
	                List<Long> ids = new ArrayList<Long>();
	                while(cursor.hasNext()){
	                        ids.add(Long.parseLong(cursor.next().get("group_id").toString()));
	                }
	                return ids;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	
	
	private UserGroups findUserGroupsByObject(DBObject object){
	      try{
	              Long groupId = Long.parseLong(object.get("group_id").toString());
	              Long parentId = Long.parseLong(object.get("parent_id").toString());
	              int deleted = Integer.parseInt(object.get("deleted").toString());
	              String groupName = object.get("group_name").toString();
	              Long adminId = Long.parseLong(object.get("admin_id").toString());
	              return new UserGroups(groupId, adminId, groupName, parentId, deleted);
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	 public boolean update_contectTime(UserGroups userGroups) {
         try {
                 DBObject object = new BasicDBObject();
                 object.put("group_id", userGroups.getGroupId());
                 BasicDBObject value = new BasicDBObject();
                 value.put("$set", new BasicDBObject("contect_time", userGroups.getContectTime()));
                 return getDBCollection(TABLE_NAME).update(object, value).getN() > -1;
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return false;
 }
	
	private UserGroups findComplicatedGroupsByObject(DBObject object){
		UserGroups userGroups = null;
	      try{	  
				  Long groupId = null;
				  try {
					  groupId = Long.parseLong(object.get("group_id").toString());
				  } catch (Exception e) {
				  }
				  Long parentId = null;
				  try {
					  parentId = Long.parseLong(object.get("parent_id").toString());
				  } catch (Exception e) {
				  }
				  Long adminId = null;
				  try {
					  adminId = Long.parseLong(object.get("admin_id").toString());
				  } catch (Exception e) {
				  }
				  Long sale  = null;
				  try {
					  sale = Long.parseLong(object.get("sale").toString());
				  } catch (Exception e) {
				  }
				  Long support = null;
				  try {
					  support = Long.parseLong(object.get("support").toString());
				  } catch (Exception e) {
				  }
				  Long preSale = null;
				  try {
					  preSale = Long.parseLong(object.get("preSale").toString());
				  } catch (Exception e) {
				  }
				  int deleted = 0;
				  try {
					  deleted = Integer.parseInt(object.get("deleted").toString());
				  } catch (Exception e) {
				  }
				  String groupName = null;
				  try {
					  groupName = object.get("group_name").toString();
				  } catch (Exception e) {
				  }
				  int payLevel = 0;
				  try {
					  payLevel = Integer.parseInt(object.get("payLevel").toString());
				  } catch (Exception e) {
				  }
				  String payTime = null;
				  try {
					  payTime = object.get("payTime").toString();
				  } catch (Exception e) {
				  }
				  String comming = null;
				  try {
					  comming = object.get("comming").toString();
				  } catch (Exception e) {
				  }
				  int emailStatus = 0;
				  try {
					  emailStatus = Integer.parseInt(object.get("emailStatus").toString());
				  } catch (Exception e) {
				  }
				  String contectTime = null;
				  try {
					  contectTime = object.get("contectTime").toString();
				  } catch (Exception e) {
				  }
				  String createTime = null;
				  try {
					  createTime = object.get("createTime").toString();
				  } catch (Exception e) {
				  }
				  String project = null;
				  try {
					  project = object.get("project").toString();
				  } catch (Exception e) {
				  }
				  Long xiaoshouyi  = null;
				  try {
					  xiaoshouyi = Long.parseLong(object.get("xiaoshouyi").toString());
				  } catch (Exception e) {
				  }
	              userGroups = new UserGroups( groupId,  adminId,  groupName,  parentId,  deleted,  sale,  support,
	                                 preSale,  payLevel,  payTime,  comming,  emailStatus,  contectTime);
	              userGroups.setCreateTime(createTime);
	              userGroups.setProject(project);
	              userGroups.setXiaoshouyi(xiaoshouyi);
	              return userGroups;
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return userGroups;
	}
		 public boolean update(UserGroups userGroups) {
	         try {
	                 DBObject object = new BasicDBObject();
	                 object.put("group_id", userGroups.getGroupId());
	                 DBObject value = new BasicDBObject();
	                 if (userGroups.getGroupId() != null && userGroups.getGroupId() > 0) {
	                         value.put("group_id", userGroups.getGroupId());
	                 }
	                 value.put("sale", userGroups.getSale());
	                 value.put("project", userGroups.getProject());
	                 value.put("support", userGroups.getSupport());
	                 value.put("preSale", userGroups.getPreSale());
	                 return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	         } catch (Exception e) {
	                 LOG.error(e.getMessage(), e);
	         }
	         return false;
	 }
		 public boolean updateOwner(UserGroups userGroups) {
             try {
                     DBObject object = new BasicDBObject();
                     object.put("group_id", userGroups.getGroupId());
                     DBObject value = new BasicDBObject();
                     value.put("support", userGroups.getSupport());
                     value.put("sale", userGroups.getSale());
                     value.put("preSale", userGroups.getPreSale());
                     return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
             } catch (Exception e) {
                     LOG.error(e.getMessage(), e);
             }
             return false;
     }
}
