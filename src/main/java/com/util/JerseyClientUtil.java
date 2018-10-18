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

    private static final String BIGDATA_API_URL = PropertyUtil.getProperty("hxim.url") + PropertyUtil.getProperty("hxim.orgName") + "/"
            + PropertyUtil.getProperty("hxim.appName");

    /**
     * post方法
     *
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static ResultPojo postMethod(String param,String method) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + method);
            response = resource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, param);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            JSONObject jsonObject = JSON.parseObject(data);
            resultPojo.setCode(status);
            resultPojo.setData(jsonObject);
            if (status == 200) {
                resultPojo.setMsg("请求成功");
            } else {
                resultPojo.setMsg("请求失败");
            }
        } catch (Exception e) {
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }

    /**
     * 需要授权的post方法
     * @param method 方法名
     * @param param  参数
     * @param token 授权信息
     * @return 返回值
     */
    public static ResultPojo postTokenMethod(String param,String token,String method) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + method);
            response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, param);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            JSONObject jsonObject = JSON.parseObject(data);
            resultPojo.setCode(status);
            resultPojo.setData(jsonObject);
            if (status == 200) {
                resultPojo.setMsg("请求成功");
            } else {
                resultPojo.setMsg("请求失败");
            }
        } catch (Exception e) {
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }

    /**
     * 授权的GET请求
     * @param method 请求方法
     * @return
     */
    public static ResultPojo getTokenMethod(String token,String method) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + method);
            response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            JSONObject jsonObject = JSON.parseObject(data);
            resultPojo.setCode(status);
            resultPojo.setData(jsonObject);
            if (status == 200) {
                resultPojo.setMsg("请求成功");
            } else {
                resultPojo.setMsg("请求失败");
            }
        } catch (Exception e) {
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }


    /**
     * 需要授权的post方法
     * @param method 方法名
     * @param type  请求类型
     * @param token 授权信息
     * @return 返回值
     */
    public static ResultPojo postTokenMethod(String token,String method,Integer type) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + method);
            switch (type){
                case 1:
                    response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);
                    break;
                case 2:
                    response = resource.header("Authorization"," Bearer "+token).get(ClientResponse.class);
                    break;
                case 3:
                    response = resource.header("Authorization"," Bearer "+token).delete(ClientResponse.class);
                    break;
                case 4:
                    response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class);
                    break;
            }
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            JSONObject jsonObject = JSON.parseObject(data);
            resultPojo.setCode(status);
            resultPojo.setData(jsonObject);
            if (status == 200) {
                resultPojo.setMsg("请求成功");
            } else {
                resultPojo.setMsg("请求失败");
            }
        } catch (Exception e) {
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
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
                resultPojo.setCode(jsonObject.getInteger("status"));
                resultPojo.setData(data);
            } else {
                resultPojo.setCode(response.getStatus());
                resultPojo.setData(response.getEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
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
                resultPojo.setCode(jsonObject.getInteger("status"));
                resultPojo.setData(data);
            } else {
                resultPojo.setCode(response.getStatus());
                resultPojo.setData(response.getEntity(String.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
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


    public static ResultPojo postTokenMethod(String param, String token, String method, int type) {
        ResultPojo resultPojo = new ResultPojo();
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(BIGDATA_API_URL  + method);
            switch (type){
                case 1:
                    response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,param);
                    break;
                case 2:
                    response = resource.header("Authorization"," Bearer "+token).get(ClientResponse.class);
                    break;
                case 3:
                    response = resource.header("Authorization"," Bearer "+token).delete(ClientResponse.class);
                    break;
                case 4:
                    response = resource.header("Authorization"," Bearer "+token).type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class,param);
                    break;
            }
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            JSONObject jsonObject = JSON.parseObject(data);
            resultPojo.setCode(status);
            resultPojo.setData(jsonObject);
            if (status == 200) {
                resultPojo.setMsg("请求成功");
            } else {
                resultPojo.setMsg("请求失败");
            }
        } catch (Exception e) {
            resultPojo.setCode(500);//服务器异常
            resultPojo.setMsg(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return resultPojo;
    }
}
