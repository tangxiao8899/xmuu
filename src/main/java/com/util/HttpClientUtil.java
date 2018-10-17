package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpClientUtil {

    /**
     * http客户端工具类
     *
     */
    public static final String SunX509 = "SunX509";
    public static final String JKS = "JKS";
    public static final String PKCS12 = "PKCS12";
    public static final String TLS = "TLS";

    /**
     * get HttpURLConnection
     * @param strUrl url地址
     * @return HttpURLConnection
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String strUrl)
            throws IOException {
        URL url = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url
                .openConnection();
        return httpURLConnection;
    }

    /**
     * get HttpsURLConnection
     * @param strUrl url地址ַ
     * @return HttpsURLConnection
     * @throws IOException
     */
    public static HttpsURLConnection getHttpsURLConnection(String strUrl)
            throws IOException {
        URL url = new URL(strUrl);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url
                .openConnection();
        return httpsURLConnection;
    }

    /**
     * 获取不带查询串的url
     * @param strUrl
     * @return String
     */
    public static String getURL(String strUrl) {

        if(null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            if(-1 != indexOf) {
                return strUrl.substring(0, indexOf);
            }

            return strUrl;
        }

        return strUrl;

    }

    /**
     * 获取查询串
     * @param strUrl
     * @return String
     */
    public static String getQueryString(String strUrl) {

        if(null != strUrl) {
            int indexOf = strUrl.indexOf("?");
            if(-1 != indexOf) {
                return strUrl.substring(indexOf+1, strUrl.length());
            }

            return "";
        }

        return strUrl;
    }

    /**
     * 查询字符串转化为map
     * name1=key1&name2=key2&...
     * @param queryString
     * @return
     */
    public static Map queryString2Map(String queryString) {
        if(null == queryString || "".equals(queryString)) {
            return null;
        }

        Map m = new HashMap();
        String[] strArray = queryString.split("&");
        for(int index = 0; index < strArray.length; index++) {
            String pair = strArray[index];
            HttpClientUtil.putMapByPair(pair, m);
        }

        return m;

    }

    /**
     * 把键值添加到map
     * pair:name=value
     * @param pair name=value
     * @param m
     */
    public static void putMapByPair(String pair, Map m) {

        if(null == pair || "".equals(pair)) {
            return;
        }

        int indexOf = pair.indexOf("=");
        if(-1 != indexOf) {
            String k = pair.substring(0, indexOf);
            String v = pair.substring(indexOf+1, pair.length());
            if(null != k && !"".equals(k)) {
                m.put(k, v);
            }
        } else {
            m.put(pair, "");
        }
    }
    /**
     * BufferedReader转换成String<br/>
     * 注意:流关闭需要自行处理
     * @param reader
     * @return
     * @throws IOException
     */
    public static String bufferedReader2String(BufferedReader reader) throws IOException {
        StringBuffer buf = new StringBuffer();
        String line = null;
        while( (line = reader.readLine()) != null) {
            buf.append(line);
            buf.append("\r\n");
        }

        return buf.toString();
    }
    /**
     * 处理输出<br/>
     * 注意:流关闭需要自行处理
     * @param out
     * @param data
     * @param len
     * @throws IOException
     */
    public static void doOutput(OutputStream out, byte[] data, int len)
            throws IOException {
        int dataLen = data.length;
        int off = 0;
        while (off < data.length) {
            if (len >= dataLen) {
                out.write(data, off, dataLen);
                off += dataLen;
            } else {
                out.write(data, off, len);
                off += len;
                dataLen -= len;
            }

            // ˢ�»�����
            out.flush();
        }

    }
    /**
     * 获取SSLContext
     * @param trustFile
     * @param trustPasswd
     * @param keyFile
     * @param keyPasswd
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws KeyManagementException
     */
    public static SSLContext getSSLContext(
            FileInputStream trustFileInputStream, String trustPasswd,
            FileInputStream keyFileInputStream, String keyPasswd)
            throws NoSuchAlgorithmException, KeyStoreException,
            CertificateException, IOException, UnrecoverableKeyException,
            KeyManagementException {

        // ca
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
        KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.JKS);
        trustKeyStore.load(trustFileInputStream, HttpClientUtil
                .str2CharArray(trustPasswd));
        tmf.init(trustKeyStore);

        final char[] kp = HttpClientUtil.str2CharArray(keyPasswd);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
        KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
        ks.load(keyFileInputStream, kp);
        kmf.init(ks, kp);

        SecureRandom rand = new SecureRandom();
        SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

        return ctx;
    }

    /**
     * 字符串转换成char数组
     * @param str
     * @return char[]
     */
    public static char[] str2CharArray(String str) {
        if(null == str) return null;

        return str.toCharArray();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * InputStream转换成Byte
     * 注意:流关闭需要自行处理
     * @param in
     * @return byte
     * @throws Exception
     */
    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        int BUFFER_SIZE = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;

        while((count = in.read(data,0,BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        byte[] outByte = outStream.toByteArray();
        outStream.close();

        return outByte;
    }

    /**
     * InputStream转换成String
     * 注意:流关闭需要自行处理
     * @param in
     * @param encoding 编码
     * @return String
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in,String encoding) throws IOException{

        return new String(InputStreamTOByte(in),encoding);

    }
    
    public static String post(String url, String jsonParam) throws ClientProtocolException, IOException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost(url);

    	//设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .build();
        httpPost.setConfig(requestConfig);
    	
//    	包装请求体
        StringEntity request = new StringEntity(jsonParam, Charset.forName("utf-8"));
        
        
//    	发送请求
    	httpPost.setEntity(request);
    	CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
    	
        //读取响应
        HttpEntity entity = httpResponse.getEntity();
        String result = "";
        if (entity != null) {
            result = EntityUtils.toString(entity, "UTF-8");
        }
 
        return result;
    }
    
    
}
