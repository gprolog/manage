package com.oneapm.dao.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oneapm.dao.DaoImplBase;
import com.oneapm.dto.Aplication;
import com.oneapm.dto.tag.Language;

public class AppDataDaoImpl extends DaoImplBase<Aplication> {
        protected static final Logger LOG = LoggerFactory.getLogger(AppDataDaoImpl.class);
        protected final String TABLE_NAME = "appData";

        static {
                Instance = new AppDataDaoImpl();
        }

        private final static AppDataDaoImpl Instance;

        public static AppDataDaoImpl getInstance() {
                return Instance;
        }
        
        public List<Aplication> findByAgent(int agent, String start, String end){
                List<Aplication> apps = new ArrayList<Aplication>();
                try{
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        if(start != null){
                               BasicDBList list = new BasicDBList();
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                               list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                                object.put("$and", list);
                        }
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        public List<Aplication> findByTime(String start, String end){
                List<Aplication> apps = new ArrayList<Aplication>();
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public boolean existByTimeAndUserId(String start, String end, Long userId){
                try{
                        DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        public List<Aplication> findByTimeAndUserId(String start, String end, Long userId){
                List<Aplication> aplications = new ArrayList<Aplication>();
                try{
                       DBObject object = new BasicDBObject();
                       BasicDBList list = new BasicDBList();
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$gte", start)));
                       list.add(new BasicDBObject("data_time", new BasicDBObject("$lt", end)));
                        object.put("$and", list);
                        object.put("user_id", userId);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        while(cursor.hasNext()){
                                aplications.add(getAplicationFromResult(cursor.next()));
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return aplications;
        }
        
        public Aplication findByUserIdLast(Long userId){
                try{
                        DBObject object = new BasicDBObject("user_id", userId);
                        DBObject sort = new BasicDBObject("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
                        if(cursor.hasNext()){
                                return getAplicationFromResult(cursor.next());
                        }
                }catch(Exception e){
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public List<Aplication> findByTimeList(String time, Long appId, int agent) {
                List<Aplication> apps = null;
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("data_time", new BasicDBObject("$gte", time));
                        object.put("agent", agent);
                        DBObject sort = new BasicDBObject();
                        sort.put("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort);
                        apps = new ArrayList<Aplication>();
                        while (cursor.hasNext()) {
                                apps.add(getAplicationFromResult(cursor.next()));
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return apps;
        }
        
        public boolean exist (String start, String end, Long appId) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        BasicDBList list = new BasicDBList();
                        list.add(new BasicDBObject("$gte", start));
                        list.add(new BasicDBObject("$lt", end));
                        object.put("data_time", list);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }


        public boolean findByIdAndTime(String dataTime, Long appId, int language) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("data_time", new BasicDBObject("$gte", dataTime));
                        object.put("app_id", appId);
                        object.put("agent", language);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public boolean exist(Long userId, String dataTime, int agent) {
                try {
                        DBObject object = new BasicDBObject();
                        if(agent > 0){
                                object.put("agent", agent);
                        }
                        object.put("user_id", userId);
                        object.put("data_time", new BasicDBObject("$gte", dataTime));
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object);
                        return cursor.hasNext();
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public String findLastDataById(Long appId, int language) {
                try {
                        DBObject object = new BasicDBObject();
                        object.put("app_id", appId);
                        object.put("agent", language);
                        DBObject sort = new BasicDBObject("data_time", -1);
                        DBCursor cursor = getDBCollection(TABLE_NAME).find(object).sort(sort).limit(1);
                        if (cursor.hasNext()) {
                                return cursor.next().get("data_time").toString();
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return null;
        }

        public boolean insert(Aplication ap) {
                try {
                        DBObject value = new BasicDBObject();
                        value.put("app_id", ap.getAppId());
                        value.put("data_time", ap.getDataTime());
                        value.put("agent", ap.getLanguage());
                        value.put("user_id", ap.getUserId());
                        return getDBCollection(TABLE_NAME).insert(value).getN() > -1;
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }

        public Aplication getAplicationFromResult(DBObject object) {
                Aplication ap = null;
                try {
                        Long appId = Long.parseLong(object.get("app_id").toString().trim());
                        Long userId = Long.parseLong(object.get("user_id").toString().trim());
                        int agent = Integer.parseInt(object.get("agent").toString().trim());
                        String dataTime = object.get("data_time").toString();
                        ap = new Aplication(appId, agent, userId, dataTime, Language.getName(agent));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return ap;
        }

}
