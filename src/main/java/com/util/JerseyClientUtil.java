package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * JerseyAPi客户端
 * Created by libt on 2015/01/30.
 */
public class JerseyClientUtil {

    private static final String BIGDATA_API_URL = "https://" + PropertyUtil.getProperty("hxim.orgName") + "/"
            + PropertyUtil.getProperty("hxim.appName") + "/token";

    /**
     * post方法
     *
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static ResultPojo postMethod(String param) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL );
            response = resource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, param);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                JSONObject jsonObject = JSON.parseObject(data);
                resultPojo.setStatus(jsonObject.getInteger("status"));
                resultPojo.setData(data);
            } else {
                resultPojo.setStatus(response.getStatus());
                resultPojo.setData(data);
            }
        } catch (Exception e) {
            resultPojo.setStatus(500);//服务器异常
            resultPojo.setErrorMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }


    /**
     * get方法
     * 例如：consultation/recommend?startDate=201412030253&endDate=201412020253
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static ResultPojo getMethod(String param) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL );
            response = resource.queryParams(parseJSON2Map(param)).accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                JSONObject jsonObject = JSON.parseObject(data);
                resultPojo.setStatus(jsonObject.getInteger("status"));
                resultPojo.setData(data);
            } else {
                resultPojo.setStatus(response.getStatus());
                resultPojo.setData(response.getEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultPojo.setStatus(500);//服务器异常
            resultPojo.setErrorMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }

    /**
     * get方法
     * 例如：consultation/recommend/A1000037B04B8C
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static ResultPojo getMethodOnly(String param) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + param);
            response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                JSONObject jsonObject = JSON.parseObject(data);
                resultPojo.setStatus(jsonObject.getInteger("status"));
                resultPojo.setData(data);
            } else {
                resultPojo.setStatus(response.getStatus());
                resultPojo.setData(response.getEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultPojo.setStatus(500);//服务器异常
            resultPojo.setErrorMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }

    public static MultivaluedMap parseJSON2Map(String jsonStr) {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        //最外层解析
        JSONObject json = JSON.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            Object v = entry.getValue();
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<Object> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = (JSONObject) it.next();
                    list.add(parseJSON2Map(json2.toJSONString()));
                }
                queryParams.add(entry.getKey(), list);
            } else {
                queryParams.add(entry.getKey(), v);
            }
        }
        return queryParams;
    }



}
